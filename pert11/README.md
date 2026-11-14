# Laporan Praktikum Pemrograman Berorientasi Objek
## Pertemuan 11: Collections, Generics, dan Unit Test Pertama

| | |
|---|---|
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | PBO A 2026/2027 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## 1. Ringkasan

Pada pertemuan ini dipelajari cara mengelola kumpulan data dengan Collections dan Generics, menangani hasil pencarian yang bisa kosong, serta memastikan kode berjalan benar lewat unit test. Praktikum dikerjakan dengan dua bahasa:

- **Java**: Repository generik dengan pengujian JUnit 5.
- **PHP**: Typed collection untuk data buku dengan pengujian PHPUnit.

## 2. Capaian yang Diharapkan

Setelah praktikum ini, mahasiswa diharapkan mampu:

1. Menggunakan Collections Framework untuk menyimpan dan mengelola data.
2. Membuat kelas generik dengan type parameter `<T>` pada Java.
3. Memakai `Optional` agar hasil pencarian tidak langsung berupa `null`.
4. Menerapkan operasi filter, map, reduce, dan grouping pada data.
5. Menulis unit test dengan JUnit 5.
6. Menulis unit test dengan PHPUnit.

## 3. Topik yang Dipelajari

| Bahasa | Topik |
|---|---|
| **Java** | Repository generik `<T>`, Collections (`Map` dan `List`), `Optional` untuk pencarian, hapus data berdasarkan ID, JUnit 5 (`assertEquals`, `assertTrue`, `assertThrows`) |
| **PHP** | Typed collection, nullable type `?Buku`, `array_filter` dan `array_map`, `array_reduce`, pengelompokan data, PHPUnit |

## 4. Struktur Proyek

```text
Pert11/
├── .vscode/
├── lib/
├── screenshots/
│   ├── 01-repository-java.png
│   ├── 02-repositorytest-java.png
│   ├── 03-koleksibuku-php.png
│   └── 04-koleksibukutest-php.png
├── src/
│   ├── Modul-Praktikum-11.docx
│   ├── PPT-Praktikum-11.pptx
│   └── starter/
│       ├── java/
│       ├── php/
│       └── pom-tambahan.xml
└── README.md
```

## 5. Cara Kerja Program

### Java: `Repository<T>`

Kelas ini berfungsi sebagai tempat penyimpanan data buku yang bisa dipakai untuk tipe data apa pun berkat Generics. Fungsi utamanya:

- menyimpan data,
- mencari data berdasarkan ID,
- menampilkan seluruh data,
- menghapus data berdasarkan ID.

Hasil pencarian dibungkus dalam `Optional`, sehingga pemanggil dipaksa memeriksa apakah data ditemukan dan risiko `NullPointerException` berkurang.

### PHP: `KoleksiBuku`

Kelas ini mengelola kumpulan objek `Buku` dalam bentuk typed collection. Method yang tersedia:

| Method | Fungsi |
|---|---|
| `simpan()` | Menambahkan buku ke koleksi |
| `cariByIsbn()` | Mencari buku berdasarkan ISBN (mengembalikan `?Buku`) |
| `filter()` | Menyaring buku sesuai kondisi tertentu |
| `map()` | Mengubah setiap data buku ke bentuk lain |
| `jumlahkan()` | Menghitung total nilai dari koleksi |
| `kelompokkan()` | Mengelompokkan buku berdasarkan penulis |

## 6. Cara Menjalankan Test

> Perintah di bawah adalah perintah standar. Sesuaikan dengan konfigurasi proyek masing-masing.

**Java (Maven + JUnit 5)**

```bash
mvn test
```

**PHP (PHPUnit)**

```bash
vendor/bin/phpunit
```

## 7. Dokumentasi Kode

### Java

**Repository.java**

![Repository Java] <img width="920" height="515" alt="01  repository-java" src="https://github.com/user-attachments/assets/5b405c15-d026-45d1-9758-cf122566e0dc" />


**RepositoryTest.java**

![Repository Test Java] <img width="943" height="495" alt="02 repositorytest-java" src="https://github.com/user-attachments/assets/c34fadce-1f4d-47fc-bc17-775a10a39d3d" />


### PHP

**KoleksiBuku.php**

![Koleksi Buku PHP] <img width="952" height="535" alt="03 koleksibuku php" src="https://github.com/user-attachments/assets/5182ff8f-6397-426b-bb1d-cdcf0fd7dd9a" />


**KoleksiBukuTest.php**

![Koleksi Buku Test PHP] <img width="858" height="524" alt="04 koleksibukutest-php" src="https://github.com/user-attachments/assets/edf35bf6-512d-4734-a73d-e440a37d61c0" />


## 8. Kesimpulan

Dari praktikum ini saya belajar bahwa Collections dan Generics membuat pengelolaan data lebih rapi dan bisa dipakai ulang untuk berbagai tipe. `Optional` di Java dan nullable type di PHP membantu menangani kasus data tidak ditemukan dengan lebih aman.

Saya juga memahami pentingnya unit testing. Dengan JUnit 5 dan PHPUnit, setiap fungsi dapat diperiksa secara otomatis sehingga perilaku program sesuai dengan yang diharapkan.
