import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;


public class Main {
  public static void main (String [] args) throws Exception {
    Kontroll ny = new Kontroll();
    ny.les("fil.txt");
    ny.meny();
  }
}
