package sistembengkel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemBengkel {

    private ArrayList<Pelanggan> daftarPelanggan;
    private ArrayList<Kendaraan> daftarKendaraan;
    private ArrayList<Transaksi> daftarTransaksi;

    public SistemBengkel() {
        daftarPelanggan = new ArrayList<>();
        daftarKendaraan = new ArrayList<>();
        daftarTransaksi = new ArrayList<>();
    }

    // =========================
    // TAMBAH DATA PELANGGAN
    // =========================

    public void tambahPelanggan(Pelanggan pelanggan) {
        daftarPelanggan.add(pelanggan);
    }

    // =========================
    // TAMBAH DATA KENDARAAN
    // =========================

    public void tambahKendaraan(Kendaraan kendaraan) {
        daftarKendaraan.add(kendaraan);
    }

    // =========================
    // TAMBAH TRANSAKSI
    // =========================

    public void tambahTransaksi(Transaksi transaksi) {

        daftarTransaksi.add(transaksi);

        tulisLog(
                "Transaksi baru: " + transaksi
        );
    }

    // =========================
    // MENAMPILKAN DATA
    // =========================

    public void tampilkanData() {

        System.out.println("\n=== DATA PELANGGAN ===");

        for (Pelanggan p : daftarPelanggan) {
            System.out.println(p);
        }

        System.out.println("\n=== DATA KENDARAAN ===");

        for (Kendaraan k : daftarKendaraan) {
            System.out.println(k);
        }

        System.out.println("\n=== DATA TRANSAKSI ===");

        for (Transaksi t : daftarTransaksi) {
            System.out.println(t);
        }
    }

    // =========================
    // FILE WRITER
    // =========================

    public void tulisLog(String aktivitas) {

        try {

            FileWriter writer =
                    new FileWriter("log_bengkel.txt", true);

            writer.write(aktivitas + "\n");

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Gagal menulis log: " + e.getMessage()
            );
        }
    }

    // =========================
    // SCANNER MEMBACA LOG
    // =========================

    public void bacaLog() {

        System.out.println("\n=== LOG AKTIVITAS BENGKEL ===");

        try {

            File file = new File("log_bengkel.txt");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String baris = scanner.nextLine();

                System.out.println(baris);
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "File log belum tersedia."
            );
        }
    }

    // =========================
    // SERIALIZATION
    // =========================

    public void simpanData() {

        try {

            FileOutputStream file =
                    new FileOutputStream("data_bengkel.dat");

            ObjectOutputStream output =
                    new ObjectOutputStream(file);

            output.writeObject(daftarPelanggan);
            output.writeObject(daftarKendaraan);
            output.writeObject(daftarTransaksi);

            output.close();

            System.out.println(
                    "\nData berhasil disimpan ke data_bengkel.dat"
            );

            tulisLog("Data bengkel berhasil disimpan.");

        } catch (IOException e) {

            System.out.println(
                    "Gagal menyimpan data: "
                    + e.getMessage()
            );
        }
    }

    // =========================
    // DESERIALIZATION
    // =========================

    public void muatData() {

        try {

            FileInputStream file =
                    new FileInputStream("data_bengkel.dat");

            ObjectInputStream input =
                    new ObjectInputStream(file);

            daftarPelanggan =
                    (ArrayList<Pelanggan>) input.readObject();

            daftarKendaraan =
                    (ArrayList<Kendaraan>) input.readObject();

            daftarTransaksi =
                    (ArrayList<Transaksi>) input.readObject();

            input.close();

            System.out.println(
                    "\nData berhasil dimuat dari data_bengkel.dat"
            );

            tulisLog("Data bengkel berhasil dimuat.");

        } catch (FileNotFoundException e) {

            System.out.println(
                    "File data_bengkel.dat belum tersedia."
            );

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Gagal memuat data: "
                    + e.getMessage()
            );
        }
    }
}