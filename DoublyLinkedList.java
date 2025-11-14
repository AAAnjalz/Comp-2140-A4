public class DoublyLinkedList {
    private Node head;
    

    public DoublyLinkedList(){
        this.head = null;
    }

    public void insert(String data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }

    public void print(){
        Node temp = head;
        while (temp!=null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void remove(String data){
        Node temp = head;
        while (temp!=null && !(temp.data.equalsIgnoreCase(data))) {
            temp = temp.next;
        }
        if(temp==null){
            return;
        }
        if(temp.prev != null){
            temp.prev.next = temp.next;
        }else{
            head = temp.next;
        }

        if(temp.next!=null){
            temp.next.prev = temp.prev;
        }
        
    }

    public boolean search(String key){
        Node curr = head;

        while(curr!=null){
            if(curr.data.equalsIgnoreCase(key)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    class Node{
        Node prev;
        Node next;
        String data;
        public Node(String data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}
