package EmployeePayroll;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DepartmentRates {
    protected int deptCode;
    protected String deptName;
    protected float regularRate;
    protected float overtimeRate;
    private Scanner input;

    //Default Constructor
    public DepartmentRates()
    {
        input = new Scanner(System.in);
        deptCode = 0;
        deptName = "No Department";
        regularRate = 0.0f;
        overtimeRate = 0.0f;
    }
    //Primary Constructor
    public DepartmentRates(int deptCode, String deptName, float regularRate, float overtimeRate)
    {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.regularRate = regularRate;
        this.overtimeRate = overtimeRate;
    }
    //Copy Constructor
    public DepartmentRates(DepartmentRates department)
    {
        this.deptCode = department.deptCode;
        this.deptName = department.deptName;
        this.regularRate = department.regularRate;
        this.overtimeRate = department.overtimeRate;
    }
    //Setters and Getters
    public int getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(int deptCode) {
        this.deptCode = deptCode;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public float getRegularRate() {
        return regularRate;
    }
    public void setRegularRate(float regularRate) {
        this.regularRate = regularRate;
    }
    public float getOvertimeRate() {
        return overtimeRate;
    }
    public void setOvertimeRate(float overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    //Department Data Control
    public void Add() {
        File file = new File("DepartmentRates.txt");
        try (FileWriter fileWriter = new FileWriter("DepartmentRates.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if(!file.exists() || file.length()==0){
                bufferedWriter.write("DEPT. CODE\t\tDEPT. NAME\t\tREGUALR RATES $\t\tOVERTIME RATES $\n");
            }
            bufferedWriter.write(deptCode + "\t\t\t\t");
            bufferedWriter.write(deptName + "\t\t\t\t");
            bufferedWriter.write(regularRate + "\t\t\t");
            bufferedWriter.write(overtimeRate + "\t\t\t");
        } catch (IOException e) {
            System.err.println("Unable to add data to file!");
            e.printStackTrace();
        }
        System.out.println("Department created successfully.");
    }
    
    public void Update(int deptCode) {
        try {
            File file = new File("DepartmentRates.txt");
            File tempFile = new File("TempDepartmentRates.txt");
            Scanner scanner = new Scanner(file);
            Scanner input = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                int recDeptNum = Integer.parseInt(parts[0]);
                String recDeptName = parts[1];
                float  recRegularRate = Float.parseFloat(parts[2]);
                float  recOvertimeRate = Float.parseFloat(parts[3]);
                if (recDeptNum == deptCode) {
                    int choice = 1;
                    //New Data + idNumber variable
                    String newDeptName = recDeptName;
                    float newRegularRate = recRegularRate;
                    float newOvertimeRate = recOvertimeRate;
                
                    while(choice != 5)
                    {
                        System.out.println("Select an Option:");
                        System.out.println("1.Change Department Code\n2.Change Department Name\n3.Change Regualar Rate\n4.Change Overtime Rate\n5.Make No  Change\nInput:");
                        choice = input.nextInt();
                        switch(choice)
                        {
                            case 1:
                                System.out.println("Enter new Department Code:");
                                deptCode = input.nextInt();
                                break;
                            case 2:
                                System.out.println("Enter new Department Name:");
                                newDeptName = input.next();
                                break;
                            case 3:
                                System.out.println("Enter new Regular Rate:");
                                newRegularRate = input.nextFloat();
                                break;
                            case 4:
                                System.out.println("Enter new Overtime Rate:");
                                newOvertimeRate = input.nextFloat();
                                break;
                            case 5:
                                if(recDeptNum == deptCode && newDeptName.equals(recDeptName) && newRegularRate == recRegularRate && newOvertimeRate == recOvertimeRate )
                                {
                                    System.out.println("Department record was not updated");
                                    break;
                                }
                                System.out.println("Department Record was updated");
                                break;
                            default:
                                System.out.println("Incorrect Input. Try Again");

                        }
                    }

                    writer.write(deptCode + "\t" + newDeptName + "\t" + newDeptName + "\t" + newRegularRate + "\t" + newOvertimeRate + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            scanner.close();
            input.close();
            file.delete();
            tempFile.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  view()
    {
       try
       {
        Scanner in = new Scanner(System.in);

        deptCode = 0;
        deptName = "";
        regularRate = 0.0f;
        overtimeRate = 0.0f;
        

        
        System.out.print("Enter Department Code:");
        int findDeptCode = in.nextInt();

        int departmentCode;
        String dName;
        float regRate;
        float overRate;

        Scanner fileInp = new Scanner(new File("DepartmentRates.txt"));
        while(fileInp.hasNext())
        {
            departmentCode = fileInp.nextInt();
            dName = fileInp.next();
            regRate = fileInp.nextFloat();
            overRate =  fileInp.nextFloat();

            if(departmentCode == findDeptCode)
            {
                deptCode = departmentCode;
                deptName = dName;
                regularRate = regRate;
                overtimeRate = overRate;
                System.out.println( "DEPT. CODE\tDEPT. NAME\tREGULAR RATE $\tOVERTIME RATE $\n");
                System.out.println( deptCode +"\t\t"+ deptName +"\t\t" + regularRate +"\t\t"+ overtimeRate );
                break;
            }
        }
        fileInp.close();
        in.close();
        if(deptCode == 0)
        {
            System.out.println("Not found!");
        }
       }
       catch(FileNotFoundException e)
       {
            System.err.println("An error has occurred. Unable to retrive data");
            e.printStackTrace();
        } 
    }
    
    public void viewAll() {
        try {
            Scanner fileInp = new Scanner(new File("DepartmentRates.txt"));
            System.out.println( "DEPT. CODE\tDEPT. NAME\tREGULAR RATE $\tOVERTIME RATE $\n");
            while (fileInp.hasNext()) {
                int departmentCode = fileInp.nextInt();
                String dName = fileInp.next();
                float  regRate = fileInp.nextFloat();
                float overRate = fileInp.nextFloat();
                System.out.println( departmentCode +"\t\t"+ dName +"\t\t" +regRate+"\t\t"+ overRate);   
            }
    
            fileInp.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error has occurred. Unable to retrieve data");
            e.printStackTrace();
        }
    }



}
