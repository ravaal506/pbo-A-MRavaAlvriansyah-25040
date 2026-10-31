# Praktikum PBO – Sistem Perpustakaan Java

**Nama:** Muchamad Rava Alvriansyah  
**NPM:** 4525210040  
**Kelas:** PBO A 2026/2027  
**Dosen Pengampu:** Adi Wahyu Pribadi, S.Si., M.Kom

## Deskripsi Program

Praktikum ini membuat Sistem Perpustakaan menggunakan bahasa pemrograman Java dengan struktur folder Maven (`src/main/java`). Program menerapkan konsep Object-Oriented Programming (OOP), seperti encapsulation, composition, dan pengelolaan objek.

Program menggunakan package `model`, `service`, dan `util`, serta library `ascii-table` untuk menampilkan tabel pada terminal.

## 1. Anggota.java

**Kode Program:**

[Lihat Anggota.java](src/main/java/id/ac/univpancasila/perpustakaan/model/Anggota.java)

![Anggota.java](ssan/ss-codingan-sesudah/java/pert-java-09-anggota.png)

**Penjelasan:**

Class `Anggota` menyimpan informasi anggota perpustakaan. Atribut menggunakan `final` agar nilainya tidak dapat diubah setelah objek dibuat. Constructor juga melakukan validasi agar nomor anggota dan nama tidak kosong.

## 2. Buku.java

**Kode Program:**

[Lihat Buku.java](src/main/java/id/ac/univpancasila/perpustakaan/model/Buku.java)

![Buku.java](ssan/ss-codingan-sesudah/java/pert-java-09-buku.png)

**Penjelasan:**

Class `Buku` menyimpan informasi buku, stok, dan daftar bab. Program menerapkan konsep composition melalui class `Bab`. Stok buku tidak boleh negatif dan akan berkurang ketika dipinjam.

## 3. Peminjaman.java

**Kode Program:**

[Lihat Peminjaman.java](src/main/java/id/ac/univpancasila/perpustakaan/model/Peminjaman.java)

![Peminjaman.java](ssan/ss-codingan-sesudah/java/pert-java-09-peminjaman.png)

**Penjelasan:**

Class `Peminjaman` mengelola transaksi peminjaman buku, mulai dari tanggal pinjam, tanggal jatuh tempo, hingga tanggal pengembalian. Status pengembalian diperiksa menggunakan method `sudahKembali()`.

## 4. PerpustakaanService.java

**Kode Program:**

[Lihat PerpustakaanService.java](src/main/java/id/ac/univpancasila/perpustakaan/service/PerpustakaanService.java)

![PerpustakaanService.java](ssan/ss-codingan-sesudah/java/pert-java-09-perpustakaanservice.png)

**Penjelasan:**

Class `PerpustakaanService` mengatur proses pengelolaan buku, anggota, peminjaman, dan pengembalian. Program membatasi maksimal 3 peminjaman aktif setiap anggota serta menghitung denda keterlambatan berdasarkan jenis anggota.

## 5. Format.java

**Kode Program:**

[Lihat Format.java](src/main/java/id/ac/univpancasila/perpustakaan/util/Format.java)

![Format.java](ssan/ss-codingan-sesudah/java/pert-java-09-format.png)

**Penjelasan:**

Class `Format` digunakan untuk mengatur tampilan mata uang Rupiah dan tanggal agar hasil program lebih rapi dan mudah dibaca.

## 6. App.java

**Kode Program:**

[Lihat App.java](src/main/java/id/ac/univpancasila/perpustakaan/App.java)

![App.java](ssan/ss-codingan-sesudah/java/pert-java-09-app.png)

**Penjelasan:**

Class `App` merupakan program utama yang menjalankan simulasi sistem perpustakaan. Program menambahkan data buku dan anggota, memproses peminjaman serta pengembalian, kemudian menampilkan hasil transaksi menggunakan tabel.

## 7. Hasil Program

**Output Program:**

![Hasil Program](ssan/ssan-setelah/ssan-java-setelah/pert-java-09-hasil-run.png)

**Penjelasan Hasil:**

- **Tabel Koleksi:** Menampilkan daftar buku beserta stok yang tersedia.
- **Tabel Transaksi:** Menampilkan informasi peminjaman, pengembalian, dan status buku.
- **Perhitungan Denda:** Anggota mahasiswa yang terlambat mengembalikan buku selama 5 hari dikenakan denda Rp5.000,00.
- **Status Peminjaman:** Buku yang sudah dikembalikan akan menambah stok, sedangkan buku yang masih dipinjam tetap tercatat sebagai transaksi aktif.

## 8. Kesimpulan

Praktikum Sistem Perpustakaan Java berhasil menerapkan konsep OOP dalam pengelolaan data buku, anggota, dan transaksi peminjaman. Penggunaan package membuat kode lebih terstruktur, sedangkan library `ascii-table` membantu menampilkan hasil program dengan rapi di terminal.
