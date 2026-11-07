**Nama:** Muchamad Rava Alvriansyah<br>
**NPM:** 4525210040<br>
**Kelas:** PBO A 2026/2027<br>
**Dosen Pengampu:** Adi Wahyu Pribadi, S.Si., M.Kom

# Praktikum Pertemuan 10: Exception Handling dan Pemrograman Defensif

Praktikum ini menerapkan *exception handling* pada Sistem Perpustakaan dalam dua bahasa, **Java** (struktur Maven `src/main/java`) dan **PHP** (autoload Composer). Titik awalnya adalah method `pinjam()` yang bersarang empat tingkat dan hanya mengembalikan `false` ketika gagal. Laporan ini disusun per konsep, bukan per file: setiap bagian menjelaskan masalahnya, keputusan desain yang diambil, lalu bagaimana keputusan itu terlihat di kode Java dan PHP.

## Struktur Proyek

```
Pert10/
├── README.md
├── skenario-uji.md
├── java/
│   ├── pom.xml
│   └── src/main/java/id/ac/univpancasila/perpustakaan/
│       ├── Main.java
│       ├── exception/      (PerpustakaanException + 4 turunan)
│       └── service/        (LibraryServiceKotor, LibraryService, PerpustakaanService)
└── php/
    ├── composer.json
    ├── Main.php
    └── src/
        ├── Exception/
        └── Service/        (LibraryServiceKotor, LibraryService)
```

## 1. Masalah Awal: Kode Bersarang yang Bisu

Kode awal ada di [LibraryServiceKotor.java](java/src/main/java/id/ac/univpancasila/perpustakaan/service/LibraryServiceKotor.java) dan [LibraryServiceKotor.php](php/src/Service/LibraryServiceKotor.php).

```java
public boolean pinjam(String idAnggota, String isbn) {
    if (pinjaman.containsKey(idAnggota)) {
        if (stok.containsKey(isbn)) {
            if (stok.get(isbn) > 0) {
                if (pinjaman.get(idAnggota) < BATAS_PINJAM) {
                    stok.merge(isbn, -1, Integer::sum);
                    pinjaman.merge(idAnggota, 1, Integer::sum);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    } else {
        return false;
    }
}
```

Ada tiga persoalan yang saya temukan:

1. **Logika utama tersembunyi.** Dua baris yang benar-benar mengubah data berada di dalam empat tingkat `if`, jauh di sisi kanan layar.
2. **Kegagalan tidak punya identitas.** Anggota tidak ada, buku tidak ada, stok habis, dan batas pinjam tercapai semuanya menghasilkan `false` yang sama. Pemanggil tidak bisa memberi pesan yang tepat kepada pengguna.
3. **Sulit dikembangkan.** Aturan baru berarti satu tingkat `if` dan satu `else` tambahan, sehingga mudah salah kurung atau salah cabang.

## 2. Guard Clause: Periksa di Awal, Gagal Lebih Cepat

Solusinya adalah membalik kondisi. Setiap syarat diperiksa lebih dulu, dan bila dilanggar langsung berhenti dengan exception yang menyebut alasannya. Kode yang tersisa di akhir adalah jalur sukses.

**Java** ([LibraryService.java](java/src/main/java/id/ac/univpancasila/perpustakaan/service/LibraryService.java)):

```java
public boolean pinjam(String idAnggota, String isbn) {
    if (!pinjaman.containsKey(idAnggota)) {
        throw new AnggotaTidakDitemukanException(idAnggota);
    }
    if (!stok.containsKey(isbn)) {
        throw new BukuTidakDitemukanException(isbn);
    }
    if (stok.get(isbn) <= 0) {
        throw new BukuSedangDipinjamException(isbn, judul.get(isbn));
    }
    if (pinjaman.get(idAnggota) >= BATAS_PINJAM) {
        throw new BatasPinjamTerlampauiException(idAnggota, BATAS_PINJAM);
    }

    stok.merge(isbn, -1, Integer::sum);
    pinjaman.merge(idAnggota, 1, Integer::sum);
    return true;
}
```

