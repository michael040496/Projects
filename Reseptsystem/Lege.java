import	java.util.*;
import java.util.Scanner;
import java.io.File;

public class Lege implements Lik, KommuneAvtale, Comparable<Lege> {//klassen implementerer to grensesnitt (Lik og KommuneAvtale)
  private String navnPaLege;
  private int avtaleNr;
  private int sumVirkestoff = 0;
  private int sumPillerVirkeStoff = 0;
  private int sumMiksturVirkeStoff = 0;
  private int tellerNarkotiskForLege;

  EnkelReseptListe eldste = new EldsteForstReseptListe();//en resept beholder

  Scanner skriv = new Scanner(System.in);
  //variablene deklareres



  public Lege (String navn, int avtale) {//konstruktoren til Lege
    navnPaLege = navn;
    avtaleNr = avtale;
  }

  public String toString () {//returnerer navntet til legen
    return navnPaLege;
  }

  public int compareTo(Lege a) {//sammenligneren
    return this.navnPaLege.toLowerCase().compareTo(a.toString().toLowerCase()); //sammenligner denne legens navn, med den som tas inn som parameter. Gir alfabetisk forrang
  }

  public boolean samme (String sammenligne) {//sammenligner en String som tas inn som en parameter, med navnet til legen. Gjores fordi legene blir funnet gjennom sine navn
    if (sammenligne.equals(navnPaLege)) {// er Stringen som tas inn som parameter, og navnet til legen lik. returneres true
      return true;
    }
    return false; //ellers returneres false
  }



  public int kommune () {//legen kan gi ut avtalenummer, om det er en fastlege. Metoden sjekker om det er tilfellet, og tildeler folgelig et avtalenummer. Om det ikke er tilfellet skrives en feilmelding

    if (avtaleNr == 0) {//er det ikke en fastlege returneres 0
      return 0;

    }

    else {//ellers avtalenummeret
      return avtaleNr;
    }

  }

  public void settInnEldste(Resept resept){//tar in resept og setter det i legens private beholder, eldste forst
    eldste.settInn(resept);

  }

  public void skrivUtEldstMikstur(){//skriver ut alle misktur preparetene legen har signert
    System.out.println("Legen " + toString() + " sine resepter pa miksturpreparater.");
    for(Resept mikstur : eldste){//foreach looke som gaar gjennom legens private resept beholder
      if (mikstur.pekLegemiddel().typeMed() == false){//om pekeren til legemiddelet retunrerer false pa typeMed (at det er en mikstur med andre ord)
        System.out.println("Uniktall: " + mikstur.unikt());//den aktuelle respetens  unike tall
      }
    }

  }

  public void skrivUtTotalVikr() {//skriver ut tsummen av virkestoffet til alle legemiddelene legen har skrevet ut
    System.out.println("Totale virkestoffet av alle reseptene til legemiddler " + toString() + " har skrevet ut: ");
    for(Resept aktuell : eldste) {//foreach som gaar gennom legens private resept beholder
      sumVirkestoff = sumVirkestoff + aktuell.pekLegemiddel().bareVirkeStoff();//sum virkestoffet ooker med den aktuelle reseptens peker til et legemiddels virkestoff
      if (aktuell.pekLegemiddel().typeMed() == true){//om det er i pilleform
        sumPillerVirkeStoff = sumPillerVirkeStoff + aktuell.pekLegemiddel().bareVirkeStoff();//sumPillerVirkeStoff ooker med den aktuelle reseptens peker til et legemiddels virkestoff
      }
      else if(aktuell.pekLegemiddel().typeMed() == false) {//om det er i mikstur form
        sumMiksturVirkeStoff = sumMiksturVirkeStoff + aktuell.pekLegemiddel().bareVirkeStoff();//sumMiksturVirkeStoff ooker med den aktuelle reseptens peker til et legemiddels virkestoff
      }
    }
    System.out.println("Totalsum av alt virkestoffet: " + sumVirkestoff + ".");
    System.out.println("");
    System.out.println("Sum av virkestoffet i alle pillelegemiddelene: " + sumPillerVirkeStoff + ".");
    System.out.println("");
    System.out.println("Sum av virkestoffet i alle miksturpreparatene: " + sumMiksturVirkeStoff + ".");
    //mengdene totalsum virkestoff, sum virkestoff av piller og sum virkestoff av mikstur skrives ut


  }

  public boolean harSkrevetNarkotisk(){//sjekker om legen har skrevet gylige og ugyldige resepter paa narkotiske legemiddel
    for(Resept b: eldste){//foreach gaar gjennom legens private resept beholder
      if (b.pekLegemiddel() instanceof LegemiddelA){//om det aktuelle legemiddelet er av typen A ooker tellerNarkotiskForLege med 1
        tellerNarkotiskForLege++;
      }
    }
    return tellerNarkotiskForLege > 0;//returnerer sann om tellerNarkotiskForLege er storre enn 0
  }

  public int antallNarkotiskeResepter(){
    return tellerNarkotiskForLege;//returnerer antall narkotsike resepter legen har skrevet ut
  }
}
