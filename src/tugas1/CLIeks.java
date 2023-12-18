/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1;



/**
 *
 * @author HP
 */
public class CLIeks {
     public static void main(String[] args) {
        Siswa sw = new Siswa();
        
        sw.dataAbsen("2218059");
        sw.dataNama("Naufal Dhiaurrafif");
        sw.dataJenisKelamin("laki laki");
        sw.datakelas("12");
        sw.dataEkskul("kokonat");
        sw.dataAlamat("Banyuwangi");
        System.out.println("Kartu Tanda Mahasiswa ITN Malang");
        System.out.println("--------------------------------");
        System.out.println("NIM                   :"+ sw.cetakAbsen());
        System.out.println("Nama                  :"+ sw.cetakNama());
        System.out.println("Ektrakurikuler        :"+ sw.cetakekskul());
        System.out.println("Kelas                 :"+ sw.cetakKelas());
        System.out.println("Alamat                :"+ sw.cetakAlamat());
    }
    
    
}
