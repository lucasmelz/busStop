
/**
 * Write a description of Queue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Queue <AnyType> {

    public void enqueue(AnyType o);
    public AnyType front() throws Exception;
    public AnyType dequeue() throws Exception;
    public int size();
    public boolean empty();
    public boolean full();
    
    
}
