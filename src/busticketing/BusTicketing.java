
package busticketing;

import java.util.Scanner;


public class BusTicketing {

    
    public static void main(String[] args) {
       
        
        Scanner sc = new Scanner(System.in);
        
        
        boolean exit = true;
        do{
            System.out.println("");
            
        System.out.println("1. Passenger");
        System.out.println("2. Bus");
        System.out.println("3. Bus Records");
        System.out.println("4. Exit");
        
         int choice;
         while (true) {
             System.out.println("");
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 5) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Enter a integer not a character.");
                    sc.next();
                }
            }
        
         
         switch(choice){
             case 1:
                 Passenger ps = new Passenger();
                    ps.mainPassenger();
                 break;
             case 2:
                 Bus bs = new Bus();
                 bs.mainBus();
                 break;
             case 3:
                 BusRecord br = new BusRecord();
                 br.mainRecords();
                 break;
             case 4:
                 System.out.print("Do you want to exit? Yes or No: ");
                 String res = sc.next();
                 if(res.equalsIgnoreCase("yes")){
                     exit = false;
                 }
                 break;
             
             
         }
        }while(exit);
    }
    
}
