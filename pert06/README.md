# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

## Informasi Praktikan

| Keterangan | Informasi |
| :--- | :--- |
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 06 - Abstraksi & Antarmuka |
| **Tanggal** | 10 Oktober 2026 |

---

## 🎯 Pokok Bahasan & Topik Pembelajaran

* **Abstraksi & Antarmuka (Abstract Class & Interface):** Mengombinasikan *abstract class* untuk pewarisan struktur dasar dan *interface* untuk memberikan kapabilitas tertentu (*behavior/role*).
* **Interface Segregation Principle (ISP):** Memisah peran/fungsi ke dalam *interface* yang spesifik agar suatu kelas tidak dipaksa mengimplementasikan *method* yang tidak diperlukannya.
* **Enum dengan Perilaku/Method:** Menggunakan fitur Enum yang bertindak seperti objek lengkap yang menampung *field*, konstruktor, serta *method* logika/perhitungan.
* **Default Method pada Java:** Memanfaatkan *default method* pada *interface* (Java 8+) untuk menyediakan implementasi bawaan tanpa merusak kelas-kelas turunannya.

---

## 1. Implementasi Java

### 1.1. File: Fuelable.java
**Penjelasan Kode:**
Interface `Fuelable` dirancang secara independen dari kemampuan pergerakan fisik. Antarmuka ini menentukan tiga fungsi utama yang berhubungan dengan manajemen bahan bakar. Pemisahan antarmuka ini mematuhi *Interface Segregation Principle* (ISP); sistem tidak memaksa entitas bergerak tanpa mesin (seperti sepeda) untuk memiliki fungsi pengisian bahan bakar.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  <img width="481" height="218" alt="Screenshot 2026-10-10 110551" src="https://github.com/user-attachments/assets/ae37af4e-2add-432d-9b2f-e9f48e0adf6a" />
 
- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="953" height="545" alt="Fuelable.java" src="https://github.com/user-attachments/assets/e28cc1ac-8eac-44d2-a62e-3f9f4f9e3ee4" />

---

### 1.2. File: Kendaraan.java
**Penjelasan Kode:**
Kelas abstrak `Kendaraan` berfungsi menyerap kesamaan data (*state*) seluruh kendaraan, yaitu `merek` dan `tahun`. Variabel diset `protected final` untuk menjamin imutabilitas nilai dasar sekaligus memberikan akses langsung kepada kelas turunan. Fungsi `umur()` dibuat konkrit menggunakan `Math.max()` untuk mencegah *return* bernilai minus, sedangkan `jumlahRoda()` disajikan sebagai *abstract method* karena nilainya bervariasi secara spesifik di tiap entitas turunan.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  <img width="610" height="389" alt="Screenshot 2026-10-10 110857" src="https://github.com/user-attachments/assets/b5fd8d4d-f03e-4156-ae7f-6cfbd0929940" />

- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="959" height="553" alt="Kendaraan.java" src="https://github.com/user-attachments/assets/4f25c88e-0581-4762-8ed3-4efac7f5417c" />

---

### 1.3. File: Movable.java
**Penjelasan Kode:**
Interface ini mendefinisikan sifat dasar entitas yang dapat berpindah tempat. Terdapat fitur *Default Method* pada `ringkasanGerak()` yang memfasilitasi *code reuse* di mana fungsi pembentukan format teks kecepatan sudah diimplementasikan di tingkat interface, sehingga kelas-kelas *implementor* tidak perlu melakukan *override* jika tidak ada kebutuhan khusus.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  <img width="485" height="320" alt="Screenshot 2026-10-10 110945" src="https://github.com/user-attachments/assets/0eba22fc-fbc8-45a7-9c69-ae48563f17b2" />

- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="944" height="561" alt="Movable.java" src="https://github.com/user-attachments/assets/c5904d7b-1242-4d62-8fba-d8a79d1e3516" />

---

### 1.4. File: Mobil.java
**Penjelasan Kode:**
`Mobil` merepresentasikan objek kompleks yang memanfaatkan pewarisan tunggal (`extends Kendaraan`) dan implementasi antarmuka ganda (`implements Movable, Fuelable`). Pada fungsi `isiBahanBakar()`, diterapkan teknik *defensive programming*: nilai pengisian bernilai nol/negatif langsung diabaikan, dan jumlah pengisian dibatasi oleh nilai `kapasitasTangki` menggunakan `Math.min()`.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  <img width="703" height="465" alt="Screenshot 2026-10-10 111041" src="https://github.com/user-attachments/assets/876bce40-733a-45ee-8e5d-7dada45c85c8" />

- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="954" height="542" alt="Mobil.java" src="https://github.com/user-attachments/assets/0765f098-63d6-4e2e-8a6b-ec511139997a" />

---

### 1.5. File: Sepeda.java
**Penjelasan Kode:**
Kelas `Sepeda` membuktikan keunggulan arsitektur berbasis interface terpisah. Sepeda mewarisi properti dasar `Kendaraan` dan menerapkan interface `Movable` saja. Kelas ini bersih dari *method* atau *field* yang berhubungan dengan bahan bakar karena tidak mengimplementasikan `Fuelable`.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  *(Tidak ada kesalahan / Bersih dari interface Fuelable)*
- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="953" height="557" alt="Sepeda.java" src="https://github.com/user-attachments/assets/a0bfbec7-8ed2-4a18-9b80-54bc903fb433" />

