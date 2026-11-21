# Praktikum 12 — Refactoring dengan SOLID dan Repository Pattern

| | |
|---|---|
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

Praktikum ini merefaktor Sistem Perpustakaan dari sesi 10–11 agar patuh pada prinsip SOLID. Penyimpanan data dipisahkan dari aturan bisnis dengan Repository pattern (`BookRepository` dan `InMemoryBookRepository`), aturan denda dipisahkan dengan Strategy pattern (`AturanDenda` beserta `AturanDendaFactory`), dan seluruh dependensi disuntikkan lewat constructor secara manual (manual dependency injection). Hasilnya dibuktikan lewat unit test, termasuk satu uji dengan repository palsu yang selalu melempar exception. Implementasi tersedia dalam Java dan PHP.

> Path gambar (`docs/img/...`) adalah placeholder. Ganti dengan tangkapan layar kode dan hasil eksekusi Anda.

---

# Java

## BookRepository.java

**Sesudah**

![BookRepository.java Java setelah]<img width="1920" height="1020" alt="prak12-BookRepository" src="https://github.com/user-attachments/assets/0f042f40-2e5f-46d1-ac35-bf4b70a3b9fd" />


Versi sebelum hanya kerangka dengan komentar TODO. Setelah dikerjakan, interface ini menjadi kontrak penyimpanan yang dibutuhkan `LibraryService`: `simpan(Buku)`, `cariByIsbn(String)` yang mengembalikan `Optional<Buku>` agar kasus "tidak ditemukan" eksplisit, `cariSemua()`, dan `kurangiStok(Buku)`. `LibraryService` cukup mengenal kontrak ini tanpa tahu data disimpan di memori atau basis data.

## InMemoryBookRepository.java

**Sebelum**

![InMemoryBookRepository.java Java setelah bagian 2]<img width="551" height="427" alt="image" src="https://github.com/user-attachments/assets/7bbb2a51-bf4e-4289-97af-18ff6ac7e93f" />


**Sesudah**

![InMemoryBookRepository.java Java setelah bagian 1]<img width="1920" height="1020" alt="prak12-InMemoryBookRepository-1" src="https://github.com/user-attachments/assets/37f3c452-cdf7-41ba-bb60-c155bd3b8741" />


Implementasi disimpan di `LinkedHashMap` dengan kunci nomor induk buku sehingga pencarian berdasarkan ISBN cepat dan urutan simpan terjaga. `simpan` dan `kurangiStok` menolak `null` dengan `IllegalArgumentException`, `cariByIsbn` mengembalikan `Optional.empty()` untuk ISBN `null` atau yang tidak ada, dan `cariSemua` mengembalikan salinan tak-berubah (`Collections.unmodifiableList`) agar koleksi internal tidak bocor.

## AturanDenda.java, Denda*.java, dan AturanDendaFactory.java

![AturanDenda.java Java setelah]<img width="1920" height="1020" alt="prak12-AturanDenda" src="https://github.com/user-attachments/assets/ecf6a860-7fc8-43e9-924a-baa98c3dda64" />


![DendaMahasiswa.java Java setelah]<img width="1920" height="1020" alt="prak12-DendaMahasiswa" src="https://github.com/user-attachments/assets/edfcb820-5024-4044-93d8-7e1ce883d910" />


![DendaDosen.java Java setelah]<img width="1920" height="1020" alt="prak12-DendaDosen" src="https://github.com/user-attachments/assets/ab1c33d3-61d2-44d6-964d-57c529b998e2" />


![DendaBebas.java Java setelah]<img width="1920" height="1020" alt="prak12-DendaBebas" src="https://github.com/user-attachments/assets/351250c6-21a6-45af-bfe5-8c8e899bf7d6" />


![DendaAlumni.java Java setelah]<img width="1920" height="1020" alt="prak12-DendaAlumni" src="https://github.com/user-attachments/assets/019c932f-fa3d-4863-9e7b-03ba42e98861" />


![AturanDendaFactory.java Java setelah]<img width="1920" height="1020" alt="prak12-AturanDendaFactory" src="https://github.com/user-attachments/assets/bd559089-58cd-400d-8a2e-a648adf4a7e8" />


Interface `AturanDenda` (Strategy) punya dua method: `hitung(int hariTerlambat)` dan `nama()`. Implementasinya adalah `DendaMahasiswa` (Rp1.000/hari), `DendaDosen` (Rp500/hari), `DendaBebas` (selalu 0, untuk anggota kehormatan), dan `DendaAlumni` (Rp750/hari) sebagai bukti Open-Closed. Hari terlambat negatif diperlakukan sebagai nol lewat `Math.max(0, ...)`. `AturanDendaFactory.untuk(jenisAnggota)` memilih implementasi berdasarkan jenis anggota (tanpa membedakan huruf besar/kecil), dan melempar `IllegalArgumentException` bila jenisnya `null` atau tidak dikenal.

