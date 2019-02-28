import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.concurrent.*;


public class Main{
  public static void main(String[] args) throws IOException, Exception{

    int antTraader = Integer.parseInt(args[0]);
    File fil = new File(args[1]);
    Scanner scanner = new Scanner(fil);
    int antOrd = Integer.parseInt(scanner.nextLine());
    System.out.println(antOrd);
    Ord[] ord = new Ord[antOrd];
    int teller = 0;
    int n = antOrd/antTraader;
    Monitor monitor = new Monitor();
    String linje;
    CountDownLatch minBarriere = new CountDownLatch(antTraader);


    while(scanner.hasNextLine()){
      linje = scanner.nextLine();
      ord[teller] = new Ord(linje);
      teller++;
    }

    for(int i = 0; i < antTraader; i++){

      new Traad(antOrd, ord, i*n, ((i+1)*n)-1, monitor, minBarriere).start();
    }
  }

}
