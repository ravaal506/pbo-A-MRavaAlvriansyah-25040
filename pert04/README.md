# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

## Informasi Praktikan

| Keterangan | Informasi |
| :--- | :--- |
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 04 - Pewarisan (Inheritance) |
| **Tanggal** | 10 Oktober 2026 |

---

## 🎯 Pokok Bahasan & Topik Pembelajaran

- **Pewarisan (Inheritance):** Menggunakan kata kunci `extends` untuk mewariskan atribut dan metode dari kelas induk (*superclass*) ke kelas anak (*subclass*) guna menghindari duplikasi kode.
.

---

## 1. Implementasi Java

### 1.1. File: Pegawai.java
**Penjelasan Kode:**
Kelas abstrak `Pegawai` berfungsi sebagai kerangka dasar yang menampung atribut umum seperti `nip`, `nama`, dan `gajiPokok`, lengkap dengan validasi konstruktor agar gaji tidak bernilai negatif. Kelas ini mendefinisikan metode dasar `hitungGaji()` serta *abstract method* `jenis()` yang wajib di-*override* oleh setiap kelas turunan.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
 <img width="953" height="513" alt="Screenshot 2026-10-10 151549" src="https://github.com/user-attachments/assets/712ba164-98e6-4b25-86d9-6134ba3e1a08" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
<img width="875" height="515" alt="pegawai java" src="https://github.com/user-attachments/assets/3b620e5b-7962-4d63-b8ad-cec3e0540872" />

---

### 1.2. File: PegawaiTetap.java
**Penjelasan Kode:**
Kelas `PegawaiTetap` merupakan turunan dari `Pegawai` yang merepresentasikan pegawai dengan masa kerja tahunan. Kelas ini mendeklarasikan konstanta persentase tunjangan (`TUNJANGAN_PER_TAHUN` sebesar 2% dan `TUNJANGAN_MAKSIMUM` sebesar 40%). Metode `hitungGaji()` di-*override* dengan menambahkan kalkulasi tunjangan masa kerja berdasarkan gaji pokok dan batas maksimum tunjangan menggunakan `Math.min()`.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
  <img width="929" height="533" alt="Screenshot 2026-10-10 151646" src="https://github.com/user-attachments/assets/47829ec8-0750-41a6-95e6-0ff6bc065c74" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="951" height="473" alt="pegawaitetap java" src="https://github.com/user-attachments/assets/a646809f-230c-413d-91d2-8910a3f04bab" />

---

### 1.3. File: PegawaiKontrak.java
**Penjelasan Kode:**
Kelas `PegawaiKontrak` memperluas kelas `Pegawai` untuk menangani pegawai dengan sistem kontrak bulanan. Kelas ini memiliki atribut tambahan `bulanKontrak`. Karena pegawai kontrak tidak mendapatkan tunjangan masa kerja seperti pegawai tetap, metode `hitungGaji()` mengembalikan nilai gaji pokok dasar dengan memanggil `super.hitungGaji()`.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
 <img width="821" height="327" alt="Screenshot 2026-10-10 151718" src="https://github.com/user-attachments/assets/075054f0-9fc4-4808-8021-d21b053b939a" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
 <img width="802" height="397" alt="pegawaikontrak java" src="https://github.com/user-attachments/assets/1a5d9081-e0b5-47c8-b27c-3fcaf3ee2186" />

---

### 1.6. File: Main.java
**Penjelasan Kode:**
Kelas `Main` mendemonstrasikan konsep polimorfisme dengan mendeklarasikan *array* bertipe induk `Pegawai[] daftar` yang menampung berbagai objek kelas turunan (`PegawaiTetap`, `PegawaiKontrak`, `Dosen`, dan `PegawaiHarian`). Melalui perulangan, program memanggil metode `hitungGaji()` dan `jenis()` secara dinamis serta mengakumulasi total keseluruhan beban gaji perusahaan.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):**  
  <img width="906" height="493" alt="Screenshot 2026-10-10 151929" src="https://github.com/user-attachments/assets/ccac5524-b1fb-443c-8ed1-0e9da0acad2a" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
