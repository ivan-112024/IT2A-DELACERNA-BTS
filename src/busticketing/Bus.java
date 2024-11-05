
package busticketing;

import java.util.Scanner;


public class Bus {
    
    
   public void addBus(){
       
       
         
       Scanner sc = new Scanner(System.in);
       Config cg = new Config();
       
       System.out.print("Enter Bus name: ");
       String busname = sc.nextLine();
       System.out.print("Capacity: ");
       int capa = sc.nextInt();
       sc.nextLine();
       System.out.print("From(Departure Location): ");
       String from = sc.nextLine();
       System.out.print("To(Arrival Location): ");
       String to = sc.nextLine();
       System.out.print("Fee: ");
       double fee = sc.nextDouble();
       sc.nextLine();
       System.out.print("Time(AM / PM) : ");
       String time  = sc.nextLine();
       
       String bus = "INSERT INTO Bus (Bus_Name, Capacity, Departure, Arrival, Fee, Time_am_pm) VALUES (?,?,?,?,?,?)";
       
       cg.addPassenger(bus, busname, capa, from, to, fee, time);
       
       
   } 
   
   public void viewBus(){
       
       
         
         String qry = "SELECT * FROM Bus";
        String[] headers = {"Bus ID ", "Bus Name","Capacity", "Departure", "Arrival", "Fee", "Time(Am / Pm)"};
        String[] columns = {"Bus_ID", "Bus_Name", "Capacity", "Departure", "Arrival", "Fee","Time_am_pm"};
        
         Config cg = new Config();
         cg.viewPassenger(qry, headers, columns);
       
       
   }
   
   
   public void mainBus(){
       
       
       
          Bus bs = new Bus();
       Scanner sc = new Scanner(System.in);
       Config cg = new Config();
       
       String res;
       do{
         System.out.println("");
       System.out.println("1. Add Bus ");
       System.out.println("2. View Bus ");
       System.out.println("3. Update Bus ");
       System.out.println("4. Delete Bus ");
       System.out.println("5. Exit");
       
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
               bs.addBus();
               break;
           case 2:
               bs.viewBus();
               break;
           case 3:
               bs.viewBus();
               String updateBus  = "UPDATE Bus SET  Departure = ?, Arrival = ?, Fee = ?, Time_am_pm = ? WHERE Bus_ID = ?";
               
               
                int busid;
                while (true) {
                System.out.print("Select Bus ID to update: ");
                if (sc.hasNextInt()) {
                    busid = sc.nextInt();
                    if (cg.getSingleValues("SELECT Bus_ID  FROM Bus  WHERE Bus_ID = ?", busid) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Bus doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Bus ID.");
                    sc.next(); 
                }
            }
        
               System.out.print("Enter new From(Departure Location):  ");
               String newfrom  = sc.next();
               System.out.print("Ente new To(Arrival Location): ");
               String newto = sc.next();
               System.out.print("Enter new Fee: ");
               double newfee = sc.nextDouble();
               System.out.print("Enter new (Time(AM / PM): ");
               String newtime = sc.next(); 
               cg.updatePassenger(updateBus,newfrom, newto, newfee, newtime,busid);
               break;
           case 4:
               bs.viewBus();
               String deleteBus = "DELETE FROM Bus WHERE Bus_ID = ? ";
                              int bdelete;
                while (true) {
                System.out.print("Select Bus ID to delete: ");
                if (sc.hasNextInt()) {
                    bdelete = sc.nextInt();
                    if (cg.getSingleValues("SELECT Bus_ID  FROM Bus  WHERE Bus_ID = ?", bdelete) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Bus doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Bus ID.");
                    sc.next(); 
                }
            }
        
               cg.deletePassenger(deleteBus, bdelete);
               break;
           case 5:
               System.out.println("Exit.");
               break;
           
           
       }
       System.out.println("");
       System.out.print("Do you want to continue in Bus Panel ? Yes or No: ");
       res = sc.next();
       }while(res.equalsIgnoreCase("yes"));   
   }
    
   
}
