
package id.ac.univpancasila.perpustakaan.util;

import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.model.Peminjaman;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerpustakaanService {

    private final List<Buku> daftarKoleksi = new ArrayList<>();
    private final List<Anggota> daftarAnggota = new ArrayList<>();
    private final List<Peminjaman> daftarPeminjaman = new ArrayList<>();

    private int counterPeminjaman = 1;

    // =========================
    // DATA BUKU
    // =========================

    public void tambahKoleksi(Buku buku) {
        if (buku != null &&
            cariKoleksi(buku.getNomorInduk()) == null) {
            daftarKoleksi.add(buku);
        }
    }

    public Buku cariKoleksi(String nomorInduk) {
        if (nomorInduk == null) {
            return null;
        }

        for (Buku buku : daftarKoleksi) {
            if (buku.getNomorInduk().equalsIgnoreCase(nomorInduk)) {
                return buku;
            }
        }
        return null;
    }

    public List<Buku> getDaftarKoleksi() {
        return Collections.unmodifiableList(daftarKoleksi);
    }

    // =========================
    // DATA ANGGOTA
    // =========================

    public void daftarkanAnggota(Anggota anggota) {
        if (anggota != null &&
            cariAnggota(anggota.getNomorAnggota()) == null) {
            daftarAnggota.add(anggota);
        }
    }

    public Anggota cariAnggota(String nomorAnggota) {
        if (nomorAnggota == null) {
            return null;
        }

        for (Anggota anggota : daftarAnggota) {
            if (anggota.getNomorAnggota().equalsIgnoreCase(nomorAnggota)) {
                return anggota;
            }
        }
        return null;
    }

    public List<Anggota> getDaftarAnggota() {
        return Collections.unmodifiableList(daftarAnggota);
    }

    // =========================
    // PEMINJAMAN BUKU
    // =========================

    public Peminjaman pinjam(
            String nomorAnggota,
            String nomorInduk,
            LocalDate tanggalPinjam,
            int lamaHari) {

        if (tanggalPinjam == null) {
            throw new IllegalArgumentException(
                "Tanggal pinjam tidak boleh kosong.");
        }

        if (lamaHari <= 0) {
            throw new IllegalArgumentException(
                "Lama peminjaman harus lebih dari 0 hari.");
        }

        Anggota anggota = cariAnggota(nomorAnggota);

        if (anggota == null) {
            throw new IllegalArgumentException(
                "Anggota tidak ditemukan: " + nomorAnggota);
        }

        Buku buku = cariKoleksi(nomorInduk);

        if (buku == null) {
            throw new IllegalArgumentException(
                "Buku tidak ditemukan: " + nomorInduk);
        }

        if (!buku.tersedia()) {
            throw new IllegalStateException(
                "Stok buku habis: " + buku.getJudul());
        }

        // Maksimal 3 peminjaman aktif
        long pinjamanAktif = daftarPeminjaman.stream()
            .filter(p ->
                p.getAnggota().getNomorAnggota()
                    .equalsIgnoreCase(nomorAnggota)
                && !p.sudahKembali())
            .count();

        if (pinjamanAktif >= 3) {
            throw new IllegalStateException(
                "Maksimal 3 buku boleh dipinjam.");
        }

        String idPeminjaman =
            String.format("PJ-%03d", counterPeminjaman);

        Peminjaman peminjaman = new Peminjaman(
            idPeminjaman,
            anggota,
            buku,
            tanggalPinjam,
            lamaHari
        );

        buku.kurangiStok();
        daftarPeminjaman.add(peminjaman);
        counterPeminjaman++;

        return peminjaman;
    }

    // =========================
    // PENGEMBALIAN BUKU
    // =========================

    public long kembalikan(
            String idPeminjaman,
            LocalDate tanggalKembali) {

        if (tanggalKembali == null) {
            throw new IllegalArgumentException(
                "Tanggal kembali tidak boleh kosong.");
        }

        Peminjaman peminjaman = null;

        for (Peminjaman p : daftarPeminjaman) {
            if (p.getIdPeminjaman()
                    .equalsIgnoreCase(idPeminjaman)) {
                peminjaman = p;
                break;
            }
        }

        if (peminjaman == null) {
            throw new IllegalArgumentException(
                "Data peminjaman tidak ditemukan.");
        }

        if (peminjaman.sudahKembali()) {
            throw new IllegalStateException(
                "Buku sudah dikembalikan.");
        }

        if (tanggalKembali.isBefore(
                peminjaman.getTanggalJatuhTempo()
                    .minusDays(
                        ChronoUnit.DAYS.between(
                            peminjaman.getTanggalJatuhTempo(),
                            peminjaman.getTanggalJatuhTempo()
                        )
                    ))) {
            // Validasi tanggal dilakukan oleh model Peminjaman
            // apabila diperlukan.
        }

        peminjaman.setTanggalKembali(tanggalKembali);

        long denda = hitungDenda(peminjaman);

        peminjaman.getBuku().tambahStok();

        return denda;
    }

    // =========================
    // PERHITUNGAN DENDA
    // =========================

    public long hitungDenda(Peminjaman peminjaman) {

        if (peminjaman == null ||
            peminjaman.getTanggalKembali() == null) {
            return 0;
        }

        long terlambatHari = ChronoUnit.DAYS.between(
            peminjaman.getTanggalJatuhTempo(),
            peminjaman.getTanggalKembali()
        );

        if (terlambatHari <= 0) {
            return 0;
        }

        String jenis = peminjaman.getAnggota()
            .getJenisKeanggotaan();

        if (jenis == null) {
            return 0;
        }

        jenis = jenis.toLowerCase();

        long tarifPerHari;

        if (jenis.contains("mahasiswa")) {
            tarifPerHari = 1000;
        } else if (jenis.contains("dosen")) {
            tarifPerHari = 500;
        } else {
            tarifPerHari = 0;
        }

        return terlambatHari * tarifPerHari;
    }

    // =========================
    // DAFTAR PEMINJAMAN
    // =========================

    public List<Peminjaman> getDaftarPeminjaman() {
        return Collections.unmodifiableList(daftarPeminjaman);
    }
}
