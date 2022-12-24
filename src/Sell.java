import java.sql.*;
import java.util.Scanner;
import java.util.Formatter;

public class Sell {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String path = "jdbc:mysql://localhost:3306/shop";
            String username = "root";
            String password = "Gagan@20";
            Connection conn = DriverManager.getConnection(path, username, password);

            System.out.println("\033\143");
            boolean check = true;
            while (check) {
                System.out.println("1.Show Databases");
                System.out.println("2.Sell Product");
                System.out.println("3.Exit");
                System.out.print("Enter your choice:- ");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                if (choice == 1) {
                    Statement st = conn.createStatement();
                    String show = "select *from product;";
                    ResultSet query = st.executeQuery(show);
                    Formatter fmt = new Formatter();
                    fmt.format("%5s %20s %10s %10s\n\n", "Id", "Name", "Quantity", "Price");
                    while (query.next()) {
                        fmt.format("%5s %20s %10s %10s\n", query.getString(1), query.getString(2), query.getString(3),
                                query.getString(4));
                    }
                    System.out.println(fmt);
                } else if (choice == 2) {
                    Statement st2 = conn.createStatement();
                    System.out.println("\n               Sell Product\n");
                    System.out.print("Enter product id:- ");
                    int id = sc.nextInt();
                    System.out.print("Enter quantity:- ");
                    int qnty = sc.nextInt();
                    String query = "select *from product where id=" + id + ";";
                    ResultSet s = st2.executeQuery(query);
                    while (s.next()) {
                        int price = Integer.parseInt(s.getString(4));
                        int old_quantity = Integer.parseInt(s.getString(3));
                        if (qnty > old_quantity) {
                            System.out.println("Ohh! Out of stock");
                        } else {
                            System.out.println("Total Amount :- "+price * qnty);
                            System.out.println();
                            int updated_quantity = old_quantity - qnty;
                            Statement st3 = conn.createStatement();
                            String query1 = "update product set quantity=" + updated_quantity + " where id=" + id + "";
                            st3.execute(query1);
                            System.out.println("You have sccessfully sell the selected product");
                        }
                    }
                } else if (choice == 3) {
                    check = false;
                    conn.close();
                    System.out.println("Your are exited");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}