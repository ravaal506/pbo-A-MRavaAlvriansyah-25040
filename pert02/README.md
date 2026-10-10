# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

## Informasi Praktikan

| Keterangan | Informasi |
| :--- | :--- |
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 02 - Kelas, Objek, dan Enkapsulasi |
| **Tanggal** | 10 Oktober 2026 |

---

## 🎯 Pokok Bahasan & Topik Pembelajaran

- **Enkapsulasi & Akses Kontrol:** Menjaga keutuhan data dengan menerapkan hak akses `private` pada atribut serta menyediakan *getter* tanpa *setter* untuk mempertahankan *class invariant*.
- **Imutabilitas Atribut:** Menggunakan kata kunci `final` pada Java dan `readonly` pada PHP 8 untuk atribut yang tidak boleh diubah setelah inisialisasi (`nim` dan `nama`).
- **Validasi Data & Penanganan Eksepsi:** Menerapkan validasi ketat pada konstruktor untuk menolak masukan `nim` kosong/null serta nilai di luar rentang `0 - 100` menggunakan `IllegalArgumentException` / `InvalidArgumentException`.
- **Kalkulasi & Format Mutu:** Menghitung nilai akhir berdasarkan bobot konstanta (`BOBOT_TUGAS`, `BOBOT_UTS`, `BOBOT_UAS`) serta mengonversikannya ke huruf mutu (`A`, `B`, `C`, `D`, `E`).

---

## 1. Implementasi Java

### 1.1. File: Mahasiswa.java
**Penjelasan Kode:**
Kelas `Mahasiswa` mendefinisikan struktur data mahasiswa dengan prinsip enkapsulasi. Atribut `nim` dan `nama` dideklarasikan sebagai `private final` agar imutabel. Konstruktor memvalidasi bahwa `nim` tidak boleh kosong serta setiap komponen nilai berada pada rentang valid `0` hingga `100`. Jika data melanggar aturan, kelas melempar `IllegalArgumentException`. Metode `nilaiAkhir()` menghitung total berdasarkan bobot konstanta, dan `hurufMutu()` menentukan predikat nilai.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
  <img width="877" height="551" alt="Mahasiswa Java Before" src="https://github.com/user-attachments/assets/7b92fce1-be3c-4391-b0c7-f2c77cacdc5b" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="743" height="515" alt="Mahasiswa Java After" src="https://github.com/user-attachments/assets/873a7da3-2d29-4858-bc34-fe56ee9f686a" />

---

### 1.2. File: Main.java
**Penjelasan Kode:**
Kelas `Main` bertindak sebagai skrip pengujian utama. Program membuat array dari objek `Mahasiswa` untuk menampilkan rekapitulasi nilai. Selain itu, program menguji mekanisme penanganan eksepsi melalui blok `try-catch` untuk memastikan bahwa objek menolak data yang melanggar aturan (seperti nilai `150` atau `NIM` kosong).

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
  <img width="472" height="431" alt="Main Java Before" src="https://github.com/user-attachments/assets/465285bb-b4b2-4415-a023-cd472267bf44" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="716" height="475" alt="Main Java After" src="https://github.com/user-attachments/assets/c2904454-8adb-4064-aaba-1c8bcb48d7b5" />

### Output
**Output Program:**
<img width="588" height="142" alt="Run Java Output" src="https://github.com/user-attachments/assets/501423ea-8493-4569-bc88-337dca28a754" />

---

## 2. Implementasi PHP

### 2.1. File: Mahasiswa.php
**Penjelasan Kode:**
Berkas `Mahasiswa.php` mengimplementasikan logika kelas pada PHP dengan fitur PHP 8+. Menggunakan *Constructor Property Promotion* serta penanda `readonly` pada properti `$nim` dan `$nama` untuk menjamin imutabilitas. Kelas menentukan konstanta bobot nilai, serta konstruktor yang melakukan validasi ketat dan melempar `InvalidArgumentException` jika data tidak memenuhi syarat. Metode `hurufMutu()` memanfaatkan ekspresi `match` untuk pemetaan huruf mutu.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):**  
  <img width="887" height="503" alt="Mahasiswa PHP Before" src="https://github.com/user-attachments/assets/67a25de6-1819-408b-9525-a243193c59c6" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="511" height="521" alt="Mahasiswa PHP After" src="https://github.com/user-attachments/assets/c784101b-5a92-4077-83cd-0e35599504e9" />

---

### 2.2. File: main.php
**Penjelasan Kode:**
Berkas `main.php` dijalankan dengan mode ketat `declare(strict_types=1)`. Skrip memuat dependensi `Mahasiswa.php` via `require_once`, lalu membentuk daftar objek `Mahasiswa` untuk mencetak rekapitulasi nilai menggunakan metode `__toString()`. Skrip juga menguji penangkapan eksepsi `InvalidArgumentException` saat mencoba menginstansiasi objek dengan data yang melanggar aturan.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):**  
  <img width="599" height="480" alt="Main PHP Before" src="https://github.com/user-attachments/assets/4696dcdc-ab42-4b57-99f9-67e6770b35b4" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="700" height="500" alt="Main PHP After" src="https://github.com/user-attachments/assets/dbe32a9c-db52-45d6-a05f-3f811a7f9b7b" />

### Output
**Output Program:**
<img width="479" height="140" alt="Run PHP Output" src="https://github.com/user-attachments/assets/5b11e899-752a-4bb7-b477-629401c2f329" />

---

## 3. Kesimpulan

Melalui praktikum mengenai Enkapsulasi, Imutabilitas, dan Validasi Data ini, dapat disimpulkan bahwa:

1. **Keamanan Data melalui Enkapsulasi dan Imutabilitas:**  
   Penerapan hak akses `private` yang dipadukan dengan kata kunci `final` pada Java dan `readonly` pada PHP berhasil menjaga keutuhan data. Data sensitif seperti `NIM` dan `Nama` terhindar dari perubahan tidak sah setelah objek diinstansiasi.

2. **Peraturan Objek (*Class Invariant*) via Validasi Konstruktor:**  
   Melakukan validasi di dalam konstruktor memastikan objek hanya diciptakan dalam keadaan valid (*valid state*). Penggunaan eksepsi (`IllegalArgumentException` pada Java dan `InvalidArgumentException` pada PHP) secara efektif menolak masukan `NIM` kosong maupun nilai di luar rentang `0-100`.

3. **Konsistensi Lintas Bahasa (Java & PHP):**  
   Prinsip Object-Oriented Programming (OOP) terbukti dapat diterapkan secara konsisten pada Java maupun PHP. Fitur modern seperti *Constructor Property Promotion* dan ekspresi `match` pada PHP 8 memberikan kapabilitas enkapsulasi dan penanganan logika yang setara dengan bahasa Java.
