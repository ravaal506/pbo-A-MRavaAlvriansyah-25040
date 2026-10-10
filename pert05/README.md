# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
|---|---|
| Nama | Muchamad Rava alvriansyah |
| NPM | 4525210040 |
| Kelas | A |
| Mata Kuliah | Pemrograman Berbasis Objek (PBO) |
| Pertemuan | 5 - Polimorfisme |
| Tanggal | 1  oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: BangunDatar.java

**Penjelasan Kode:**

`BangunDatar` adalah **abstract class** yang menjadi kelas induk (kontrak) bagi semua bangun datar. Atribut `nama` bersifat `private final` dan diisi lewat konstruktor `protected`, sehingga hanya kelas turunan yang dapat memanggilnya melalui `super(...)`. Kelas ini mendeklarasikan dua method abstrak, yaitu `luas()` dan `keliling()`, yang wajib diimplementasikan oleh setiap turunan. Method `toString()` didefinisikan di kelas induk tetapi memanggil `luas()` dan `keliling()`. Hal ini dimungkinkan karena **dynamic binding**: saat dijalankan, Java memanggil versi method milik objek turunan yang sebenarnya.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="737" height="469" alt="Screenshot 2026-10-10 162245" src="https://github.com/user-attachments/assets/5170f156-60bf-4615-9d77-b4fd9edb3ceb" />



- **After:**
  
  <img width="925" height="497" alt="bangundatar java" src="https://github.com/user-attachments/assets/342faa6b-0c4d-4d71-9074-a727ee675d3c" />


### 1.2. File: Lingkaran.java

**Penjelasan Kode:**

`Lingkaran` meng-extend `BangunDatar`. Konstruktor memanggil `super("Lingkaran")` lalu memvalidasi bahwa `jariJari > 0`; jika tidak, dilempar `IllegalArgumentException`. Method `luas()` di-override dengan rumus `Math.PI * jariJari * jariJari` dan `keliling()` dengan `2 * Math.PI * jariJari`. Digunakan `Math.PI`, bukan angka 3.14, agar hasil lebih akurat. Tersedia juga `getJariJari()` yang khusus dimiliki `Lingkaran`.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="688" height="343" alt="Screenshot 2026-10-10 162452" src="https://github.com/user-attachments/assets/59456f1d-a38d-4046-9632-03796f3f2d02" />



- **After:**
  
 <img width="814" height="368" alt="lingkaran java" src="https://github.com/user-attachments/assets/a7ef3c67-4dc9-4517-8858-b06867c94e72" />


### 1.3. File: Persegi.java

**Penjelasan Kode:**

`Persegi` meng-extend `BangunDatar` dengan atribut `sisi`. Konstruktor menolak `sisi <= 0`. Method `luas()` mengembalikan `sisi * sisi` dan `keliling()` mengembalikan `4 * sisi`, keduanya diberi anotasi `@Override`.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="716" height="320" alt="Screenshot 2026-10-10 162632" src="https://github.com/user-attachments/assets/e705881c-8fa3-4518-91ff-9021ca52e9a1" />



- **After:**
  
  <img width="755" height="337" alt="persegi java" src="https://github.com/user-attachments/assets/5d7e092c-18e9-430c-8973-b0d292b88c1d" />


### 1.4. File: Segitiga.java

**Penjelasan Kode:**

`Segitiga` memiliki tiga sisi (`sisiA`, `sisiB`, `sisiC`) dan menolak sisi yang bernilai `<= 0`. Method `luas()` menggunakan **rumus Heron**: dihitung semi-perimeter `s = (a + b + c) / 2`, lalu luas `= sqrt(s(s-a)(s-b)(s-c))`. Method `keliling()` menjumlahkan ketiga sisi.

**Bukti Eksekusi (Screenshot):**

- **Before:**



- **After:**
  
  <img width="827" height="482" alt="segitiga java" src="https://github.com/user-attachments/assets/c1e68d2f-d27e-4223-a88f-6585e6644574" />

