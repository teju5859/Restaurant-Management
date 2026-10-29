package com.rest.CLI;

import com.rest.models.table;
import com.rest.services.TableService;
import java.util.List;
import java.util.Scanner;

public class tablecli{
    private final TableService services;
    private final Scanner scanner=new Scanner(System.in);

    public tablecli(TableService services){
        this.services=services;
    }

    public void start(){
        System.out.println("===RESTARUANRT TABLE MANAGEMENT===");
        System.out.println("1.View All Tables");
        System.out.println("0.Exit");

        System.out.print("Enter choice: ");
        int choice=Integer.parseInt(scanner.nextLine());

        if(choice==1){
            List<table> tables = services.getAllTables();

            System.out.println("\n=== ALL TABLES ===");
            for(table table : tables){
                System.out.println(table);
            }
        }
        else if (choice==0){
            System.out.println("THANK YOU");
        }

        else{
            System.out.println("Invalid Choice");
        }

        scanner.close();
    }

}