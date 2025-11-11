import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryQ3 implements Dictionary{
    Node root;

    public DictionaryQ3(){
        this.root = null;
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
        // TODO Auto-generated method stub
        Node temp = root;
        if(root == null){
            return false; //nothing to search
        }

        while (temp!=null && !temp.data.equals(key)) { 
            if(key.compareTo(temp.data) < 0){
                temp = temp.left;
            }else{
                temp = temp.right;
            }
        }
        if(temp == null){
            return false;
        }
        return true;
    }

    @Override
    public void insert(String key) {
        Node temp = root;
        Node newNode = new Node(key);
        Node parent = null;

        // Case 1: Tree is empty, new node becomes root
        if(root == null){
            root = newNode;
            return;
        }
            //Traverse the tree to find the correct spot
            while(temp!=null){
                parent = temp;
                if(key.compareTo(temp.data) == 0){ //key already exists
                    return;
                }else if(key.compareTo(temp.data)> 0){
                    temp = temp.right; // Go right if key is larger than current node
                }else{   
                    temp = temp.left; // Go left if key is smaller than current node
                }
            }

        //Attach the new node as a child of parent
        if(key.compareTo(parent.data) > 0){
            parent.right = newNode;
        }else{
            parent.left = newNode;
        }
        
    }

    @Override
    public void delete(String key) {
        Node temp = root;
        Node parent = null;

        while(temp!=null && temp.data.compareTo(key)!=0){
            parent = temp;
            if(key.compareTo(temp.data) >0){
                temp = temp.right;
            }else{
                temp = temp.left;
            }
        }
        if(temp==null){
            return;
        }

        if(temp.left == null && temp.right == null){
            if(parent == null){       // deleting root
                root = null;
            } else if(parent.left == temp){
                parent.left = null;
             } else {
                parent.right = null;
            }
            return;
}

        //case 2
        if(temp.left == null && temp.right!=null){
            if(parent == null){
                root = temp.right;
            }else if(parent.right == temp){
                parent.right = temp.right;
            }else{
                parent.left = temp.right;
            }
            return;
        }

        if(temp.left!=null && temp.right == null){
            if(parent == null){
                root = temp.left;
            }else if(parent.right == temp){
                parent.right = temp.left;
            }else{
                parent.left = temp.left;
            }
        }

        if(temp.left!=null && temp.right!=null){
            parent = temp;
            Node successor =temp.right;
            while (successor.left!=null) { 
                parent = successor;
                successor = successor.left;
            }
            String tempdata = temp.data;
            temp.data = successor.data;
            successor.data = tempdata;

                if(successor.left!=null && successor.right == null){ //successor is not the node we need to delete
                    if(parent.left == successor){
                        parent.left = successor.left;
                    }else{
                        parent.right = successor.left;
                    }
                    return;
                }

                if(successor.right!=null && successor.left == null){
                    if(parent.right == successor){
                        parent.right = successor.right;
                    }else{
                        parent.left = successor.right;
                    }
                    return;
                }

                if(successor.left == null && successor.right == null){
                    if(parent.left == successor){
                        parent.left = null;
                    }else{
                        parent.right = null;
                    }
                    return;
                }
        }

    }

        @Override
    public void print() {
        System.out.println("Dictionary contents:");
        printHelper(root, 0);
    }

    private void printHelper(Node root, int currDepth){
        if(root == null){
            return;
        }
        printHelper(root.left, currDepth + 1);
        for(int i=0;i<currDepth;i++){
            System.out.print("    ");
        }
        System.out.println(root.data);
        printHelper(root.right, currDepth + 1);
    }
        class Node{
        Node left;
        Node right;
        String data;
        public Node(String data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        DictionaryQ3 dq3 = new DictionaryQ3();
        // dq3.open("VeryShortWords.txt");
        // dq3.print();
        dq3.insert("OR");
        dq3.insert("BE");
        dq3.insert("AN");
        dq3.insert("NO");
        dq3.insert("TO");
        dq3.insert("PI");
        dq3.print();
    }
}
