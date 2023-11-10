package EmployeePayroll;

import java.io.BufferedWriter;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class EmployeePayroll
{
    //Attributes
    protected int idNumber;
    protected String firstName;
    protected String lastName;
    protected String position;
    protected float hoursWorked;
    protected Scanner scan = new Scanner(System.in);
    //Default Constructor
    public  EmployeePayroll()
    {
        idNumber = 00000;
        firstName = "John";
        lastName = "Doe";
        position = "none";
        hoursWorked = 0.0f;
    }
    //Primary Constructor
    public EmployeePayroll(int idNumber, String firstName, String lastName, String position, float hoursWorked)
    {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hoursWorked = hoursWorked;
    }
    //Copy Constructor
    public EmployeePayroll(EmployeePayroll worker)
    {
        this.idNumber = worker.idNumber;
        this.firstName = worker.firstName;
        this.lastName = worker.lastName;
        this.position = worker.position;
        this.hoursWorked = worker.hoursWorked;
    }
    //Setters and Getters
    public int getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public float getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    //Employee Data Control
    public void Add() {
        File file = new File("EmployeePayroll.txt");
        try (FileWriter fileWriter = new FileWriter("EmployeePayroll.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if(!file.exists() || file.length()==0){
                bufferedWriter.write("ID NUMBER\t\tFIRST NAME\t\tLAST NAME\t\tPOSITON\t\tHOURS WORKED\n");
            }
            bufferedWriter.write(idNumber + "\t\t\t\t");
            bufferedWriter.write(firstName + "\t\t\t\t");
            bufferedWriter.write(lastName + "\t\t\t");
            bufferedWriter.write(position + "\t\t\t");
            bufferedWriter.write(hoursWorked + "\n");
        } catch (IOException e) {
            System.err.println("Unable to add data to file!");
            e.printStackTrace();
        }
        System.out.println("Employee record created successfully.");
    }

    public void Update(int idNumber) {
        try {
            File file = new File("EmployeePayroll.txt");
            File tempFile = new File("TempEmployeePayroll.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\t");
                int recIdNumber = Integer.parseInt(parts[0]);
                String recFirstName = parts[1];
                 String recLastName = parts[2];
                String recPosition = parts[3];
                float  recHoursWorked = Float.parseFloat(parts[4]);
                if (recIdNumber == idNumber) {
                    int choice = 1;
                    //New Data + idNumber variable
                    String newFName = recFirstName;
                    String newLName = recLastName;
                    String newPosition = recPosition;
                    float newHours = recHoursWorked;
                
                    while(choice != 6)
                    {
                        System.out.println("Select an Option:");
                        System.out.println("1.Change Id Number\n2.Change First Name\n3.Change Last Name\n4.Change Postion\n5.Change Hours Worked 6.Make No  Change\nInput:");
                        choice = scan.nextInt();
                        switch(choice)
                        {
                            case 1:
                                System.out.println("Enter new Id Number:");
                                idNumber = scan.nextInt();
                                break;
                            case 2:
                                System.out.println("Enter new First Name:");
                                newFName = scan.next();
                                break;
                            case 3:
                                System.out.println("Enter new Last Name:");
                                newLName = scan.next();
                                break;
                            case 4:
                                System.out.println("Enter new Positon:");
                                newPosition = scan.next();
                                break;
                            case 5:
                                System.out.println("Enter new Hours Worked:");
                                newHours = scan.nextFloat();
                                break;
                            case 6:
                                if(idNumber == recIdNumber && newFName.equals(recFirstName) && newLName.equals(recLastName) && newPosition.equals(recPosition) && hoursWorked == recHoursWorked )
                                {
                                    System.out.println("Employee record was not updated");
                                    break;
                                }
                                System.out.println("Employee Record was updated");
                                break;
                            default:
                                System.out.println("Incorrect Input. Try Again");

                        }
                    }

                    writer.write(idNumber + "\t" + newFName + "\t" + newLName + "\t" + newPosition + "\t" + newHours + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            file.delete();
            tempFile.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void view() {
        try {
            scan = new Scanner(new File("EmployeePayroll.txt"));
            scan = new Scanner(System.in);
            System.out.print("Enter Worker Id Number: ");
            int workId = scan.nextInt();
            
            boolean found = false;
   
            if (scan.hasNextLine()) {
              scan.nextLine(); // Skip the header
            }
            
            while (scan.hasNextLine()) {                   
                
                if (scan.hasNextInt()) {
                      
                    int recIdNumber = scan.nextInt();
                    if (recIdNumber == workId) {
                        String recFirstName = scan.next();
                        String recLastName = scan.next();
                        String recPosition = scan.next();
                        float recHoursWorked = scan.nextFloat();
                        
                        System.out.println("ID NUMBER\tFIRST NAME\tLAST NAME\tPOSITION\tHOURS WORKED");
                        System.out.println(recIdNumber + "\t\t" + recFirstName + "\t\t" + recLastName + "\t\t" + recPosition + "\t\t" + recHoursWorked);
                        found = true;
                        break;
                    }
                }
   
            }


            if (!found) {
                System.out.println("Employee Not Found!");
                
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error has occurred. Unable to retrieve data");           
        }
          
    }
    
     public void viewAll() {
        try {
            scan = new Scanner(new File("EmployeePayroll.txt"));
            System.out.println( "ID NUMBER\tFIRST NAME\tLAST NAME\tPOSITON\tHOURS WORKED\n");
            while (scan.hasNext()) {
                int idNum = scan.nextInt();
                String fName = scan.next();
                String lName = scan.next();
                String pos = scan.next();
                float hours = scan.nextFloat();
                System.out.println( idNum +"\t\t"+fName +"\t\t" +lName +"\t\t"+ pos +"\t\t"+ hours);
               
            }
    
        } catch (FileNotFoundException e) {
            System.err.println("An error has occurred. Unable to retrieve data");
            e.printStackTrace();
        }
    }

    public void delete(int workId) {
        try {
            File inputFile = new File("EmployeePayroll.txt");
            File tempFile = new File("TempEmployeePayroll.txt");
    
            scan = new Scanner(inputFile);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            boolean found = false;
    
            while (scan.hasNext()) {
                int idNum = scan.nextInt();
                String fName = scan.next();
                String lName = scan.next();
                String pos = scan.next();
                float hours = scan.nextFloat();
    
                if (idNum == workId) {
                    // If the ID matches, skip this record (delete it)
                    found = true;
                } else {
                    // Otherwise, write the record to the temporary file
                    writer.write(idNum + "\t" + fName + "\t" + lName + "\t" + pos + "\t" + hours + "\n");
                }
            }
    
            writer.close();

    
            if (found) {
                // Replace the original file with the temporary file
                if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                    System.out.println("Record deleted successfully.");
                } else {
                    System.err.println("Failed to delete the record.");
                }
            } else {
                System.out.println("Record not found.");
            }
        } catch (IOException e) {
            System.err.println("An error has occurred while deleting the record.");
            e.printStackTrace();
        }
    }
    
} 