# Praktikum PBO - Pertemuan 7: Komposisi dan Agregasi

| Informasi Praktikan | Keterangan |
|---|---|
| Nama | Muchamad Rava Alvriansyah |
| NPM | 4525210040 |
| Kelas | A |
| Mata Kuliah | Pemrograman Berorientasi Objek (PBO) |
| Pertemuan | 7 - Komposisi dan Agregasi |
| Tanggal | 17 Oktober 2026 |

Repositori ini berisi implementasi relasi *has-a* berupa **Komposisi** dan **Agregasi** menggunakan Java dan PHP, lengkap dengan diagram UML dan dokumen analisisnya.

---

## 1. Implementasi Java

### 1.1. `Buku.java`

**Penjelasan Kode:**

Hubungan `Buku` dan `Bab` dibuat sebagai komposisi. `Bab` adalah nested class dengan constructor `private`, sehingga objeknya hanya bisa dibuat lewat `tambahBab(int, String)` milik `Buku`. Method `getBab()` mengembalikan `List.copyOf(...)`, yaitu salinan yang tidak dapat diubah, sehingga koleksi internal tetap aman dari modifikasi luar.

**Bukti Eksekusi (Screenshot):**

- Before (kondisi awal / belum diimplementasi):

<img width="922" height="512" alt="image" src="https://github.com/user-attachments/assets/114592fb-9bca-4f53-bfae-a461581f8a3a" />


- After (kondisi akhir / eksekusi berhasil):

  <img width="542" height="524" alt="buku java" src="https://github.com/user-attachments/assets/23c29be6-bbe1-4ff4-b0a8-e69d3ccbcd15" />


### 1.2. `Jurusan.java`

**Penjelasan Kode:**

`Jurusan` menerapkan agregasi terhadap `Mahasiswa`. Objek `Mahasiswa` dibuat di luar, lalu didaftarkan lewat `daftarkan(Mahasiswa m)`. Saat `keluarkan(Mahasiswa m)` dipanggil, hanya referensinya yang dilepas dari `Jurusan`, sedangkan objek `Mahasiswa` tetap hidup.

**Bukti Eksekusi (Screenshot):**

- Before (kondisi awal / belum diimplementasi):

  ]<img width="765" height="514" alt="image" src="https://github.com/user-attachments/assets/5b3159d2-146b-4928-98ea-3fc41c184d21" />


- After (kondisi akhir / eksekusi berhasil)

  <img width="538" height="509" alt="jurusan java" src="https://github.com/user-attachments/assets/57f3ebc4-fd5e-4552-b4e8-1fcbc8ababef" />


### 1.3. `PerpustakaanService.java`

**Penjelasan Kode:**

Kelas layanan yang mengelola koleksi buku dan transaksi perpustakaan. Relasinya dengan `Buku` adalah agregasi. Layanan yang tersedia: mencari buku berdasarkan nomor induk, meminjam (stok berkurang), dan mengembalikan (stok bertambah).

**Bukti Eksekusi (Screenshot):**

- Before (kondisi awal / belum diimplementasi):

  <img width="655" height="496" alt="image" src="https://github.com/user-attachments/assets/0481c507-43bb-4546-9527-e8bd312b981f" />


- After (kondisi akhir / eksekusi berhasil):

  <img width="563" height="482" alt="perpustakanservice java" src="https://github.com/user-attachments/assets/f0ceea69-7f4b-42d8-9c09-c8c574077d1c" />


### 1.4. `Main.java`

**Penjelasan Kode:**

Entry point pengujian. Berisi penambahan `Bab` ke `Buku`, pembuktian bahwa `getBab()` tidak dapat dimodifikasi (pemanggilan `clear()` memicu `UnsupportedOperationException`), serta alur pendaftaran dan pengeluaran `Mahasiswa` pada `Jurusan`.

**Bukti Eksekusi (Screenshot):**

- Before (kondisi awal / belum diimplementasi):

  <img width="683" height="514" alt="image" src="https://github.com/user-attachments/assets/945a7a93-4ff1-4c49-9c32-dce4663ff410" />


- After (kondisi akhir / eksekusi berhasil)

  <img width="840" height="518" alt="main java" src="https://github.com/user-attachments/assets/088e1e4f-6335-4a8c-bdd7-da2ade95245d" />

  
### Output Java

