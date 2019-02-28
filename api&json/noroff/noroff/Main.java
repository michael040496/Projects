package no.noroff;
import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.net.URL;
import java.net.URLConnection;

import java.util.Scanner;

public class Main {

    public static String requestURL(String url) throws Exception {
        // Set URL
        URLConnection connection = new URL(url).openConnection();
        // Set property - avoid 403 (CORS)
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        // Create connection
        connection.connect();
        // Create a buffer of the input
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // Convert the response into a single string
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = buffer.readLine()) != null) {
            stringBuilder.append(line);
        }
        // return the response
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter: ");

        String input = sc.nextLine();
        
        String url = "https://anapioficeandfire.com/api/characters/" + input;

        JSONObject jo = new JSONObject(requestURL(url));

        System.out.println("Name: " + jo.get("name"));
        System.out.println("Gender: " + jo.get("gender"));
        System.out.println("Culture: " + jo.get("culture"));
        System.out.println("Born: " + jo.get("born"));
        System.out.println("Died: " + jo.get("died"));

        JSONArray aliases = new JSONArray(jo.get("aliases").toString());
        System.out.println("Aliases: ");
        for (int i = 0; i < aliases.length(); i++) {
            System.out.println(aliases.get(i));
        }
        JSONArray allegiancesJ = new JSONArray(jo.get("allegiances").toString());
        ArrayList<String> arrL = new ArrayList<String>();
        /*an arraylist containging JSONobjects of the url's of the names of the members of the house
        Used just in case there are characters with more than 1 allegiance
         */

        for (int j = 0; j < allegiancesJ.length(); j++) {
            arrL.add(allegiancesJ.get(j).toString()); //adds urls to the arraylist
        }


        System.out.println("Would you like to see the allegiances of the house this character belongs to? Press y");
        input = sc.nextLine();

        ArrayList<JSONObject> arrJS = new ArrayList<JSONObject>();
        //same purpose as the arraylist above, but with JSONobjects so we can get the names from that object
        JSONObject house;
        JSONArray swornMembers = null;
        if (input.equalsIgnoreCase("y")) {
            try {
                for (int h = 0; h < arrL.size(); h++) {

                    arrJS.add(new JSONObject(requestURL(arrL.get(h))));
                    System.out.println(arrJS.get(h).get("name"));
                    System.out.println("");
                    swornMembers = new JSONArray(arrJS.get(h).get("swornMembers").toString());

                }
                JSONObject namesOfMembers;

                for (int j = 0; j < swornMembers.length(); j++) {
                    namesOfMembers = new JSONObject(requestURL(swornMembers.get(j).toString()));
                    System.out.println(namesOfMembers.get("name").toString());
                }
            }
            catch (Exception e){System.out.println("No allegiances");}

            String bookUrl = "https://anapioficeandfire.com/api/books/";
            JSONArray books = new JSONArray(requestURL(bookUrl));


            for (int l = 0; l < books.length(); l++) {
                JSONObject book = books.getJSONObject(l);

                String nameOfBook = book.optString("name");

                String povChars = book.optString("povCharacters");

                String publisher = book.optString("publisher");

                JSONArray characterL = new JSONArray(povChars);
                
                if (publisher.equalsIgnoreCase("Bantam Books")) {
                    String format = "||%25s||%n";

                    System.out.println("Book: " + nameOfBook);

                    System.out.println("-----------------------------");
                    for (int m = 0; m < characterL.length(); m++) {
                        if (characterL.length() > 0) {
                            JSONObject chars = new JSONObject(requestURL(characterL.get(m).toString()));
                            System.out.printf(format, chars.get("name").toString());
                        }
                    }
                    System.out.println("-----------------------------");
                }
            }
        }
    }
}

