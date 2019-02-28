import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Boks {
  private Rute [] boksVerdier;//array som holder verdiene til den aktuelle boksen
  private int uniktNr;//boksens unike nummer


  public Boks(int unik, Rute [] verdier){//konstruktoren
    boksVerdier = verdier;
    uniktNr = unik;
  }

  public Rute [] boksArr(){//returnerer verdiene boksen holder pa
    return boksVerdier;
  }

  public int hvilkenBoks(){//returnerer boksens unike nummmer
    return uniktNr;
  }
}
