# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

## Informasi Praktikan

| Keterangan | Informasi |
| :--- | :--- |
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 03 - Constructor, Anggota Statis, & Konstanta |
| **Tanggal** | 10 Oktober 2026 |

---

## 🎯 Pokok Bahasan & Topik Pembelajaran

- **Constructor Overloading & Delegation:** Menggunakan teknik pendelegasian konstruktor (`this(...)`) pada Java untuk menghindari duplikasi kode inisialisasi, serta memanfaatkan *default parameter* atau *named constructor* pada PHP untuk meniru fungsionalitas overloading.
- **Anggota Statis (Static Members):** Memanfaatkan variabel dan metode statis (`static`) untuk mengelola data bersama lintas objek, seperti penghitungan total instansi rekening yang aktif.
- **Konstanta (Constants):** Menggunakan kata kunci `static final` pada Java dan `const` pada PHP untuk mendefinisikan nilai tetap (seperti persentase bunga tahunan, biaya administrasi, dan batas penarikan) guna menghindari *magic numbers* di dalam kode.
- **Validasi & Penanganan Aturan Bisnis:** Menerapkan validasi ketat pada konstruktor dan metode operasional (seperti setor dan tarik tunai) untuk menjaga keutuhan saldo (*class invariant*) agar tidak bernilai negatif.

---

## 1. Implementasi Java

### 1.1. File: RekeningBank.java
**Penjelasan Kode:**
Kelas `RekeningBank` menerapkan konstanta bisnis seperti `bunga_tahunan`, `biaya_administrasi`, dan `batas_penarikan_sekali` menggunakan `public static final`. Terdapat variabel statis `jumlahRekening` untuk menghitung jumlah instansi rekening yang berhasil dibuat. Kelas ini mengimplementasikan *constructor delegation* di mana konstruktor ringkas mendelegasikan tugas inisialisasi ke konstruktor lengkap menggunakan `this(...)`, serta melakukan validasi agar saldo awal tidak bernilai negatif.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
  <img width="874" height="523" alt="Screenshot 2026-10-10 142142" src="https://github.com/user-attachments/assets/d45377ee-b82a-40fb-a73d-3d8a45e80bb3" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="740" height="521" alt="rekeningbank java" src="https://github.com/user-attachments/assets/8eac05a4-4a3e-4ef7-ad09-221e4cd5275d" />

---

### 1.2. File: Main.java
**Penjelasan Kode:**
Kelas `Main` berfungsi sebagai penguji utama aplikasi perbankan berbasis Java. Skrip ini menguji pembuatan objek rekening dengan konstruktor berbeda, memanggil metode statis `RekeningBank.JumlahRekening()`, melakukan operasi penyetoran, penarikan saldo dengan mekanisme penanganan eksepsi (*try-catch*), pemotongan biaya administrasi, serta penghitungan bunga tahunan berdasarkan saldo akhir.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
  <img width="878" height="472" alt="Screenshot 2026-10-10 142237" src="https://github.com/user-attachments/assets/39646453-ef7c-486a-912b-2ea36a0a5658" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="839" height="465" alt="main java" src="https://github.com/user-attachments/assets/256036ec-93c6-4d51-8ae8-3150fbdb4f19" />


### Output
**Output Program Java:**
<img width="606" height="160" alt="run java" src="https://github.com/user-attachments/assets/a60ce3d3-5e23-49ad-aacf-1ac08cd11c3e" />


---

## 2. Implementasi PHP

### 2.1. File: RekeningBank.php
**Penjelasan Kode:**
Berkas `RekeningBank.php` menyesuaikan konsep OOP Java ke dalam lingkungan PHP 8+. Mengingat PHP tidak mendukung *constructor overloading* secara langsung, implementasi diatasi menggunakan *default parameter* pada konstruktor utama serta *named constructor* (static factory method). Kelas ini juga mendefinisikan konstanta kelas dan variabel statis, serta metode manipulasi saldo seperti penyetoran dan penarikan yang aman dari nilai negatif.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):**  
 <img width="711" height="504" alt="Screenshot 2026-10-10 142322" src="https://github.com/user-attachments/assets/34e5adff-a5e9-420b-8654-e9044315efaa" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="868" height="522" alt="rekeningbankj php" src="https://github.com/user-attachments/assets/011ced34-eed6-415c-9824-8d31ee82bde1" />


---

### 2.2. File: main.php
**Penjelasan Kode:**
Berkas `main.php` dijalankan dengan mode ketat `declare(strict_types=1)` untuk memverifikasi alur logika program PHP. Skrip ini memuat dependensi kelas `RekeningBank.php`, melakukan pengujian instansiasi objek rekening, menampilkan status jumlah rekening aktif, serta menguji transaksi keuangan seperti setor tunai, validasi batas penarikan, dan kalkulasi bunga.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):**  
  <img width="776" height="473" alt="Screenshot 2026-10-10 142353" src="https://github.com/user-attachments/assets/753b9b56-bf9d-49d2-8761-594b3575134e" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="761" height="425" alt="main php" src="https://github.com/user-attachments/assets/2f34c198-0272-47aa-98fe-e496b1da703a" />


### Output
**Output Program PHP:**
<img width="574" height="155" alt="run php" src="https://github.com/user-attachments/assets/168eb829-f9a1-4f8a-a390-f7541518111d" />


## 3. Kesimpulan

Melalui praktikum mengenai Constructor Berdelegasi, Anggota Statis, dan Konstanta ini, dapat disimpulkan bahwa:

1. **Efisiensi Kode via Constructor Delegation:**  
   Pendelegasian konstruktor (`this(...)` pada Java atau penggunaan parameter default pada PHP) sangat membantu dalam merampingkan kode inisialisasi objek, mencegah redundansi, serta memusatkan logika validasi hanya pada satu titik konstruktor utama.

2. **Manajemen Data Bersama dengan Anggota Statis:**  
   Penggunaan variabel dan metode statis (`static`) memungkinkan sistem untuk melacak informasi global lintas objek (seperti penghitungan total rekening yang tercipta) secara konsisten tanpa terikat pada instansi objek tertentu.

3. **Penerapan Konstanta untuk Menghindari Magic Numbers:**  
   Pendefinisian konstanta kelas (`static final` / `const`) membuat kode lebih mudah dibaca, dipelihara, dan dimodifikasi jika terjadi perubahan aturan bisnis seperti penyesuaian suku bunga atau biaya administrasi bank.
