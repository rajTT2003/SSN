package EmployeePayroll;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeePayroll {
    // Attributes
    protected int idNumber;
    protected String firstName;
    protected String lastName;
    protected String position;
    protected float hoursWorked;
    protected Scanner scan;

    // Default Constructor
    public EmployeePayroll() {
        idNumber = 00000;
        firstName = "John";
        lastName = "Doe";
        position = "none";
        hoursWorked = 0.0f;
        scan = new Scanner(System.in);
    }

    // Primary Constructor
    public EmployeePayroll(int idNumber, String firstName, String lastName, String position, float hoursWorked) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hoursWorked = hoursWorked;
        scan = new Scanner(System.in);
    }

    // Copy Constructor
    public EmployeePayroll(EmployeePayroll worker) {
        this.idNumber = worker.idNumber;
        this.firstName = worker.firstName;
        this.lastName = worker.lastName;
        this.position = worker.position;
        this.hoursWorked = worker.hoursWorked;
        scan = new Scanner(System.in);
    }

    // Setters and Getters
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

    // Employee Data Control
    public void Add() {
        File file = new File("EmployeePayroll.txt");
        try (FileWriter fileWriter = new FileWriter("EmployeePayroll.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if (!file.exists() || file.length() == 0) {
                bufferedWriter.write("ID NUMBER\t\tFIRST NAME\t\tLAST NAME\t\tPOSITION\t\tHOURS WORKED\n");
            }
            bufferedWriter.write(idNumber + "\t\t\t");
            bufferedWriter.write(firstName + "\t\t\t");
            bufferedWriter.write(lastName + "\t\t\t");
            bufferedWriter.write(position + "\t\t\t");
            bufferedWriter.write(hoursWorked + "\n");
        } catch (IOException e) {
            System.err.println("Unable to add data to file!");
            e.printStackTrace();
        }
        System.out.println("Employee record created successfully.");
    }

    public void Update(int idNumber, Scanner input) {
        try {
            File file = new File("EmployeePayroll.txt");
            File tempFile = new File("TempEmployeePayroll.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            scan = new Scanner(file);
            String header = scan.nextLine(); // Read and write the header
            writer.write(header + "\n");
            boolean found = false;
           //TO DO: CHANGE SCAN AND INPUT scanners around
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split("\t\t\t");
                int recIdNumber = Integer.parseInt(parts[0]);
                String recFirstName = parts[1];
                String recLastName = parts[2];
                String recPosition = parts[3];
                float recHoursWorked = Float.parseFloat(parts[4]);
     
                if (recIdNumber == idNumber) {
                   found = true;
                    System.out.println("Employee Found.");
                    int choice = 1;
                    // New Data + idNumber variable
                    int newIdNumber = recIdNumber;
                    String newFName = recFirstName;
                    String newLName = recLastName;
                    String newPosition = recPosition;
                    float newHours = recHoursWorked;
    
                    while (choice != 6) {
                        System.out.println("Select an Option:");
                        System.out.println("1.Change Id Number\n2.Change First Name\n3.Change Last Name\n4.Change Position\n5.Change Hours Worked\n6.Confirm Changes\nInput:");
                        choice = input.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Enter new Id Number:");
                                newIdNumber = input.nextInt();
                                break;
                            case 2:
                                System.out.println("Enter new First Name:");
                                newFName = input.next();
                                break;
                            case 3:
                                System.out.println("Enter new Last Name:");
                                newLName = input.next();
                                break;
                            case 4:
                                System.out.println("Enter new Position:");
                                newPosition = input.next();
                                break;
                            case 5:
                                System.out.println("Enter new Hours Worked:");
                                newHours = input.nextFloat();
                                break;
                            case 6:
                                if (newIdNumber == recIdNumber && newFName.equals(recFirstName) && newLName.equals(recLastName) && newPosition.equals(recPosition) && newHours == recHoursWorked) {
                                    System.out.println("Employee record was not updated");
                                    break;
                                }
                                System.out.println("Employee Record was updated");
                                break;
                            default:
                                System.out.println("Incorrect Input. Try Again");
                        }


                    }
    
                    writer.write(newIdNumber + "\t\t\t" + newFName + "\t\t\t" + newLName + "\t\t\t" + newPosition + "\t\t\t" + newHours + "\n");
                } else {
                    writer.write(line + "\n");
                }

                
            }

            writer.close();
            scan.close();
            file.delete();
            tempFile.renameTo(file);
            if(!found){
                 System.out.println("Employee Not Found");
            }
           
    
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
    public void view(Scanner input) {
        try {
            scan = new Scanner(new File("EmployeePayroll.txt"));
    
            System.out.print("Enter Worker Id Number: ");
            int workId = input.nextInt();
    
            boolean found = false;
    
            if (scan.hasNextLine()) {
                scan.nextLine(); // Skip the header
            }
    
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\t\t\t"); // Set the delimiter
    
                int recIdNumber = lineScanner.nextInt();
                String recFirstName = lineScanner.next();
                String recLastName = lineScanner.next();
                String recPosition = lineScanner.next();
                float recHoursWorked = lineScanner.nextFloat();
    
                if (recIdNumber == workId) {
                    System.out.println("ID NUMBER\tFIRST NAME\tLAST NAME\tPOSITION\tHOURS WORKED");
                    System.out.println(recIdNumber + "\t\t" + recFirstName + "\t\t" + recLastName + "\t\t" + recPosition + "\t\t" + recHoursWorked);
                    found = true;
                    
                    break;
                }
             
            }

            if (!found) {
                System.out.println("Employee Not Found!");
            }
        
        } catch (IOException e) {
            System.err.println("An error has occurred. Unable to retrieve data");
            e.printStackTrace();
        }
    }
    public void viewAll() {
        try {
            scan = new Scanner(new File("EmployeePayroll.txt"));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);

            }

        } catch (IOException e) {
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
            String header = scan.nextLine(); // Read and write the header
            writer.write(header + "\n");
            boolean found = false;
    
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] parts = line.split("\t\t\t");
                int recIdNumber = Integer.parseInt(parts[0]);
    
                if (recIdNumber == workId) {
                    // If the ID matches, skip this record (delete it)
                    found = true;
                } else {
                    // Otherwise, write the record to the temporary file
                    writer.write(line + "\n");
                }
            }
    
            writer.close();
            scan.close();
    
            if (found) {
                // Delete the original file
                if (!inputFile.delete()) {
                    System.err.println("Failed to delete the original file.");
                }
    
                // Rename the temporary file to the original file name
                if (!tempFile.renameTo(inputFile)) {
                    System.err.println("Failed to rename the temporary file.");
                }
    
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Record not found.");
            }
        } catch (IOException e) {
            System.err.println("An error has occurred while deleting the record.");
            e.printStackTrace();
        }
    }
    // Close the Scanner in the end or when it's no longer needed
    public void closeScanner() {
        if (scan != null) {
            scan.close();
        }
    }
}