<img width="940" height="532" alt="main java" src="https://github.com/user-attachments/assets/600ee69f-c976-45d6-af87-7e7f5059a481" />

### Output
**Output Program Java:**
<img width="386" height="320" alt="run java" src="https://github.com/user-attachments/assets/41e7d465-96e1-451b-aa76-b3b0f50d56dc" />


---

## 2. Implementasi PHP

### 2.1. File: Pegawai.php
**Penjelasan Kode:**
Berkas `Pegawai.php` menerapkan struktur hierarki kelas berbasis OOP pada PHP 8+. Seluruh kelas dalam hierarki pegawai dikelompokkan atau dimuat bersama untuk memudahkan pembacaan, di mana properti dilindungi menggunakan `protected readonly` dan metode abstrak dideklarasikan agar kelas turunan wajib mengimplementasikan logika penghitungan gaji masing-masing.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):**  
 <img width="715" height="511" alt="Screenshot 2026-10-10 152138" src="https://github.com/user-attachments/assets/0e027ff3-e3ef-4d57-8ce6-64853154e3ba" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
  <img width="785" height="521" alt="pegawai php" src="https://github.com/user-attachments/assets/0aa87a6c-1534-422e-924a-e63e6f6e1c83" />

---

### 2.2. File: main.php
**Penjelasan Kode:**
Skrip `main.php` dijalankan dengan mode `declare(strict_types=1)` untuk menjamin keamanan tipe data. Skrip ini menguji pengumpulan objek-objek pegawai ke dalam *array*, menampilkan rincian informasi gaji secara polimorfik, serta menghitung total beban gaji menggunakan fungsi pemetaan array (*array_map* dan *array_sum*).

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):**  
<img width="854" height="423" alt="Screenshot 2026-10-10 152218" src="https://github.com/user-attachments/assets/a73c9c86-501e-41ba-a9f6-ccc8685440bc" />

- **After (Kondisi akhir / Eksekusi berhasil):**  
 <img width="952" height="467" alt="main php" src="https://github.com/user-attachments/assets/a79b333e-e9ea-429f-a116-28c3f91118a2" />

### Output
**Output Program PHP:**
<img width="418" height="254" alt="run php" src="https://github.com/user-attachments/assets/7411dd88-33bb-43ee-b5f1-977da94c8b21" />

---

## 3. Kesimpulan


Melalui praktikum mengenai konsep Pewarisan (*Inheritance*), dapat disimpulkan bahwa:

1. **Reusabilitas dan Efisiensi Kode:**  
   Pewarisan memungkinkan kelas anak (*subclass*) untuk mewarisi atribut dan metode dari kelas induk (*superclass*) secara langsung melalui kata kunci `extends`. Hal ini secara signifikan mengurangi duplikasi kode karena sifat dasar yang serupa (seperti `nip`, `nama`, dan `gajiPokok` pada entitas pegawai) cukup didefinisikan sekali di tingkat kelas induk.

2. **Hierarki Objek dan Spesialisasi Perilaku:**  
   Pewarisan membentuk struktur hierarki kelas yang terorganisir dengan baik, memungkinkan kelas induk bertindak sebagai kerangka umum, sementara kelas-kelas turunan (seperti `PegawaiTetap`, `PegawaiKontrak`, `PegawaiHarian`, dan `Dosen`) dapat melakukan spesialisasi dan *method overriding* untuk mengakomodasi aturan bisnis atau kalkulasi fungsional yang unik sesuai posisinya masing-masing.

3. **Penggunaan Kata Kunci `super`:**  
   Mekanisme pewarisan didukung secara optimal oleh penggunaan kata kunci `super` untuk memanggil konstruktor atau metode dari kelas induk. Hal ini memastikan inisialisasi data dasar tetap terjaga keamanannya dan memungkinkan kelas anak untuk memperluas fungsionalitas (*extend behavior*) tanpa merusak struktur logika utama yang sudah ada di induknya.