**PHP** ([LibraryService.php](php/src/Service/LibraryService.php)):

```php
public function pinjam(string $idAnggota, string $isbn): void
{
    if (!array_key_exists($idAnggota, $this->pinjaman)) {
        throw new AnggotaTidakDitemukanException($idAnggota);
    }
    if (!array_key_exists($isbn, $this->stok)) {
        throw new BukuTidakDitemukanException($isbn);
    }
    if ($this->stok[$isbn] <= 0) {
        throw new BukuSedangDipinjamException($isbn, $this->judul[$isbn]);
    }
    if ($this->pinjaman[$idAnggota] >= self::BATAS_PINJAM) {
        throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
    }

    $this->stok[$isbn]--;
    $this->pinjaman[$idAnggota]++;
}
```

Urutan pemeriksaan sengaja dibuat dari yang paling mendasar: anggota, buku, stok, baru batas pinjam. Dengan begitu pesan error selalu menyebut masalah yang paling pertama relevan. Menambah aturan baru cukup menambah satu blok `if (...) throw ...;` tanpa menyentuh alur lain.

Perbedaan kecil antara dua versi: di PHP, `pinjam()` bertipe `void` karena keberhasilan cukup ditandai dengan tidak adanya exception, sedangkan versi Java masih mengembalikan `true` agar kompatibel dengan kode lama.

## 3. Hierarki Exception Domain

Semua class ada di folder [exception (Java)](java/src/main/java/id/ac/univpancasila/perpustakaan/exception/) dan [Exception (PHP)](php/src/Exception/).

| Class | Membawa data | Dilempar saat |
|-------|--------------|---------------|
| `PerpustakaanException` (abstract) | - | Induk semua exception domain |
| `AnggotaTidakDitemukanException` | `idAnggota` | Anggota belum terdaftar |
| `BukuTidakDitemukanException` | `isbn` | ISBN tidak ada di katalog |
| `BukuSedangDipinjamException` | `isbn`, `judul` | Stok eksemplar habis |
| `BatasPinjamTerlampauiException` | `idAnggota`, `batas` | Anggota sudah meminjam 3 buku |

Beberapa keputusan desain di balik tabel ini:

- **Unchecked, bukan checked.** `PerpustakaanException` diturunkan dari `RuntimeException`. Kegagalan ini berasal dari permintaan yang tidak sah, bukan gangguan luar yang bisa dipulihkan dengan mengulang pemanggilan, jadi tidak ada gunanya memaksa setiap method menulis `throws` atau `try-catch`.
- **Induk bersama.** Karena semua turunan berbagi satu induk, pemanggil bisa menangkap seluruh kegagalan domain dengan satu `catch (PerpustakaanException e)`, atau menangkap yang spesifik bila perlu.
- **Membawa data, bukan hanya pesan.** Setiap exception menyimpan penyebabnya sebagai atribut `final` dengan getter. Pemanggil membaca `getIsbn()` atau `getBatas()`, bukan mengurai kalimat pesan yang rapuh terhadap perubahan teks.
- **Satu exception, satu penyebab.** Nama class sudah menjawab pertanyaan "gagal kenapa?" tanpa membaca pesan.

Di PHP, exception memakai *constructor property promotion* (`private readonly`), sehingga deklarasi atribut dan konstruktor menyatu. PHP juga tidak punya checked exception, jadi anotasi `@throws` di PHPDoc dipakai sebagai dokumentasi pengganti peringatan kompilator.

## 4. Menangkap Exception di Lapisan Tampilan

Pada Java, penanganan ada di [PerpustakaanService.java](java/src/main/java/id/ac/univpancasila/perpustakaan/service/PerpustakaanService.java):

```java
try {
    libService.pinjam(idAnggota, isbn);
    System.out.println("Peminjaman Berhasil!");
} catch (PerpustakaanException e) {
    System.out.println("[Error Domain] " + e.getMessage());

    if (e instanceof BukuTidakDitemukanException ex) {
        System.out.println("ISBN bermasalah: " + ex.getIsbn());
    } else if (e instanceof BukuSedangDipinjamException ex) {
        System.out.println(" -> Judul Buku: " + ex.getJudul() + " (ISBN: " + ex.getIsbn() + ")");
    } // dan seterusnya untuk exception lain
} catch (Exception e) {
    System.out.println("[Error Sistem] Terjadi kesalahan input " + e.getMessage());
}
```

