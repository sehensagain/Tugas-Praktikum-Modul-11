package sistembengkel;

import java.io.Serializable;

public class Pelanggan implements Serializable {

    private String idPelanggan;
    private String nama;
    private String noHp;

    public Pelanggan(String idPelanggan, String nama, String noHp) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.noHp = noHp;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    @Override
    public String toString() {
        return idPelanggan + " | " + nama + " | " + noHp;
    }
}