Laporan Praktikum Pemrograman Berorientasi Objek (PBO) - Pertemuan 06

Nama : Muchamad Rava Alvriansyah

NPM : 4525210040

Mata Kuliah : Pemrograman Berorientasi Objek

🎯 Pokok Bahasan & Topik Pembelajaran

Abstraksi & Antarmuka (Abstract Class & Interface)

Mengombinasikan abstract class untuk pewarisan struktur dasar dan interface untuk memberikan kapabilitas tertentu (behavior/role).

Interface Segregation Principle (ISP)

Memisah peran/fungsi ke dalam interface yang spesifik agar suatu kelas tidak dipaksa mengimplementasikan method yang tidak diperlukannya.

Enum dengan Perilaku/Method

Menggunakan fitur Enum pada Java yang bertindak seperti objek lengkap, yang dapat menampung field, konstruktor, serta method logika/perhitungan.

Default Method pada Java

Memanfaatkan default method pada interface (Java 8+) untuk menyediakan implementasi bawaan tanpa merusak kelas-kelas turunannya.

💻 Implementasi Kode Program (Java)

1. Fuelable.java

<img width="953" height="545" alt="fluelable java" src="https://github.com/user-attachments/assets/e28cc1ac-8eac-44d2-a62e-3f9f4f9e3ee4" />



Penjelasan & Analisis Teknis:

Fuelable dirancang secara independen dari kemampuan pergerakan fisik. Antarmuka ini menentukan tiga fungsi utama yang berhubungan dengan manajemen bahan bakar. Pemisahan antarmuka ini mematuhi Interface Segregation Principle (ISP); sistem tidak memaksa entitas bergerak tanpa mesin (seperti sepeda) untuk memiliki fungsi pengisian bahan bakar.

2. Kendaraan.java

<img width="959" height="553" alt="kendaraan java" src="https://github.com/user-attachments/assets/4f25c88e-0581-4762-8ed3-4efac7f5417c" />



Penjelasan & Analisis Teknis:

Kelas abstrak Kendaraan berfungsi menyerap kesamaan data (state) seluruh kendaraan, yaitu merek dan tahun. Variabel diset protected final untuk menjamin imutabilitas nilai dasar sekaligus memberikan akses langsung kepada kelas turunan. Fungsi umur() dibuat konkrit menggunakan Math.max() untuk mencegah return bernilai minus, sedangkan jumlahRoda() disajikan sebagai abstract method karena nilainya bervariasi secara spesifik di tiap entitas turunan.

3. Movable.java

<img width="944" height="561" alt="movable java" src="https://github.com/user-attachments/assets/c5904d7b-1242-4d62-8fba-d8a79d1e3516" />



Penjelasan & Analisis Teknis:

Interface ini mendefinisikan sifat dasar entitas yang dapat berpindah tempat. Menariknya, terdapat fitur Default Method pada ringkasanGerak(). Fitur ini memfasilitasi code reuse di mana fungsi pembentukan format teks kecepatan sudah diimplementasikan di tingkat interface, sehingga kelas-kelas implementor tidak perlu melakukan override jika tidak ada kebutuhan khusus.

4. Mobil.java

<img width="954" height="542" alt="mobil java" src="https://github.com/user-attachments/assets/0765f098-63d6-4e2e-8a6b-ec511139997a" />



Penjelasan & Analisis Teknis:

Mobil merepresentasikan objek kompleks yang memanfaatkan pewarisan tunggal (extends Kendaraan) dan implementasi antarmuka ganda (implements Movable, Fuelable). Pada fungsi isiBahanBakar(), diterpakan teknik defensive programming: nilai pengisian bernilai nol/negatif langsung diabaikan, dan jumlah pengisian dibatasi oleh nilai kapasitasTangki menggunakan Math.min().

5. Sepeda.java

<img width="953" height="557" alt="sepeda java" src="https://github.com/user-attachments/assets/a0bfbec7-8ed2-4a18-9b80-54bc903fb433" />




Penjelasan & Analisis Teknis:

Kelas Sepeda membuktikan keunggulan arsitektur berbasis interface terpisah. Sepeda mewarisi properti dasar Kendaraan dan menerapkan interface Movable saja. Kelas ini bersih dari method atau field yang berhubungan dengan bahan bakar karena tidak mengimplementasikan Fuelable.

