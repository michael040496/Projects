import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

class ForStortBrettUnntak extends Exception {//untaksklasse
  public ForStortBrettUnntak(int meld) {//konstruktoren som printer en feilmelding
    System.out.println("Feil, dette brettet er for stort. Det er " + meld + ", men ma veare hoyst 64x64");
  }
}
