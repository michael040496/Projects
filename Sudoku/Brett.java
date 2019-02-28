import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;


public class Brett{//klassen brett

  boolean lestInn = false;
  int tall1;
  int tall2;
  int n;
  int kvadrat;
  String linje;
  Rute [][] sudo ;
  Rute [][] boks ;
  int teller = 0;
  int count = 0;
  int storrelseBrett = 0;
  final char TOM_RUTE_TEGN = '.';
  //de aktuelle variabelene


  public void lesInn(String fil)  throws UgyldigVerdiUnntak, FeilAntallUnntak, IOException, ForStortBrettUnntak {//innleser metoden

    Scanner sc = new Scanner(new File(fil));
    //skanner variabelen som leser inn den aktuelle filen
    if(!new File(fil).exists()){//kaster unntak om filen ikke finnes

     throw new FileNotFoundException(fil);

    }


    tall1 = Integer.parseInt(sc.nextLine());
    tall2 = Integer.parseInt(sc.nextLine());
    //tall1 og tall2 settes henholdsvis til den forste og andre linjen i filen

    n = tall1 * tall2;//n er produktet av tall1 og tall2
    kvadrat = n* n;
    sudo = new Rute [n][n];//en todimensjonell array, med storrelse n*n



    int a = 0;// en teller
		while (sc.hasNextLine()){//saa lenge filen har flere liner gaar loopen
      if (a < n){//sa lenge a er mindre enn n utfores handlingene nedenfor
      linje = sc.nextLine();





        char [] deler = linje.replaceAll("\\s","").toCharArray();//linjen som leses inn deles i char-variabler, der mellomrom fjernes



        if (deler.length != n){//om lengden paa en linje ikke er n, vil et unntak kastes (feil antall elementer i en rad)
          throw new FeilAntallUnntak(deler.length);
        }

				for (int b = 0; b < n; b++){
            tegnTilVerdi(deler[b]);//den aktuelle charen gjores om til sin tilsvarende int verdi
            storrelseBrett ++;

            if (tegnTilVerdi(deler[b]) != (-1) && tegnTilVerdi(deler[b]) <= n) {//hvis tegnTilVerdi ikke er -1 (ugyldig verdi), og den er mindre eller lik n, utfores handlingene nedenfor
					       sudo[a][b] = new Rute(count+1, tegnTilVerdi(deler[b]));//sudo[a][b] settes til et nytt ruteelement, som tar inn count (en teller) +1 som unikt nummer, og den aktuelle tegnTilVerdi som verdi
                 count++;
           }

            else if(tegnTilVerdi(deler[b]) > n || tegnTilVerdi(deler[b]) == (-1)) {//om tegntilverdi er storre enn n, ruten har en verdi som ikke er gyldig i det aktuelle sudoku brettet, eller tegntilverdi er -1, kastes et unntak
              throw new UgyldigVerdiUnntak(deler[b]);
            }
          }
          a++;
      }
    }
    lestInn = true;//lesInn settes som true
    if (storrelseBrett > 64 *64){
      throw new ForStortBrettUnntak(storrelseBrett);
    }

  }

