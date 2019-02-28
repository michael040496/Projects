import	java.util.*;
import java.util.Scanner;
import java.io.File;

public abstract class Legemiddel {//en abstrakt klasse
  protected String navnPaMed;
  protected int prisPaMed;
  protected int nrTilMed;
  protected int antPiller;
  protected int mengde;
  protected int virkeStoff;
  protected boolean pille;
  //variablene deklareres





  public Legemiddel (String navn, int pris, int nr, boolean erDetPiller, int mengdeMed, int virk) {//konstruktoren som tar inn de aktuelle variablene
    navnPaMed = navn;
    prisPaMed = pris;
    nrTilMed = nr;
    pille = erDetPiller;
    mengde = mengdeMed;
    virkeStoff = virk;
  }

  public String toString() {//returnerer navnet pa medisinen
    return navnPaMed;
  }

  public int unikt(){//det unike nummeret til legemiddelet
    return nrTilMed;
  }

  public int medPris() {//returnerer prisen til medisine
    return prisPaMed;
  }

  public int uniktTall() {//returnerer medisinens unike tall
    return nrTilMed;
  }

  public int narkotiskEffekt(){
    return 0;
  }

  public int vanedanedeEffekt(){
    return 0;
  }

  public boolean typeMed() {// returnerer om medisinen er i pilleform eller ikke (miks)


    return pille;
  }



    public void mengdeMed () {//metode som forteller hvor om medisinen er i mikstur eller pilleform, hvor mange piller det er i esken eller volumet pa miksturen, og virkestoff per pille eller cm^3
      if (pille == true) {//sjekker om det er en pille. Hvis det er tilfellet utfores handlingene nedenfor
        System.out.println("Det er " + mengde + " piller i esken. Hver pille har " + virkeStoff + " mg virkestoff i seg.");

      }

      else {
        System.out.println("Det er " + mengde + " cm3 i flasken. Det er " + virkeStoff + " virkestoff i hver kvadratcentimeter.");


      }
    }

    public int bareVirkeStoff(){//skriver ut bare virkestoffet
      return virkeStoff;
    }

    public int bareMengde(){//skriver ut bare mengden
      return mengde;
    }
}



class LegemiddelA extends Legemiddel implements Type {//subklasse av Legemiddel, som implementerer grensesnittet type
  private int narkotisk;
  //variabl som er unik for LegemiddelA

  LegemiddelA (String navn, int pris, int nr,  boolean erDetPiller, int mengdeMed, int virk, int effekt) {//konstruktoren til LegemiddelA.
    super(navn, pris, nr, erDetPiller, mengdeMed, virk);//kall pa konstruktoren til superklassen
    narkotisk = effekt;
  }


  public int narkotiskEffekt () {//returnerer medisinens narkotiske effekt
    return narkotisk;
  }



}

class LegemiddelB extends Legemiddel implements Type{//subklasse av legemiddel. Implementer grensesnittet type
  private int vanedanende;
  LegemiddelB (String navn, int pris, int nr,  boolean erDetPiller,  int mengdeMed, int virk, int effekt) {//konstruktoren til LegemiddelB
    super(navn, pris, nr, erDetPiller, mengdeMed, virk);//kall pa konstruktoren til superklassen
    vanedanende = effekt;

  }

  public int vanedanedeEffekt () {//returnerer hvor vanedanende medisinen er
    return vanedanende;
  }




}

class LegemiddelC extends Legemiddel implements Type {//subklasse av legemiddel. Implementer grensesnittet type
  public LegemiddelC (String navn, int pris, int nr,  boolean erDetPiller, int mengdeMed, int virk) {//konstruktoren til LegemiddelC
    super(navn, pris, nr, erDetPiller, mengdeMed, virk);//kall pa konstruktoren til superklassen
  }





}
