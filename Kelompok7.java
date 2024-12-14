import java.util.Scanner;

//FIX
public class Kelompok7{
    static Scanner sc = new Scanner(System.in);
    static String menuKafe[][] = {{"Kopi Hitam", "15000"}, {"Latte", "22000"}, {"Teh Tarik", "12000"}, {"Mie Goreng", "18000"}};
    static String pesanan[][][] = new String[50][50][2];
    static String statusPembayaran[][] = new String[50][2];
    static String namaPelanggan[] = new String[50];
    static int nomorMeja[] = new int[50], jumlah[][] = new int[50][50], totalHarga[] = new int[50];
    static int hargaSementara[][][] = new int[50][50][1];
    static int i = 0, j = 0;

    //TAMPIL MENU
    static void tampilMenu(){
        System.out.println("\n===== MENU KAFE KELOMPOK 7 =====");
        for (int i = 0; i < menuKafe.length; i++) {
            System.out.println((i+1) + ". " + menuKafe[i][0] + " - Rp " + menuKafe[i][1]);
        }
        tambahPesanan();
    }

    //TAMBAH PESANAN DAN VALIDASI
    static void tambahPesanan() {
        sc.nextLine(); 
        System.out.print("\nMasukkan Nama Pelanggan: ");
        namaPelanggan[i] = sc.nextLine();

        System.out.print("Masukkan Nomor Meja: ");
        nomorMeja[i] = sc.nextInt();

        int j = 0; 
        while (true) {
            System.out.print("\nMasukkan Nama Menu (atau 'selesai' untuk keluar): ");
            sc.nextLine(); 
            String namaMenu = sc.nextLine();

            if (namaMenu.equalsIgnoreCase("selesai")){
                System.out.println("\nPesanan diterima.");
                break;
            }

            int indexMenu = cariMenu(namaMenu);
            if (indexMenu == -1) {
                System.out.println("Menu tidak ditemukan. Coba lagi.");
                continue;
            }

            System.out.print("Masukkan Jumlah Item: ");
            int jumlahItem = sc.nextInt();
            if (jumlahItem <= 0) {
                System.out.println("Jumlah item harus lebih dari 0.");
                continue;
            }

            pesanan[i][j][0] = namaMenu;
            jumlah[i][j] = jumlahItem;

            int hargaMenu = Integer.parseInt(menuKafe[indexMenu][1]);
            hargaSementara[i][j][0] = hargaMenu * jumlahItem;
            totalHarga[i] += hargaSementara[i][j][0];

            j++;
            System.out.println("Pesanan berhasil ditambahkan.");
        }

        i++; 
    }

    static int cariMenu(String namaMenu) {
        for (int i = 0; i < menuKafe.length; i++) {
            if (menuKafe[i][0].equalsIgnoreCase(namaMenu)) {
                return i;
            }
        }
        return -1; 
    }

    //TAMPILKAN DAFTAR PESANAN
    static void tampilPesanan(){
        System.out.println("\n===== DAFTAR PESANAN =====");
        
        for (int i = 0; i < namaPelanggan.length; i++) {
            if (namaPelanggan[i] != null){
                System.out.println("Nama Pelanggan : " + namaPelanggan[i]);
                System.out.println("Nomor Meja     : " + nomorMeja[i]);
                System.out.println("Detail Pesanan :");

                for (int j = 0; j < pesanan[i].length; j++) {
                    if (pesanan[i][j][0] != null) { 
                        System.out.println("- " + pesanan[i][j][0] + " x " + jumlah[i][j] + " = Rp " + hargaSementara[i][j][0]);
                    }
                }

            System.out.println("Total Harga Pesanan : Rp " + totalHarga[i]);
            }
        }
        return;
    }

    //PEMBAYARAN PESANAN
    static void pembayaranPesanan() {
        System.out.print("\nMasukkan Nama Pelanggan: ");
        sc.nextLine(); 
        String nama = sc.nextLine();
    
        boolean ditemukan = false;
        for (int i = 0; i < namaPelanggan.length; i++) {
            if (namaPelanggan[i] != null && namaPelanggan[i].equalsIgnoreCase(nama)) {
                ditemukan = true;
                System.out.println("\n===== PEMBAYARAN =====");
                System.out.println("Nama Pelanggan : " + namaPelanggan[i]);
                System.out.println("Nomor Meja     : " + nomorMeja[i]);
                System.out.println("Detail Pesanan :");
    
                for (int j = 0; j < pesanan[i].length; j++) {
                    if (pesanan[i][j][0] != null) {
                        System.out.println("- " + pesanan[i][j][0] + " x " + jumlah[i][j] + " = Rp " + hargaSementara[i][j][0]);
                    }
                }
    
                System.out.println("Total Harga Pesanan : Rp " + totalHarga[i]);
                System.out.print("Masukkan jumlah uang yang dibayarkan: ");
                int jumlahUang = sc.nextInt();
    
                if (jumlahUang >= totalHarga[i]) {
                    int kembalian = jumlahUang - totalHarga[i];
                    System.out.println("Pembayaran berhasil! Kembalian: Rp " + kembalian);
                } else {
                    System.out.println("Uang yang dibayarkan kurang. Pembayaran gagal.");
                }
                break;
            }
        }
    
        if (!ditemukan) {
            System.out.println("\nPelanggan dengan nama " + nama + " tidak ditemukan.");
        }
    }
    

    public static void main(String[] args) {
        int menu;
        
        while (true){
            System.out.println("\n====== MENU UTAMA ======");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Pembayaran dan Cetak Struk");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu : ");
            menu = sc.nextInt();
    
            switch (menu){
                case 1:
                    tampilMenu();
                    break;
                case 2:
                    tampilPesanan();
                    break;
                case 3:
                    pembayaranPesanan();
                    break;
                case 4:
                    System.out.println("\nTerima kasih telah berbelanja di kafe kami!");
                    return;
            }
        }
    }
}