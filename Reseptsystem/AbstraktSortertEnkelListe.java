import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

interface AbstraktSortertEnkelListe<T extends Comparable<T>, Lik> extends Iterable<T> {//Det generiske grensesnittet AbstraktSortertEnkel Liste som extender Iterable<T> og har begrensede typeparametere
public void settInnSortert(T aktuell);
public T finn(String nokkel);
//grensesnittets aktuelle metoder


}
