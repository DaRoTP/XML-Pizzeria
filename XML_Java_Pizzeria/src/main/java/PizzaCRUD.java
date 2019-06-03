import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PizzaCRUD {

    private final SAXReader READER = new SAXReader();
    private Document document;

    public PizzaCRUD() throws DocumentException{
        this.document = this.READER.read("Pizzeria_DataBase.xml");
    }

    public void read(String path){
        List<Node> nodes = this.document.selectNodes(path);
        for (Node n : nodes) {
            Element e = (Element) n;
            System.out.println(this.getContent(e));
        }
    }

    public void printJobPos(){
        List<Node> nodes = document.selectNodes("pizzeria/job_positions/job_position");
        for( Node node: nodes){
            System.out.println("\nID# "+node.selectSingleNode("@positionID").getText()+"\n- job position: " + node.selectSingleNode("position_name").getText()
                    +"\n- description: "+node.selectSingleNode("position_description").getText());
        }
    }
    public void printBeverages(){
        List<Node> nodes = document.selectNodes("pizzeria/beverages/beverage");
        for( Node node: nodes){
            try {
                System.out.println("\nID# " + node.selectSingleNode("@beverageID").getText()
                        + "\n- beverage name: " + node.selectSingleNode("beverage_name").getText()
                        + "\n- units: " + node.selectSingleNode("units").getText()
                        + "\n- price: (" + node.selectSingleNode("price").selectSingleNode("@currency").getText() + ") " + node.selectSingleNode("price").getText());
            }
            catch(NullPointerException e){
                System.out.println(" ");
            }
            }
    }
    public void printEmployee(){
        List<Node> nodes = document.selectNodes("pizzeria/employees/employee");
        for( Node node: nodes){
            try {
                System.out.println("\nID# " + node.selectSingleNode("@employeeID").getText()
                        + "\n- name: " + node.selectSingleNode("name").getText()
                        + "\n- surname: " + node.selectSingleNode("surname").getText()
                        + "\n- personal Number: " + node.selectSingleNode("personal_No").getText()
                        + "\n- phone Number: (" + node.selectSingleNode("phone_number").selectSingleNode("@prefix").getText()+") "+node.selectSingleNode("phone_number").getText()
                        + "\n- E-Mail: " + node.selectSingleNode("e_mail").getText()
                        + "\n- employment date: " + node.selectSingleNode("employment_date").getText()
                        + "\n- salary: (" + node.selectSingleNode("salary").selectSingleNode("@currency").getText() + ") " + node.selectSingleNode("salary").getText()
                        + "\n- Job position ID: " + node.selectSingleNode("positionID").selectSingleNode("@refid").getText());
            }
            catch(NullPointerException e){
                System.out.println(" ");
            }
            }
    }
    public void printPizzas() {
        List<Node> nodes = document.selectNodes("pizzeria/pizzas/pizza");
        for (Node node : nodes) {
            System.out.println("\nID# " + node.selectSingleNode("@pizzaID").getText()
                    + "\n- type: " + node.selectSingleNode("@pizza_type").getText()
                    + "\n- name: " + node.selectSingleNode("pizza_name").getText()
                    + "\n- toppings: ");
            List<Node> tempNode = node.selectNodes("topping");
            for (Node tp : tempNode)
                System.out.print(tp.getText()+", ");

        }
        System.out.println(" ");
    }


    private String getContent(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<Element> i = element.elementIterator(); i.hasNext();) {
            Element e = i.next();
            stringBuilder.append(e.asXML());
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    public void createPizza() throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/pizzas");
        Element root = (Element) n;
        Element pizza = root.addElement("pizza");

        Element pizzaName = pizza.addElement("pizza_name");

        System.out.print("Pizza Name: ");
        pizzaName.setText(bufor.nextLine());
        System.out.print("Pizza ID: ");
        String PizzaID = bufor.nextLine();
        pizza.addAttribute("pizzaID", PizzaID);
        System.out.print("Pizza type ID: ");
        pizza.addAttribute("pizza_type", bufor.nextLine());

        while(true){
            System.out.print("Toppings: ");
            String chosenToping = bufor.nextLine();
            if(chosenToping.equals("."))
                break;
            else
            addToppings(PizzaID,chosenToping);
        }

        save();
    }

    public void addToppings(String ID,String toppingstr) throws IOException {

        Node n = document.selectSingleNode("pizzeria/pizzas/pizza[@pizzaID='"+ID+"']");
        Element root = (Element) n;
        Element topping = root.addElement("topping");

        topping.setText(toppingstr);

        save();
    }

    public void createBeverage() throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/beverages");
        Element root = (Element) n;
        Element pizza = root.addElement("beverage");
        Element beverageName = pizza.addElement("beverage_name");
        Element price = pizza.addElement("price");
        Element units = pizza.addElement("units");

        System.out.print("Name: ");
        beverageName.setText(bufor.nextLine());
        System.out.print("price: ");
        price.setText(bufor.nextLine());
        System.out.print("Units: ");
        units.setText(bufor.nextLine());

        System.out.print("Beverage ID: ");
        pizza.addAttribute("beverageID", bufor.nextLine());

        save();
    }

    public void createJobPos() throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/job_positions");
        Element root = (Element) n;
        Element pizza = root.addElement("job_position");
        Element positionName = pizza.addElement("position_name");
        Element posdescr = pizza.addElement("position_description");

        System.out.print("Name: ");
        positionName.setText(bufor.nextLine());
        System.out.print("Description: ");
        posdescr.setText(bufor.nextLine());

        System.out.print("Position ID: ");
        pizza.addAttribute("positionID", bufor.nextLine());

        save();
    }
    public void createEmployee() throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/employees");
        Element root = (Element) n;
        Element pizza = root.addElement("employee");

        Element name = pizza.addElement("name");
        Element surname = pizza.addElement("surname");
        Element personal_No = pizza.addElement("personal_No");
        Element phone_number = pizza.addElement("phone_number");
        Element e_mail = pizza.addElement("e_mail");
        Element employment_date = pizza.addElement("employment_date");
        Element salary = pizza.addElement("salary");
        Element position = pizza.addElement("positionID");



        System.out.print("Name: ");
        name.setText(bufor.nextLine());
        System.out.print("Surname: ");
        surname.setText(bufor.nextLine());
        System.out.print("personal Number: ");
        personal_No.setText(bufor.nextLine());
        System.out.print("Phone Number: ");
        phone_number.setText(bufor.nextLine());
        System.out.print("Phone Number Prefix: ");
        phone_number.addAttribute("prefix",bufor.nextLine());
        System.out.print("E-Mail: ");
        e_mail.setText(bufor.nextLine());
        System.out.print("Employment date: ");
        employment_date.setText(bufor.nextLine());
        System.out.print("Salary: ");
        salary.setText(bufor.nextLine());

        System.out.print("Job Position ID: ");
        position.addAttribute("refid", bufor.nextLine());
        System.out.print("employee ID: ");
        pizza.addAttribute("employeeID", bufor.nextLine());

        save();
    }

    private void save() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        Writer writer = new OutputStreamWriter(new FileOutputStream(this.document.getName()));
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        xmlWriter.write(this.document);
        xmlWriter.close();
    }

    public void delete(String xPath) throws IOException {
        Node node = this.document.selectSingleNode(xPath);
        node.detach();
        save();
    }

    public void sort(String xPath) {
        List<Node> nodes = this.document.selectNodes(xPath);
        Collections.sort(nodes, (Node n1, Node n2) -> n1.getStringValue().compareTo(n2.getStringValue()));
        for (Node n : nodes) {
            Element e = (Element) n;
            System.out.println(this.getContent(e));
        }
    }
    public void updateJobPos(String ID) throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/job_positions/job_position[@positionID='"+ID+"']");
        Element root = (Element) n;
        String answer = "";

        System.out.println("write '.' leave as it is");
        System.out.print("new Name: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("position_name").setText(answer);

        System.out.print("Description: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("position_description").setText(answer);

        save();
    }

    public void updatePizza(String ID) throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/pizzas/pizza[@pizzaID='"+ID+"']");
        Element root = (Element) n;
        String answer = "";

        System.out.println("write '.' leave as it is");
        System.out.print("new pizza name: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("pizza_name").setText(answer);

        System.out.print("new type: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.addAttribute("pizza_type",answer);

        save();
    }

    public void updateBeverage(String ID) throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/beverages/beverage[@beverageID='"+ID+"']");
        Element root = (Element) n;
        String answer = "";

        System.out.println("write '.' leave as it is");
        System.out.print("new beverage name: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("beverage_name").setText(answer);

        System.out.print("new Price: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("price").setText(answer);

        System.out.print("new unit count: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("units").setText(answer);
        save();
    }

    public void updateEmployee(String ID) throws IOException {
        Scanner bufor = new Scanner(System.in);
        Node n = document.selectSingleNode("pizzeria/employees/employee[@employeeID='"+ID+"']");
        Element root = (Element) n;
        String answer = "";

        System.out.println("write '.' leave as it is");
        System.out.print("new Name: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("name").setText(answer);

        System.out.print("new surname: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("surname").setText(answer);

        System.out.print("new personal Number: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("personal_No").setText(answer);

        System.out.print("new phone number: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("phone_number").setText(answer);

        System.out.print("new E-Mail: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("e_mail").setText(answer);

        System.out.print("new employment date: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("employment_date").setText(answer);

        System.out.print("new salary: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("salary").setText(answer);

        System.out.print("Description: ");
        answer = bufor.nextLine();
        if(!answer.equals("."))
            root.element("positionID").addAttribute("refid",answer);

        save();
    }



    public void showElements(String element){
        switch(element){
            case  "pizza":
                read("pizzeria/pizzas/pizza");
                break;
            case "beverage":
                read("pizzeria/beverages/beverage");
                break;
            case "job_position":
                read("pizzeria/job_positions/job_position");
                break;
            case "employee":
                read("pizzeria/employees/employee");
                break;
        }
    }


}
