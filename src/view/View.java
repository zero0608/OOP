package view;

import controller.DataController;
import model.Book;

import java.util.ArrayList;
import java.util.Scanner;

/*
    Viết menu cho phép thực hiện các chức năng 1, 2
 */
public class View {
    public static void main(String[] args) {
        int choice = 0;
        var booksFileName = "BOOK.DAT";
        var controller = new DataController();
        var books = new ArrayList<Book>();
        var isBookChecked = false;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("_______________MENU_______________");
            System.out.println("1. Thêm một đầu sách vào file.");
            System.out.println("2. Hiển thị danh sách các sách có trong file.");
            System.out.println("0. Thoát khỏi ứng dụng.");
            System.out.println("Bạn chọn ? ");

            choice = scanner.nextInt();
            scanner.nextLine();// doc bo dong chua lua chon

            switch (choice) {
                case 0:
                    System.out.println("_____________________________________________");
                    System.out.println("Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!");
                    break;

                case 1:
                    if(!isBookChecked){
                        checkBookID(controller, booksFileName);
                        isBookChecked = true;
                    }
                    String[] specs = {"Science", "Art", "Economic", "IT"};
                    String bookName, author, spec;
                    int year, quan, sp;
                    System.out.println("Nhập tên sách: ");
                    bookName = scanner.nextLine();

                    System.out.println("Nhập tên tác giả: ");
                    author = scanner.nextLine();

                    do {
                        System.out.println("Nhập thể loại sách: ");
                        System.out.println("1. Science.\n2. Art.\n3. Economic.\n4. IT.");
                        sp = scanner.nextInt();
                    } while (sp < 1 || sp > 4);

                    spec = specs[sp - 1];

                    System.out.println("Nhập năm xuất bản: ");
                    year = scanner.nextInt();

                    System.out.println("Nhập số lượng: ");
                    quan = scanner.nextInt();
                    //public Book(int bookID, String bookName, String author,
                    //                String specialization, int publishYear, int quantity)
                    Book book = new Book(0, bookName, author, spec, year, quan);
                    controller.writeBookToFile(book, booksFileName);
                    break;

                case 2:
                    books = controller.readBooksFromFile(booksFileName);
                    showBookInfo(books);
                    break;
            }
        } while (choice != 0);
    }

    private static void checkBookID(DataController controller, String fileName) {
        var listBooks = controller.readBooksFromFile(fileName);
        Book.setId(listBooks.get(listBooks.size()-1).getBookID() + 1);
    }

    private static void showBookInfo(ArrayList<Book> books) {
        System.out.println("_________________Thông tin sách trong file_________________");
        for (var b : books) {
            System.out.println(b);
        }

    }
}
