import java.io.Serializable;

public abstract class Transaksi
        implements Serializable {

    private String idPasien;
    private String kodeObjek;
    private int qty;

    public Transaksi(
            String idPasien,
            String kodeObjek,
            int qty) {

        this.idPasien = idPasien;
        this.kodeObjek = kodeObjek;
        this.qty = qty;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public String getKodeObjek() {
        return kodeObjek;
    }

    public int getQty() {
        return qty;
    }

    public abstract int hitungTotal();

    @Override
    public String toString() {

        return "ID="
                + idPasien
                + ", OBJ="
                + kodeObjek
                + ", QTY="
                + qty
                + ", TOTAL="
                + hitungTotal();
    }
}