import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;


class Kontroll {
  Scanner skriv = new Scanner(System.in);

  private int uniktTallPasient = 0;
  private int uniktTallLegemiddel = 0;
  private int uniktTallResept = 0;
  private int nrResept;

  private int valg;
  private boolean pille;
  private String navnLegemiddel;
  private String navnPasient;
  private String form;
  private String type;
  private int virkestoff;
  private int mengde;
  private int pris;
  private String typeLegemiddel;
  private String adresse;
  private String fodselsNr;
  private String navn;
  private String fil;
  private int tellerVanedanedeOslo = 0;
  private int tellerVanedanede = 0;
  private int zero = 0;



  private int prisLegemiddel;
  private int kvantitet ;
  private int virkestoffMed;
  private int effekt;
  private int lengde;
  private int lengde2;
  private String fnr;
  private String adr;
  private String holder;
  private String finnFodselsNr;
  private int finnUnikt;
  private int forsteSiffer;
  private int andreSiffer;



  private String navnLege;
  private int avtale;
  private String postNr;

  private String farge;
  private int persNummer;
  private String legeNavn;
  private int legemiddelNr;
  private int antallReit;
// de aktuelle variablene som brukes i programmet

  Lege aktuellLege;
  Legemiddel aktuellMedisin;
  Pasient aktuellPasient;
  Resept aktuellResept;
  //aktuelle obekt som brukes i diverse steder

  Tabell<Pasient> tabellPasient = new Tabell<Pasient>(1000);
  SortertEnkelListe<Lege> legeListe = new SortertEnkelListe<Lege>();
  Tabell<Legemiddel> tabellMed = new Tabell<Legemiddel>(1000);
  EnkelReseptListe oversikt = new EldsteForstReseptListe();
  //forskellige beholdere


  public void lagPasient(String navn, String fodselsNr, String adresse, String postNr) {//metoden som lager en pasienten, gjennom aktuelle parametere som tas inn


    tabellPasient.settInn(uniktTallPasient, new Pasient(uniktTallPasient, postNr, adresse, fodselsNr, navn));//en ny pasient lages, og settes i beholderen
    uniktTallPasient++;//for hver gang denne metoden kalles pa oekes dette tallet med en (en pasients unike tall, som starter paa null, og oeker med 1 for hver pasient)



  }

  public void lagLege(String navn, int avtaleNr) {//metoden som lager en lege

    legeListe.settInnSortert(new Lege(navn, avtaleNr));//en lege lages, og settes i sin aktuelle beholder

  }



  public void lagLegemiddel(String navn, boolean erDetPiller, String type, int pris, int mengdeMed, int virk, int effekt) {//metoden som lager legemiddel
    if (!type.equalsIgnoreCase("a") && !type.equalsIgnoreCase("b") && !type.equalsIgnoreCase("c")) {//hvis string-parameteren "type" "ikke er a, b eller c, lages ikke et legemiddel
      System.out.println("Dette legemiddelet kan ikke lages");

    }
    else {//er typen dog av a, b eller c
      if (type.equalsIgnoreCase("a")) {
        tabellMed.settInn(uniktTallLegemiddel, new LegemiddelA(navn, pris, uniktTallLegemiddel, erDetPiller, mengdeMed, virk, effekt));//om typen er a, lages et legemiddel av subklassen LegemiddelA
      }
      else if (type.equalsIgnoreCase("b")) {
        tabellMed.settInn(uniktTallLegemiddel, new LegemiddelB(navn, pris, uniktTallLegemiddel, erDetPiller, mengdeMed, virk, effekt));//om typen er b, lages et legemiddel av subklassen LegemiddelB
      }
      else if (type.equalsIgnoreCase("c")) {
        tabellMed.settInn(uniktTallLegemiddel, new LegemiddelC(navn, pris, uniktTallLegemiddel, erDetPiller, mengdeMed, virk));//om typen er c, lages et legemiddel av subklassen LegemiddelC
      }
      uniktTallLegemiddel++;//for hver gang denne metoden kalles, oeker dette tallet(saa lenge et legemiddel kalles)
    }
  }

