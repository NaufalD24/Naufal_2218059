/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1;

/**
 *
 * @author HP
 */
public class esport implements SiswaInterface {
     String  tim,rank,game;
   
    //konstruktor
    
    void datatim (String tim){
    this.tim = tim;
    }
    
    void datarank (String rank){
        this.rank = rank;
    }
    void datagame (String game){
        this.game = game;
    }    
    String rank (){
        return rank;
    }
    
    String tim (){
        return tim;

    }
    String game (){
        return game;
}

    @Override
    public String Volly() {
        return "Selamat datang"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
