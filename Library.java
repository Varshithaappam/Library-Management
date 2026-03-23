import java.sql.*;
import java.util.Scanner;

public class Library {

    Scanner sc = new Scanner(System.in);

    public void addBook() {
        try {
            Connection con = DBConnection.getConnection();

            sc.nextLine(); // clear buffer
            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            String query = "INSERT INTO books(title, author) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, author);

            ps.executeUpdate();
            System.out.println("Book Added!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewBooks() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM books";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | Issued: " +
                        rs.getBoolean("isIssued")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void issueBook() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String check = "SELECT isIssued FROM books WHERE id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setInt(1, id);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("isIssued")) {
                    System.out.println("Already Issued!");
                } else {
                    String update = "UPDATE books SET isIssued=true WHERE id=?";
                    PreparedStatement ps2 = con.prepareStatement(update);
                    ps2.setInt(1, id);
                    ps2.executeUpdate();
                    System.out.println("Book Issued!");
                }
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void returnBook() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String update = "UPDATE books SET isIssued=false WHERE id=?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Book Returned!");
            else
                System.out.println("Book not found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 🔍 Search Book
    public void searchBook() {
        try {
            Connection con = DBConnection.getConnection();

            sc.nextLine(); // clear buffer
            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            String query = "SELECT * FROM books WHERE title=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | Issued: " +
                        rs.getBoolean("isIssued")
                );
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ❌ Delete Book
    public void deleteBook() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String query = "DELETE FROM books WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Book Deleted!");
            else
                System.out.println("Book not found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}