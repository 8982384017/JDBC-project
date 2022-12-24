import java.sql.*;
import java.util.Scanner;

public class App {

    static int delay() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Saving Records ...");
            for (int ii = 0; ii < 20000; ii++) {
                for (int iii = 0; iii < 90000; iii++) {
                }
            }
        }
        return 0;
    }

    static void Menu() {
        System.out.println("\033\143");

        System.out.println("           Main Menu          ");
        System.out.println("---------------------------------");
        System.out.println();
        System.out.println("1. Insert Product");
        System.out.println("2. Show Product");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("0. Exit");
        System.out.println();
    }

    static void InsertProduct(Connection con, Statement st) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Gagan@20");
            st = con.createStatement();
            System.out.println("\033\143");
            Scanner sc = new Scanner(System.in);
            System.out.println("     Insert Product     ");
            System.out.println();
            System.out.print("Enter Product id : ");
            int id = sc.nextInt();
            System.out.print("Enter Product Name : ");
            String Name = sc.next();
            System.out.print("Enter Product Quantity : ");
            int Quantity = sc.nextInt();
            System.out.print("Enter Product Price : ");
            int Price = sc.nextInt();

            String query = "insert into product value('" + id + "','" + Name + "','" + Quantity + "','" + Price
                    + "')";
            st.execute(query);
            delay();
            System.out.println("Data Inserted.");
            con.close();
            System.out.println("Press 1 to go main menu :- ");
            int press = sc.nextInt();
            if (press == 1)
                Menu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void ShowProduct(Connection con, Statement st) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Gagan@20");
            st = con.createStatement();

            ResultSet resultSet = st.executeQuery("SELECT * from product");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + "\t\t");
                }
                System.out.println("");
            }
            System.out.println("Press 1 to go main menu :- ");
            Scanner sc = new Scanner(System.in);
            int press = sc.nextInt();
            if (press == 1)
                Menu();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void UpdateProduct(Connection con, Statement st) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Gagan@20");
            st = con.createStatement();
            System.out.println("\033\143");
            Scanner sc = new Scanner(System.in);
            System.out.println("Update Product");
            System.out.println();
            System.out.println("1.Product Name");
            System.out.println("2.Product Quantity");
            System.out.println("3.Product Price");
            System.out.print("Enter your choice:- ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Product_id :- ");
                    String Old = sc.next();
                    System.out.print("Enter New name :- ");
                    String New = sc.next();
                    String query = "update product set name='" + New + "' where id='" + Old + "'";
                    st.execute(query);
                    break;
                case 2:
                    System.out.print("Enter Product_id :- ");
                    String Old1 = sc.next();
                    System.out.print("Enter New quantity :- ");
                    String New1 = sc.next();
                    String query1 = "update product set quantity='" + New1 + "' where id='" + Old1 + "'";
                    st.execute(query1);
                    break;
                case 3:
                    System.out.print("Enter Product_id :- ");
                    String Old2 = sc.next();
                    System.out.print("Enter New price :- ");
                    String New2 = sc.next();
                    String query2 = "update product set price='" + New2 + "' where id='" + Old2 + "'";
                    st.execute(query2);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Value Updated.");
            System.out.print("Press 1 to go main menu :- ");
            int press = sc.nextInt();
            if (press == 1)
                Menu();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void DeleteProduct(Connection con, Statement st) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Gagan@20");
            st = con.createStatement();

            try (Scanner sc = new Scanner(System.in)) {
                System.out.print("Enter Product Name : ");
                String product = sc.nextLine();
                String query = "delete from product where name='" + product + "'";
                st.execute(query);
                delay();
                System.out.println("Product Deleted");
                System.out.println("Press 1 to go main menu :- ");
                int press = sc.nextInt();
                if (press == 1)
                    Menu();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "Gagan@20");
            Statement st = con.createStatement();
            Scanner sc = new Scanner(System.in);

            Menu();

            while (true) {
                System.out.print("Enter your Choice : ");
                int choice = sc.nextInt();
                int goback = 0;

                switch (choice) {
                    case 1:
                        InsertProduct(con, st);
                        break;
                    case 2:
                        ShowProduct(con, st);
                        Menu();
                        break;
                    case 3:
                        UpdateProduct(con, st);
                        Menu();
                        break;
                    case 4:
                        DeleteProduct(con, st);
                        Menu();
                        break;
                    case 0:
                        goback = 1;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                if (goback == 1)
                    break;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
