/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1;

/**
 *
 * @author HP
 */
public class sepakbola extends Siswa {
     String  klub,position;
     int tokas, toabs, kas;
 
    //konstruktor
   
    void dataklub(String klub){
    this.klub = klub;
}
    void dataposition (String position){
        this.position = position;
    }
    String cetakklub (){
        return klub; 
    }
    String cetakposition(){
        return position;   
    }
    //@Override
    public String infoKlub(){
        this.klub = "MU";
        return super.InfoKlub();
    }
    @Override
    public String infoposisi(){
        this.position = "striker";
        return super.infoposisi();
    }
   
}
