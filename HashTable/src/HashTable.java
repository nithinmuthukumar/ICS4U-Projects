/*
Nithin Muthukumar
ICS4U-02
April 4, 2019
HashTable.java
this is an implementation of a hashtable which allows o(1) access
the object is accessed mathematically using the object's hash
it also supports all objects with generics.
 */
import java.util.*;

public class HashTable<T>{
    private ArrayList<LinkedList<T>> table;
    //number of items in table
    private int items = 0;
    //the amount of item to space ratio that is allowed until the table is resized
    private double maxLoad = 70;


    public HashTable(){
        //initial number of spots is ten
        fill(10);
    }

    private void fill(int n){
        //creates a new arraylist with the desired number of spots
        table = new ArrayList<>();
        for(int i = 0; i < n; i++){
            table.add(null);
        }
    }


    public void add(T v){
        //get the index within the arraylist that the value will be placed at using hashcode
        //abs value is necessary because hashcode can be negative if object is big enough
        int spot = Math.abs(v.hashCode()) % table.size();
        //get the linked list at that spot
        LinkedList<T> lst = table.get(spot);
        //if it is null we create show
        if(lst == null){
            lst = new LinkedList<>();
            table.set(spot,lst);
        }
        //if it does not contain we add the value since we do not want duplicates
        if(!lst.contains(v)){
            lst.add(v);
            items++;
            //if the number of items exceeds the maxload we resize
            // so that the efficiency of our hashtable does not become o(n)
            if((double)items*100/table.size() > maxLoad)
                resize();
        }
    }
    public void remove(T v){
        int hash = Math.abs(v.hashCode());
        //calculates the position of the value in the hashtable
        LinkedList<T> list = table.get(hash % table.size());
        //uses the linked list method to remove item
        list.remove(v);
        items--;
    }
    //method which checks if value is in dictionary

    public boolean contains(T v){
        //gets the spot by taking the hashcode position
        LinkedList<T> list = table.get(Math.abs(v.hashCode()) % table.size());
        //if the list is null its not obviously not in it
        if(list != null)
            return list.contains(v);
        return false;
    }
    public double getLoad(){
        //needs to be casted to a double to avoid integer division
        //the load is simply number of items over number of spots in total
        return ((double)items)/table.size();
    }

    public void setMaxLoad(double p){
        //if maxLoad is valid we set it to p
        if(p <= 0.8 && p >= 0.1){
            //p is multiplied by 100 because it is a percentage
            maxLoad = p*100;
        }
    }

    public void setLoad(double p){

        if(p <= 0.8 && p >= 0.1 && p < maxLoad){
            //change the size so that the load percentage is as close as possible
            // load=items/size so rearranging gives us size
            int size = (int)(items/p);
            resize(size);
        }
    }

    public ArrayList<T> toArray(){
        //go through the table and skip over the nulls and add to list
        ArrayList<T> list = new ArrayList<T>();
        ArrayList<LinkedList<T>> tmp = table;
        for(LinkedList<T> lst : tmp){
            if(lst != null){
                for(T v : lst){
                    list.add(v);
                }
            }
        }
        return list;
    }

    public String toString(){
        String ans = "";
        //gets an array of all the values and adds it to a string
        for(T i : toArray())
            ans += i + ", ";

        //if there are values there will be an extra , and  at the end of the ans so we remove them
        if(ans.length() > 0)
            ans = ans.substring(0,ans.length()-2);

        return "{" + ans + "}";
    }

    public void resize(){
        resize(10);
    }
    //intellij made the overload for me
    //also used in setLoad
    private void resize(int i) {
        //items must be set to 0 because the values will be re added to table
        items = 0;
        //make a temporary table to preserve the values
        ArrayList<LinkedList<T>> tmp = table;
        //change to size its supposed to be now
        fill(table.size()* i);
        //go through items and re add them to hashtable
        for(LinkedList<T> lst : tmp){
            if(lst != null){
                for(T item : lst){
                    add(item);
                }
            }
        }
    }






}
