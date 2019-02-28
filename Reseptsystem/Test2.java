import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

class Test2 {
  public static void main(String [] args) {//test av SortertEnkelListe
    SortertEnkelListe<Lege> ny = new SortertEnkelListe<Lege>();
    int x = 0;
    String navn1 = "Aleksander";
    String navn2 = "Ole";
    String navn3 = "Peter";
    String navn4 = "Jo";
    String navn5 = "Miki";
    String navn6 = "dwdafsdfdasdas";
    String navn7 = "A";
    Lege a = new Lege(navn1, x);
    Lege b = new Lege(navn2, x);
    Lege c = new Lege(navn3,x);
    Lege d = new Lege(navn4, x);
    Lege e = new Lege(navn5, x);
    Lege f = new Lege(navn6, x);
    Lege g = new Lege(navn7, x);
    //variablene deklareres og 7 lege objekt lages

    ny.settInnSortert(a);
    ny.settInnSortert(b);

    ny.settInnSortert(c);
    ny.settInnSortert(d);
    ny.settInnSortert(e);
    ny.settInnSortert(f);
    ny.settInnSortert(g);
    //setter inn objektene i vilkarelige rekkefolge(ikke sortert etter storrelse). Plus en spesialtilstand testes ved at den tredje som settes inn bare er storre enn hode

    Iterator it = ny.iterator();//iterator av ny (SortertEnkelListe)

    while(it.hasNext()) {//sa lenge hasNext() returnerer true, gaar loopen
      System.out.println(it.next().toString());//skriver ut next() sin toString.
    }

    if (ny.tom()) {//sjekker om listen er tom
      System.out.println("Tom");
    }
    else {
      System.out.println("Ikke tom");
    }
    }


}
