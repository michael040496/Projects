import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

class Main{//main klassen
  public static void main(String[] args)  throws UgyldigVerdiUnntak, FeilAntallUnntak, IOException, Exception, ForStortBrettUnntak{
    Brett b = new Brett();//brett element
    Rute [][] aktuell;//en todimensjonell rute-array
    int [] c;//int array


    try {
      b.lesInn("sud.txt");//b leser inn sud.txt
    }

    catch (UgyldigVerdiUnntak verdiUnntak) {
      verdiUnntak.printStackTrace();
    }

    catch (FeilAntallUnntak antallUnntak){
      antallUnntak.printStackTrace();
    }

    catch (ForStortBrettUnntak storrelseUnntakk){
      storrelseUnntakk.printStackTrace();
    }



    b.lagDataStruktur();//b lager datastrukturen
    aktuell = b.returnBrett();//aktuell settes til brettet b returnerer
    System.out.println("Rute " + aktuell[5][5].hvilkenRute() + " tilhorer rad " + aktuell[5][5].pekRad().hvilkenRad() + " kolone " + aktuell[5][5].pekKolone().hvilkenKolone() + " og boks " + aktuell[5][5].pekBoks().hvilkenBoks());//en vilkaerlig rute returnerer sitt unike nummer, og raden den tilhorer
    c = aktuell[5][5].finnAlleMuligeTall();//alle mulige losninger for den aktuelle ruten (gitt at den er 0)
    for (int x = 0; x <c.length; x++){
      System.out.println("Losning: " + (x+1));
      System.out.println(c[x]);//losningnene skrives ut
    }
    b.skrivTil();//skriver til fil



  }

}