### 1.5. File: Trapesium.java

**Penjelasan Kode:**

`Trapesium` memiliki atribut `sisiAtas`, `sisiBawah`, dan `tinggi`, dengan validasi ketiganya harus lebih besar dari 0. Luas dihitung dengan `((sisiAtas + sisiBawah) / 2) * tinggi`. Untuk keliling, diasumsikan trapesium sama kaki: sisi miring dihitung dengan teorema Pythagoras `sqrt(((sisiBawah - sisiAtas) / 2)^2 + tinggi^2)`, lalu keliling `= sisiAtas + sisiBawah + 2 * sisiMiring`.

**Bukti Eksekusi (Screenshot):**

- **Before:**

  
- **After:**
  
  <img width="928" height="497" alt="trapesium java" src="https://github.com/user-attachments/assets/722be76d-d39e-4550-b6ac-cd43bec331f8" />

### 1.6. File: AntiPattern.java

**Penjelasan Kode:**

File ini adalah contoh **anti-pattern** sebagai pembanding. Bangun datar direpresentasikan dengan `record` biasa (`LingkaranData`, `PersegiData`, `SegitigaData`) tanpa hierarki pewarisan. Perhitungan luas dipusatkan di satu method `hitungLuas(Object)` yang berisi rantai `if ... else if ... instanceof`. Kekurangannya: setiap menambah bangun datar baru, method ini harus disunting (menambah satu cabang), dan jika ada cabang yang terlupa, program melempar `IllegalArgumentException` saat dijalankan. Pengetahuan cara menghitung luas seharusnya berada di kelas bangunnya masing-masing, seperti pada pendekatan polimorfisme.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="831" height="511" alt="Screenshot 2026-10-10 162933" src="https://github.com/user-attachments/assets/ff383977-cb0c-47e1-a5d9-e9c6a9fd3a6d" />



- **After:**
- 
  <img width="936" height="539" alt="antipatern java" src="https://github.com/user-attachments/assets/af51a161-2a33-44e6-bde4-ef499df9763b" />

### 1.7. File: Main.java

**Penjelasan Kode:**

`Main` adalah program uji. Array bertipe `BangunDatar[]` diisi objek turunan (`Lingkaran`, `Persegi`, `Segitiga`, `Trapesium`), yang disebut **upcasting**. Dengan satu perulangan `for`, setiap objek dicetak dan luasnya dijumlahkan tanpa perlu memeriksa tipe aslinya, karena method yang dipanggil ditentukan secara polimorfik. Pada bagian akhir ditunjukkan **downcasting** yang aman memakai `instanceof` untuk mengakses `getJariJari()`, yang hanya dimiliki `Lingkaran`. Downcasting dilakukan hanya bila benar-benar diperlukan.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="951" height="533" alt="Screenshot 2026-10-10 163021" src="https://github.com/user-attachments/assets/2d49d21e-81fa-43a8-a94b-eb951b4c7822" />



- **After:**

   <img width="695" height="491" alt="main java" src="https://github.com/user-attachments/assets/29c71b22-04f7-4d94-ab1f-f7c3c328473f" />

### Output

**Output Program:**

<img width="609" height="217" alt="run java" src="https://github.com/user-attachments/assets/9cef2cc1-c7ed-435b-922c-818fb84093e8" />


---

## 2. Implementasi PHP

### 2.1. File: BangunDatar.php

**Penjelasan Kode:**

File ini memuat `abstract class BangunDatar` beserta kelas-kelas turunannya (`Lingkaran`, `Persegi`, `Segitiga`, `Trapesium`). Kelas induk memakai *constructor property promotion* (`private readonly string $nama`) dan mendeklarasikan method abstrak `luas(): float` dan `keliling(): float`. Method `__toString()` memformat nama, luas, dan keliling dengan `sprintf`, dan memanggil `luas()` serta `keliling()` milik turunan (polimorfisme). Kelas `Lingkaran` memanggil `parent::__construct('Lingkaran')` dan menolak jari-jari `<= 0`.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="710" height="524" alt="Screenshot 2026-10-10 163121" src="https://github.com/user-attachments/assets/698639f4-9046-4908-84b7-900cf1ddb3b5" />



