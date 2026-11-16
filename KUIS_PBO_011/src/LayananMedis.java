import java.io.Serializable;

public class LayananMedis implements Serializable {

    private String kode;
    private String nama;
    private int stok;

    public LayananMedis(
            String kode,
            String nama,
            int stok) {

        this.kode = kode;
        this.nama = nama;
        this.stok = stok;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return kode
                + " - "
                + nama
                + " - Stok: "
                + stok;
    }
}