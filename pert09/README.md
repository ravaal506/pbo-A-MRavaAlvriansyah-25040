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

[Lihat Anggota.java]<img width="1050" height="1171" alt="pert-java-09-anggota" src="https://github.com/user-attachments/assets/d4498bc2-7e55-49f1-8ed7-93f5689dbdaf" />


![Anggota.java]<img width="1050" height="1171" alt="pert-java-09-anggota" src="https://github.com/user-attachments/assets/1df3c582-e462-4589-9ca8-baf7acba1d45" />


**Penjelasan:**

Class `Anggota` menyimpan informasi anggota perpustakaan. Atribut menggunakan `final` agar nilainya tidak dapat diubah setelah objek dibuat. Constructor juga melakukan validasi agar nomor anggota dan nama tidak kosong.

## 2. Buku.java

**Kode Program:**

[Lihat Buku.java]<img width="1094" height="2683" alt="pert-java-09-buku" src="https://github.com/user-attachments/assets/4c4092b7-90fe-4bff-849d-e36805a1ddb3" />


![Buku.java]<img width="1094" height="2683" alt="pert-java-09-buku" src="https://github.com/user-attachments/assets/ab5f182c-0c33-4727-8dc5-66ef7bbcf89c" />


**Penjelasan:**

Class `Buku` menyimpan informasi buku, stok, dan daftar bab. Program menerapkan konsep composition melalui class `Bab`. Stok buku tidak boleh negatif dan akan berkurang ketika dipinjam.

## 3. Peminjaman.java

**Kode Program:**

[Lihat Peminjaman.java]<img width="1343" height="2062" alt="pert-java-09-peminjaman" src="https://github.com/user-attachments/assets/f9eadb5d-770d-49ce-98da-1835cfa43bf6" />


![Peminjaman.java]<img width="1343" height="2062" alt="pert-java-09-peminjaman" src="https://github.com/user-attachments/assets/0d7f1970-4f1a-4644-931a-f37bcff63da3" />


**Penjelasan:**

Class `Peminjaman` mengelola transaksi peminjaman buku, mulai dari tanggal pinjam, tanggal jatuh tempo, hingga tanggal pengembalian. Status pengembalian diperiksa menggunakan method `sudahKembali()`.

## 4. PerpustakaanService.java

**Kode Program:**

[Lihat PerpustakaanService.java]<img width="1495" height="3709" alt="pert-java-09-perpustakaanservice" src="https://github.com/user-attachments/assets/0568078a-642a-4550-ae8b-33a6d8433da3" />


![PerpustakaanService.java]<img width="1495" height="3709" alt="pert-java-09-perpustakaanservice" src="https://github.com/user-attachments/assets/5d622958-76bb-490f-9580-bd02299af87c" />


**Penjelasan:**

Class `PerpustakaanService` mengatur proses pengelolaan buku, anggota, peminjaman, dan pengembalian. Program membatasi maksimal 3 peminjaman aktif setiap anggota serta menghitung denda keterlambatan berdasarkan jenis anggota.

## 5. Format.java

**Kode Program:**

[Lihat Format.java]<img width="1387" height="955" alt="pert-java-09-format" src="https://github.com/user-attachments/assets/162a1b00-60c8-41e8-ad0c-62e2b1505b65" />


![Format.java]<img width="1387" height="955" alt="pert-java-09-format" src="https://github.com/user-attachments/assets/b2ab4b9c-fdf3-4e3f-9db0-f3e33492121e" />


**Penjelasan:**

Class `Format` digunakan untuk mengatur tampilan mata uang Rupiah dan tanggal agar hasil program lebih rapi dan mudah dibaca.

## 6. App.java

**Kode Program:**

[Lihat App.java]<img width="1593" height="3007" alt="pert-java-09-app" src="https://github.com/user-attachments/assets/5a96830a-9afc-4e45-ac13-de90b93a7004" />


![App.java]<img width="1593" height="3007" alt="pert-java-09-app" src="https://github.com/user-attachments/assets/6cbc0a42-cae7-4743-895c-1233822d9ef5" />


**Penjelasan:**

Class `App` merupakan program utama yang menjalankan simulasi sistem perpustakaan. Program menambahkan data buku dan anggota, memproses peminjaman serta pengembalian, kemudian menampilkan hasil transaksi menggunakan tabel.

## 7. Hasil Program

**Output Program:**

![Hasil Program]<img width="1588" height="990" alt="pert-java-09-hasil-run" src="https://github.com/user-attachments/assets/c4229315-8785-4979-b4d7-715f51f97c81" />


**Penjelasan Hasil:**

- **Tabel Koleksi:** Menampilkan daftar buku beserta stok yang tersedia.
- **Tabel Transaksi:** Menampilkan informasi peminjaman, pengembalian, dan status buku.
- **Perhitungan Denda:** Anggota mahasiswa yang terlambat mengembalikan buku selama 5 hari dikenakan denda Rp5.000,00.
- **Status Peminjaman:** Buku yang sudah dikembalikan akan menambah stok, sedangkan buku yang masih dipinjam tetap tercatat sebagai transaksi aktif.

## 8. Kesimpulan

Praktikum Sistem Perpustakaan Java berhasil menerapkan konsep OOP dalam pengelolaan data buku, anggota, dan transaksi peminjaman. Penggunaan package membuat kode lebih terstruktur, sedangkan library `ascii-table` membantu menampilkan hasil program dengan rapi di terminal.