## LibraryService.java

![LibraryService.java Java setelah bagian 1]<img width="1920" height="1020" alt="prak12-LibraryService-1" src="https://github.com/user-attachments/assets/286aecaf-6b12-42d7-a8ba-18635980a566" />


![LibraryService.java Java setelah bagian 2] <img width="1920" height="1020" alt="prak12-LibraryService-2" src="https://github.com/user-attachments/assets/7c2aec7d-d0ac-4be6-a554-63027465ca12" />


![LibraryService.java Java setelah bagian 3]<img width="1920" height="1020" alt="prak12-LibraryService-3" src="https://github.com/user-attachments/assets/b77c6ccc-d65f-4205-8f3d-2bcbebe19ae8" />


![LibraryService.java Java setelah bagian 4]<img width="1920" height="1020" alt="prak12-LibraryService-4" src="https://github.com/user-attachments/assets/95ae9377-20ab-4014-ae4b-bc63a616ae6b" />


Sebelumnya `LibraryService` membuat sendiri penyimpanannya dengan `new Repository<>(Buku::getNomorInduk)`. Sekarang constructor-nya menerima `BookRepository` dan `AturanDenda` (keduanya ditolak bila `null`), sehingga tidak ada lagi `new` untuk urusan penyimpanan di dalam kelas ini. Method `pinjam` memakai guard clause berurutan: anggota tidak terdaftar, buku tidak ditemukan, buku sedang dipinjam, lalu batas pinjam (`BATAS_PINJAM = 3`), masing-masing dengan exception khusus. Penghitungan denda didelegasikan ke `aturanDenda.hitung(...)`.

## Main.java

![Main.java Java setelah bagian 1]<img width="1920" height="1020" alt="prak12-Main-1" src="https://github.com/user-attachments/assets/161e2fac-86b2-414d-9228-35c1d1a1ce54" />

![Main.java Java setelah bagian 2] <img width="1920" height="1020" alt="prak12-Main-2" src="https://github.com/user-attachments/assets/1c2a49eb-e658-4fb8-bcac-ef799ffa7d37" />


`Main` menjadi satu-satunya tempat kelas konkret disebut: membuat `InMemoryBookRepository`, memilih aturan lewat `AturanDendaFactory.untuk("mahasiswa")`, lalu menyuntikkan keduanya ke `new LibraryService(repo, aturanDenda)`. Menu konsol dan ekspor `laporan_buku.txt` tetap seperti sebelumnya.

## LibraryServiceTest.java

![LibraryServiceTest.java Java setelah bagian 1]<img width="1920" height="1020" alt="prak12-Main-2" src="https://github.com/user-attachments/assets/ced13fd3-1742-4526-82d7-68f3c2cc8609" />


![LibraryServiceTest.java Java setelah bagian 2]<img width="1920" height="1020" alt="prak12-LibraryServiceTest-2" src="https://github.com/user-attachments/assets/478f350c-363e-4948-91bf-bfbaf2c05457" />


![LibraryServiceTest.java Java setelah bagian 3]<img width="1920" height="1020" alt="prak12-LibraryServiceTest-3" src="https://github.com/user-attachments/assets/460dbc67-e4c4-4016-9316-14812acd3d4f" />


Ada tiga uji. Pertama, `LibraryService` bekerja dengan `InMemoryBookRepository` lewat DI: tambah buku, daftar anggota, pinjam, lalu sisa eksemplar berkurang dari 2 menjadi 1. Kedua, Strategy dan Factory menghitung denda 3 hari sesuai jenis anggota (Rp3.000 mahasiswa, Rp1.500 dosen, Rp0 kehormatan, Rp2.250 alumni), hari negatif menghasilkan 0, dan jenis tidak dikenal ditolak. Ketiga, `FailingBookRepository` yang selalu melempar `RuntimeException` dipakai untuk membuktikan bahwa `LibraryService` dapat diuji terpisah dari penyimpanan sesungguhnya, sesuatu yang tidak mungkin ditulis sebelum refactoring karena repository dulu dibuat di dalam service.

---

# PHP

## BookRepository.php

![BookRepository.php PHP setelah]<img width="1920" height="1020" alt="prak12-BookRepository" src="https://github.com/user-attachments/assets/ed16eddd-bf49-4b44-867f-5d6fbadbe85c" />

