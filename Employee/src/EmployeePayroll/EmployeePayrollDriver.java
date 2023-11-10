package EmployeePayroll;


import java.util.Scanner;
import EmployeePayroll.EmployeePayroll;
import java.util.InputMismatchException;





public class EmployeePayrollDriver 
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while( choice != 4)
        {
            try
            {
                System.out.println("SSN Payroll Management System");
                System.out.println("Select An Option:");
                System.out.println("1.Department Rates");
                System.out.println("2.Employee Information");
                System.out.println("3.Employee Payroll");
                System.out.println("4.Exit");
                choice = scan.nextInt();
                 switch(choice)
            {
                case 1:
                    break;
                case 2:
                    int empChoice = 0;
                    while (empChoice != 6) 
                    {
                        try{
                                System.out.println("SSN Employee Information");
                                System.out.println("Select An Option:");
                                System.out.println("1.Add New Employee");
                                System.out.println("2.Update Existing Employee Information");
                                System.out.println("3.View An Employee Information");
                                System.out.println("4.View All Employee Information");
                                System.out.println("5.Delete Employee Information");
                                System.out.println("6.Back");
                                empChoice = scan.nextInt();
                                switch(empChoice)
                                {
                                    case 1:
                                        EmployeePayroll newEmployee = new EmployeePayroll();
                                        System.out.println("New Employee");
                                        System.out.println("Enter Employee ID number: ");
                                        newEmployee.setIdNumber(scan.nextInt());
                                        System.out.println("Enter Employee First Name: ");
                                        newEmployee.setFirstName(scan.next());
                                        System.out.println("Enter Employee Last Name: ");
                                        newEmployee.setLastName(scan.next());
                                        System.out.println("Enter Employee Positon: ");
                                        newEmployee.setPosition(scan.next());
                                        System.out.println("Enter Employee Hours Worked: ");
                                        newEmployee.setHoursWorked(scan.nextFloat());
                                        newEmployee.Add();
                                        break;
                                    
                                        
                                        
                                    
                                    case 2:
                                    
                                        System.out.println("Update Existing Employee Data");
                                        System.out.println("Enter Employee ID:");
                                        int idNumber = scan.nextInt();
                                        EmployeePayroll updater = new EmployeePayroll();
                                        updater.Update(idNumber);
                                        break;
                                    
                                    
                                    case 3:
                                        EmployeePayroll viewRecord = new EmployeePayroll();
                                        viewRecord.view();
                                        break;

                                    case 4:
                                        EmployeePayroll viewAll = new EmployeePayroll();
                                        viewAll.viewAll();
                                        break;
                                    
                                    case 5:
                                        System.out.println("Delete Employee Data");
                                        System.out.println("Enter Employee ID:");
                                        idNumber = scan.nextInt();
                                        EmployeePayroll delete = new EmployeePayroll();
                                        delete.delete(idNumber);
                                        break;
                                    
                                    case 6:
                                        break;

                                    default:
                                        System.out.println("Invalid Input:");
                                        break;
                                }   
                
                        }catch (InputMismatchException e) {
                                        System.err.println("Datatype Error...");
                                        scan.nextLine(); 
                                    }
                    }
                default:
                    System.err.println("Invalid Input...");
                    
            
            }
            }catch (InputMismatchException e) 
            {
                System.err.println("Datatype Error...");
                scan.nextLine(); 
            }

           
        }
        
        scan.close();
    }
}
