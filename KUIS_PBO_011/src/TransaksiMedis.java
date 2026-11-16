public class TransaksiMedis
        extends Transaksi {

    private int harga;

    public TransaksiMedis(
            String idPasien,
            String kodeObjek,
            int qty,
            int harga) {

        super(
                idPasien,
                kodeObjek,
                qty
        );

        this.harga = harga;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public int hitungTotal() {

        return getQty() * harga;
    }

    @Override
    public String toString() {

        return "ID="
                + getIdPasien()
                + ", OBJ="
                + getKodeObjek()
                + ", QTY="
                + getQty()
                + ", TOTAL="
                + hitungTotal();
    }
}