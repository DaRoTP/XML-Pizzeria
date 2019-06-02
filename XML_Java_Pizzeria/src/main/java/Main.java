import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) throws DocumentException, IOException {
        PizzaCRUD pc = new PizzaCRUD();

        Scanner bufor = new Scanner(System.in);
        Scanner bufor2 = new Scanner(System.in);
        boolean loop = true;


        while(loop){

            System.out.println("<=========================>");
            System.out.println("1. View");
            System.out.println("2. Add");
            System.out.println("3. Delete");
            System.out.println("4. Exit");

            int choice = bufor.nextInt();
            String strChoice;

            switch(choice){
                case 1:{
                    System.out.println("<=========================>");
                    System.out.println("1. Pizzas");
                    System.out.println("2. Beverages");
                    System.out.println("3. Job Positions");
                    System.out.println("4. Employees");

                    choice = bufor.nextInt();
                    switch(choice){
                        case 1: pc.showElements("pizza");
                            break;
                        case 2: pc.showElements("beverage");
                            break;
                        case 3: pc.showElements("job_position");
                            break;
                        case 4: pc.showElements("employee");
                            break;
                        default:
                            break;
                    }
                }
                break;
                case 2:{
                    System.out.println("<=========================>");
                    System.out.println("1. Pizza");
                    System.out.println("2. Beverage");
                    System.out.println("3. Job Position");
                    System.out.println("4. Employee");

                    choice = bufor.nextInt();
                    switch(choice){
                        case 1:{
                            System.out.println("pizza");
                        }
                        break;
                        case 2:{
                            pc.createBeverage();
                        }
                        break;
                        case 3:{
                            pc.createJobPos();
                        }
                        break;
                        case 4:{
                            pc.createEmployee();
                        }
                        break;
                    }
                }
                break;
                case 3:{
                    System.out.println("<=========================>");
                    System.out.println("1. Pizzas");
                    System.out.println("2. Beverages");
                    System.out.println("3. Job Positions");
                    System.out.println("4. Employees");

                    choice = bufor.nextInt();
                    switch(choice){
                        case 1: {
                            System.out.print("ID: ");
                            strChoice = bufor2.nextLine();
                            pc.delete("pizzeria/pizzas/pizza[@pizzaID='"+strChoice+"']");
                        }
                            break;
                        case 2: {
                            System.out.print("ID: ");
                            strChoice = bufor2.nextLine();
                            pc.delete("pizzeria/beverages/beverage[@beverageID='"+strChoice+"']");
                        }
                            break;
                        case 3: {
                            System.out.print("ID: ");
                            strChoice = bufor2.nextLine();
                            pc.delete("pizzeria/job_positions/job_position[@positionID='"+strChoice+"']");
                        }
                            break;
                        case 4: {
                            System.out.print("ID: ");
                            strChoice = bufor2.nextLine();
                            pc.delete("pizzeria/employees/employee[@employeeID='"+strChoice+"']");
                        }
                            break;
                    }
                }
                break;
                default:
                {
                    System.out.println("kk");
                    loop = false;
                }
                break;
            }
        }
    }

}