  public void skrivTil() throws Exception{// metoden som skriver til fil
  String fil2 = "fil.txt";//navnen paa filen det skrives til
  PrintWriter writer = new PrintWriter(new File(fil2));
  char empty = ' ';
  int tellerKol = 0;
  int tellerDel = 0;
  int tellerRad = 0;
  int tellerSplit = 0;
  int tellerHorisontal = 0;
  int tellerVertikal = 0;
  //de aktuelle variabelene som brukes i metoden

  for (int x = 0; x<n; x++){
    for (int z = 0; z<n; z++){
      writer.print(verdiTilTegn(sudo[x][z].verdiTilRute(), ' '));//det som returneeres av verdiTilTegn, naar begge for-lookene gaar gjennom den todimensjonale arrayen sudo, skrives til fil

      if (tellerKol == tall2-1 && tellerDel < tall1-1){//skriver en | for a skille boksene i utskriftfilen. Det skjer for hvert tall2 - 1 (teller begynner fra 0) element i raden. For at en | ikke skrives pa slutten av raden ogsa, sjekker if'en om tellerDel er mindre enn tall1 - 1
        writer.print("|");
        tellerKol = 0;//tellerKol settes til 0 igjen, sa at den begynner "tellingen" pa nytt
        tellerDel++;//oker med en hvergang denne if'en er sann. Denne telleren forhindreer at en | ikke skrives pa slutten av raden
      }

      else if(tellerKol < tall2-1){//om tellerKol er mindre enn tall2 - 1, ooker den med 1 for hvergang looken gaar og denne tilstanden er sann
        tellerKol++;
      }

    }
    writer.println();
    tellerDel = 0;
    tellerKol = 0;
    //begge settes til 0 igjen, sa at de "begynner tellingen pa nytt" neste gang

    if (tellerHorisontal < tall1){//sjekker om tellerHorisontal er mindre enn tall1. Denne teller brukes sa at den siste raden i filen ikke etterfolges av et horisontalt skille
      if(tellerRad == tall1-1){
        tellerRad = 0;//teller antall rader som skal skrives foor et horisontalt skille
        tellerVertikal = 0;//passer pa at en "+" ikke skrives pa enden av et horisontalt skille
        tellerSplit = 0;//teller hvor mange "-" som skal skrives foor en "+"


        for(int y = 0; y<n; y++){
          writer.print("-");//lager et horisontalt skille mellom to bokser som staar under og over hverandre
          if (tellerSplit == tall2-1 && tellerVertikal < tall1-1){//folger samme logikk som ovenfor
            writer.print("+");//deler det horisontale skillet i tall2 - 1 antall deler
            tellerSplit = 0;
            tellerVertikal++;
            tellerHorisontal++;
          }

        else if(tellerRad < tall2-1){
          tellerSplit++;

        }
      }


      writer.println();//begynner en ny linje

    }
    else{
    tellerRad++;
    }
  }
}
  writer.close();
}




  public Rute [][] returnBrett(){//returnerer brettet om lestInn er true
    if (lestInn == true){
    return sudo;
    }
    return null;
  }




	public void lagDataStruktur (){



    for (int c = 0; c < n; c++){//gaar gjennom sudoArrayen, og lage rader
      Rute [] rad = new Rute[n]; //arrayen som holder verdiene til den aktuelle raden

      for(int d = 0; d < n; d++){
        rad[d] = sudo[c][d];//sammen med for-looken ovenfor gaar denne gjennom sudo rad for rad, og kolone for kolone. Tilordner de riktige verdiene til en rad, til den raden

      }



        Rad aktuellR = new Rad(c+1, rad);//et radelement lages med c+1 som unikt nummer, og den naaverende rad-arrayen som verdier
        for (int e = 0; e < n; e++){//gaar saa gjennom elementene i den aktuelle raden i sudoArrayen, og tilordner denne raden til de rutene som tilhorerer den

          sudo[c][e].tilordneRad(aktuellR);
        }
      }


        for (int f = 0; f < n; f++){//gaar gjennom sudoArrayen, og lage koloner
          Rute  [] kolone = new Rute[n]; //arrayen som holder verdiene til den aktuelle kolonen

          for(int g = 0; g < n; g++){
            kolone[g] = sudo[g][f];//sammen med for-looken ovenfor gaar denne gjennom sudo rad for rad, og kolone for kolone. Tilordner de riktige verdiene til en kolone, til den kolonen
            }



            Kolone aktuellK = new Kolone(f+1, kolone);//et koloneelement lages med f+1 som unikt nummer, og den naaverende kolone-arrayen som verdier
            for (int h = 0; h < n; h++){//gaar saa gjennom elementene i den aktuelle kolonen i sudoArrayen, og tilordner denne kolonen til de rutene som tilhorerer den
              sudo[h][f].tilordneKolone(aktuellK);
            }
          }


          if (tall2 >= tall1){
            for (int i = 0; i < tall2; i++){
              for (int j = 0; j < tall1; j++){

                  Rute [] boks = new Rute [n];//arrayen som holder verdiene til den aktuelle boksen. Storrelse n (tall1 * tall2)

                for (int radA = i * tall1; radA < i * tall1 + tall1; radA++){//denne for-looken begynner pa tall1 * i, og fortsetter tall1 ganger. Den overste for-looken maa gaa tall1 ganger om tall1 er mindre enn tall2, eller tall2 ganger om tall2 er mindre enn tall1. Med andre ord maa den overste for-looken gaa ferre ganger enn den nederste
                  for (int koloneA = j * tall2; koloneA < j * tall2 + tall2; koloneA++){//denne for-looken begynner pa tall2 * j, og fortsetter tall2 ganger
                  //ved at den overste for-looken begynner pa tall1 * i, og gaar en iterasjon (tall1 ganger) hver gang den nederste for-looke har gaat tall2 ganger, vil tall2 elementer hentes vertikalt, foor tall2 elementer hentes fra neste rad. Dette skjer da tall1 ganger. Foor neste iterasjon skjer i for-looken over disse to igjen
                  //pa den maaten vil elementene hentes i "boks"-rekkefolger

                  boks[teller] = sudo[radA][koloneA];//den aktuelle boksArrayen lages
                  teller++;

                }
              }



              Boks aktuellBoks = new Boks(teller, boks);//et koloneelement lages med j+1 som unikt nummer, og den naaverende boks-arrayen som verdier
              teller = 0;



            for (int radB = i * tall1; radB < i * tall1 + tall1; radB++){
              for (int koloneB = j * tall2; koloneB < j * tall2 + tall2; koloneB++){//gaar saa gjennom elementene i den aktuelle "boksen" i sudoArrayen, og tilordner denne boksen til de rutene som tilhorerer den
                sudo[radB][koloneB].tilordneBoks(aktuellBoks);
              }
            }
          }
        }
      }





        else if (tall1 > tall2){//folger samme logikk som ovenfor, men her er tall1 storre en tall2, saa de to for-looken som gaar gjennom sudo i "boks - moonster" er forandret pa
          for (int i = 0; i < tall1; i++){
            for (int j = 0; j < tall2; j++){

              Rute [] boks = new Rute [n];//arrayen som holder verdiene til den aktuelle boksen. Storrelse n (tall1 * tall2)

              for (int radA = i * tall2; radA < i * tall2 + tall2; radA++){//her er radA produktet av tall2 og i, istedenfor tall1 og i. Denne looken gaar ogsa tall2 ganger, og ikke tall1 ganger
                for (int koloneA = j * tall1; koloneA < j * tall1 + tall1; koloneA++){//her er KoloneA produktet av tall1 og j, istedenfor tall2 og j. Denne looken gaar ogsa tall1 ganger, og ikke tall2 ganger

                  boks[teller] = sudo[radA][koloneA];
                  teller++;

                }
              }

          Boks aktuellBoks = new Boks(teller, boks);
          teller = 0;


          for (int radB = i * tall2; radB < i * tall2 + tall2; radB++){
            for (int koloneB = j * tall1; koloneB < j * tall1 + tall1; koloneB++){
              sudo[radB][koloneB].tilordneBoks(aktuellBoks);
            }
          }
        }
      }
    }
  }



