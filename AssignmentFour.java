import java.util.*;
import java.io.*;

/**
 * AssignmentFour.java
 * Purpose: Implements a spellchecker using LinkedLists.
 * @author Nathaniel Sigafoos
 * @version 1.1 3/24/17
 */
public class AssignmentFour {
    //The number of valid characters a word can start with.
    private final int NUMBER_OF_Letters = 26;
    //The dictionary to store all the valid words.
    private MyLinkedList[] dictionary = new MyLinkedList[NUMBER_OF_Letters];
    /**
     * Initializes class and loads the dictionary.
     */
    public AssignmentFour () {
        loadDictionary(new File("./random_dictionary.txt"));
    }
    /**
     * Fills the dictionary array with words from a given File.
     * Allows for the dictionary to be refilled with different values.
     * @param f The file object being used to load the dictionary array.
     */
    private void loadDictionary (File f) {
        try (Scanner input = new Scanner(f)) {
            //ready the array to be filled
            for (int i = 0; i < dictionary.length; i++)
                dictionary[i] = new MyLinkedList<String>();
            //load the values into the dictionary
            while (input.hasNext()) {
                String word = input.next().toLowerCase();
                dictionary[word.charAt(0) - 97].add(word);
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Could not find the file '" + f.getName() +"'.");
            System.out.println(e);
        }
    }
    /**
     * Reads through a given file any counts the number of words
     *  in the dictionary of valid words.
     * @param f The File being read.
     */
    public void spellcheck (File f) {
        //Counter variables
        long wordsFound = 0; //Total number of valid words found.
        long wordsNotFound = 0; //Total number of invalid words found.
        long compsFound = 0; //Total comparisons used to find vaild words.
        long compsNotFound = 0; //Total comparisons to find invalid words.
        //try to open the file
        try (Scanner input = new Scanner(f)) {
            //set the delimiter so "words" are split by whitespace or hyphens
            input.useDelimiter("[\\s-]");
            //read through the file
            while (input.hasNext()) {
                //get the next "word" from the file
                String word = input.next();
                //remove anything after a '
                //and then remove any non-alphabetical characters
                word = word.replaceAll("('[\\S]+)|[^a-zA-Z- ]", "");
                //move on to the next word if this one is now empty
                if (word.isEmpty()) continue;
                //convert anything left to lowercase 
                word = word.toLowerCase();
                //look for the word in the dictionary
                int index = dictionary[word.charAt(0) - 97].indexOf(word);
                if (index == -1) {
                    //the word was not in the dictionary
                    wordsNotFound++;
                    compsNotFound += dictionary[word.charAt(0) - 97].size;
                } else {
                    //the word was in the dictionary
                    wordsFound++;
                    compsFound += index + 1;
                }
            }
            //close the Scanner
            input.close();
            //print out the results
            System.out.println("Words found: " + wordsFound);
            System.out.println("Non-words not found: " + wordsNotFound);
            System.out.println("Total comparisons to find words: " 
                + compsFound);
            System.out.println("Total comparisons to find non-words: " 
                + compsNotFound);
            System.out.printf("Average number of comparisons to find a word: "
                + "%.2f\n", compsFound / (double)wordsFound);
            System.out.printf("Average number of comparisons to find a non-word: "
                + "%.2f\n", compsNotFound / (double)wordsNotFound);
        } catch (IOException e) {
            System.out.println("Could find the file '" + f.getName() +"'.");
            System.out.println(e);
        }
    }
    public static void main (String[] args) {
        AssignmentFour a = new AssignmentFour();
        a.spellcheck(new File("./oliver.txt"));
    }
}