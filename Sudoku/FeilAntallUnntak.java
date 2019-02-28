import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

class FeilAntallUnntak extends Exception {//untaksklasse
  public FeilAntallUnntak(int meld) {//konstruktoren som printer en feilmelding
      System.out.println("Feil, det er feil antall ruter i brettet");
  }
}
