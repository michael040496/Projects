import	java.util.*;
import java.util.Scanner;
import java.io.File;

public class Pasient  {
  private int uniktNr;
  private String adresse;
  private String fodselsNr;
  private String navnPaPerson;
  private String postNr;
  private int tellerNarkotiskGyldigForPasient;
  EnkelReseptListe yngste = new YngsteForstReseptListe();


  //variablene deklareres

  public Pasient (int unikt, String post, String adr, String fodsel, String navn) {//konstruktoren til Pasient som tar inn diverse parametere
    navnPaPerson = navn;
    uniktNr = unikt;
    fodselsNr = fodsel;
    adresse = adr;
    postNr = post; // Stringen adresse settes sammen av "adr", "gate" og "post"
  }

  public String toString () { //toString metoden som returnerer navntet til pasienten
    return navnPaPerson;
  }

  public String fodselsnummer () {//metoden som returnerer fodselsnummeret til pasienten
    return fodselsNr;
  }

  public String pasientAdresse(){//metoden som retrunerer adressen til pasienten
    return adresse + ", " + postNr;
  }

  public int unikt(){//returnerer pasientens unike nummer
    return uniktNr;
  }

  public String postNummer(){//retunrerer pasientens postnummer
    return postNr;
  }

  public void settInnYngste(Resept resept){//tar inn en resept som parameterer og setter den inn i pasientens private reseptbeholder, yngste forst
    yngste.settInn(resept);
  }

  public Resept hentResept(int tall)throws Untak{//henter en resept fr pasientens private reseptbeholder gjennom en int som tas inn som parameterer
    return yngste.finnResept(tall);

  }

  public void skrivAlleResepter(){
    Iterator<Resept> it = yngste.iterator();//iterator av yngste(EnkelReseptListe)

    while(it.hasNext()) {//sa lenge hasNext() returnerer true, gaar loopen
      System.out.println("Reseptens unike nummer: " + it.next().unikt());//skriver ut next() (en resept) sitt unike nummer
    }
  }

  public void skrivUtYngsteBla(){//skriver ut pasientens bla resepter
      System.out.println("Pasienten " + toString() + " sine bla resepter.");
      for(Resept bla : yngste){//foreach som gaar gjennom en pasient sin private resept beholder
        if (bla instanceof BlaResept){//om den aktuelle resepten er av typen blaa
        System.out.println("Uniktall: " + bla.unikt());//reseptens unike tall skrives til skjerm, yngste forst
        }
      }

    }

    public boolean harNarkotisk(){//sjekker om pasienten har minst en gyldig resept pa narkotsike legemiddel
      for(Resept c: yngste){//foreach som gaar gjennom pasientens private resept beholder
        if (c.pekLegemiddel() instanceof LegemiddelA && c.gyldig() == true){//hvis den aktuelle reseptens peker til legemiddel er av typen A, og er gyldig (reit > 0)
          tellerNarkotiskGyldigForPasient++;//teller ooker med 1
        }
      }
      return tellerNarkotiskGyldigForPasient > 0;//returnerer sann om tellerNarkotiskGyldigForPasient er storre enn 0. Ellers false
    }

    public int antallNarkotiskeResepter(){
      return tellerNarkotiskGyldigForPasient;//returnerer antall gyldige resepter pasienten har for narkotiske legemiddel
    }
  }
