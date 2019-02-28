import	java.util.*;
import java.util.Scanner;
import java.io.File;
import	java.util.*;


public class Tabell<T> implements AbstraktTabell<T> {//generisk klasse som implenterer AbstraktTabell<T>
  private T[] alle;
  private int antPlasser;
  private int plass;
  private int pos;
  private T element;
  //aktuelle variablene til klassen


     Tabell(int storrelse) {//konstruktoren. Tar inn en int parameter som representer storrelsen pa en array
     antPlasser = storrelse;
     alle = (T[]) new Object[antPlasser];
   }

   public boolean tom(int tall) {//boolean sjekker om en plass (representert av en int parameter) er ledig
     return alle[tall] == null;
   }

   public int plasser(){
     return plass;
   }

   public boolean settInn(int tall, T aktuell) {//boolean metoden som setter inn elementer i arrayen. Tar to parametere, et tall(plassen ) og et element
     plass = tall;
     element = aktuell;
     if (plass < antPlasser && plass > -1) {//er parameteren som tas inn mindre enn storrelsen pa arrayen eller mindre enn null utfores handlingene nedenfor
       if (tom(plass)) {//hvis lassen er ledig utfores handlingene nedenfor
         alle[plass] = element;//elementet settes i den osnkede plassen

         return true;//true returneres
       }

       else {//ellers2
         System.out.println("Okkupert");
         return false;
         //feilmelding skrives til bruker, og false returneres
       }
     }

     else {//ellers1
       System.out.println("Denne plassen finnes ikke");//arrayen er enten ikke stor nok, eller et negativt tall har blitt oppgitt
       return false;
       //false returneres
     }
   }

   public T finn(int tall) {//finner et element via et tall(parameteren)
     pos = tall;
     if (alle[pos] != null) {//hvis plassen ikke er null (okkupert) skjer handlingene nedenfor
       //elementet har blitt funnet
       return alle[pos];
     }
     else {
       return null;
     }
   }

   public Iterator<T> iterator(){ //konstruktor til en iterator

       return new ListeIterator();
     }

   private class ListeIterator implements Iterator<T>{//en private iteratorklasse som gaar gjennom vilkarelige element
    int posisjon = 0;
    T returobjekt = null; //element som returneres i next()



     public boolean hasNext()	{//sjekker om det er flere element i listen
       return posisjon < antPlasser; //returnerer om posisjon er mindre enn storrelsen pa arrayen. Med andre ord om det er flere plasser
     }


     public T next()	{//returner det neste elementet i arrayen
       if (!hasNext()) {//om posisjon er storre en antPlasser kastes et untak
         throw new IndexOutOfBoundsException();
       }
       returobjekt = alle[posisjon];//returnobjekt settes til det naverende objektet (identifsert av posisjonen i arrayen)
       posisjon++;//posisjon ooker med 1
       return returobjekt; //returnobjekt returneres

      }

     }
}
