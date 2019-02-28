import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

class UgyldigVerdiUnntak extends Exception {//untaksklasse
  public UgyldigVerdiUnntak(int meld) {//konstruktoren som printer en feilmelding
    System.out.println("Verdien " + meld + " er en ugyldig verdi for dette brettet");
  }
}
