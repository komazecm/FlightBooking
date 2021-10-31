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

        if (filtriranaLista.size() > 0) {
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
                    System.out.println("Morate uneti a ili b. Probajte ponovo.");
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
