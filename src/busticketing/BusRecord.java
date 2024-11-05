
package busticketing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class BusRecord {
    
    
    public void addBusRecord(){
        
          Config cg = new Config();
        Scanner sc = new Scanner(System.in);
        System.out.println(" - Passenger List  - ");    
          Passenger ps = new Passenger();
          ps.viewPassenger();
          System.out.println(" - Bus List - ");
            Bus bs = new Bus();
            bs.viewBus();
    
    
         int passid;
                while (true) {
                System.out.print("Select Passenger ID: ");
                if (sc.hasNextInt()) {
                    passid = sc.nextInt();
                    if (cg.getSingleValues("SELECT Passenger_ID  FROM Passenger  WHERE Passenger_ID = ?", passid) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Passenger doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Passenger ID.");
                    sc.next(); 
                }
            }
        
        
       
         int busid;
                while (true) {
                System.out.print("Select Bus ID: ");
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
        
        
        
        sc.nextLine();
        System.out.print("Seat No: ");
        String seatNumber  = sc.nextLine();
        
        
        System.out.print("Cash: ");
        double cash = sc.nextDouble();
        
        String fee = "SELECT Fee FROM Bus WHERE Bus_ID = ? ";
        double fees = cg.getSingleValues(fee, busid);
        
        while(cash < fees ){
            System.out.print("Not enough cash, Try Again: ");
             cash = sc.nextDouble();
        }
        double change = cash - fees;
       
        System.out.println("-----------------------------------");
        System.out.printf("Total Change is: %.2f\n", change); 
        System.out.println("-----------------------------------");
        
        sc.nextLine();
        String depart =  "SELECT Departure FROM Bus WHERE Bus_ID = ? ";
        String dep = cg.getSingleVal(depart, busid);
         
        
        String arrival = "SELECT Arrival FROM Bus WHERE  Bus_ID = ? ";
        String arrive = cg.getSingleValues1(arrival, busid);
         
         LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
    
        System.out.print("Time(Am / Pm): ");
        String time = sc.nextLine();
        
        String status = "Not Arrive";
        String sqlBusRecord = "INSERT INTO BusRecords (Passenger_ID, Bus_ID, Seat_No, Fee, Cash, Change, Departure, Arrival, Date, Time,Status) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
      cg.addPassenger(sqlBusRecord, passid, busid, seatNumber, fees, cash, change, dep, arrive, date, time,status);
    }
    public void viewRecords(){
       String view = "SELECT BusRecords.BusRecord_ID, First_Name, Last_Name, Bus.Bus_Name, Bus.Departure, Bus.Arrival, Status "
             + "FROM BusRecords "
             + "LEFT JOIN Passenger ON Passenger.Passenger_ID = BusRecords.Passenger_ID "
             + "LEFT JOIN Bus ON Bus.Bus_ID = BusRecords.Bus_ID";

        
        String[] header = {"Bus Record ID","First Name","Last Name", "Bus Name", "Departure", "Arrival", "Status"};
        String[] column = {"BusRecord_ID","First_Name","Last_Name", "Bus_Name","Departure", "Arrival", "Status"};
        
         Config cg = new Config();
         cg.viewPassenger(view, header, column);
        
    }
    
    
    
    public void mainRecords(){
        
        BusRecord br  = new BusRecord();
          Config cg = new Config();
        Scanner sc = new Scanner(System.in);
        
        
        String res;
        do{
            System.out.println("");
        System.out.println("1. Add a Record ");
        System.out.println("2. View Record ");
        System.out.println("3. Update Record ");
        System.out.println("4. Delete Record ");
        System.out.println("5. Exit ");        
        
        
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
                 br.addBusRecord();
                 break;
             case 2:
                 br.viewRecords();
                 break;
             case 3:
                 br.viewRecords();
                 
                 String update = "UPDATE BusRecords SET Status = ? WHERE  BusRecord_ID = ? ";
                
                 int brid;
                while (true) {
                System.out.print("Select Records ID to update: ");
                if (sc.hasNextInt()) {
                    brid = sc.nextInt();
                    if (cg.getSingleValues("SELECT BusRecord_ID  FROM BusRecords  WHERE BusRecord_ID = ?", brid) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Records  doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Records ID.");
                    sc.next(); 
                }
            }
                 System.out.print("Enter new Status: ");
                 String newstats = sc.next();
                 
                 cg.updatePassenger(update, newstats, brid);
                 break;
             case 4:
                 br.viewRecords();
                 String delete = "DELETE FROM BusRecords WHERE BusRecord_ID = ? ";
              
                 
                 int brid1;
                while (true) {
                System.out.print("Select Records ID to delete : ");
                if (sc.hasNextInt()) {
                    brid1 = sc.nextInt();
                    if (cg.getSingleValues("SELECT BusRecord_ID  FROM BusRecords  WHERE BusRecord_ID = ?", brid1) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Records  doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Records ID.");
                    sc.next(); 
                }
            }
                 cg.deletePassenger(delete, brid1);
                 break;
             case 5:
                 System.out.println("Exit.");
                 break;
       
         }
            System.out.println("");
            System.out.print("Do you want to continue on Records? Yes or No: ");
            res  = sc.next();
            
        }while(res.equalsIgnoreCase("yes"));
    }
    
    
    
    
    
    
}
