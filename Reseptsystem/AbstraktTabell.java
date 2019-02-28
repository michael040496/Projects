import	java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

interface AbstraktTabell<T> extends Iterable<T> {//Det generiske grensesnittet AbstraktTabell som extender Iterable<T>
public boolean settInn(int plass, T aktuell);
public T finn(int plass);
//grensesnittets aktuelle metoder



}
