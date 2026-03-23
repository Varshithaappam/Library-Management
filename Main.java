import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: lib.addBook(); break;
                case 2: lib.viewBooks(); break;
                case 3: lib.issueBook(); break;
                case 4: lib.returnBook(); break;
                case 5: lib.searchBook(); break;
                case 6: lib.deleteBook(); break;
                case 7: System.exit(0);
            }
        }
    }
}