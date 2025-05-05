package school.sorokin.javacore;

import java.util.ArrayList;
import java.util.List;

public class CatalogManager {
    List<Book> catalog;

    public CatalogManager() {
        catalog = new ArrayList<>();
    }

    public void addBook(String title, String author, int copies) {
        Book newBook = new Book(title, author, copies);
        for (Book book : catalog) {
            if (book.getTitle().equals(newBook.getTitle()) && book.getAuthor().equals(newBook.getAuthor())) {
                book.increaseCopies(copies);
                return;
            }
        }
        catalog.add(newBook);
    }

    public void takeBook(String title) throws ItemNotFoundException, NoAvailableCopiesException {
        Book book = findBookByTitle(title);
        if (book.getAvailableCopies() <= 0) {
            throw new NoAvailableCopiesException("Нет доступных экземпляров книги: " + title);
        }
        book.decreaseCopies(1);
    }

    public void returnBook(String title) throws ItemNotFoundException {
        Book book = findBookByTitle(title);
        book.increaseCopies(1);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(catalog);
    }

    private Book findBookByTitle(String title) throws ItemNotFoundException {
        for (Book book : catalog) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new ItemNotFoundException("Книга не найдена: " + title);
    }
}