  public void lagResept(String typeResept, int nrPasient, String navnPalege, int nrMedisin, int reit){//metoden som lager en resept
    aktuellLege = legeListe.finn(navnPalege);
    aktuellMedisin = tabellMed.finn(nrMedisin);
    aktuellPasient = tabellPasient.finn(nrPasient);
    //parameterene som tas inn brukes til aa hente henholdsvis, lege, legemiddel og pasientobekt fra sine respektive beholdere

    if (!typeResept.equalsIgnoreCase("hvit") && !typeResept.equalsIgnoreCase("blaa")) {//hvis stringen-parameteren som representer typen resept ikke er "blaa" eller "hvit", kan ikke resepten lages
      System.out.println("Denne resepten kan ikke lages");
    }
    else {//ellers lages resepten
      if (typeResept.equals("hvit")) {//hvite resepter lages typeResept er "hvit"
        aktuellLege.settInnEldste(new HvitResept(uniktTallResept, aktuellMedisin, aktuellLege, aktuellPasient, reit));//en ny resept lages, og settes i en leges EldsteForstReseptListe
        aktuellPasient.settInnYngste(new HvitResept(uniktTallResept, aktuellMedisin, aktuellLege, aktuellPasient, reit));//samme resepten settes ogsa i en pasients YngsteForstReseptListe
        oversikt.settInn(new HvitResept(uniktTallResept, aktuellMedisin, aktuellLege, aktuellPasient, reit));//den samme resepten settes ogsaa i en beholder som tar vare paa alle reseptene


      }
      else if(typeResept.equals("blaa")) {//blaa resepter lages typeResept er "blaa"
        aktuellLege.settInnEldste(new BlaResept(uniktTallResept, aktuellMedisin, aktuellLege, aktuellPasient, reit));//en ny resept lages, og settes i en leges EldsteForstReseptListe
        aktuellPasient.settInnYngste(new BlaResept(uniktTallResept, aktuellMedisin, aktuellLege, aktuellPasient, reit));//samme resepten settes ogsa i en pasients YngsteForstReseptListe
        oversikt.settInn(new BlaResept(uniktTallResept, aktuellMedisin, aktuellLege, aktuellPasient, reit));//samme resepten settes ogsaa i en beholder som tar vare paa alle reseptene

      }

      uniktTallResept++;//reseptenes unike tall. Oeaker med 1 for hvert korrekte kall paa denne metoden
    }
  }

  public void brukResept() throws Untak{//metoden der en resept tas i bruk

    System.out.println("Onsker du aa finne personen gjennom det unike nummeret, eller fodselsnummer? Hvis fodselsnummer trykk 1, hvis uniktnummer trykk2");
    valg = Integer.parseInt(skriv.nextLine());//brukeren taster inn valget
    if (valg != 1 && valg !=2) {//om valger ikke er 1 eller 2, funker det ikke
      System.out.println("Ugyldig valg");
      return;
    }
    else {
      if (valg == 1) {//om valget er 1 maa fodselsnummeret til pasiienten tastes

        System.out.println("Skriv inn fodselsnummeret til vedkommende");
        finnFodselsNr = skriv.nextLine();
        Iterator<Pasient> it = tabellPasient.iterator();//iterator til pasientbeholderen
        while (it.hasNext()) {

          aktuellPasient = it.next();
          if (aktuellPasient != null) {//hindrer nullPointerException
            if (aktuellPasient.fodselsnummer().equalsIgnoreCase(finnFodselsNr)) {//om  pasientens fodselsnummer finnes breakes det ut av whilen
              break;
            }
          }
        }
        if (aktuellPasient == null) {//om pasienten ikke finnes
          System.out.println("Denne pasienten finnes ikke.");
          return;
        }
        System.out.println("Alle reseptene til denne personen. Tast den unike koden til resepten du onsker.");//om pasiienten finnes dog
        aktuellPasient.skrivAlleResepter();//pasientens resepter skrives ut. Yngste forst
        nrResept = Integer.parseInt(skriv.nextLine());//brukeren velger saa mellom pasientens respeter
        aktuellResept = aktuellPasient.hentResept(nrResept);//resept bestemt av det unike nummeret brukeren tastes inn hentes fra pasientens reseptbeholder
        if (aktuellResept == null) {//om aktuellResept er null (brukeren tastet inn en resept den aktuelle pasienten ikke har)
          return;
        }
        else {
          if (aktuellResept.gyldig() == false) {//om resepten er ugyldig(reit == 0)
            System.out.println("Denne resepten er ugyldig(0 reit)");

            return;
          }
        }
      }

      else if (valg == 2) {//hvis brukeren onsker aa  pasienten gjennom det unike nummeret
        System.out.println("Skriv inn det unike nummeret til vedkommende. Dette tallet ma veare fra 0 til og med " + uniktTallPasient + ".");
        finnUnikt = Integer.parseInt(skriv.nextLine());
        if (finnUnikt > uniktTallPasient || finnUnikt <= -1) {//om tallet brukeren tastet inn er mindre enn 0, eller storre en unikTallPasient (det siste unike tallet til en pasient saa langt)
          System.out.println("Dette er et ugyldig valg");
          return;

        }
        else if(tabellPasient.finn(finnUnikt) == null){//taster inn det unike tallet til en pasient som ikke finnes
          System.out.println("Denne pasienten finnes dessverre ikke.");
          return;
        }

        aktuellPasient = tabellPasient.finn(finnUnikt);//aktuellPasient settes til pasienten i tabelPasient med det tilsvarende unike nummeret


        System.out.println("Alle reseptene til denne personen. Tast den unike koden til resepten du onsker.");
        aktuellPasient.skrivAlleResepter();//alle reseptene til denne pasieten skrives ut
        nrResept = Integer.parseInt(skriv.nextLine());
        aktuellResept = aktuellPasient.hentResept(nrResept);//den aktuelle resepten settes til en resept hentet fra den unike pasientens resept beholder
        if (aktuellResept == null) {//om den ikke finnes

          return;
        }
        else {
          if (aktuellResept.gyldig() == false) {//om den er ugyldig
            System.out.println("Denne resepten er ugyldig(0 reit)");
            return;
          }
        }
      }


      aktuellResept.brukResept();//aktuellResept (hentet gjennom uniktnummer eller fodselsnummer) brukes. Reiten synker med 1


      if (aktuellResept instanceof HvitResept){//om resepten er hvit
        System.out.println(aktuellResept.pekLegemiddel().medPris() + " kr skal betales.");//prisen som skal betales
      }

      else if (aktuellResept instanceof BlaResept){//blaa resepter gir 100% rabat paa legemiddler
        System.out.println("Dette er en blaa resept, og ingen pris skal betales");
      }

      System.out.println("Pasient: " + aktuellPasient.toString() + ".");
      System.out.println("Lege: " + aktuellResept.pekLege().toString() + ".");
      System.out.println("Legemiddel: " + aktuellResept.pekLegemiddel().toString() + ".");
      aktuellResept.pekLegemiddel().mengdeMed();
      if (aktuellResept.pekLegemiddel() instanceof LegemiddelA) {
        System.out.println("Narkotisk effekt: " + aktuellResept.pekLegemiddel().narkotiskEffekt());
      }
      else if (aktuellResept.pekLegemiddel() instanceof LegemiddelB) {
        System.out.println("Vanedanende effekt: " + aktuellResept.pekLegemiddel().vanedanedeEffekt());
      }
      //aktuell informasjon om legemiddelet som har blitt hentet printes ut




    }


  }

