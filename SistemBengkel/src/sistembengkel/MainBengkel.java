package sistembengkel;

import java.util.Scanner;

public class MainBengkel {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        SistemBengkel bengkel = new SistemBengkel();

        // =========================
        // DATA AWAL
        // =========================

        bengkel.tambahPelanggan(
                new Pelanggan("P001", "Andi", "081234567890")
        );

        bengkel.tambahPelanggan(
                new Pelanggan("P002", "Budi", "082345678901")
        );

        bengkel.tambahPelanggan(
                new Pelanggan("P003", "Citra", "083456789012")
        );

        bengkel.tambahKendaraan(
                new Kendaraan("BE 1234 AB", "Honda", "Beat")
        );

        bengkel.tambahKendaraan(
                new Kendaraan("BE 5678 CD", "Yamaha", "NMAX")
        );

        bengkel.tambahKendaraan(
                new Kendaraan("BE 9012 EF", "Toyota", "Avanza")
        );

        int pilihan;

        do {

            System.out.println("\n==============================");
            System.out.println("      SISTEM BENGKEL");
            System.out.println("==============================");
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Simpan Data");
            System.out.println("4. Muat Data");
            System.out.println("5. Tampilkan Log");
            System.out.println("6. Keluar");
            System.out.println("==============================");

            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:

                    System.out.println("\n=== TAMBAH TRANSAKSI ===");

                    System.out.print("ID Transaksi: ");
                    String idTransaksi =
                            input.nextLine();

                    System.out.print("ID Pelanggan: ");
                    String idPelanggan =
                            input.nextLine();

                    System.out.print("Nomor Plat: ");
                    String nomorPlat =
                            input.nextLine();

                    System.out.print("Jenis Servis: ");
                    String jenisServis =
                            input.nextLine();

                    System.out.print("Biaya: ");
                    int biaya =
                            input.nextInt();
                    input.nextLine();

                    Transaksi transaksi =
                            new Transaksi(
                                    idTransaksi,
                                    idPelanggan,
                                    nomorPlat,
                                    jenisServis,
                                    biaya
                            );

                    bengkel.tambahTransaksi(transaksi);

                    System.out.println(
                            "Transaksi berhasil ditambahkan."
                    );

                    break;

                case 2:

                    bengkel.tampilkanData();

                    break;

                case 3:

                    bengkel.simpanData();

                    break;

                case 4:

                    bengkel.muatData();

                    break;

                case 5:

                    bengkel.bacaLog();

                    break;

                case 6:

                    System.out.println(
                            "\nProgram Sistem Bengkel selesai."
                    );

                    break;

                default:

                    System.out.println(
                            "Pilihan tidak tersedia."
                    );
            }

        } while (pilihan != 6);

        input.close();
    }
}