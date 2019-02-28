import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;


  class SortertEnkelListe<T extends Comparable<T>> implements AbstraktSortertEnkelListe<T, Lik> {//generisk klasse med begrensende typeparameter som implenterer AbstraktSortertEnkelListe<T, Lik>
  private T element;
  private Node hode;


  class Node {//nodeklassen
   T data;
   Node neste;

   Node (T a) {//konstruktoren til noden
     data = a;
     neste = null;

   }
 }

  public void settInnSortert(T aktuell) {//setter inn element sortert med hensyn til storrelse. Tar et vilkarelige element som parameter

    Node ny = new Node(aktuell);//ny node lages
    Node forrige = null;



    if (hode == null || ny.data.compareTo(hode.data) < hode.data.compareTo(ny.data)) //om hode er null, eller ny sin data er mindre en hode sin data utfores handligene nedenfor

    {

      ny.neste = hode; //ny.neste settes til hode som lenker den nye noden foran

      hode = ny; //hode settes tul ny
      return;//gaar ut av metoden

    }
    else {//ellers


      Node temp = hode.neste;//node temp settes til hode neste. Om vi er i elsen betyr det at hode er mindre en ny, sa det forste elementet som skal sjekkes her blir da det neste i listen

      while(temp != null && temp.data.compareTo(ny.data) < ny.data.compareTo(temp.data)) {//sa lenge temp ikke er null(naad enden), og dataen til ny er storre en temp sin, gaar loopen

        forrige = temp; //forrige settes til temp


        temp = temp.neste; //temp settes til den neste noden
        //etter en loop vil derfor det neste elementet sin storrelse sjekkes opp mot ny sin storrelse

        }
        if (forrige == null) {//spesialtilfelet. Om ny er storre en hode, men mindre en hode.neste (det nest minste elementet i listen) utfores handligene nedenfor
          hode.neste = ny; //hode neste settes til ny
          ny.neste = temp; //ny.neste settes til temp. Med andre ord settes ny.neste til den forrige hode.neste (den forrige nest storste verdien)
          return;//gaar ut av metoden
        }
        //disse to hendelsene utfores etter at whilen har gaat minst 1 gang
        forrige.neste = ny;//forrige.neste settes til ny
        ny.neste = temp;//ny.neste settes til temp
        //med andre ord settes ny mellom forrige og temp


    }
  }

  public T finn(String nokkel) {//finner et element bassert pa en nokkel
    Node lop = hode;// en node som looper gjennom listen. Settes til hode i forste omgang
    while (lop != null) {//sa lenge lop ikke er null, gaar loopen
      if (lop.data.toString().equalsIgnoreCase(nokkel)) {//sjekker om lop sin string er den samme som parameteren
        return lop.data;//er det tilfellet returneres det
      }
      lop = lop.neste; //lop settes til det neste elementet i listen
    }
  return null;//om stringen ikke er lik noen av nodenes stringer, kastes et untak

  }



public boolean tom() {//sjekker om hode er null (om listen er tom)
  return hode == null;
}


  public Iterator<T> iterator(){//konstruktor til en iterator

    return new ListeIterator();
  }

  private class ListeIterator implements Iterator<T>{//en private iteratorklasse som gaar gjennom resepter
    Node na= hode; //den naaverende nodes i en iterasjon
    Node print; //noden som returneres



    public boolean hasNext()	{//sjekker om noden finnes

      return na != null;

    }


    public T next()	{//metoden som returnerer den forste noden
      if (!hasNext()) {//er hasNext() false kastes et untak
        throw new NoSuchElementException();
      }
      print = na;
      na = na.neste;
      return print.data;
      //ellers settes "print" til "na", na sin neste node kalles pa og print sin data returneres

    }

  }
}
