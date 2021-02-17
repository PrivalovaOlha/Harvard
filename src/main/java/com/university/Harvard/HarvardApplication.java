package com.university.Harvard;

import com.university.Harvard.service.DepartmentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class HarvardApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HarvardApplication.class, args);
        DepartmentService departmentService = context.getBean(DepartmentService.class);


        Scanner command = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Possible options: \n" +
                    "1.Who is head of department \n" +
                    "2.Show statistics of \n" +
                    "3. Show the average salary for the department \n" +
                    "4.Show count of employee for \n" +
                    "5.Global search by \n" +
                    "6.Stop");
            System.out.println("Please enter number of the command: ");

            switch (command.nextInt()) {
                case 1: {
                    System.out.println("Please enter name of department");
                    String departmentName = input.nextLine();
                    System.out.println(departmentService.getHead(departmentName));
                }
                break;
                case 2: {
                    System.out.println("Please enter name of department");
                    System.out.println(departmentService.statisticOfDegree(input.nextLine()));
                }
                break;
                case 3: {
                    System.out.println("Please enter name of department");
                    System.out.println(departmentService.getAvgSalary(input.nextLine()));
                }
                break;
                case 4: {
                    System.out.println("Please enter name of department");
                    System.out.println(departmentService.getCountOfEmployee(input.nextLine()));
                }
                break;
                case 5: {
                    System.out.println("Please enter word for searching");
                    System.out.println(departmentService.searchByName(input.nextLine()));
                }
                break;
                case 6: {
                    System.exit(0);
                }
                break;
                default:
                    System.out.println("Incorrect command");
            }
        }
    }
}
