package sistembengkel;

import java.io.Serializable;

public class Kendaraan implements Serializable {

    private String nomorPlat;
    private String merk;
    private String tipe;

    public Kendaraan(String nomorPlat, String merk, String tipe) {
        this.nomorPlat = nomorPlat;
        this.merk = merk;
        this.tipe = tipe;
    }

    public String getNomorPlat() {
        return nomorPlat;
    }

    public String getMerk() {
        return merk;
    }

    public String getTipe() {
        return tipe;
    }

    @Override
    public String toString() {
        return nomorPlat + " | " + merk + " | " + tipe;
    }
}