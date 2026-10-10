# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

## Informasi Praktikan

| Keterangan | Informasi |
| :--- | :--- |
| **Nama** | Muchamad Rava Alvriansyah |
| **NPM** | 4525210040 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 01 - Git & GitHub |
| **Tanggal** | 3 September 2026 |

---

## 🎯 Pokok Bahasan & Topik Pembelajaran

* **Instalasi Git:** Melakukan instalasi Version Control System (VCS) Git pada komputer lokal[cite: 1].
* **Konfigurasi SSH Key:** Membuat kunci SSH menggunakan perintah `ssh-keygen` untuk autentikasi yang aman[cite: 1].
* **Integrasi GitHub:** Mendaftarkan kunci SSH ke akun GitHub dan melakukan verifikasi koneksi jaringan[cite: 1].

---

## 📁 Berkas Laporan

| Berkas | Keterangan |
| :--- | :--- |
| `Muchamad Rava Alvriansyah.docx` | Laporan Lengkap Praktikum (Word) |

> **Catatan:** Pertemuan ini berfokus pada pengenalan serta konfigurasi dasar *tools* Version Control System (Git & GitHub), sehingga **tidak memiliki kode sumber Java/PHP**.

---

## 🚀 Ringkasan Langkah Kerja (Step by Step)

1. **Instalasi Git:** Mengunduh dan memasang aplikasi Git di komputer lokal[cite: 1].
2. **Membuka Git Bash:** Menjalankan antarmuka terminal Git Bash[cite: 1].
3. **Membuat Kunci SSH:** Menjalankan perintah `ssh-keygen -t ed25519 -C "email-anda"` untuk menghasilkan *public* dan *private key*[cite: 1].
4. **Menambahkan SSH Key ke GitHub:** Menyalin kunci publik dari direktori lokal ke menu pengaturan *SSH and GPG keys* di akun GitHub[cite: 1].
5. **Verifikasi Kunci SSH:** Memastikan kunci SSH telah tersimpan dan aktif di akun GitHub[cite: 1].
6. **Uji Koneksi:** Menjalankan perintah `ssh -T git@github.com` pada terminal untuk memastikan komputer lokal sudah terhubung secara aman dengan GitHub[cite: 1].

---

## 3. Kesimpulan

Melalui Praktikum 01 mengenai Git & GitHub ini, dapat disimpulkan bahwa:

1. **Pentingnya Version Control System (VCS):** Penggunaan Git sangat krusial dalam pengembangan perangkat lunak modern untuk melacak riwayat perubahan berkas, mempermudah manajemen versi kode, serta meminimalisir risiko kehilangan data program.
2. **Keamanan Otentikasi SSH:** Mengintegrasikan kunci SSH antara komputer lokal dan platform GitHub memberikan skema enkripsi yang lebih aman dan efisien dalam melakukan pertukaran data repositori tanpa memerlukan kredensial sandi secara berulang.
3. **Kesiapan Lingkungan Pengembangan:** Berhasilnya konfigurasi awal dan pengujian koneksi SSH menjadi landasan yang kokoh bagi praktikan untuk menjalankan alur kerja pengembangan seperti *commit*, *push*, dan kolaborasi proyek Pemrograman Berbasis Objek selanjutnya secara profesional.
