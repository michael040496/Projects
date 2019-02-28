import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

class Test1 {//testfil for Tabell<T>
  public static void main(String [] args) {
    int storrelse = 5;
    Tabell<String> objekt = new Tabell<String>(storrelse);
    String inn = "hei"; //variablene deklareres

    test(inn, objekt, storrelse);//metodekall pa test

  }
  public static <T> void test (T a, Tabell b, int i) {//testmetoden, tar in 3 parametere
    int j = 0;
    while (j < i) {//sa lenge j er mindre enn i (storrelsen pa arrayen) gaar loopen

     if (!b.tom(j)) {
       System.out.println("Her gikk noe galt"); //en allerede okkupert plass
     }
     else {
       System.out.println("Dette gikk bra");
       b.settInn(j, a);
       //plass er ikke okkupert, sa elementet settes inn

     }
     j++;//j ooker med 1
  }

    Iterator it = b.iterator();//en iterator av b (Tabell<T>)
    while(it.hasNext()) {//sa lenge hasNext() returnerer true gaar loopen
      System.out.println(it.next().toString());//skriver ut next sin toString
    }
     b.settInn(j, a);//prover aa sette inn j, men den er for stor sa en feilmelding skrives
     b.settInn(j-1, a);//prover aa sette inn j pa en plass som er okkupert


  }
}
