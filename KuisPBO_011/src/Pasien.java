import java.io.Serializable;

public class Pasien implements Serializable {

    private String idPasien;
    private String nama;

    public Pasien(String idPasien, String nama) {
        this.idPasien = idPasien;
        this.nama = nama;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return idPasien + " - " + nama;
    }
}