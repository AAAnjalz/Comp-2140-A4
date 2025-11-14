import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellCheck {
    class Node{
        Node left;
        Node right;
        String data;
        ArrayList<Integer> lines;
        public Node(String word){
            this.left = null;
            this.right = null;
            this.data = word;
            lines = new ArrayList<>();
        }
    }

    Node root;
    public SpellCheck(){
        root = null;
    }

public void insert(String word, int line) {
    word = cleanWord(word);
    if (word.isEmpty()) return;       // skip empty results
    word = word.toLowerCase();        // case insensitive

    // Case 1: empty tree
    if (root == null) {
        root = new Node(word);
        root.lines.add(line);
        return;
    }

    Node temp = root;
    Node parent = null;

    while (temp != null) {
        parent = temp;
        int cmp = word.compareTo(temp.data.toLowerCase());  

        if (cmp == 0) { // word already exists
            temp.lines.add(line);
            return;
        } else if (cmp < 0) {
            temp = temp.left;
        } else {
            temp = temp.right;
        }
    }

    // create new node
    Node newNode = new Node(word);
    newNode.lines.add(line);

    // attach new node to parent
    if (word.compareTo(parent.data) < 0)
        parent.left = newNode;
    else
        parent.right = newNode;
}

    
    private static String cleanWord(String word) {
    int start = 0;
    int end = word.length() - 1;

    // remove punctuation from the start
    while (start <= end && !Character.isLetterOrDigit(word.charAt(start))) {
        start++;
    }

    // remove punctuation from the end
    while (end >= start && !Character.isLetterOrDigit(word.charAt(end))) {
        end--;
    }

    // return the cleaned part
    if (start > end) {
        return ""; // word was all punctuation
    }

    return word.substring(start, end + 1);
}

public void  print(DictionaryQ1 dq1){
    printHelper(root, dq1);
    
}

public void printHelper(Node root , DictionaryQ1 dq1){
    if(root == null){
        return;
    }
    printHelper(root.left, dq1);
    if(!(dq1.search(root.data))){
        System.out.print("The unknown word " + root.data + " occurs on lines: ");
        for(int i=0;i<root.lines.size();i++){
            System.out.print(root.lines.get(i));
            if(i!=root.lines.size()-1){
                System.out.print(", ");
            }else{
                System.out.println();
            }
        }
        
    }
    printHelper(root.right, dq1);
}



    public static void main(String[] args) {
        // Scanner sc = new Scanner(args[0]);
        DictionaryQ1 dq1 = new DictionaryQ1();
        SpellCheck spellCheck = new SpellCheck();
        
        String wordsFile = args[0];
        String textFile = args[1];

        try(Scanner wordInput = new Scanner(new File(wordsFile));) {
            while (wordInput.hasNextLine()) { 
                dq1.insert(wordInput.nextLine().trim());
            }
            dq1.print();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int lineNumber = 0;

        try(Scanner textInput = new Scanner(new File(textFile))){
            while (textInput.hasNextLine()) { 
                lineNumber++;
                String line = textInput.nextLine();
                String[] token = line.split("\\s+");
                for(String temp : token){
                    // System.out.println(!dq1.search(temp));
                    if(!dq1.search(temp)){
                        spellCheck.insert(temp, lineNumber);
                    }
                    
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        spellCheck.print(dq1);
    }
}
