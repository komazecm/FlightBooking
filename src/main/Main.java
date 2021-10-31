/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Avion;
import domain.Grad;
import domain.Let;
import domain.Pilot;
import domain.Putanja;
import domain.Putnik;
import domain.Rezervacija;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Let> letovi = new ArrayList<>();
    private static String filePath = "C:\\Users\\Neon\\Desktop\\Testiranje\\FlightBooking\\files\\letovi.txt";

    public static void main(String[] args) {

//        Grad g1 = new Grad("Belgrade", "Serbia");
//        Grad g2 = new Grad("Paris", "France");
//        Grad g3 = new Grad("Tokyo", "Japan");
//        ArrayList<Grad> putanja = new ArrayList<>();
//        putanja.add(g1);
//        putanja.add(g2);
//        putanja.add(g3);
//        Putanja p = new Putanja(putanja, 80000, 12000);
//
//        System.out.println(p.toString());
//
//        System.out.println("Polaziste je: " + p.vratiPolaziste());
//        System.out.println("Odrediste je: " + p.vratiOdrediste());
//
//        //unos letova koji postoje:
//        Avion avion = new Avion(150);
//        Pilot pilot = new Pilot("Milan Maric");
//        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
//        String pol = "12.11.2021 14:15";
//        String dol = "12.11.2021 19:50";
//        Let letBGPariz = null;
//        try {
//            letBGPariz = new Let(sdf1.parse(pol), sdf1.parse(dol), avion, p, pilot);
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        letovi.add(letBGPariz);
//
//        Grad g4 = new Grad("Sarajevo", "BiH");
//        Grad g5 = new Grad("New York", "USA");
//        ArrayList<Grad> putanja2 = new ArrayList<>();
//        putanja2.add(g4);
//        putanja2.add(g1);
//        putanja2.add(g5);
//        Putanja p2 = new Putanja(putanja2, 80500, 7450);
//        Avion avion2 = new Avion(150);
//        Pilot pilot2 = new Pilot("Marko Markovic");
//        pol = "31.10.2021 10:15";
//        dol = "31.10.2021 20:50";
//        Let let2 = null;
//        try {
//            let2 = new Let(sdf1.parse(pol), sdf1.parse(dol), avion2, p2, pilot2);
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        letovi.add(let2);
//
//
//        ArrayList<Grad> putanja3 = new ArrayList<>();
//        putanja3.add(g4);
//        putanja3.add(g2);
//        putanja3.add(g5);
//        Putanja p3 = new Putanja(putanja3, 85000, 7400);
//        Avion avion3 = new Avion(174);
//        Pilot pilot3 = new Pilot("Dusan Kovic");
//        pol = "31.10.2021 11:15";
//        dol = "31.10.2021 22:35";
//        Let let3 = null;
//        try {
//            let3 = new Let(sdf1.parse(pol), sdf1.parse(dol), avion3, p3, pilot3);
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        letovi.add(let3);
//        
//        //sarajevo bg paris new york
//        ArrayList<Grad> putanja4 = new ArrayList<>();
//        putanja4.add(g4);
//        putanja4.add(g1);
//        putanja4.add(g2);
//        putanja4.add(g5);
//        Putanja p4 = new Putanja(putanja4, 95000, 8400);
//        Avion avion4 = new Avion(175);
//        Pilot pilot4 = new Pilot("Marko Maric");
//        pol = "22.12.2021 09:15";
//        dol = "22.12.2021 22:15";
//        Let let4 = null;
//        try {
//            let4 = new Let(sdf1.parse(pol), sdf1.parse(dol), avion4, p4, pilot4);
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        letovi.add(let4);
//        
//        try {
//            //write object to file:
//            FileOutputStream out = new FileOutputStream(filePath);
//            ObjectOutputStream objectOut = new ObjectOutputStream(out);
//            objectOut.writeObject(letovi);
//            objectOut.close();
//            System.out.println("The Object was succesfully written to a file");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

        
        //read object from file:
        letovi = ucitajLetove();

        prikaziSveLetove();

        
        boolean t = true;
        while (t) {
            t = registracijaPutnika();
        }

    }

    private static void filtriraj(String param, Date polazak, Date dolazak, String pocetnaDestinacija, String krajnjaDestinacija, Putnik putnik) {
        ArrayList<Let> filtriranaLista = new ArrayList<>();

        for (Let let : letovi) {
            if (polazak == null && dolazak == null && pocetnaDestinacija.equals(let.getPutanja().vratiPolaziste().toString())
                    && krajnjaDestinacija.equals(let.getPutanja().vratiOdrediste().toString())) {
                filtriranaLista.add(let);
            }
            if (polazak != null && polazak.equals(let.getDatVremePolaska()) && dolazak == null && pocetnaDestinacija.equals(let.getPutanja().vratiPolaziste().toString())
                    && krajnjaDestinacija.equals(let.getPutanja().vratiOdrediste().toString())) {
                filtriranaLista.add(let);
            }
            if (polazak != null && polazak.equals(let.getDatVremePolaska()) && dolazak != null
                    && dolazak.equals(let.getDatVremeDolaska()) && pocetnaDestinacija.equals(let.getPutanja().vratiPolaziste().toString())
                    && krajnjaDestinacija.equals(let.getPutanja().vratiOdrediste().toString())) {
                filtriranaLista.add(let);
            }
            if (polazak == null && dolazak != null && dolazak.equals(let.getDatVremeDolaska()) && pocetnaDestinacija.equals(let.getPutanja().vratiPolaziste().toString())
                    && krajnjaDestinacija.equals(let.getPutanja().vratiOdrediste().toString())) {
                filtriranaLista.add(let);
            }
        }

        //sortiranje
        if (param.equals("a")) {
            Collections.sort(filtriranaLista, Let.poredjenjePoCeni);
        } else {
            Collections.sort(filtriranaLista, Let.poredjenjePoDistanci);
        }

        if (filtriranaLista.size() != 0) {
            System.out.println("LETOVI:");
            for (Let let : filtriranaLista) {
                System.out.println(let);
            }

            //vrsenje rezervacije
            Scanner in = new Scanner(System.in);
            System.out.println("Izaberite let (1 do " + filtriranaLista.size() + ") za koji pravite rezervaciju: ");
            try {
                int i = in.nextInt();
                if (i < 1 || i > filtriranaLista.size()) {
                    System.out.println("Broj mora biti od 1 do " + filtriranaLista.size());
                    return;
                }

                System.out.println("Unesite broj osoba za rezervaciju:");
                int brOsoba = in.nextInt();

                Rezervacija rez = new Rezervacija(filtriranaLista.get(i - 1), putnik, brOsoba);

                //pregled rezervacije
                System.out.println(rez.toString());
            } catch (Exception ex) {
                System.out.println("Broj mora biti od 1 do " + filtriranaLista.size());
            }
        } else {
            System.out.println("Nema letova za unesene parametre");
        }
    }

    private static ArrayList<Let> ucitajLetove() {
        try {
            FileInputStream in = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(in);
            letovi = new ArrayList<>();
            letovi = (ArrayList<Let>) objectIn.readObject();

            objectIn.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letovi;
    }

    private static boolean registracijaPutnika() {
        //Unos putnika:
        Scanner in = new Scanner(System.in);
        System.out.println("Unesite ime putnika: ");
        String ime = in.nextLine();
        System.out.println("Unesite prezime putnika: ");
        String prezime = in.nextLine();

        if (!ime.equals("") && !prezime.equals("")) {
            Putnik putnik = new Putnik(ime, prezime);

            //unos parametara za filtriranje:
            System.out.println("Unesite datum i vreme polaska. (Format: dd.MM.yyyy hh:mm) ");
            String datVremPolaska = in.nextLine();
            System.out.println("Unesite datum i vreme dolaska. (Format: dd.MM.yyyy hh:mm) ");
            String datVremDolaska = in.nextLine();
            System.out.println("Unesite pocetnu destinaciju.");
            String pocetnaDestinacija = in.nextLine();
            System.out.println("Unesite krajnju destinaciju.");
            String krajnjaDestinacija = in.nextLine();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                Date polazak = null;
                Date dolazak = null;
                if (!datVremPolaska.equals("")) {
                    polazak = sdf.parse(datVremPolaska);
                    System.out.println("POLAZAK::" + polazak);
                }
                if (!datVremDolaska.equals("")) {
                    dolazak = sdf.parse(datVremDolaska);
                }

                if (pocetnaDestinacija.equals("") || krajnjaDestinacija.equals("")) {
                    System.out.println("Morate uneti pocetnu i krajnju destinaciju.");
                    return true;
                }

                System.out.println("Sortiranje po:\n a)najjeftiniji let \n b)najkraci let? (a ili b)");
                String param = in.nextLine();
                if (!param.equals("a") && !param.equals("b")) {
                    System.out.println("Unesite a ili b");
                    return true;
                }

                filtriraj(param, polazak, dolazak, pocetnaDestinacija, krajnjaDestinacija, putnik);
                return false;
            } catch (ParseException ex) {
                System.out.println("Neodgovarajuci format datuma.");
                return true;
            }

        } else {
            System.out.println("Morate uneti ime i prezime");
            return true;
        }
    }

    private static void prikaziSveLetove() {
        System.out.println("Svi letovi:\n");
        for (Let let : letovi) {
            System.out.println(let);
        }
    }

}
