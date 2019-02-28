import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.concurrent.*;


public class Traad extends Thread{

  Ord[] tabell;
  Ord[] forste;
  Ord[] andre;
  int st;
  int en;
  int maksStorrelse;
  int antGanger = 0;
  Monitor monitor;
  Ord temp;
  CountDownLatch barriere;

  Traad(int maksStorrelse, Ord[] tabell, int st, int en, Monitor monitor, CountDownLatch barriere){
    this.tabell = tabell;
    this.st = st;
    this.en = en;
    this.monitor = monitor;
    this.maksStorrelse = maksStorrelse;
    this.barriere = barriere;
  }

  public void run() {


    int teller = en-st;
    Ord [] a = new Ord[teller];

    for(int f = 0; f<teller; f++){
      a[f] = tabell[st + f];
    }


    for(int i = 0; i < teller; i++){
      for(int j = i+1; j < teller; j++){
        if(a[i].toString().compareTo(a[j].toString()) > 0){
          temp = a[j];
          a[j] = a[i];
          a[i] = temp;
        }
      }
    }

    monitor.giSortert(a);
    try {
      sleep((long) (1000 * Math.random()));
    }
    catch (InterruptedException e) {}
      Ord[][] liste = monitor.hentSortert();


  forste = liste[1];

  andre = liste[0];


      try {
        sleep((long) (1000 * Math.random()));
      }
      catch (InterruptedException e) {}


    while(monitor.storrelse() < maksStorrelse){


        Ord[] nyTabell = new Ord[forste.length + andre.length];
        liste = new Ord[2][nyTabell.length];

        for (int k = 0; k < forste.length; k++){

          nyTabell[k] = forste[k];
        }

        for (int l = 0; l < andre.length; l++){
          nyTabell[forste.length + l] = forste[l];
        }

        for(int m = 0; m < nyTabell.length; m++){
          for(int n = m+1; n < nyTabell.length; n++){
            if(nyTabell[m].toString().compareTo(nyTabell[n].toString()) > 0){
              temp = nyTabell[n];
              nyTabell[n] = nyTabell[m];
              nyTabell[m] = temp;
            }
          }
        }


        monitor.giSortert(nyTabell);


                try {
                  sleep((long) (1000 * Math.random()));

                }
                catch (InterruptedException e) {}

                  liste = monitor.hentSortert();



                forste = liste[0];
                andre = liste[1];
                try {
                  sleep((long) (1000 * Math.random()));
                }
                catch (InterruptedException e) {}







      }
    }

}
