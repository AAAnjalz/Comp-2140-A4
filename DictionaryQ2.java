import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryQ2 implements Dictionary {

    String[] table;
    int size;
    int currEntries;

    public DictionaryQ2(){
        table = new String[8];
        size = table.length;
        currEntries = 0;
    }
    /**
    * Computes the hash index for a given string using a polynomial hash function.
    * The hash is computed using Horner's method to avoid integer overflow.
    * 
    * @param word The string for which the hash is computed
    * @return An integer index in the range [0, SIZE-1] suitable for the hash table
    */

    public int hashCode(String word, int size){
        int hash = 0;
        final int P = 13;

        for(int i=word.length()-1;i>=0;i--){ //We start from last character and work back
            int charValue = (int)word.charAt(i);
            hash = (hash*P + charValue) % size;
        }
        return hash;
    }

    public String[] resize(){
        String[] newArray = new String[this.table.length * 2];
        size = newArray.length;

        //copy array according to the new table and its size
        for(String key : table){
            if(key!=null && !(key.equals("REMOVED"))){
                int newIndex = hashCode(key, size);

                while(newArray[newIndex]!=null){
                    newIndex++;
                    if(newIndex == newArray.length){
                        newIndex = 0;
                    }
                }
                newArray[newIndex] = key;
            }
        }
        return newArray;
    }

    @Override
    public void open(String filename) {
        
        try(Scanner sc = new Scanner(new File(filename))){
            while(sc.hasNext()){
                this.insert(sc.next());
            }
        }catch(IOException ioe){
            System.out.println("Sorry, file not found");
        }
    }

@Override
public boolean search(String key) {
    int index = hashCode(key, size);

    while (table[index] != null) { // stop when we hit an empty spot
        if (!table[index].equals("REMOVED") && table[index].equals(key)) {
            return true; // found it
        }

        // move to the next slot
        index++;
        if (index == table.length) {
            index = 0; // wrap around to start
        }
    }

    return false; // not found
}


    @Override
    public void insert(String key) {
        int index = hashCode(key, size);
            while (table[index]!=null && !table[index].equals("REMOVED")) { 
                index++;
                if(index == table.length){
                    index = 0;
                }
            }
            table[index] = key;
            currEntries++;

        double loadFactor = (double)currEntries/size;
        if(loadFactor > 0.75){
            table = resize();
      }
        
        
    }

    public int getTableSize(){
        return size;
    }

@Override
public void delete(String key) {
    int index = hashCode(key, size);

    // Linear probing until we find key or hit an empty slot
    while (table[index] != null) {
        if (!table[index].equals("REMOVED") && table[index].equals(key)) {
            table[index] = "REMOVED";
            currEntries--;
            return; // key deleted, done
        }

        index++;
        if (index == table.length) {
            index = 0; // wrap around
        }
    }
    // If we reach a null, key doesn’t exist so do nothing
}


    @Override
    public void print() {
        System.out.println("Dictionary contents: ");
        for(int i=0;i<table.length;i++){
            if(table[i] != null && !table[i].equals("REMOVED")){
                System.out.println(table[i]);
            }
        }
    }
    public static void main(String[] args) {
        DictionaryQ2 d2 = new DictionaryQ2();
        d2.open("VeryShortWords.txt");
        d2.print();
    }
    
}