<img width="315" height="157" alt="run java" src="https://github.com/user-attachments/assets/2a4ce0d6-4d45-4e93-8a81-2a1f5cd96840" />


**Penjelasan Output:** Program menampilkan data buku beserta bab-babnya, menolak manipulasi koleksi bab dari luar, dan menampilkan proses pendaftaran serta pengeluaran mahasiswa tanpa menghapus objek mahasiswa itu sendiri.

**Cara menjalankan:**

```bash
javac *.java
java Main
```

---

## 2. Implementasi PHP

### 2.1. `relasi.php`

**Penjelasan Kode:**

Berisi struktur komposisi dan agregasi di PHP. `Bab` memakai properti `readonly`. `Buku` memegang `Bab` secara komposisi lewat `tambahBab()`. `Jurusan` menerapkan agregasi terhadap `Mahasiswa`, mencegah pendaftaran ganda, dan mengeluarkan objek menggunakan `array_filter`.

**Bukti Eksekusi (Screenshot):**

- Before (kondisi awal / galat logika):

  <img width="671" height="512" alt="image" src="https://github.com/user-attachments/assets/0c546718-1d14-471f-b10e-69c2174cf07f" />


- After (kondisi akhir / eksekusi berhasil):

  <img width="757" height="530" alt="relasi php" src="https://github.com/user-attachments/assets/acf78777-78ef-4a22-a584-9b93823bee5c" />


### 2.2. `main.php`

**Penjelasan Kode:**

Skrip utama yang mengimpor `relasi.php`. Menguji pembentukan komposisi `Buku` dan `Bab`, mendaftarkan mahasiswa ke `Jurusan` lewat perulangan, dan membuktikan bahwa objek `Mahasiswa` tetap ada setelah dikeluarkan dari `Jurusan`.

**Bukti Eksekusi (Screenshot):**

- Before (kondisi awal / galat logika):

  <img width="721" height="464" alt="image" src="https://github.com/user-attachments/assets/73993bc0-8467-4635-8bca-7f79bab25018" />


- After (kondisi akhir / eksekusi berhasil):

  <img width="806" height="451" alt="main php" src="https://github.com/user-attachments/assets/a06e24e1-73fc-430f-a6c5-0c30e6ab56bd" />


### Output PHP

<img width="391" height="169" alt="run php" src="https://github.com/user-attachments/assets/8d4ddaa3-241f-4679-8daf-1be03c05beb1" />


**Penjelasan Output:** Program berjalan lancar, menampilkan struktur komposisi buku dan bab, serta membuktikan bahwa objek `Mahasiswa` berdiri sendiri di luar `Jurusan`.

**Cara menjalankan:**

```bash
php main.php
```

---

## 3. Diagram UML & Dokumentasi Analisis

### 3.1. `perpustakaan-class.puml`

**Penjelasan:**

Class diagram PlantUML dengan hierarki pewarisan `Koleksi` (`Buku`, `Majalah`, `Skripsi`), relasi komposisi (`Buku *-- Bab`), relasi agregasi (`Anggota o-- Peminjaman`, `PerpustakaanService o-- Koleksi`), serta enum `StatusKoleksi` dan `JenisKeanggotaan`.

![PlantUML Class Diagram]

<img width="579" height="851" alt="image" src="https://github.com/user-attachments/assets/47c2ee2e-7548-43b8-a766-382815fc4de3" />


### 3.2. `bukti-relasi.md`

**Penjelasan:**

Dokumen pembuktian yang membandingkan proteksi imutabilitas koleksi pada Java (`List.copyOf`) dan PHP (array by-value dan properti `readonly`).

### 3.3. `analisis.md`

**Penjelasan:**

Dokumen analisis domain sistem perpustakaan: identifikasi kata benda dan kata kerja, penetapan invarian atau aturan bisnis, serta dasar pertimbangan memilih agregasi pada `PerpustakaanService`.

---

## 4. Kesimpulan

- **Komposisi** adalah kepemilikan kuat: bagian (`Bab`) tidak berdiri sendiri dan siklus hidupnya mengikuti induknya (`Buku`).
- **Agregasi** adalah kepemilikan longgar: bagian (`Mahasiswa`) dibuat di luar dan tetap hidup meski dilepas dari wadahnya (`Jurusan`).
- Proteksi koleksi dengan `List.copyOf(...)` di Java dan `readonly` di PHP penting untuk menjaga enkapsulasi.
