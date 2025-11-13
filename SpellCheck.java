import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellCheck {
    class Node{
        Node left;
        Node right;
        String word;
        ArrayList<Integer> lines;
        public Node(String word){
            this.left = null;
            this.right = null;
            this.word = word;
            lines = new ArrayList<>();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(args[0]);
        DictionaryQ1 dq1 = new DictionaryQ1();
        
        String wordsFile = args[0];
        String textFile = args[1];

        try(Scanner wordInput = new Scanner(new File(wordsFile));) {
            while (wordInput.hasNextLine()) { 
                dq1.insert(wordInput.nextLine().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