  public void les(String filnavn) throws Exception { //metoden der txt* filen leses inn, og de forskjellige elementene legges inn i sine beholdere
    String linje = "";
    Scanner sc = new Scanner(new File(filnavn));


    while (sc.hasNextLine()) { //while-looken gaar saa lenge det er flere linjer i filen

      linje = sc.nextLine(); //string variabelen linje, settes til den neste linjen i tekstfilen

      if (linje.substring(2,8).equals("Person")) {//er linjen lik bokstavene fra og med 2 til 8 lik "Person"
      linje = sc.nextLine();//linje settes igen til det den neste linen i teskfilen

      while (!linje.startsWith("#")) {//saa lenge linje ikke starter med "#" gaar loopen


        String[] deler = linje.split(", ", -1); //linjen deles der det er komma etterfulgt av mellomrom. Delene settes saa i en array
        navnPasient = deler[1];

        fnr = deler[2];
        adr = deler[3];

        postNr = deler[4];

        lagPasient(navnPasient, fnr, adr, postNr);
        //pasientatributter settes saa til de forskellige stringfragmente, og brukes som parametere til lagPasient metoden. Med andre ord har informasjon om en pasient blitt lest inn, og lagret

        linje = sc.nextLine();//linje settes saa til den neste linen av filen igjen
        if ("".equals(linje)) {//hvis linjen er blank, "breakes" det
          break;
        }




      }

    }
    linje = sc.nextLine();//linje settes igen til den neste linjen i filen

    if (linje.substring(2,7).equals("Legem")) {//er linjen lik bokstavene fra og med 2 til 7 lik "Legem"
    linje = sc.nextLine();//linje settes til den neste linjen i filen

    while (!linje.startsWith("#")) {//loopen gaar saa lenge linje ikke starter med "#"

      String[] deler1 = linje.split(", ");//igjen deles linjen der det er komma, etterfulgt av mellomrom. De forskjellige delene settes igjen i en string array

      navnLegemiddel = deler1[1];

      form = deler1[2];

      if (form.equals("pille")) {//hvis "pille" leses inn, settes boolean pille til true
        pille = true;
      }
      else if (form.equals("mikstur")){//ellers til false
        pille = false;
      }

      type = deler1[3];

      prisLegemiddel = Integer.parseInt(deler1[4]);
      kvantitet = Integer.parseInt(deler1[5]);
      virkestoffMed = Integer.parseInt(deler1[6]);
      if (deler1.length > 7) {//hvis den aktuelle string arrayen er storre en 7 (linje deles til flere enn 7 deler) settes effekt varaiblen til den siste delen, og metoden lagLegemiddel kalles paa
        effekt = Integer.parseInt(deler1[7]);


        lagLegemiddel(navnLegemiddel, pille, type, prisLegemiddel, kvantitet, virkestoffMed, effekt);
        linje = sc.nextLine();
      }

      else {//ellers om det bare er syv deler av linje, kalles lagLegemiddel fortsatt paa, men "effekt" faar ikke en verdi(legemiddel type c lages, med andre ord)
        lagLegemiddel(navnLegemiddel, pille, type, prisLegemiddel, kvantitet, virkestoffMed, 0);
        linje = sc.nextLine();
      }

      if ("".equals(linje)) {//break om linje er tom
        break;
      }

    }
  }
  linje = sc.nextLine();


  if (linje.substring(2,7).equals("Leger")) {//om bokstavene fro og med 2 til 7 er lik "Leger"  folges den samme logikken ovenfor, og lege Objekt leses inn og lages
  linje = sc.nextLine();

  while (!linje.startsWith("#")) {

    String[] deler2 = linje.split(", ");

    navnLege = deler2[0];
    avtale = Integer.parseInt(deler2[1]);

    lagLege(navnLege, avtale);
    linje = sc.nextLine();
    if ("".equals(linje)) {
      break;
    }



  }
}
linje = sc.nextLine();


if (linje.substring(2,8).equals("Resept")) {//er bokstavene fra og med 2 til 8 lik "Resept", folges logikken fra tidligere, og resept objekt leses inn og lagres
linje = sc.nextLine();

while (!linje.startsWith("#")) {

  String[] deler3 = linje.split(", ");

  farge = deler3[1];
  persNummer = Integer.parseInt(deler3[2]);
  legeNavn = deler3[3];
  legemiddelNr = Integer.parseInt(deler3[4]);
  antallReit = Integer.parseInt(deler3[5]);

  lagResept(farge, persNummer, legeNavn, legemiddelNr, antallReit);
  linje = sc.nextLine();
  if ("".equals(linje)) {
    break;
  }



}
}

linje = sc.nextLine();

if (linje.equals("# Slutt")) {//om line tilsvarer dette, slutter innleseren aa gaa
  break;
  }

  }
}

public void skrivTil() throws Exception{// metoden som skriver til fil
  PrintWriter writer = new PrintWriter("fil2.txt");

  String formLegemiddel;
  String formResept;
  String klasseLegemiddel;
  //"tolker" - stringvariabler
  writer.println("# Personer (nr, navn, fnr, adresse, postnr)");//"overskriften" til personer

  for(Pasient printPasient : tabellPasient) {//for-each looke der "kloner" lages av alle pasient objektene i tabellPasient

    if (printPasient != null){//forhindrer nullPointerException
      writer.println(printPasient.unikt() + ", " + printPasient.toString() + ", " + printPasient.fodselsnummer() + ", " + printPasient.pasientAdresse());//skriver ut den aktuelle personen sin informasjon.
    }
  }
  writer.println("");//lager et mellomrom

  writer.println("# Legemidler (nr, navn, form, type, pris, antall/mengde, virkestoff [, styrke])");//overskriften til Legemidler
  for(Legemiddel printLegemiddel : tabellMed) {//for-each looke der "kloner" lages av alle Legemiddel objektene i tabellMed
    if (printLegemiddel instanceof LegemiddelC) {//om dette objektet er av typen legemiddelC
      klasseLegemiddel = "c";//denne "tolker"-stringen settes til "c"
      if(printLegemiddel.typeMed() == true) {//om metoden typeMed returnerer true (dvs at dette legemiddelet er i pilleform)
        formLegemiddel = "pille"; //denne "tolker"-stringen settes til "pille"

      }
      else {
        formLegemiddel = "mikstur";//om typeMed() er false, settes denne "tolker"-stringen til "mikstur"
      }

    writer.println(printLegemiddel.uniktTall() + ", " + printLegemiddel.toString() + ", " + formLegemiddel + ", " + klasseLegemiddel + ", " + printLegemiddel.medPris() + ", " + printLegemiddel.bareMengde() + ", " + printLegemiddel.bareVirkeStoff());
    //dette aktuelle legemiddelet av typen C sin informasjon skrives til fil
    }

    else if (printLegemiddel instanceof LegemiddelB) {//om det dog er et legemiddel av typen B
      klasseLegemiddel = "b";//denne "tolker"-stringen settes til b
      if(printLegemiddel.typeMed() == true) {//om metoden typeMed er true
        formLegemiddel = "pille";//denne "tolker"-stringen settes til "pille"

      }
      else {
        formLegemiddel = "mikstur";//om typeMed er false, settes formLegemiddel heller til "mikstur"
      }

    writer.print(printLegemiddel.uniktTall() + ", " + printLegemiddel.toString() + ", " + formLegemiddel + ", " + klasseLegemiddel + ", " + printLegemiddel.medPris() + ", " + printLegemiddel.bareMengde() + ", ");
    writer.println(printLegemiddel.bareVirkeStoff() + ", " + printLegemiddel.vanedanedeEffekt());
    //dette legemiddelet av type B sin aktuelle informasjon skrives til fil (inkludert vanedanende effeket)
    }

    else if (printLegemiddel instanceof LegemiddelA) {//om dette legemiddelet er av typen A foolges den samme logikken ovenfor
      klasseLegemiddel = "a";
      if(printLegemiddel.typeMed() == true) {
        formLegemiddel = "pille";

      }
      else {
        formLegemiddel = "mikstur";
      }

    writer.print(printLegemiddel.uniktTall() + ", " + printLegemiddel.toString() + ", " + formLegemiddel + ", " + klasseLegemiddel + ", " + printLegemiddel.medPris() + ", " + printLegemiddel.bareMengde() + ", ");
    writer.println(printLegemiddel.bareVirkeStoff() + ", " + printLegemiddel.narkotiskEffekt());
    }


  }
  writer.println("");//lager mellomrom

  writer.println("# Leger (navn, avtalenr / 0 hvis ingen avtale)");//overskriften til Leger

  for(Lege printLege: legeListe) {//for-each som lager "kloner" av alle lege objektene i legeListe
    writer.println(printLege.toString() + ", " + printLege.kommune());//den aktuelle legens informasjon skrives til fil
  }

  writer.println("");//mellomrom

  writer.println("# Resepter (nr, hvit/blaa, persNummer, legeNavn, legemiddelNummer, reit)");//overskrifen til Reseptene
  for(Resept printResept : oversikt) {//for-each som lager kloner av alle resept objektene i oversikt beholderen (som tar vare paa ALLE resept objektene)
    if (printResept instanceof BlaResept) {//om den aktuelle resepten er av typen BlaResept
      formResept = "blaa";//denne "tolker"-stringen settes til "blaa"
      writer.print(printResept.unikt() + ", " + formResept + ", " + printResept.pekPasient().unikt() + ", ");
      writer.println(printResept.pekLege().toString() + ", " + printResept.pekLegemiddel().unikt() + ", " + printResept.hvorMyeReit());
      //den aktuelle resepten sin informasjon skrives til fil
    }
    else if (printResept instanceof HvitResept) {//om den aktuelle resepten er av typen HvitResept
      formResept = "hvit";//denne "tolker"-variablen settes til "hvit"
      writer.print(printResept.unikt() + ", " + formResept + ", " + printResept.pekPasient().unikt() + ", ");
      writer.println(printResept.pekLege().toString() + ", " + printResept.pekLegemiddel().unikt() + ", " + printResept.hvorMyeReit());
      //denne reseptens aktuelle informason skives til fil
    }
  }
  writer.println("");//lager mellomrom
  writer.println("# Slutt");//signaliserer at utskrivingen er over
  writer.close();
  System.out.println("Skrevet til fil");//informerer brukeren om at det har blitt skrevet til fil

}





public void skrivAlt(){//skriver all informason til skjerm
  for(Pasient skrivUtPasient : tabellPasient){//en foreach som lager kopier av alle pasient objektene i tabellPasient
      if (skrivUtPasient != null){//forhindrer nullPointerException
        System.out.println("Pasientens unike tall: " + skrivUtPasient.unikt());
        System.out.println("Navn: " + skrivUtPasient.toString());
        System.out.println("");
        //pasientens navn, og unike tall skrives til skjerm
      }
    }
  for(Legemiddel skrivUtLegemiddel : tabellMed){
    if (skrivUtLegemiddel != null){//forhninder nullPointerException
      System.out.println("Legemiddelets unike tall: " + skrivUtLegemiddel.unikt());
      System.out.println("Legemiddelets navn: " + skrivUtLegemiddel.toString());
      System.out.println("");
      //legenmiddeltes unike tall, og navn skrives til skjerm
    }
  }
  for(Lege skrivUtLege : legeListe){
    System.out.println("Legens navn: " + skrivUtLege.toString());
    System.out.println("");
    //legens navn skrives til skjerm
  }
  for(Resept skrivUtResept : oversikt){
    System.out.println("Reseptens unike tall: " + skrivUtResept.unikt());
    System.out.println("");
    //Reseptens unike tall skrives til skjerm
  }
}


public void meny() throws Untak, Exception {//kommandolooken
  Scanner tast = new Scanner(System.in);//leser inn brukerens input
  int valg = 0;

  System.out.println("\n\n=== MENY ===");
  System.out.println("1: Ny pasient");
  System.out.println("2: Nytt legemiddel");
  System.out.println("3: Ny lege");
  System.out.println("4: Ny resept");
  System.out.println("5: Hent legemiddel");
  System.out.println("6: Skriv til fil");
  System.out.println("7: Generer statistikk");
  System.out.println("8: Organiser ");
  //valgene brukeren har




  while((valg = Integer.parseInt(tast.nextLine())) != 9) {//kan velge mellom 0 0g 9
    System.out.println("Du har valgt " + valg);
    System.out.println();
    switch(valg) {

      case 1://en pasient lages fra brukerens input

      System.out.println("Hva heter pasienten?");
      navn = skriv.nextLine();//pasientens navn
      System.out.println("Fodselsnummer? 11 siffer.");
      fodselsNr = skriv.nextLine();//pasientens Fodselsnummer
      lengde = String.valueOf(fodselsNr).length();//lengden pa Fodselsnummeret
      if (lengde != 11) {
        while (lengde != 11) {//om Fodselsnummeret ikke er 11 siffer faar brukeren flere forsok
          System.out.println("Fodselsnummeret maa veare 11 siffer. Prov igjen");
          fodselsNr = skriv.nextLine();
          lengde = String.valueOf(fodselsNr).length();
          if (lengde == 11) {
            break;
          }
        }

      }
      System.out.println("Hva er postnummeret til pasienten?");
      postNr = skriv.nextLine();//postnummeret til pasienten
      lengde2 = String.valueOf(postNr).length();
      if(lengde2 != 4) {
        while (lengde2 != 4) {//om lengden paa postnummeret ikke er 4 faar brukeren flere forsok
          System.out.println("Postnummeret maa veare 4 siffer. Prov igjen ");
          postNr = skriv.nextLine();
          lengde2 = String.valueOf(postNr).length();
          if (lengde2 == 4) {
            break;
          }
        }

      }
      System.out.println("Hva er adressen til pasienten");
      adresse = skriv.nextLine();//pasientens adresse

      lagPasient(navn, fodselsNr, adresse, postNr);//metoden lag pasient kalles paa, og en pasient lages


      break;

      case 2:

      System.out.println("Hva heter legemiddelet?");
      navnLegemiddel = skriv.nextLine();//legemiddelets navn
      System.out.println("Er legemiddelet i pille eller miksturform. For pille trykk 1, for mikstur trykk 2");
      valg = Integer.parseInt(skriv.nextLine());//brukeren faar velge om legemiddelet er i pille(1) eller mikstur form(2)
      if (valg != 1 && valg != 2) {//om brukeren ikke har valgt 1 eller 2 faar vedkommende flere forsok til det er tilfellet
        while (valg !=1 && valg != 2) {
          System.out.println("Ugyldig valg. Prov igjen");
          valg = Integer.parseInt(skriv.nextLine());
        }
      }

      if (valg == 1){//om valget er 1, settes boolean pille til true
        pille = true;
      }
      else if (valg == 2) {//om valget er 2, settes boolean pille til false
        pille = false;
      }
      System.out.println("Er legemiddelet av type A, B eller C");

      typeLegemiddel = skriv.nextLine();//brukeren faar velge om legemiddelet er av type  A, B eller C

      if(!typeLegemiddel.equalsIgnoreCase("a") && !typeLegemiddel.equalsIgnoreCase("b") && !typeLegemiddel.equalsIgnoreCase("c")) {
        while (!typeLegemiddel.equalsIgnoreCase("a") && !typeLegemiddel.equalsIgnoreCase("b") && !typeLegemiddel.equalsIgnoreCase("c")){//om brukeren ikke har tastet inn a, b eller c som sitt valg, faar vedkommende flere forsok til det er tilfellet
          System.out.println("Ugylig valg. Prov igjen");
          typeLegemiddel = skriv.nextLine();
        }
      }
      System.out.println("Pris?");
      pris = Integer.parseInt(skriv.nextLine());//legemiddelets pris
      if (pille == true) {//hvis pille er true
        System.out.println("Hvor mange piller er det i en pakke?");
        mengde = Integer.parseInt(skriv.nextLine());//antall piller i en eske
      }
      else if (pille == false){//om pille er false (det er en mikstur)
        System.out.println("Hva er volumet pa miksturflasken?");
        mengde = Integer.parseInt(skriv.nextLine());//volumet av flasken
      }
      System.out.println("Hvor mye virkestoff?");
      virkestoff = Integer.parseInt(skriv.nextLine());//virkestoffet

      if(typeLegemiddel.equals("a")) {//om legemiddelet er av typen A
        System.out.println("Hva er den narkotiske effekten?");
        effekt = Integer.parseInt(skriv.nextLine());//legemiddelets narkotiske effekt
      }
      else if (typeLegemiddel.equals("b")){//om legemiddelet er av typen B
        System.out.println("Hva er den vanedanende effekten?");
        effekt = Integer.parseInt(skriv.nextLine());// legemiddelets vanedanende effekt

      }


      lagLegemiddel(navnLegemiddel, pille, typeLegemiddel, pris, mengde, virkestoff, effekt);//legemiddelet lages
      break;

      case 3://leger lages fra brukerens input
      System.out.println("Hva heter legen");
      legeNavn = skriv.nextLine();//legens navn
      System.out.println("Hva er avtalenummeret til legen. Hvis legen ikke har et, tast 0");
      avtale = Integer.parseInt(skriv.nextLine());//legens avtalenummer
      lagLege(legeNavn, avtale);// metoden lagLege kalles
      break;


      case 4://resepter lages
      System.out.println("Hvilken type resept er det? For blaa tast 1, for hvit tast 2");
      valg = Integer.parseInt(skriv.nextLine());//type resept. 1 for blaa, 2 for hvit
      if (valg != 1 && valg != 2) {//om valget hverken er 1 eller 2, faar brukeren flere forsok til det er tilfellet
        while (valg !=1 && valg != 2) {
          System.out.println("Ugyldig valg. Prov igjen");
          valg = Integer.parseInt(skriv.nextLine());
        }
      }

      if (valg == 1){//om valg er 1
        farge = "blaa";//farge settes til bla
      }
      else if (valg == 2) {
        farge = "hvit";//ellers settes farge til hvit
      }




      System.out.println("Skriv inn det unike nummeret til vedkommende. Dette tallet ma veare fra og med 0 til " + uniktTallPasient + ".");
      finnUnikt = Integer.parseInt(skriv.nextLine());//brukeren taster inn vedkommendes unike nummer
      if (finnUnikt > uniktTallPasient || finnUnikt <= -1) {//taster inn et ugyldig unikt nummer.
        System.out.println("Dette er et ugyldig valg");
        break;

      }
      else if(tabellPasient.finn(finnUnikt) == null){//om det unike nummeret peker paa en pasient som ikke finnes
        System.out.println("Denne pasienten finnes dessverre ikke.");
        break;
      }

      System.out.println("Hva heter legen som skriver ut resepten?");
      navnLege = skriv.nextLine();//taster inn en leges navn
      if(legeListe.finn(navnLege) == null) {//om denne legen ikke finnes
        System.out.println("Denne legen finnes ikke");
        break;
      }




      System.out.println("Skriv inn det unike nummeret til legemiddelet. Dette tallet ma veare fra og med 0 til " + uniktTallLegemiddel + ".");
      legemiddelNr = Integer.parseInt(skriv.nextLine());//taster inn det unike nummeret til et legemiddel
      if (legemiddelNr > uniktTallLegemiddel || legemiddelNr <= -1) {//om et ugyldig legemiddel nummer tastes inn
        System.out.println("Dette er et ugyldig valg");
        return;

      }
      else if(tabellMed.finn(legemiddelNr) == null){//om det unike nummeret peker paa et legemiddel som ikke finnes
        System.out.println("Dette legemiddelet finnes dessverre ikke.");
        return;
      }

      System.out.println("Hva er reiten til resepten?");
      antallReit = Integer.parseInt(skriv.nextLine());//taste inn reiten til resepten
      if (antallReit < 0){
        while(antallReit < 0) {//om reiten er mindre enn 0, faar brukeren flere forsok tild et er tilfellet
        System.out.println("Ugyldig valg. Ma veare storre enn null");
        antallReit = Integer.parseInt(skriv.nextLine());

        }

      }

      lagResept(farge, finnUnikt, navnLege, legemiddelNr, antallReit);//lagResept metoden kalles paa
      break;

      case 5://brukResept kalles paa
      brukResept();
      break;

      case 6://skrivTil kalles paa
      skrivTil();
      break;

      case 7://diverse statistikk genereres

      for(Resept lop: oversikt){//en foreach looke for overrsiktbeholderen som inneholder alle reseptene
        if(lop.pekLegemiddel() instanceof LegemiddelB ){//hvis den aktuelle reseptens peker til et legemiddel er av typen B

          forsteSiffer = Integer.parseInt(lop.pekPasient().postNummer().substring(0,1));
          andreSiffer = Integer.parseInt(lop.pekPasient().postNummer().substring(1,2));
          //postnummerets forste og andre siffer settes til sine henholdsvise int variabler (etter at de parses). postNummer og fodselsnummerene til pasientene er i string format, saa atde kan starte paa 0


          tellerVanedanede++;


          if (forsteSiffer == 0) {//bare postnummere i oslo starter paa 0
            tellerVanedanedeOslo++;
          }
          else if (forsteSiffer == 1 && (andreSiffer >= 0 && andreSiffer <= 2)) {//hvis forsteSiffer er 1, og andre siffer ikke er storre enn tre ooker ogsaa tellerVanedanedeOslo med 1
            tellerVanedanedeOslo++;

          }

        }


      }
      System.out.println("Det har blitt skrevet ut " + tellerVanedanede + " vanedanende resepter. " + tellerVanedanedeOslo + " i Oslo.");//antall vanedanende resepter skrives ut, samt antallet i Oslo

      System.out.println("Hvilken pasient sine bla resepter onsker du aa se? Tast inn det unike nummeret");
      finnUnikt = Integer.parseInt(skriv.nextLine());//brukeren taster inn pasientens unike nummer, og faar eventulle feilmeldinger om noe gikk galt
      if (finnUnikt > uniktTallPasient || finnUnikt <= -1) {
        System.out.println("Dette er et ugyldig valg");
        return;

      }
      else if(tabellPasient.finn(finnUnikt) == null){
        System.out.println("Denne pasienten finnes dessverre ikke.");
        return;

      }
      aktuellPasient = tabellPasient.finn(finnUnikt);//aktuellPasient settes til pasienten i tabelPasient med det samme unike nummeret

      aktuellPasient.skrivUtYngsteBla();//denne pasientens bla respeter skrives ut, yngste forst

      System.out.println("");
      System.out.println("Alle legene: ");
      System.out.println("");

      Iterator legeIterator = legeListe.iterator();//iterator av legeListe (SortertEnkelListe)

      while(legeIterator.hasNext()) {//sa lenge hasNext() returnerer true, gaar loopen
        System.out.println(legeIterator.next().toString());//skriver ut next() sin toString.
      }
      //navnene til alle legene skrives ut til skjerm saa at brukeren kan velge

      System.out.println("Hvilken lege? Skriv inn vedkommendes navn.");
      navnLege = skriv.nextLine();//taster navnet pa den onskede legen

      if(legeListe.finn(navnLege) == null) {//om legen ikke finnes faar brukeren flere forsok til det er tiflellet
        while(legeListe.finn(navnLege) == null)
        System.out.println("Denne legen finnes ikke. Prov igjen");
        navnLege = skriv.nextLine();

      }

      aktuellLege = legeListe.finn(navnLege);//aktuell lege settes til legen i legeListe med det samme navnet
      System.out.println("");
      aktuellLege.skrivUtEldstMikstur();//legens resepter pa miksturpreparater skrives til skjerm, eldste forst
      System.out.println("");
      aktuellLege.skrivUtTotalVikr();//summen av virkemiddlene skrives ut, samt hvor mye som er i pille form, og hvor mye som er i mikstur

      System.out.println("");
      System.out.println("Alle leger som har skrevet ut resepter pa narkotiske legemiddel");

      for(Lege a : legeListe){//foreach looke som gaar gjennom legeliste
        if(a.harSkrevetNarkotisk() == true){//om den aktuelle legen har skrevet ut narkotiske respeter (ugyldig eller ikke)
          System.out.println(a.toString() + ": " + a.antallNarkotiskeResepter());//navnet pa legen, og antall narkotiske resepter han/hun har skrevet skrives til skerm
        }
      }

      System.out.println("");
      System.out.println("Alle pasienter som har minst en gydlig resept pa narkotiske legemiddel");

      for(Pasient b : tabellPasient){//foreach som gaar gjennom tabelPasient
        if(b != null && b.harNarkotisk() == true ){//nullPointerException hindres. Sjekker om pasienten har misnt en gyldig narkotisk resept
          System.out.println(b.toString() + ": " + b.antallNarkotiskeResepter());//alle pasientene med minst en gyldig resept skrives til skjer, og antallet hver
        }
      }
      break;

      case 8://metoden skrivAlt() kalles paa (skrives til skjerm)
      skrivAlt();
      break;


      default:
      System.out.println("Ugyldig valg");
      break;

    }
  }
}
}
