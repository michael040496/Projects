import java.util.*;

public class task11 {
    public static void main(String[] args) {

        task11 t11 = new task11();
        int str = 8;

        String[][] brett = new String[str][str];

        for (int i = 0; i < str; i++) { //fills the array with "o'" for an empty board
            for (int j = 0; j < str; j++) {
                brett[i][j] = "o";
            }
        }

        t11.matprint(brett); //method for printing out the board
       
            System.out.println("Write coordinate");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine(); //takes inn user input

            int[] pos = t11.translate(input); //the method that translates the input in algeabraic notations, into ints we can work with

            if (t11.checkPlacable(brett, str, pos[0], pos[1])) { //an if calling a method that checks if the queen is placable at the square
                brett[pos[0]][pos[1]] = "x"; //places the queen as an "X"
            }

            t11.matprint(brett);

            boolean solv = t11.solve(brett, 0, str, pos[1]); 

            t11.matprint(brett);

    }

    boolean solve(String brett[][], int column, int str, int strtCol) {/*a recursive method that creates a solution 
        based on the user input (the algeabraic coordinates)*/

        if (column >= str) {
            System.out.println(" ");
            return true;
        }

            for (int i = 0; i < str; i++) {

                if (checkPlacable(brett, str, i, column) || column == strtCol) {

                    if(column != strtCol) {//the checkPlaceable method  finds where the 7 other queens can be placed, by eliminating positions in the same row, column and diagonally
                        brett[i][column] = "x"; //if the queen can be placed, the square is set as "x"
                    }

                    if (solve(brett, column + 1, str, strtCol) == true)//recursion
                        return true;

                    brett[i][column] = "o"; //if a queen cant be placed, the square will be set as "O"
                }
            }
        return false;
    }

    public boolean checkPlacable(String[][] brett, int str, int row, int column) {
        // checks the whole column for the coordinate
        for (int i = 0; i < str; i++) {
            
            if ((brett[i][column]).equals("x")) {
                return false;
            }
        }
        // checks the whole row for the coordinate
        for (int i = 0; i < str; i++) {
            if ((brett[row][i]).equals("x")) {
                return false;
            }
        }
        // checks the diagonals of the coordinate
        // upright
        for (int i = 0; i < str; i++) {

            if ((row - i) < 0 || (column + i >= str)) break;
            if ((brett[row - i][column + i]).equals("x")) {
                return false;
            }
        }
        //downleft
        for (int i = 0; i < str; i++) {

            if ((row + i) >= str || ((column - i) < 0)) break;

            if ((brett[row + i][column - i]).equals("x")) {
                return false;
            }
        }

        // upleft
        for (int i = 0; i < str; i++) {

            if ((row - i) < 0 || (column - i < 0)) break;

            if ((brett[row - i][column - i]).equals("x")) {
                return false;
            }
        }

        // downright
        for (int i = 0; i < str; i++) {

            if ((row + i) >= str || (column + i >= str)) break;

            if ((brett[row + i][column + i]).equals("x")) {
                return false;
            }
        }
        return true;
    }

    public void matprint(String[][] brett) {//the method that prints the board
        for (String[] row : brett) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[] translate(String pos) {//the method translating the user "String"- coordinates into ints we can work with 
        String pos2 = (pos.substring(0, 1)).toLowerCase();
        String pos1 = pos.substring(1, 2);
        //splits the input into two chars
        int p1 = 9;
        int p2 = 9;

        if (pos2.equals("a")) p2 = 0;
        if (pos2.equals("b")) p2 = 1;
        if (pos2.equals("c")) p2 = 2;
        if (pos2.equals("d")) p2 = 3;
        if (pos2.equals("e")) p2 = 4;
        if (pos2.equals("f")) p2 = 5;
        if (pos2.equals("g")) p2 = 6;
        if (pos2.equals("h")) p2 = 7;
        //translates the files
        if (pos1.equals("1")) p1 = 7;
        if (pos1.equals("2")) p1 = 6;
        if (pos1.equals("3")) p1 = 5;
        if (pos1.equals("4")) p1 = 4;
        if (pos1.equals("5")) p1 = 3;
        if (pos1.equals("6")) p1 = 2;
        if (pos1.equals("7")) p1 = 1;
        if (pos1.equals("8")) p1 = 0;
        //translates the ranks
        if (p1 > 8 || p2 > 8) return null;
        if (p1 < 0 || p2 < 0) return null;

        return new int[]{p1, p2};
    }
}

