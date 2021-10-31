/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Neon
 */
public class Rezervacija {
    private Let let;
    private Putnik putnik;
    private int brojOsoba;

    public Rezervacija() {
    }

    public Rezervacija(Let let, Putnik putnik, int brojOsoba) {
        this.let = let;
        this.putnik = putnik;
        this.brojOsoba = brojOsoba;
    }

    public int getBrojOsoba() {
        return brojOsoba;
    }

    public void setBrojOsoba(int brojOsoba) {
        this.brojOsoba = brojOsoba;
    }

    public Let getLet() {
        return let;
    }

    public void setLet(Let let) {
        this.let = let;
    }

    public Putnik getPutnik() {
        return putnik;
    }

    public void setPutnik(Putnik putnik) {
        this.putnik = putnik;
    }

    @Override
    public String toString() {
        return "PREGLED REZERVACIJE \n " + let.toString() + "Putnik: " + putnik.toString() + "\n" + "Broj osoba: " + brojOsoba;
    }
    
    
}