        public  int tegnTilVerdi(char tegn) {
            if (tegn == TOM_RUTE_TEGN) {                // tom rute, DENNE KONSTANTEN MAA DEKLARERES
                return 0;
            } else if ('1' <= tegn && tegn <= '9') {    // tegn er i [1, 9]
                return tegn - '0';
            } else if ('A' <= tegn && tegn <= 'Z') {    // tegn er i [A, Z]
                return tegn - 'A' + 10;
            } else if (tegn == '@') {                   // tegn er @
                return 36;
            } else if (tegn == '#') {                   // tegn er #
                return 37;
            } else if (tegn == '&') {                   // tegn er &
                return 38;
            } else if ('a' <= tegn && tegn <= 'z') {    // tegn er i [a, z]
                return tegn - 'a' + 39;
            } else {                                    // tegn er ugyldig
                return -1;
            }
        }

        /**
         * Oversetter en tallverdi (int) til et tegn (char)
         *
         * @param verdi     verdien som skal oversettes
         * @param tom       tegnet som brukes for Ã¥ representere 0 (tom rute)
         * @return          tegnet som verdien tilsvarer
         */
        public  char verdiTilTegn(int verdi, char tom) throws UgyldigVerdiUnntak {

            if (verdi == 0) {                           // tom
                return tom;
            } else if (1 <= verdi && verdi <= 9) {      // tegn er i [1, 9]
                return (char) (verdi + '0');
            } else if (10 <= verdi && verdi <= 35) {    // tegn er i [A, Z]
                return (char) (verdi + 'A' - 10);
            } else if (verdi == 36) {                   // tegn er @
                return '@';
            } else if (verdi == 37) {                   // tegn er #
                return '#';
            } else if (verdi == 38) {                   // tegn er &
                return '&';
            } else if (39 <= verdi && verdi <= 64) {    // tegn er i [a, z]
                return (char) (verdi + 'a' - 39);
            } else {                                    // tegn er ugyldig
                throw new UgyldigVerdiUnntak(verdi);    // HUSK DEFINISJON AV UNNTAKSKLASSE
            }
        }
}