Ada dua lapis `catch` dengan maksud berbeda. Lapis pertama menangani kegagalan **domain** yang memang diharapkan terjadi. Lapis kedua menangkap `Exception` umum sebagai jaring pengaman untuk kesalahan **sistem** yang tidak terduga, dengan label berbeda agar keduanya tidak tertukar saat debugging.

Selain itu, `tambahBuku` dan `tambahAnggota` memvalidasi input: ID atau ISBN kosong ditolak, dan jumlah eksemplar yang bukan angka atau kurang dari 1 ditolak lewat `NumberFormatException`. Pada form peminjaman, ketik `exit` untuk kembali ke menu.

## 5. Membersihkan Sumber Daya: try-with-resources vs finally

Ekspor laporan memakai berkas, dan berkas harus selalu ditutup walaupun terjadi error di tengah penulisan. Kedua bahasa menyelesaikan ini dengan cara berbeda:

| | Java ([Main.java](java/src/main/java/id/ac/univpancasila/perpustakaan/Main.java)) | PHP ([Main.php](php/Main.php)) |
|---|---|---|
| Mekanisme | `try-with-resources` | `try ... finally` |
| Penutupan berkas | Otomatis, `PrintWriter` ditutup saat blok selesai | Manual lewat `fclose` di blok `finally` |
| Error I/O | `IOException` ditangkap dengan pesan jelas | Ditangani di blok `try-catch` yang sama |
| Risiko lupa menutup | Tidak ada | Ada, bergantung pada disiplin penulis |

Java lebih aman karena penutupan dijamin oleh bahasa. PHP membutuhkan `finally` yang eksplisit, tetapi hasilnya sama: berkas tidak pernah tertinggal terbuka.

`Main` di Java menampilkan menu interaktif (Tambah Buku, Tambah Anggota, Pinjam Buku, Keluar) dan mengekspor `laporan_buku.txt` saat keluar. `Main.php` menjalankan tujuh percobaan peminjaman dalam satu `foreach` dengan `try-catch (PerpustakaanException)`, lalu `createReport()` menulis laporan yang sama.

## 6. Skenario Uji

Data awal: buku `111` "Laskar Pelangi" (1 eksemplar), buku `222` "Bumi Manusia" (5 eksemplar), anggota `A-001` dan `A-002`, batas pinjam 3. Uraian lengkap ada di [skenario-uji.md](skenario-uji.md).

| No | Anggota | ISBN | Hasil |
|----|---------|------|-------|
| 1 | A-001 | 111 | Berhasil |
| 2 | A-002 | 111 | `BukuSedangDipinjamException` |
| 3 | A-002 | 999 | `BukuTidakDitemukanException` |
| 4 | X-999 | 222 | `AnggotaTidakDitemukanException` |
| 5 | A-001 | 222 | Berhasil |
| 6 | A-001 | 222 | Berhasil |
| 7 | A-001 | 222 | `BatasPinjamTerlampauiException` |

Dengan `LibraryServiceKotor`, keempat kegagalan (skenario 2, 3, 4, dan 7) hanya menghasilkan `false` yang tidak bisa dibedakan. Dengan versi baru, masing-masing punya exception, pesan, dan data penyebab sendiri.

## 7. Cara Menjalankan dan Hasilnya

Versi JDK dan PHP yang dipakai:

<img width="900" height="260" alt="01-versi-java-php" src="https://github.com/user-attachments/assets/bc90e000-6cf6-4f74-be1f-5643896768b1" />

### Java

Kompilasi:

