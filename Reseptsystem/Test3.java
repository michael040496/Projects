import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

class Untak extends Exception {//untaksklasse
  public Untak(String meld) {//konstruktoren som printer en feilmelding
    super(meld);
  }
}

class Test3 {//test av EnkelReseptListe
    public static void main(String [] args) throws IOException {
      int tall = 1;
      String tekst = "a";
      boolean tilstand = false;
      EnkelReseptListe yngste = new YngsteForstReseptListe();
      EnkelReseptListe eldste = new EldsteForstReseptListe();
      //to EnkelReseptLister lages, en av typen YngsteForstReseptListe og den andre av typen EldsteForstReseptListe


      Lege tilfeldig = new Lege(tekst, tall);
      Pasient tilfeldig1 = new Pasient(tall, tekst, tekst, tekst, tekst);
      Legemiddel tilfeldig2 = new LegemiddelC(tekst, tall, tall, tilstand, tall, tall);

      Resept resept1 = new BlaResept(tall, tilfeldig2, tilfeldig, tilfeldig1, tall);
      Resept resept2 = new BlaResept(tall + 1, tilfeldig2, tilfeldig, tilfeldig1, tall);
      Resept resept3 = new BlaResept(tall + 2, tilfeldig2, tilfeldig, tilfeldig1, tall);
      Resept resept4 = new BlaResept(tall + 3, tilfeldig2, tilfeldig, tilfeldig1, tall);
      Resept resept5 = new BlaResept(tall + 4, tilfeldig2, tilfeldig, tilfeldig1, tall);
      //variablene deklareres og 5 resepter lages

      test(eldste, resept1, resept2, resept3, resept4, resept5, tall + 5);
      test(yngste, resept1, resept2, resept3, resept4, resept5, tall + 5);
      //testene kalles pa


    }

    public static void test(EnkelReseptListe subjekt, Resept a, Resept b, Resept c, Resept d, Resept e, int numb) throws IOException {//test metoden tar inn 7 parametere
      subjekt.settInn(a);
      subjekt.settInn(b);
      subjekt.settInn(c);
      subjekt.settInn(d);
      subjekt.settInn(e);
      //setter inn resepter i den vilkarelige EnkelReseptListen

      Iterator<Resept> it = subjekt.iterator();//iterator av subjekt(EnkelReseptListe)

      if (subjekt.tom()) {//sjekker om listen er tom
        System.out.println("Listen er tom");
      }

      else {
        System.out.println("Listen er ikke tom");
      }
    System.out.println(subjekt.antallNoder() + " noder i lenken");//printer ut antall resepter i listen

      while(it.hasNext()) {//sa lenge hasNext() returnerer true, gaar loopen
        System.out.println(it.next().unikt());//skriver ut next() (en resept) sitt unike nummer
      }

      try {//tester en ugyldig tilstand (prover aa finne en resept gjennom et tall som er storre enn lengden pa listen)
        subjekt.finnResept(numb);
      }

      catch (Untak untak) {//utaket fanges
        untak.printStackTrace();//feilmelding skrives
      }


    }

}
