import java.util.Scanner;
import java.io.File;

public interface Type {//grensesnitt som implementeres av LegemiddelA, LegemiddelB og LegemiddelC. Sjekker om en medisin er i pilleform eller mikstur.
  public boolean typeMed();
   public void mengdeMed();
 }
