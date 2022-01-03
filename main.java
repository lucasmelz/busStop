
/**
 * Write a description of main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.text.DecimalFormat;

public class main {
    public static void main(String[] args){
        DecimalFormat format = new DecimalFormat("0.00");
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira, separado por espaços, o dia, mês e ano em que serão efetuados os registos.");
        System.out.println("Exemplo: \"26 10 2020\"");
        String input = sc.nextLine();
        String[] date = input.split(" ");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        BusStop busStop = new BusStop(year, month, day);
        System.out.println("Comandos: \n  - Para registar a chegada de um grupo de pessoas,\n    insira a letra P, horas,  minutos e nº de pessoas (ex: \"P 10 30 40\").\n");        
        System.out.println("  - Para registar a partida de um autocarro, insira separado por espaços\n    a letra B, as horas, minutos e o número de vagas no ônibus, neste formato: \"B 10 30 40\".\n");
        System.out.println("  - Se quiser ver os elementos da queue, insira \"q\".\n");
        System.out.println("  - Se quiser encerrar o programa, insira a letra \"e\".\n");
        input = sc.nextLine();
        try {
            while(!input.equalsIgnoreCase("E")){
                String[] strings = input.split(" ");
                int hour = 0;
                int min = 0;
                int sz = 0;
                   if(strings.length>1){
                    hour = Integer.parseInt(strings[1]);
                    min = Integer.parseInt(strings[2]);
                    sz = Integer.parseInt(strings[3]);
                }
                switch(strings[0].toLowerCase()){                    
                    case "p":
                    busStop.chegaGrupo(hour, min, sz);
                    break;
                    case "b":
                    busStop.chegaBus(hour,min, sz);
                    System.out.println("O tempo médio de espera é de: " + format.format(busStop.averageTime()) + " minutos.");
                    break;
                    case "q":
                    System.out.println(busStop);
                }

                input = sc.nextLine();
            }
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    
    
}
