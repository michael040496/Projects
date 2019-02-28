import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

public abstract class Resept {//en abstrakt klasse
  Legemiddel pekerTilLegemiddel;
  LegemiddelA pekerTilLegemiddelA;
  LegemiddelB pekerTilLegemiddelB;


  Lege pekerTilLege;
  Pasient pekerTilPasient;
  protected int reit;
  protected int nrTilResept;
  //variablene deklareres



  public Resept (int nr, Legemiddel aktuellMed, Lege aktuellLege, Pasient aktuellPasient, int antall) {//konstruktoren til Resept. Tar inn de aktuelle parameterene
    nrTilResept = nr;
    pekerTilLegemiddel = aktuellMed;
    pekerTilLege = aktuellLege;
    pekerTilPasient = aktuellPasient;
    reit = antall;

  }

  public int unikt() {//returner det unike nummeret til klassen
    return nrTilResept;
  }

  public Lege pekLege() {
    return pekerTilLege;
  }

  public Pasient pekPasient() {
    return pekerTilPasient;
  }

  public int hvorMyeReit(){
    return reit;
  }

  public Legemiddel pekLegemiddel() {
    if (pekerTilLegemiddel instanceof LegemiddelA) {
      pekerTilLegemiddelA = (LegemiddelA) pekerTilLegemiddel;

    return pekerTilLegemiddelA;
    }

    else if (pekerTilLegemiddel instanceof LegemiddelB) {
      pekerTilLegemiddelB = (LegemiddelB) pekerTilLegemiddel;

      return pekerTilLegemiddelB;
    }
    return pekerTilLegemiddel;
  }



  public void brukResept() {//metoden som lar brukeren utnytte resepten. Om reiten er 0, er resepten brukt opp, men ellers kan den brukes
    if (reit > 0) {//er reiten storre en 0, kan den brukes. Hendelsene nedenfor utfores
      System.out.println(pekerTilPasient.toString() + " brukte resepten.");
      reit--; //reiten reduseres med 1 for hver gang den brukes
    }
    else if (reit == 0) { // er reiten lik 0, er den tom
      System.out.println("Resepten er brukt opp");
    }
  }

  public boolean gyldig () {//sjekker om resepten er gyldig (ved a sjekke om reiten er over 0)
    if (reit > 0 ) {
      return true;
    }
    return false;
  }
}

class BlaResept extends Resept {//subklasse av Resept
  private int pris = 0;
  BlaResept (int nr, Legemiddel aktuellMed, Lege aktuellLege, Pasient aktuellPasient, int antall) {//konstruktoren til BlaResept
    super(nr, aktuellMed, aktuellLege, aktuellPasient, antall);//kall pa konstruktoren til superklassen
  }
  public int prisPaResept () {//returnerer prisen til den bla resepten (som er gratis).
    return pris;
  }

}

class HvitResept extends Resept {
  HvitResept (int nr, Legemiddel aktuellMed, Lege aktuellLege, Pasient aktuellPasient, int antall) {//konstruktoren til RodResept
    super(nr, aktuellMed, aktuellLege, aktuellPasient, antall);// kall pa konstruktoren til superklassen

  }



}
