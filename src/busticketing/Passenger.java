
package busticketing;

import java.util.Scanner;


public class Passenger {
    
    
    
   public void addPassenger(){
       
       
       Scanner sc = new Scanner(System.in);
       Config cg = new Config();
       
       
       System.out.println("");
       System.out.print("Enter Passenger First Name:  ");
       String fname = sc.next();
       System.out.print("Enter Passenger Last Name:  ");
       String lname = sc.next();
       System.out.print("Age: ");
       int age = sc.nextInt();
       System.out.print("Gender: ");
       String gender = sc.next();
       System.out.print("Contact No. ");
       String no = sc.next();
       System.out.print("Email: ");
       String email = sc.next();
       
       
       String sqlPass = "INSERT INTO Passenger (First_Name, Last_Name, Age, Gender, Contact_No, Email) VALUES (?,?,?,?,?,?)";
       
       cg.addPassenger(sqlPass, fname, lname, age, gender, no, email);
   }
   
   public void viewPassenger(){
        
         String qry = "SELECT * FROM Passenger";
        String[] hdrs = {"Passenger ID ", "First Name", "Last Name", "Age", "Gender", "Contact No", "Email"};
        String[] clmns = {"Passenger_ID", "First_Name", "Last_Name", "Age", "Gender", "Contact_No","Email"};
        
         Config cg = new Config();
         cg.viewPassenger(qry, hdrs, clmns);
       
   }
   
   public void mainPassenger(){
       
       
       
          Passenger ps = new Passenger();
       Scanner sc = new Scanner(System.in);
       Config cg = new Config();
       
       String res;
       do{
           System.out.println("");
       System.out.println("1. Add Passenger ");
       System.out.println("2. View Passenger ");
       System.out.println("3. Update Passenger ");
       System.out.println("4. Delete Passenger ");
       System.out.println("5. Exit");
       
       
       int choice;
         while (true) {
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
               ps.addPassenger();
               break;
           case 2:
               ps.viewPassenger();
                break;
           case 3:
               ps.viewPassenger();
               String passUP = "UPDATE Passenger SET Age = ?, Gender = ?, Contact_No = ?, Email = ? WHERE Passenger_ID = ? ";
               
               int update;
                while (true) {
                System.out.print("Select Passenger ID to update: ");
                if (sc.hasNextInt()) {
                    update = sc.nextInt();
                    if (cg.getSingleValues("SELECT Passenger_ID  FROM Passenger  WHERE Passenger_ID = ?", update) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Passenger doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Passenger ID.");
                    sc.next(); 
                }
            }
               System.out.print("Enter new Age: ");
               int newage = sc.nextInt();
               System.out.print("Enter new Gender:");
               String newgen = sc.next();
               System.out.print("Enter new Contact No. ");
               String newcon = sc.next();
               System.out.print("Enter new Email: ");
               String newemail = sc.next();
               
               cg.updatePassenger(passUP, newage, newgen, newcon, newemail, update);
               
               break;
           case 4:
               ps.viewPassenger();
               String passDEL = "DELETE FROM Passenger WHERE Passenger_ID = ?";
               
              
               
                int delete;
                while (true) {
                System.out.print("Select Passenger ID to delete: ");
                if (sc.hasNextInt()) {
                    delete = sc.nextInt();
                    if (cg.getSingleValues("SELECT Passenger_ID  FROM Passenger  WHERE Passenger_ID = ?", delete) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Passenger doesn't exist in the system.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Passenger ID.");
                    sc.next(); 
                }
            }
               cg.deletePassenger(passDEL, delete);
               
               break;
           
           case 5: 
               System.out.println("Exit.");
               break;
       
       }
        System.out.println("");
        System.out.print("Do you want to continue on Passenger? Yes or No: ");
        res = sc.next();
       }while(res.equalsIgnoreCase("yes"));
   }
    
    
    
    
}
