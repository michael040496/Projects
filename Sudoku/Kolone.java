import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Kolone {//kolone klassen
  private Rute [] koloneVerdier;//en array som holder verdiene til kolonen
  private int uniktNr;//koloenens unike tall


  public Kolone(int unik, Rute [] verdier){//konstruktoren
    koloneVerdier = verdier;
    uniktNr = unik;
  }

  public Rute [] koloneArr(){//returnerer verdiene kolonen holder
    return koloneVerdier;
  }

  public int hvilkenKolone(){//returner kolonens unike nummer
    return uniktNr;
  }
}
