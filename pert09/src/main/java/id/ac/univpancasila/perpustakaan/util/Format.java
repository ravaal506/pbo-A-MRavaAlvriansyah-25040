package id.ac.univpancasila.perpustakaan.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Format {

    private static final Locale LOCAL_ID = Locale.forLanguageTag("id-ID");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Format() {
        // Mencegah pembuatan objek
    }

    public static String formatRupiah(long nominal) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(LOCAL_ID);
        return nf.format(nominal);
    }

    public static String formatTanggal(LocalDate tanggal) {
        if (tanggal == null) {
            return "-";
        }
        return tanggal.format(DATE_FORMATTER);
    }
}