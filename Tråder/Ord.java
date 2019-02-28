import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.concurrent.*;


public class Ord implements Comparable<String>{

  String ord;

  Ord(String ord){
    this.ord = ord;
  }

  public int compareTo(String word){
    return ord.compareTo(word);
  }

  @Override
  public String toString(){
    return ord;
  }
}
