/*
Nithin Muthukumar
ICS4U-02
April 4, 2019
HashAssign1.java
this a program that asks the user for letters and outputs the possible words that can be formed
with the help of a hashtable class to do it fast
 */
import java.util.*;
import java.io.*;

public class HashAssign1{


    public static void main(String[] args){
        HashTable<String> words = new HashTable<String>();
        try{
            //adding words from dictionary.txt to hashtable
            Scanner stdin = new Scanner(new BufferedReader(new FileReader("dictionary.txt")));
            while(stdin.hasNextLine()){
                words.add(stdin.nextLine());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Scanner kb = new Scanner(System.in);
        System.out.println("please enter less than 8 letters");
        String letters = kb.nextLine();
        //permutations takes the letters and the dictionary as inputs
        if(letters.length()<=8)
            permutations(letters, words);
        else System.out.println("sorry too big");


    }
    //overload method just to make permutations cleaner
    public static void permutations(String letters,HashTable<String> table){
        permutations("",letters,table,new ArrayList<>());
    }

    private static void permutations(String left,String word, HashTable<String> table, ArrayList<String> used){
        //checks if there is nothing left
        if(word.length() == 0){
            //if its in the dictionary print and add to used which allows it to transcend the recursion
            if(table.contains(left) && !used.contains(left)){
                System.out.println(left);
                used.add(left);
            }
        }
        else for(int i = 0; i < word.length(); i++)
            //recurse through the word and take each letter and add to word to create more permutations
            permutations(left + word.charAt(i), word.substring(0,i) + word.substring(i+1), table, used);


    }
}
