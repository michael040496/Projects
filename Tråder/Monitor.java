import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.concurrent.*;


public class Monitor{

  private boolean full = false;
  private int teller = 0;
  private final int maks = 2;
   Ord[][] liste;
      Ord[][] gi;

   Ord [] tabell;


  synchronized void giSortert(Ord[] aktuell){
    while(teller >= maks){
      try {
        System.out.println("w pa tabell");

        wait();
      }
      catch (InterruptedException e){}
    }

    tabell = aktuell;


    liste = new Ord[2][tabell.length];

    System.out.println(tabell.length);
    liste[teller] = aktuell;
    teller++;


    System.out.println(teller + " tabeller er sortert");


    notify();





  }


  synchronized Ord[][] hentSortert(){
    while(teller < maks){
      try {
        System.out.println("Venter pa tabell");

        wait();
      }

      catch(InterruptedException e){}
    }
    teller = 0;


    notify();


    return liste;
  }

  public int storrelse(){
  return tabell.length;
}

}