Padanan PHP dari kontrak penyimpanan: `simpan(Buku): void`, `cariByIsbn(string): ?Buku` (tipe nullable menggantikan `Optional`), `cariSemua(): array`, dan `kurangiStok(Buku): void`, dengan `declare(strict_types=1)`.

## InMemoryBookRepository.php

![InMemoryBookRepository.php PHP setelah]<img width="1920" height="1020" alt="prak12-InMemoryBookRepository" src="https://github.com/user-attachments/assets/a081148b-f1e7-4760-b6da-96326f94b2ac" />


Kelas `final` ini menyimpan buku di array asosiatif berkunci nomor induk. `cariByIsbn` memakai operator `?? null`, sedangkan `cariSemua` mengembalikan `array_values(...)` sehingga indeks rapi dan array internal tidak dibagikan.

## AturanDenda.php, Denda*.php, dan AturanDendaFactory.php

![AturanDenda.php PHP setelah]<img width="1920" height="1020" alt="prak12-AturanDenda" src="https://github.com/user-attachments/assets/beb935d0-42bb-43b0-8e2c-b2ecf0e0c21f" />


![DendaMahasiswa.php PHP setelah]<img width="1920" height="1020" alt="prak12-DendaMahasiswa" src="https://github.com/user-attachments/assets/6602da5d-fd3b-4a50-9205-a296fdb384c6" />


![DendaDosen.php PHP setelah]<img width="1920" height="1020" alt="prak12-DendaDosen" src="https://github.com/user-attachments/assets/bda78dcd-c301-410c-9f3d-43476c423fb1" />


![DendaBebas.php PHP setelah]<img width="1920" height="1020" alt="prak12-DendaBebas" src="https://github.com/user-attachments/assets/4dad65c3-0e33-4e34-86a4-cab844aa033e" />


![DendaAlumni.php PHP setelah]<img width="1920" height="1020" alt="prak12-DendaAlumni" src="https://github.com/user-attachments/assets/73dcf7d8-c5b1-44e8-8b6b-f210463ab93d" />


![AturanDendaFactory.php PHP setelah]<img width="1920" height="1020" alt="prak12-AturanDendaFactory" src="https://github.com/user-attachments/assets/4fc4b298-bac6-480b-81d6-c3ba3944071e" />


Struktur dan tarifnya sama dengan versi Java: `DendaMahasiswa`, `DendaDosen`, `DendaBebas`, dan `DendaAlumni`, semuanya `final` dan mengimplementasikan interface `AturanDenda`. `AturanDendaFactory::untuk()` memakai ekspresi `match` dengan `strtolower(trim(...))` dan melempar `InvalidArgumentException` untuk jenis yang tidak dikenal.

## LibraryService.php

![LibraryService.php PHP setelah bagian 1]<img width="1920" height="1020" alt="prak12-LibraryService-1" src="https://github.com/user-attachments/assets/f93c1659-3f2a-476d-9cca-893968265aaa" />


![LibraryService.php PHP setelah bagian 2]<img width="1920" height="1020" alt="prak12-LibraryService-2" src="https://github.com/user-attachments/assets/8af332b1-7ed8-40cb-8d9c-2a5ab4833b13" />


![LibraryService.php PHP setelah bagian 3]<img width="1920" height="1020" alt="prak12-LibraryService-3" src="https://github.com/user-attachments/assets/1a99f53e-5880-4170-9ff2-f95080f20f1d" />


![LibraryService.php PHP setelah bagian 4]<img width="1920" height="1020" alt="prak12-LibraryService-4" src="https://github.com/user-attachments/assets/4376297b-c024-46a5-b569-bc9f08247250" />


Sebelumnya `LibraryService` membuat `new KoleksiBuku()` sendiri. Sekarang `BookRepository` dan `AturanDenda` diterima lewat constructor promotion dengan properti `private readonly`. Method `pinjam` memakai guard clause dan melempar exception khusus untuk anggota tidak terdaftar, buku tidak ditemukan, buku sedang dipinjam, dan batas pinjam tercapai.

## main.php

![main.php PHP setelah bagian 1]<img width="1920" height="1020" alt="prak12-main-1" src="https://github.com/user-attachments/assets/6700692d-8465-4588-bdf6-8acdb00a7315" />


![main.php PHP setelah bagian 2]<img width="1920" height="1020" alt="prak12-main-2" src="https://github.com/user-attachments/assets/4f7b65cf-3388-447e-ab9c-84dbd2ae8513" />


`main.php` merakit objek di satu tempat: `new InMemoryBookRepository()`, `AturanDendaFactory::untuk('mahasiswa')`, lalu `new LibraryService($repo, $aturanDenda)`. Tujuh skenario peminjaman dijalankan dan tiap kegagalan ditangkap lewat `PerpustakaanException`.

