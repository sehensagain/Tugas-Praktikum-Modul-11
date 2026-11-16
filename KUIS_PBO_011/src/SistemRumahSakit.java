import java.io.*;
import java.util.ArrayList;

public class SistemRumahSakit
        implements Serializable {

    private String namaRumahSakit;

    private ArrayList<Pasien> daftarPasien;
    private ArrayList<LayananMedis> daftarLayanan;
    private ArrayList<Transaksi> daftarTransaksi;

    public SistemRumahSakit(
            String namaRumahSakit) {

        this.namaRumahSakit =
                namaRumahSakit;

        daftarPasien =
                new ArrayList<>();

        daftarLayanan =
                new ArrayList<>();

        daftarTransaksi =
                new ArrayList<>();
    }

    // =========================
    // TAMBAH PASIEN
    // =========================

    public void tambahPasien(
            String id,
            String nama) {

        daftarPasien.add(
                new Pasien(id, nama)
        );
    }

    // =========================
    // TAMBAH LAYANAN MEDIS
    // =========================

    public void tambahLayananMedis(
            String kode,
            String nama,
            int stok) {

        daftarLayanan.add(
                new LayananMedis(
                        kode,
                        nama,
                        stok
                )
        );
    }

    // =========================
    // BACA FILE TRANSAKSI
    // =========================

    public void jalankanTransaksiDariFile(
            String namaFile)
            throws Exception {

        File file =
                new File(namaFile);

        if (!file.exists()) {

            throw new FileNotFoundException(
                    "File "
                    + namaFile
                    + " tidak ditemukan."
            );
        }

        BufferedReader br =
                new BufferedReader(
                        new FileReader(file)
                );

        String baris;

        while ((baris = br.readLine())
                != null) {

            if (baris.trim().isEmpty()) {
                continue;
            }

            /*
             * DIGIT KEDUA = 1
             *
             * Format:
             *
             * ID-ACT-01/KD-OBJ-A/JML-3/
             * ID-ACT-02/KD-OBJ-C/JML-2/
             * ID-ACT-01/KD-OBJ-B/JML-5/
             * ...
             *
             * Karena "/" adalah delimiter,
             * kita pecah seluruh isi berdasarkan "/".
             */

            String[] data =
                    baris.split("/");

            /*
             * Setiap transaksi terdiri
             * dari 3 bagian:
             *
             * ID
             * KD
             * JML
             */

            for (int i = 0;
                    i < data.length;
                    i += 3) {

                try {

                    if (i + 2 >= data.length) {

                        throw new TransaksiTidakValidException(
                                "Format transaksi tidak lengkap."
                        );
                    }

                    String idPasien =
                            data[i]
                            .replace("ID-", "")
                            .trim();

                    String kodeObjek =
                            data[i + 1]
                            .replace("KD-", "")
                            .trim();

                    String qtyText =
                            data[i + 2]
                            .replace("JML-", "")
                            .trim();

                    if (idPasien.isEmpty()) {

                        throw new TransaksiTidakValidException(
                                "ID pasien kosong."
                        );
                    }

                    if (kodeObjek.isEmpty()) {

                        throw new TransaksiTidakValidException(
                                "Kode objek kosong."
                        );
                    }

                    int qty =
                            Integer.parseInt(
                                    qtyText
                            );

                    if (qty <= 0) {

                        throw new TransaksiTidakValidException(
                                "QTY harus lebih dari 0."
                        );
                    }

                    // =========================
                    // CEK PASIEN
                    // =========================

                    Pasien pasien =
                            cariPasien(idPasien);

                    if (pasien == null) {

                        throw new TransaksiTidakValidException(
                                "Pasien "
                                + idPasien
                                + " tidak ditemukan."
                        );
                    }

                    // =========================
                    // CEK LAYANAN
                    // =========================

                    LayananMedis layanan =
                            cariLayanan(kodeObjek);

                    if (layanan == null) {

                        throw new TransaksiTidakValidException(
                                "Layanan "
                                + kodeObjek
                                + " tidak ditemukan."
                        );
                    }

                    // =========================
                    // TENTUKAN HARGA
                    // =========================

                    int harga =
                            tentukanHarga(
                                    kodeObjek
                            );

                    // =========================
                    // BUAT OBJECT TRANSAKSI
                    // =========================

                    Transaksi transaksi =
                            new TransaksiMedis(
                                    idPasien,
                                    kodeObjek,
                                    qty,
                                    harga
                            );

                    daftarTransaksi.add(
                            transaksi
                    );

                    System.out.println(
                            "[OK] Transaksi berhasil: "
                            + transaksi
                    );

                } catch (
                        NumberFormatException e) {

                    System.out.println(
                            "[DATA ERROR] QTY harus berupa angka."
                    );

                } catch (
                        TransaksiTidakValidException e) {

                    System.out.println(
                            "[DATA ERROR] "
                            + e.getMessage()
                    );
                }
            }
        }

        br.close();

        System.out.println(
                "Pembacaan file transaksi selesai."
        );
    }

    // =========================
    // CARI PASIEN
    // =========================

    private Pasien cariPasien(
            String idPasien) {

        for (Pasien p :
                daftarPasien) {

            if (p.getIdPasien()
                    .equals(idPasien)) {

                return p;
            }
        }

        return null;
    }

    // =========================
    // CARI LAYANAN
    // =========================

    private LayananMedis cariLayanan(
            String kode) {

        for (LayananMedis l :
                daftarLayanan) {

            if (l.getKode()
                    .equals(kode)) {

                return l;
            }
        }

        return null;
    }

    // =========================
    // TENTUKAN HARGA
    // =========================

    private int tentukanHarga(
            String kode) {

        if (kode.equals("OBJ-A")) {

            return 1000000;
        }

        if (kode.equals("OBJ-B")) {

            return 300000;
        }

        if (kode.equals("OBJ-C")) {

            return 150000;
        }

        return 0;
    }

    // =========================
    // SIMPAN LAPORAN AKHIR
    // =========================

    public void simpanLaporanAkhir() {

        // ==================================
        // SERIALIZATION
        // DIGIT KETIGA = 1
        // ==================================

        try {

            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    "storage.db"
                            )
                    );

            output.writeObject(this);

            output.close();

            System.out.println(
                    "Data berhasil disimpan ke storage.db"
            );

        } catch (IOException e) {

            System.out.println(
                    "Gagal menyimpan storage.db: "
                    + e.getMessage()
            );
        }

        // ==================================
        // FILE LAPORAN
        // DIGIT KETIGA = 1
        //
        // Laporan Transaksi Besar
        // HANYA QTY >= 5
        // ==================================

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    "laporan.txt"
                            )
                    );

            writer.println(
                    "LAPORAN TRANSAKSI BESAR"
            );

            writer.println(
                    "Rumah Sakit: "
                    + namaRumahSakit
            );

            writer.println(
                    "================================"
            );

            int jumlahTransaksi =
                    0;

            for (Transaksi t :
                    daftarTransaksi) {

                /*
                 * DIGIT KETIGA = 1
                 *
                 * Hanya transaksi
                 * dengan QTY >= 5
                 * yang ditulis.
                 */

                if (t.getQty() >= 5) {

                    writer.println(
                            t
                    );

                    jumlahTransaksi++;
                }
            }

            writer.println(
                    "================================"
            );

            writer.println(
                    "Jumlah Transaksi Besar: "
                    + jumlahTransaksi
            );

            writer.close();

            System.out.println(
                    "Laporan berhasil disimpan ke laporan.txt"
            );

        } catch (IOException e) {

            System.out.println(
                    "Gagal membuat laporan: "
                    + e.getMessage()
            );
        }
    }
}