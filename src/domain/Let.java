/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Comparator;

/**
 *
 * @author Neon
 */
public class Let implements Serializable {

    private Date datVremePolaska;
    private Date datVremeDolaska;
    private Avion avion;
    private Putanja putanja;
    private Pilot pilot;

    public Let() {
    }

    public Let(Date datVremePolaska, Date datVremeDolaska, Avion avion, Putanja putanja, Pilot pilot) {
        this.datVremePolaska = datVremePolaska;
        this.datVremeDolaska = datVremeDolaska;
        this.avion = avion;
        this.putanja = putanja;
        this.pilot = pilot;
    }

    public Date getDatVremePolaska() {
        return datVremePolaska;
    }

    public void setDatVremePolaska(Date datVremePolaska) {
        this.datVremePolaska = datVremePolaska;
    }

    public Date getDatVremeDolaska() {
        return datVremeDolaska;
    }

    public void setDatVremeDolaska(Date datVremeDolaska) {
        this.datVremeDolaska = datVremeDolaska;
    }

    public Putanja getPutanja() {
        return putanja;
    }

    public void setPutanja(Putanja putanja) {
        this.putanja = putanja;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "POLETANJE: " + datVremePolaska + ", SLETANJE: " + datVremeDolaska + ", KAPACITET AVIONA: " + avion.getKapacitet() + ", POLAZISTE: "
                + putanja.vratiPolaziste() + ", ODREDISTE: " + putanja.vratiOdrediste() + ", PUTANJA: " + putanja.toString() + " , PILOT: "
                + pilot.getImePrezime() + ", CENA: " + putanja.getCena() + "RSD, DISTANCA: " + putanja.getDistanca() + "km \n";
    }

    public static Comparator<Let> poredjenjePoCeni = new Comparator<Let>() {
        @Override
        public int compare(Let l1, Let l2) {
            int cena1 = l1.getPutanja().getCena();
            int cena2 = l2.getPutanja().getCena();
            
            return cena1-cena2;
        }
    };
    
    public static Comparator<Let> poredjenjePoDistanci = new Comparator<Let>() {
        @Override
        public int compare(Let l1, Let l2) {
            int dist1 = l1.getPutanja().getDistanca();
            int dist2 = l2.getPutanja().getDistanca();
            
            return dist1-dist2;            
        }
    };

}