## LibraryServiceTest.php

![LibraryServiceTest.php PHP setelah bagian 1]<img width="1920" height="1020" alt="prak12-LibraryServiceTest-1" src="https://github.com/user-attachments/assets/973ce369-869d-4315-a6f4-4b9111ed392e" />


![LibraryServiceTest.php PHP setelah bagian 2]<img width="1920" height="1020" alt="prak12-LibraryServiceTest-2" src="https://github.com/user-attachments/assets/30be2011-fdfa-4f81-b504-5c09a9e53897" />


![LibraryServiceTest.php PHP setelah bagian 3]<img width="1920" height="1020" alt="prak12-LibraryServiceTest-3" src="https://github.com/user-attachments/assets/3ce61659-e9b6-4196-93fa-23ca05ca6602" />


Tiga uji yang sama dengan Java: DI dengan `InMemoryBookRepository`, Strategy dan Factory (Rp3.000, Rp1.500, Rp0, Rp2.250 untuk 3 hari), serta repository palsu berupa kelas anonim yang mengimplementasikan `BookRepository` dan selalu melempar `RuntimeException`.

---

# REFACTORING.md

Dokumen `REFACTORING.md` berisi lima bagian yang diminta modul.

## Bagian 1 — Tiga temuan pelanggaran SOLID

| Prinsip | Letak | Ringkasan |
|---|---|---|
| DIP | `LibraryService` sebelum refactoring (Java baris 20–21, PHP baris 19–22) | Membuat penyimpanan konkret sendiri sehingga tidak bisa diganti atau diuji dengan repository tiruan. |
| OCP | `LibraryService` sebelum memakai `AturanDenda` | Aturan denda per jenis anggota berupa rantai `if/else` yang harus disunting tiap ada jenis baru. |
| SRP | `PerpustakaanService` dan `LibraryService` | Satu kelas mengurus penyimpanan sekaligus aturan bisnis, dan satu lagi mengurus input sekaligus format output. |

## Bagian 2 — Bukti Open-Closed

Menambah aturan denda alumni hanya menambah satu berkas baru (`DendaAlumni`) dan satu cabang `case "alumni"` di factory. `LibraryService` tidak disunting sama sekali.

## Bagian 3 — Bukti Dependency Inversion

Bila penyimpanan diganti dari memori ke basis data, cukup satu berkas baru (`DatabaseBookRepository`) dan satu baris perakitan di `Main` / `main.php`. `LibraryService` tidak berubah.

## Bagian 4 — Yang sengaja tidak diberi pattern

Pembuatan objek `Buku` di `LibraryService.tambahBuku(...)` sengaja tidak memakai Factory atau Builder. Constructor `Buku(isbn, judul, penulis, eksemplar)` hanya punya empat parameter yang jelas dan tidak ada varian subkelas, sehingga Factory hanya menambah lapisan tanpa manfaat nyata.

---

# Hasil Keseluruhan

**Java — sebelum**

![Hasil Java sebelum](docs/img/hasil/java-sebelum.png)

**Java — setelah**

![Hasil Java setelah]<img width="1267" height="1368" alt="prak12-Main" src="https://github.com/user-attachments/assets/c74019be-51f2-4e21-9734-346a4449b6a0" />


**PHP — sebelum**

![Hasil PHP sebelum](docs/img/hasil/php-sebelum.png)

**PHP — setelah**

![Hasil PHP setelah]<img width="715" height="1032" alt="prak12-main" src="https://github.com/user-attachments/assets/12eee0d6-1f1e-4338-ba7a-a6e723edbf5b" />


Hasil `mvn test` pada Java: `RepositoryTest` menjalankan 9 uji dan `LibraryServiceTest` 3 uji, total 12 uji dengan 0 gagal dan 0 error. Pada PHP terdapat 13 uji PHPUnit (10 di `KoleksiBukuTest` dan 3 di `LibraryServiceTest`) yang dijalankan dengan `./vendor/bin/phpunit tests`.

---

# Berkas Praktikum

- [Sumber Java](java/)
- [Sumber PHP](php/)
- [REFACTORING.md](REFACTORING.md)

---

# Kesimpulan

Repository pattern memisahkan cara penyimpanan dari aturan bisnis, Strategy membuat jenis aturan denda baru cukup berupa kelas baru, dan constructor injection memusatkan perakitan objek di titik masuk program. Dengan begitu `LibraryService` dapat diuji memakai repository palsu tanpa penyimpanan sesungguhnya, dan pilihan untuk tidak memberi pattern pada pembuatan `Buku` menunjukkan bahwa pattern dipakai hanya bila ada kebutuhan nyata.
