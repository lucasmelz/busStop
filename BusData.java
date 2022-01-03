
/**
 * Write a description of BusData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BusData {
    public int year, month, day;
    public final int maxRecords = 200;
    public QueueArray<Grupos> grupos;
    
    
    public BusData(int year, int month, int day){
    this.year = year;
    this.month = month;
    this.day = day;
    this.grupos = new QueueArray<>(maxRecords);
    }
    
    
    
    

}
