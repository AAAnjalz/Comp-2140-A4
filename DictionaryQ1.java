
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryQ1 implements Dictionary{
    final int SIZE = 223;
     DoublyLinkedList[] table;

    public DictionaryQ1(){
        table = new DoublyLinkedList[SIZE];
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
        int index = hashCode(key.toLowerCase());
        if(table[index] == null){
            return  false;
        }else{
            return table[index].search(key);
        }
    }

    @Override
    public void insert(String key) {
        int index = hashCode(key.toLowerCase());
        if(table[index] == null){
            table[index] = new DoublyLinkedList();
        }
        if(!table[index].search(key)){
            table[index].insert(key);
        }
        
    }

    @Override
    public void delete(String key) {
        int index = hashCode(key.toLowerCase());
        if(table[index] !=null){
            table[index].remove(key);
        }
    }

    @Override
    public void print() {
        for(int i=0;i<SIZE;i++){
            if(table[i]!=null){
                table[i].print();
            }
            System.out.println();
        }
    }

    /**
    * Computes the hash index for a given string using a polynomial hash function.
    * The hash is computed using Horner's method to avoid integer overflow.
    * 
    * @param word The string for which the hash is computed
    * @return An integer index in the range [0, SIZE-1] suitable for the hash table
    */

    public int hashCode(String word){
        int hash = 0;
        final int P = 13;

        for(int i=word.length()-1;i>=0;i--){ //We start from last character and work back
            int charValue = (int)word.charAt(i);
            hash = (hash*P + charValue) % SIZE;
        }
        return hash;
    }

    public static void main(String[] args) {
        DictionaryQ1 dq1 = new DictionaryQ1();
        dq1.open("ShortWords.txt");
        dq1.print();
    }
    
}