- **After:**
- 
   <img width="853" height="500" alt="bangundatar php" src="https://github.com/user-attachments/assets/58b4a074-d178-4c07-9911-0803018c7dc4" />


### 2.2. File: main.php

**Penjelasan Kode:**

File ini memuat `BangunDatar.php` dengan `require_once`, lalu membuat array `$daftar` berisi objek `Lingkaran`, `Persegi`, `Segitiga`, dan `Trapesium`. Perulangan `foreach` mencetak setiap objek (otomatis memanggil `__toString()`). Total luas dihitung dengan `array_sum(array_map(fn (BangunDatar $b): float => $b->luas(), $daftar))`, yaitu tanpa pemeriksaan tipe objek.

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="854" height="373" alt="Screenshot 2026-10-10 163209" src="https://github.com/user-attachments/assets/86c8794b-6d4d-4e79-acd5-12b176fd03c4" />



- **After:**
  
   <img width="830" height="476" alt="main php" src="https://github.com/user-attachments/assets/ed029b1e-bbc6-46f2-abb0-fcf6fba28dd8" />

### 2.3. File: notifikasi.php 

**Penjelasan Kode:**

Latihan membuat hierarki `Notifikasi` dengan tiga turunan: `Email`, `SMS`, dan `WhatsApp`. Kelas abstrak `Notifikasi` memiliki properti `public readonly string $tujuan`, method abstrak `kirim(string $pesan): void`, dan method `saluran()` yang mengembalikan nama kelas turunan lewat `static::class`. Fungsi `kirimSemua()` harus melakukan pengiriman tanpa satu pun pemeriksaan tipe.

> Catatan: lengkapi bagian ini sesuai kode akhir `notifikasi.php` Anda (kelas turunan, `kirimSemua()`, dan outputnya).

**Bukti Eksekusi (Screenshot):**

- **Before:**
<img width="636" height="454" alt="Screenshot 2026-10-10 163305" src="https://github.com/user-attachments/assets/fe9fb502-0a69-4589-af45-2a54d7a37df5" />



- **After:**
  
   <img width="860" height="500" alt="notifikasi php" src="https://github.com/user-attachments/assets/902c1d4d-2749-4a2c-9037-2f3efb4b2e57" />

### Output

**Output Program:**

<img width="482" height="203" alt="run php" src="https://github.com/user-attachments/assets/8d2f8783-0b1b-4e99-9423-1291ea2a75b5" />


`
```

> Catatan: nilai Trapesium berbeda dengan versi Java karena argumen yang dipakai di `main` berbeda (Java: `Trapesium(3, 4, 5)`, PHP: `Trapesium(3, 5, 4)`), sehingga total luas juga berbeda (202.44 vs 200.94).

---

## 3. Kesimpulan

Pada pertemuan ini dipelajari **polimorfisme**: kelas induk abstrak (`BangunDatar`) menetapkan kontrak berupa method `luas()` dan `keliling()`, sedangkan setiap kelas turunan mengisinya dengan caranya sendiri melalui *method overriding*. Dengan upcasting, objek berbeda dapat disimpan dalam satu array bertipe induk dan diproses dengan satu perulangan tanpa `instanceof`. Menambah bangun datar baru cukup dengan membuat kelas baru dan menambah satu baris ke array, tanpa mengubah logika perulangan. Hal ini berbeda dengan pendekatan *anti-pattern* di `AntiPattern.java`, yang harus menyunting rantai `if-else` setiap ada bangun baru. Downcasting hanya digunakan bila benar-benar diperlukan, misalnya untuk mengakses `getJariJari()` milik `Lingkaran`. Konsep yang sama berlaku di Java dan PHP, dengan perbedaan sintaks (`extends`/`abstract`/`@Override` pada Java, serta `readonly` dan `__toString()` pada PHP).
