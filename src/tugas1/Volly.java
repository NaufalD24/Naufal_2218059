/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1;

/**
 *
 * @author HP
 */
public class Volly implements SiswaInterface{
    String tim, position;
    String  nama;
    String absen;
    String Kelas;
    
    public Volly(){
        this.tim = "MU";
        this.position = "spiker";
        this.nama = "";
        this.Kelas = "";
        this.absen = "";
    }
    void namaPemain(String Nama){
        this.nama = Nama;
    }
    void kelasSiswa (String Kelas){
        this.Kelas = Kelas;
    }
    void absenSiswa(String absen){
        this.absen = absen;
    }
    void posisimain(String position){
        this.position = position;
    }
    void timmain (String tim){
        this.tim = tim;
    }
    
    String cetakNama(){
        return nama;
    }
    String cetakKelas(){
        return Kelas;
    }
    String cetakAbsen(){
        return absen;
    }
    String cetakTim(){
        return tim;
    }
    String cetakPosisi(){
        return position;
    }
    
    
   @Override
    public String Volly() {
        return "Selamat datang"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
