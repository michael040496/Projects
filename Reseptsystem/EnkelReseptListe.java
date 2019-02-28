import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;



class EnkelReseptListe implements Iterable<Resept>{//klassen EnkelReseptListe som implenterer Iterable<Resept>
  protected Resept element;
  protected Node hode = null;
  protected Node hale;
  protected int hvorMange = 0;
//de aktuelle metodene som ogsa kan brukes av subklassene til klassen

  class Node {//nodeklassen
    Resept aktuell;
    Node neste;
    //nodens aktuelle parametere

    Node (Resept a) {//konstruktoren til noden
      aktuell = a;
      neste = null;
    }


  }


  public int antallNoder() {//int metoden som returner hvor mange noder det er i lenkelisten
    return hvorMange;
  }


  public boolean tom() {//boolean metode som sjekker om en lenkeliste er tom
    return hode == null;
  }

  public void settInn(Resept resept)  {}

  public Resept finnResept(int reseptNr) throws Untak {//en metode som finner en resept basert pa det unike nummeret som tas inn som parameter
    Node lop = hode;//en node som looper gjennom listen
    while (lop != null) { //sa lenge node ikke er null utfores handlingene nedenfor
      if (lop.aktuell.unikt() == reseptNr) {//er noden (en resept) sitt unikenummer det samme som det tallet som tas inn som parameter returneres den resepten
        return lop.aktuell;
      }
      lop = lop.neste; //neste node kalles pa
    }
    throw new Untak("Fant ikke resept"); //kaster et untak med feilmelding om resepten ikke finnes

  }



  public Iterator<Resept> iterator(){//konstruktor til en iterator

    return new ListeIterator();
  }

  private class ListeIterator implements Iterator<Resept>{//en private iteratorklasse som gaar gjennom resepter
    Node na= hode; //den naaverende nodes i en iterasjon
    Node print; //noden som returneres



    public boolean hasNext()	{//sjekker om noden finnes

      return na != null;

    }


    public Resept next()	{//metoden som returnerer den forste noden
      if (!hasNext()) {//er hasNext() false kastes et untak
        throw new NoSuchElementException();
      }
      print = na;
      na = na.neste;
      return print.aktuell;
      //ellers settes "print" til "na", na sin neste node kalles pa og print sin data returneres

    }

  }




}

class EldsteForstReseptListe extends EnkelReseptListe {//subklasse til EnkelReseptListe


  public void settInn(Resept resept) {//setter inn noder i queue-rekkefolge. Tar inn resepter som parameter
    Node ny = new Node(resept);//ny noode lages
    if (tom()) {//om listen er tom utfores denne unike handligen nede
      hode = ny;//hode settes til ny

    } else {//om listen ikke er tom utfores denne unike handligen
      hale.neste = ny;//hale.neste settes til ny, og lenker den nye noden bakerst
    }
    hale = ny; //hale settes til ny
    hale.neste = null; //halen neste peker pa null (enden av lenken)
    hvorMange ++;
    //disse handlingene utfores uansett om listen er tom eller ikke (sa lenge metoden kalles pa riktig)

  }


}

class YngsteForstReseptListe extends EnkelReseptListe {//subklasse til EnkelReseptListe
  public void settInn(Resept resept) {//setter noder i stack-rekkefolge (LIFO). Tar inn resepter som parameter
    Node ny = new Node(resept); //en ny node lages

    if (tom()) { //er lenkelisten tom utfores handligene nedenfor
      ny.neste = hode; //ny.neste settes til hode (som er null)
      hode = ny;
      hale = ny;
      //hode og hale settes til den nye noden. I forste omgang har de samme verdi fordi det bare er et element i listen
    }
    else {//om det er mer en et element i listen utfores disse handligene
      ny.neste = hode;//ny.neste settes til hode (som na har en verdi), og "lenker" den nyeste noden foran
      hode = ny; //hode settes til ny
    }
    hvorMange ++;
    //antallet ooker med en for hvert riktige kall pa denne metoden

  }

}