```powershell
cd java
mkdir out
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

<img width="900" height="197" alt="02-compile-java" src="https://github.com/user-attachments/assets/3377b1ee-f4f6-496b-bf22-3d5a3ae26d47" />

Menjalankan program:

```powershell
java -cp out id.ac.univpancasila.perpustakaan.Main
```

<img width="900" height="239" alt="03-menu-java" src="https://github.com/user-attachments/assets/5a65006c-c397-436f-855e-91464a792485" />

Hasil uji peminjaman:

```
[Error Domain] Buku Laskar Pelangi dengan ISBN 111 sedang dipinjam
 -> Judul Buku: Laskar Pelangi (ISBN: 111)

[Error Domain] Buku dengan ISBN 999 tidak ditemukan
ISBN bermasalah: 999

[Error Domain] Anggota dengan ID X-999 tidak ditemukan
Id Anggota bermasalah: X-999

[Error Domain] Anggota dengan ID A-001 telah mencapai batas maksimum peminjaman
 -> Batas Maksimum: 3 buku
```

<img width="900" height="1037" alt="04-pinjam-java" src="https://github.com/user-attachments/assets/6e30bdd6-9629-4d73-b8ff-6d606992a3c8" />

Laporan `laporan_buku.txt` versi Java (`java\laporan_buku.txt`):

<img width="900" height="260" alt="05-laporan-java" src="https://github.com/user-attachments/assets/6e09d1bb-7750-4a99-aeb0-624453487af6" />

### PHP

```powershell
cd php
composer install
php Main.php
```

```
Anggota A-001 meminjam ISBN '111' ...
[BERHASIL]
Anggota A-002 meminjam ISBN '111' ...
[GAGAL] Buku "Laskar Pelangi" (ISBN 111) sedang dipinjam.
Anggota A-002 meminjam ISBN '999' ...
[GAGAL] Buku dengan ISBN 999 tidak ditemukan.
Anggota X-999 meminjam ISBN '222' ...
[GAGAL] Anggota dengan ID X-999 tidak ditemukan.
Anggota A-001 meminjam ISBN '222' ...
[BERHASIL]
Anggota A-001 meminjam ISBN '222' ...
[BERHASIL]
Anggota A-001 meminjam ISBN '222' ...
[GAGAL] Anggota A-001 telah mencapai batas peminjaman (3 buku).

--- Ekspor Laporan Buku ---
>> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas 'laporan_buku.txt'.
[INFO] File 'laporan_buku.txt' telah ditutup dengan aman melalui blok finally.
```

<img width="900" height="491" alt="06-run-php" src="https://github.com/user-attachments/assets/2b5b46e9-40c3-454f-9823-e61f1b946d77" />

Laporan `php\laporan_buku.txt`:

<img width="900" height="260" alt="07-laporan-php" src="https://github.com/user-attachments/assets/3cbc3533-795e-4dd3-8170-36818692af77" />

### Laporan yang dihasilkan

Skenario 1, 5, dan 6 berhasil sehingga stok `111` menjadi 0 dan stok `222` menjadi 3. Laporan `laporan_buku.txt` (di `java\` dan `php\`) mencerminkan hal itu:

```
==========================================================================
                     LAPORAN DAFTAR BUKU PERPUSTAKAAN
==========================================================================
ISBN         | JUDUL BUKU                          | STOK     | STATUS
--------------------------------------------------------------------------
111          | Laskar Pelangi                      | 0        | Tidak Tersedia
222          | Bumi Manusia                        | 3        | Tersedia
==========================================================================
```

## 8. Kesimpulan

- **Guard clause** meratakan kode dan menaruh jalur sukses di tingkat paling luar, sehingga lebih mudah dibaca dan diubah.
- **Exception domain** menggantikan `false` yang ambigu dengan informasi penyebab yang bisa dibaca mesin (lewat getter) maupun manusia (lewat pesan).
- **Unchecked exception dengan induk bersama** memberi pilihan menangkap secara umum atau spesifik tanpa membebani setiap method dengan `throws`.
- **Pembersihan sumber daya** dijamin oleh `try-with-resources` di Java dan `finally` di PHP; Java lebih otomatis, PHP lebih eksplisit.
- Logika inti kedua bahasa hampir identik, sehingga perbedaan utamanya ada pada fitur bahasa (checked exception, property promotion, tipe `void`), bukan pada desainnya.
