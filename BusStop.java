
/**
 * Write a description of BusStop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Date;

public class BusStop {
    public BusData info;
    public int pessoasNaFila;
    public static int totalMinutes;
    public static int totalPeopleThatLeft;

    public BusStop(int year, int month, int day)
    {
        this.info = new BusData(year, month, day);
        this.pessoasNaFila = 0; 
        this.totalMinutes = 0;
        this.totalPeopleThatLeft = 0;
    }

    public void chegaGrupo(int hour, int minute, int sz){
        this.pessoasNaFila+=sz;
        Grupos grupo = new Grupos(hour, minute, sz);
        this.info.grupos.enqueue(grupo);  
        System.out.println("Hora: " + hour + ":" + minute + " " + sz + " pessoas chegaram.");
        System.out.println("Restam " + pessoasNaFila + " pessoas esperando o autocarro.");
    }

    public void chegaBus(int hour, int minute, int sz){
        int peopleLeaving = sz;
        int year = this.info.year;
        int month = this.info.month;    
        int day = this.info.day;
        Date dateOfDeparture = new Date(year, month, day, hour, minute);
        int pessoasNaFilaAntes = this.pessoasNaFila;

        if(this.pessoasNaFila>=sz){//mais pessoas esperando q vagas no autocarro

            try{
                while(peopleLeaving>0){
                    Grupos grp = this.info.grupos.front();
                    Date dateOfArrival = new Date(year, month, day, grp.hour, grp.min);
                    if(grp.sz<=peopleLeaving){ //Se no último grupo há menos pessoas do q irão partir.
                        pessoasNaFila-=grp.sz;
                        peopleLeaving-=grp.sz;
                        totalPeopleThatLeft+=grp.sz;
                        totalMinutes+=((dateOfDeparture.getTime()-dateOfArrival.getTime())/60000)*grp.sz;
                        this.info.grupos.dequeue();
                    }
                    else{
                        grp.sz-=peopleLeaving;
                        pessoasNaFila-=peopleLeaving;
                        totalPeopleThatLeft+=peopleLeaving;
                        totalMinutes+=((dateOfDeparture.getTime()-dateOfArrival.getTime())/60000)*peopleLeaving;
                        peopleLeaving=0;
                    }
                }
            }
            catch(Exception e){System.out.println("Não há grupos esperando na paragem!");}
        }
        else{
            pessoasNaFila=0;
            try{
                while(peopleLeaving>0){
                    Grupos grp = this.info.grupos.front();
                    Date dateOfArrival = new Date(year, month, day, grp.hour, grp.min);
                    if(grp.sz<=peopleLeaving){
                        peopleLeaving-=grp.sz;
                        totalPeopleThatLeft+=grp.sz;
                        this.info.grupos.dequeue();
                        totalMinutes+=((dateOfDeparture.getTime()-dateOfArrival.getTime())/60000)*grp.sz;
                    }
                    else{
                        grp.sz-=peopleLeaving;
                        totalPeopleThatLeft+=peopleLeaving;
                        totalMinutes+=((dateOfDeparture.getTime()-dateOfArrival.getTime())/60000)*peopleLeaving;
                        peopleLeaving=0;
                    }
                }
            }
            catch(Exception e){System.out.println("Não há grupos esperando na paragem!");}
        }
        int departingPeople = Math.abs(pessoasNaFilaAntes - pessoasNaFila);

        System.out.println("Hora " + hour + ":" + minute + " " + departingPeople + " pessoas partiram.");
        System.out.println("Restam " + pessoasNaFila + " pessoas esperando o autocarro.");
    }

    public double averageTime(){
        return Double.valueOf(this.totalMinutes)/this.totalPeopleThatLeft;   
    }

    public String toString(){
        int size = 0;
        int head = this.info.grupos.head;
        int tail = this.info.grupos.tail;
        if(head>tail){size = this.info.grupos.size - head + tail + 1;}
        else{size = tail-head+1;}
        Grupos[] grps = new Grupos[size];
        String str = "[";
        int i = 0;
        
        while(this.info.grupos.tail!=-1){
         try{Grupos group = this.info.grupos.dequeue();
             str+=group.hour + ":" + group.min + " Grupo " + group.sz + " pessoas;";
             grps[i]=group;
             i++;
            }
         catch(Exception e){System.out.println("Não é possível fazer dequeue(método toString)");}
         
        }
        
        str+="]";
        
        for(int j = 0; j<i; j++){
        this.info.grupos.enqueue(grps[j]);
        }
        
        return str;
    }
    
}
