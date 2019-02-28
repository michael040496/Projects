import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Rad {//rad klassen
  private Rute [] radVerdier; //en array som holder ruteverdien i den aktuelle raden
  private int uniktNr;//radens unike nummer


  public Rad(int unik, Rute [] verdier){//konstruktoren
    radVerdier = verdier;
    uniktNr = unik;
  }

  public Rute [] radArr(){//returnerer verdiene raden holder
    return radVerdier;
  }

  public int hvilkenRad(){//returnerer radens unike tall
    return uniktNr;
  }
}
