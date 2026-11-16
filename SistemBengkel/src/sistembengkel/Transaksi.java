package sistembengkel;

import java.io.Serializable;

public class Transaksi implements Serializable {

    private String idTransaksi;
    private String idPelanggan;
    private String nomorPlat;
    private String jenisServis;
    private int biaya;

    public Transaksi(String idTransaksi, String idPelanggan,
                     String nomorPlat, String jenisServis, int biaya) {

        this.idTransaksi = idTransaksi;
        this.idPelanggan = idPelanggan;
        this.nomorPlat = nomorPlat;
        this.jenisServis = jenisServis;
        this.biaya = biaya;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNomorPlat() {
        return nomorPlat;
    }

    public String getJenisServis() {
        return jenisServis;
    }

    public int getBiaya() {
        return biaya;
    }

    @Override
    public String toString() {
        return idTransaksi
                + " | Pelanggan: " + idPelanggan
                + " | Plat: " + nomorPlat
                + " | Servis: " + jenisServis
                + " | Biaya: Rp" + biaya;
    }
}