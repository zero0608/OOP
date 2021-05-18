package view;

import controller.DataController;
import model.Book;
import model.Reader;

import java.util.ArrayList;
import java.util.Scanner;

/*
    Viết menu cho phép thực hiện các chức năng 1, 2
 */
public class View {
    public static void main(String[] args) {
        int choice = 0;
        var booksFileName = "BOOK.DAT";
        var usersFileName = "REDER.DAT";
        var controller = new DataController();
        var books = new ArrayList<Book>();
        var readers= new ArrayList<Reader>();
        var isBookChecked = false;
        var isuserchecked = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("_______________MENU_______________");
            System.out.println("1. Thêm một đầu sách vào file.");
            System.out.println("2. Hiển thị danh sách các sách có trong file.");
            System.out.println("3. Thêm một người đọc vào file.");
            System.out.println("4. Hiển thị danh sách người đọc có trong file.");
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
                case 3:
                    if(!isuserchecked){
                        checkReaderID(controller, usersFileName);
                        isuserchecked = true;
                    }
                    String fullName,address, phoneNumber;
                    System.out.println("Nhập tên reader: ");
                    fullName = scanner.nextLine();

                    System.out.println("Nhập tên địa chỉ: ");
                     address = scanner.nextLine();

                    System.out.println("Nhập tên sđt: ");
                    phoneNumber = scanner.nextLine();

                    Reader reader = new Reader(0, fullName, address, phoneNumber);
                    controller.writeReaderToFile(reader,usersFileName);
                    break;
                case 4:
                    readers = controller.readReadersFromFile(usersFileName);
                    showReaderInfo(readers);
                    break;
            }
        } while (choice != 0);
    }
    private static void checkReaderID(DataController controller, String fileName) {
        var readers = controller.readReadersFromFile(fileName);
        if(readers.size() == 0){
            ///
        }else{
            Reader.setId(readers.get(readers.size()-1).getReaderID() + 1);
        }


    }
    private static void checkBookID(DataController controller, String fileName) {
        var listBooks = controller.readBooksFromFile(fileName);
        if(listBooks.size() == 0){
            ///
        }else{
            Book.setId(listBooks.get(listBooks.size()-1).getBookID() + 1);
        }

    }

    private static void showBookInfo(ArrayList<Book> books) {
        System.out.println("_________________Thông tin sách trong file_________________");
        for (var b : books) {
            System.out.println(b);
        }
    }

    private static void showReaderInfo(ArrayList<Reader> readers) {
        System.out.println("_________________Thông tin reader trong file_________________");
        for (var b : readers) {
            System.out.println(b);
        }
    }
}
