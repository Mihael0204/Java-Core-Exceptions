package school.sorokin.javacore;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CatalogManager catalogManager = new CatalogManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();

            int choice = readInt("Выберите действие: ");
            switch (choice) {
                case 1 -> addBook();
                case 2 -> takeBook();
                case 3 -> returnBook();
                case 4 -> listBooks();
                case 5 -> running = false;
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        System.out.println("Работа завершена.");
    }

    private static void printMenu() {
        System.out.println("\n--- Каталог библиотеки ---");
        System.out.println("1. Добавить книгу");
        System.out.println("2. Взять книгу");
        System.out.println("3. Вернуть книгу");
        System.out.println("4. Показать все книги");
        System.out.println("5. Выход");
    }

    private static void addBook() {
        String title = readString("Введите название книги: ");
        String author = readString("Введите автора книги: ");
        int copies = readInt("Введите количество экземпляров: ");

        catalogManager.addBook(title, author, copies);
        System.out.println("Книга добавлена.");
    }

    private static void takeBook() {
        String title = readString("Введите название книги для взятия: ");
        try {
            catalogManager.takeBook(title);
            System.out.println("Книга выдана.");
        } catch (ItemNotFoundException | NoAvailableCopiesException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void returnBook() {
        String title = readString("Введите название книги для возврата: ");
        try {
            catalogManager.returnBook(title);
            System.out.println("Книга возвращена.");
        } catch (ItemNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void listBooks() {
        List<Book> books = catalogManager.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Каталог пуст.");
        } else {
            System.out.println("--- Список книг ---");
            for (Book book : books) {
                System.out.printf("Название: %s | Автор: %s | Кол-во: %d%n",
                        book.getTitle(), book.getAuthor(), book.getAvailableCopies());
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: ожидалось число. Попробуйте снова.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
