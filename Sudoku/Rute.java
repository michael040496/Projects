import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Rute {
  private Boks pekerBoks;
  private Kolone pekerKolone;
  private Rad pekerRad;
  private int uniktNr;
  private int verdiRute;
  private Rute [] a;
  private Rute [] b;
  private Rute [] c;
  private int teller1 = 0;
  private int teller2 = 0;
  private int teller3 = 0;
  private ArrayList <Integer> holder = new ArrayList<Integer>(); //en int-arraylist
  //de aktuelle variabelene


  public Rute(int unik, int verdi){//konstruktoren

    uniktNr = unik;
    verdiRute = verdi;
  }

  public int hvilkenRute(){//returnerer det unike tallet til ruten
    return uniktNr;
  }



  public void tilordneRad(Rad rad){//tar en rad som parameter, og tilordner det dene ruten
    pekerRad = rad;
  }

  public Rad pekRad(){//returnerer raden denne ruten tilhorer
    return pekerRad;
  }

  public Kolone pekKolone(){//returnerer kolonenen denne ruten tilhorer
    return pekerKolone;
  }

  public Boks pekBoks(){//returnerer boksen denne ruten tilhorer
    return pekerBoks;
  }

  public int verdiTilRute(){//returnerer verdien til denne ruten
    return verdiRute;
  }

  public void tilordneKolone(Kolone kolone){//tar en kolone som parameter og tilordener det denne ruten
    pekerKolone = kolone;
  }

  public void tilordneBoks(Boks boks){//tar en boks som parameter, og tilordner det denne ruten
    pekerBoks = boks;
  }
  public int [] finnAlleMuligeTall(){//finner alle mulige losninger til en tom rute
    a = pekerRad.radArr();
    b = pekerKolone.koloneArr();
    c = pekerBoks.boksArr();


    if(verdiRute != 0){//om ruten ikke er tom(0), gis en feilmelding og null returneres
      System.out.println("Denne ruten har allerede en verdi.");
      return null;
    }

    for (int i = 1; i <= a.length; i++){//denne forlooken begynner paa 1. Naar den gaar sjekkes i opp mot verdiene i denne rutens aktuelle, rad, kolone og boks

      for(int j=0; j < a.length; j++){//for looke som gaar a.length ganger (like mange ganger som det er verdier i den aktuelle raden)
        if(i == a[j].verdiTilRute()){//om i == en av verdiene i a (om raden denne ruten tilhorer har noen verdier som er lik i) ooker teller1 med 1
          teller1++;
          }
        }

        for(int k = 0; k < b.length; k++){//for looke som gaar b.length ganger (like mange ganger som det er verdier i den aktuelle kolonen)
          if(i == b[k].verdiTilRute()){//om i == en av verdiene i b (om kolonen denne ruten tilhorer har noen verdier som er lik i) ooker teller2 med 1
            teller2++;
          }
        }

        for(int l = 0; l < c.length; l++){//for looke som gaar c.length ganger (like mange ganger som det er verdier i den aktuelle boksen)
          if(i == c[l].verdiTilRute()){//om i == en av verdiene i c(om boksen denne ruten tilhorer har noen verdier som er lik i) ooker teller3 med 1
            teller3++;
        }
      }

      if (teller1 == 0 && teller2 == 0 && teller3 == 0){//om alle tellerene er 0, betyr det at i er en mulig losning, og den settes i en arraylist
        holder.add(i);
      }

    teller1= 0;
    teller2= 0;
    teller3= 0;

  }
  int storrelse = holder.size();//storrelsen pa arraylisten
  int [] muligeVerdier = new int[storrelse];//en array med storresle lik storrelse

  for (int m = 0; m < storrelse; m++){
    muligeVerdier[m] = holder.get(m);//verdiene til arraylisten "Overfores" til denne arrayen
  }

  return muligeVerdier;//arrayen returners

}
}