---

### 1.6. File: TipeBahanBakar.java
**Penjelasan Kode:**
Pada Java, Enum melampaui sekadar daftar nilai konstanta. Enum `TipeBahanBakar` diperlakukan layaknya tipe data khusus dengan enkapsulasi *field* (`label`, `hargaPerSatuan`), konstruktor internal, serta *method* kalkulasi bisnis seperti `biayaPengisian()` dan evaluasi boolean `ramahLingkungan()`.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  *(Tidak ada kesalahan / Konstruktor & method enum valid)*
- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="959" height="547" alt="TipeBahanBakar.java" src="https://github.com/user-attachments/assets/ec835e10-7cee-45f2-beb7-6d0154403b5b" />

---

### 1.7. File: Main.java
**Penjelasan Kode:**
Kelas `Main` mendemonstrasikan kekuatan polimorfisme antarmuka:
* Perulangan `List.of(mobil, sepeda)` memperlakukan kedua objek sebagai tipe `Movable`, mengeksekusi perilaku gerak masing-masing.
* Method `isiPenuh(Fuelable kendaraan)` menerima parameter tipe interface `Fuelable`. Method ini bekerja secara fleksibel untuk objek apa pun yang mengimplementasikan `Fuelable` tanpa peduli kelas konkretnya.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Kesalahan kompilasi):** 
  *(Tidak ada kesalahan / Siap dijalankan)*
- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="941" height="542" alt="Main.java" src="https://github.com/user-attachments/assets/82e4b2a6-a9db-4b77-a242-eafadbf2baee" />

### Output
**Output Program:**
<img width="677" height="242" alt="run program java" src="https://github.com/user-attachments/assets/32d380bc-fa4c-4daa-bd72-d6a63ae3c8a6" />

---

## 2. Implementasi PHP

### 2.1. File: abstraksi.php
**Penjelasan Kode:**
Berkas `abstraksi.php` memuat fondasi struktur OOP PHP berbasis pemisahan peran. Berkas ini mendefinisikan interface `Movable` dan `Fuelable`, serta `TipeBahanBakar` sebagai *Backed Enum* berketikan string (fitur PHP 8.1+) yang memanfaatkan ungkapan `match` untuk pemetaan label dan harga. Selain itu, terdapat deklarasi Trait `Loggable` yang memfasilitasi *horizontal code reuse*—memungkinkan kelas `Mobil` dan `Pesanan` untuk langsung berbagi kapabilitas pencatatan pesan log tanpa perlu pewarisan tunggal (*single inheritance*).

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):** 
  *(Tidak ada galat / Trait & Enum terdefinisi secara presisi)*
- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="917" height="557" alt="abstraksi" src="https://github.com/user-attachments/assets/901c3c3c-91f5-4b9b-8acd-e0dc6d723f07" />

---

### 2.2. File: main.php
**Penjelasan Kode:**
Berkas `main.php` berfungsi sebagai skrip eksekusi utama yang menggunakan instruksi `declare(strict_types=1)` untuk menjamin ketepatan tipe data. Skrip ini memuat dependensi berkas via `require_once`, menguji *type hinting* pada fungsi `isiPenuh(Fuelable $kendaraan)`, meliterasi koleksi enum `TipeBahanBakar::cases()`, serta memanggil metode `log()` dari trait `Loggable` pada instance `Mobil` dan `Pesanan`.

**Bukti Eksekusi (Screenshot):**
- **Before (Kondisi awal / Galat logika):** 
  *(Tidak ada galat / Type checking terpenuhi)*
- **After (Kondisi akhir / Eksekusi berhasil):** 
  <img width="771" height="513" alt="main php" src="https://github.com/user-attachments/assets/a163aae6-d370-41bd-b541-edd603b1095e" />

### Output
**Output Program:**
<img width="532" height="234" alt="run php" src="https://github.com/user-attachments/assets/74f4ed9a-fa31-41ae-8227-13d541fd3fac" />

---

## 3. Kesimpulan

 Melalui praktikum ini, dapat disimpulkan bahwa:
 1. Kombinasi **Abstract Class** dan **Interface** memungkinkan pembentukan arsitektur perangkat lunak yang terstruktur, di mana *Abstract Class* fokus pada kesamaan struktur data dasar (*state*), sedangkan *Interface* menangani pembagian peran/kapabilitas (*behavior*).
 2. Penerapan **Interface Segregation Principle (ISP)** mencegah pembebanan method yang tidak relevan pada kelas turunan (seperti kelas `Sepeda` yang tidak perlu memuat fungsi bahan bakar).
 3. Penggunaan **Enum dengan Method** pada Java serta **Backed Enum & Trait** pada PHP meningkatkan keamanan tipe data (*type safety*), fleksibilitas kode, serta mendukung *horizontal code reuse* tanpa terbatas pada struktur *single inheritance*.
