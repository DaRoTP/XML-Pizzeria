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
        Node n = document.selectSingleNode("pizzeria/pizzas");
        Element root = (Element) n;
        Element pizza = root.addElement("pizza");
        Element pizzaName = pizza.addElement("pizza_name");
        pizzaName.setText("Wdah");
        pizza.addAttribute("pizzaID", "Wdhh");
        pizza.addAttribute("pizza_type", "spicy");

        Element topping = pizza.addElement("topping");
        topping.setText("cheese");
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

//    public void update(String xPath){
//        Node node = this.document.selectSingleNode("//formula1/engines/engine[manufacter='" + oldManufacter + "']");
//        Element engine = (Element) node;
//        engine.element("manufacter").setText(newManufacter);
//        save();
//    }


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
