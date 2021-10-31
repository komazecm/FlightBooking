/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Neon
 */
public class Putanja implements Serializable {
    private ArrayList<Grad> listaGradova;
    private int cena;
    private int distanca;
    
    public Putanja() {
    }

    public Putanja(ArrayList<Grad> listaGradova, int cena, int distanca) {
        this.listaGradova = listaGradova;
        this.cena = cena;
        this.distanca = distanca;
    }
    public ArrayList<Grad> getListaGradova() {
        return listaGradova;
    }

    public void setListaGradova(ArrayList<Grad> listaGradova) {
        this.listaGradova = listaGradova;
    }
    
    @Override
    public String toString() {
        String niz = "";
        for (Grad grad : listaGradova) {
            niz+=grad.toString() + " - ";
        }
        return niz;
    }
    
    public Grad vratiOdrediste(){
        return listaGradova.get(listaGradova.size()-1);
    }
    
    public Grad vratiPolaziste(){
        return listaGradova.get(0);
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getDistanca() {
        return distanca;
    }

    public void setDistanca(int distanca) {
        this.distanca = distanca;
    }
}