6. TipeBahanBakar.java

<img width="959" height="547" alt="tipebahanbakar java" src="https://github.com/user-attachments/assets/ec835e10-7cee-45f2-beb7-6d0154403b5b" />



Penjelasan & Analisis Teknis:

Pada Java, Enum melampaui sekadar daftar nilai konstanta. Enum TipeBahanBakar diperlakukan layaknya tipe data khusus dengan enkapsulasi field (label, hargaPerSatuan), konstruktor internal, serta method kalkulasi bisnis seperti biayaPengisian() dan evaluasi boolean ramahLingkungan().

7. Main.java

<img width="941" height="542" alt="main java" src="https://github.com/user-attachments/assets/82e4b2a6-a9db-4b77-a242-eafadbf2baee" />



Penjelasan & Analisis Teknis:

Kelas Main mendemonstrasikan kekuatan polimorfisme antarmuka:

Perulangan List.of(mobil, sepeda) memperlakukan kedua objek sebagai tipe Movable, mengeksekusi perilaku gerak masing-masing.

Method isiPenuh(Fuelable kendaraan) menerima parameter tipe interface Fuelable. Method ini bekerja secara fleksibel untuk objek apa pun yang mengimplementasikan Fuelable tanpa peduli kelas konkretnya.

🖥️ Output Eksekusi Program

<img width="677" height="242" alt="run program java" src="https://github.com/user-attachments/assets/32d380bc-fa4c-4daa-bd72-d6a63ae3c8a6" />


Penjelasan Output:

Blok 1: Membuktikan polimorfisme pada interface Movable, di mana pesan aksi melaju/dikayuh dipanggil sesuai tipe objek aslinya, diikuti deskripsi kecepatan dari default method.

Blok 2: Menunjukkan pemanggilan method polimorfik isiPenuh(). Mobil dengan kapasitas 45 liter diisi dengan Bensin (Rp12.000/liter) menghasilkan total biaya Rp540.000.

Blok 3: Menampilkan hasil iterasi dari seluruh nilai Enum TipeBahanBakar, mengonfirmasi bahwa LISTRIK diidentifikasi sebagai satu-satunya tipe yang ramah lingkungan (true).

2. Implementasi Bahasa PHP

Screenshot Coding abstraksi.php

<img width="917" height="557" alt="abstraksi" src="https://github.com/user-attachments/assets/901c3c3c-91f5-4b9b-8acd-e0dc6d723f07" />


Penjelasan: Berkas abstraksi.php memuat fondasi struktur OOP PHP berbasis pemisahan peran. Berkas ini mendefinisikan interface Movable dan Fuelable, serta TipeBahanBakar sebagai Backed Enum berketikan string (fitur PHP 8.1+) yang memanfaatkan ungkapan match untuk pemetaan label dan harga.

Selain itu, terdapat deklarasi Trait Loggable yang memfasilitasi horizontal code reuse—memungkinkan kelas Mobil dan Pesanan (dua hirarki kelas yang tidak saling berhubungan) untuk langsung berbagi kapabilitas pencatatan pesan log tanpa perlu pewarisan tunggal (single inheritance).

Screenshot Coding Main.php
<img width="771" height="513" alt="main php" src="https://github.com/user-attachments/assets/a163aae6-d370-41bd-b541-edd603b1095e" />


Penjelasan: Berkas main.php berfungsi sebagai skrip eksekusi utama yang menggunakan instruksi declare(strict_types=1) untuk menjamin ketepatan tipe data. Skrip ini memuat dependensi berkas via require_once, menguji type hinting pada fungsi isiPenuh(Fuelable $kendaraan), meliterasi koleksi enum TipeBahanBakar::cases(), serta memanggil metode log() dari trait Loggable pada instance Mobil dan Pesanan.

Hasil Running Program PHP
<img width="532" height="234" alt="run php" src="https://github.com/user-attachments/assets/74f4ed9a-fa31-41ae-8227-13d541fd3fac" />


Penjelasan Output: Output eksekusi skrip PHP mengonfirmasi kelancaran alur logika: penghitungan biaya bahan bakar terformat secara presisi, penanganan polimorfisme pada kelas Mobil dan Sepeda, iterasi enum dengan struktur kondisional match, serta pencetakan log berstempel waktu (timestamp) hasil penggunaan Trait.
