
/**
 * Write a description of QueueArray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QueueArray<AnyType> implements Queue<AnyType> {

    public int size;
    public static int head;
    public static int tail;
    public AnyType queue[];
    public AnyType front;

    public QueueArray(int sz){
        this.size = sz;
        AnyType queue[] = (AnyType[]) new Object[sz];

        this.queue = queue;
        this.tail = -1;
        this.head = -1;
    }

    public void setFront(AnyType front){
        this.front = front; 
    }

    public AnyType front(){
        return this.front;
    }

    public boolean empty(){
        return (tail==-1 && head==-1);
    }

    public boolean full(){
        return (tail+1)%size==head;
    }

    public int size(){
        return size; 
    }

    public void setHead(int newHead){
        this.head = newHead;
    }

    public void setTail(int newTail){
        this.tail = newTail;
    }

    public void enqueue(AnyType o){

        if(this.full()){
            System.out.println("A queue está cheia!");
        }
        else{
            if(this.head==-1 && this.tail==-1){ 
                this.tail=0; 
                this.head=0; 
                this.queue[tail]= o;
                this.setFront(o);
            }
            else
            {
                this.tail = (this.tail+1)%this.size;
                this.queue[tail]=o;
            }
        }
    }

    public AnyType dequeue() throws Exception{

        if(this.empty()){
            throw new Exception("A queue está vazia!");
        }
        else{
            AnyType headOfQueue = this.queue[this.head];
            if(head==tail){
                this.head = -1;
                this.tail = -1;
            }
            else{this.head = (this.head+1)%this.size;
            this.setFront(this.queue[this.head]);}
            return headOfQueue;
        }
    }

}
