public class LinkList {
    protected Node start;
    protected Node end;
    int size;
    public LinkList(){
        start = null;
        end = null;
        size = 0;

    }
    public boolean isEmpty(){
        return start == null;
    }
    public int getSize(){
        return size;
    }
    public void insertAtStart(int val){
        Node nptr = new Node(val,null);
        size++;
        if(start == null){
            //start = end = nptr;
            start = nptr;
            end = start;
        }else {
            nptr.setlink(start);
            start = nptr;
        }
    }
    public void insertAtEnd(int val){
        Node nptr = new Node(val,null);
        size++;
        if(start == null){
            //end = start = nptr;
            start = nptr;
            end = start;
        }else {
            end.setlink(nptr);
            end = nptr;
        }
    }
    public void insertAtPos(int val,int pos){
        Node nptr = new Node(val,null);
        Node ptr = start;
        pos = pos -1;
        for (int i = 1; i < size; i++) {
            if(i==pos){
                Node tmp = ptr.getlink();
                ptr.setlink(nptr);
                nptr.setlink(tmp);
                break;
            }
            ptr = ptr.getlink();
        }
        size++;
    }
    public void deleteAtPos(int pos){
        if(pos == 1){
            start = start.getlink();
            size--;
            return;
        }
        if (pos == size) {
            Node s = start;
            Node t = start;
            while (s != end) {
                t = s;
                s = s.getlink();
            }
            end = t;
            end.setlink(null);
            size--;
            return;
        }
        Node ptr = start;
        pos = pos -1;
        for(int i = 1; i < size; i++){
            if(i==pos){
                Node tmp = ptr.getlink();
                tmp = tmp.getlink();
                ptr.setlink(tmp);
                break;
            }
            ptr = ptr.getlink();
        }
        size--;
    }
    public void display(){
        System.out.println("\n Singly Linked List = ");
        if (start == 0){
            System.out.print(" Empty\n");
        }
        if(start.getlink()==null){
            System.out.print(start.getData());
            return;
        }
        Node ptr = start;
        System.out.print(start.getData() + " -> ");
        ptr = start.getlink();
        while(ptr.getlink() != null){
            System.out.print(ptr.getData() + " -> ");
            ptr = ptr.getlink();
        } 
        System.out.print(ptr.getData() + " \n");
    }
}
