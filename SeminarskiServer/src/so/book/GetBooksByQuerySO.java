/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.book;

import domain.Author;
import domain.Book;
import domain.AuthorBook;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class GetBooksByQuerySO extends AbstractSO {

    private List<Book> books;
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof String)) {
            throw new Exception("Object is the wrong type!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            books = repository.getByQuery(new Book(), " Where title like '%" + (String) param + "%'");
            for (Book book : books) {
            List<AuthorBook> itemAuthors = repository.getByQuery(new AuthorBook(), " where b.bookid =" + book.getId());
            List<Author> authors  = new ArrayList();
            for (AuthorBook itemAuthor : itemAuthors) {
                authors.add(itemAuthor.getAuthor());
            }
            book.setAuthor(authors);
        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the students", e);
        }
        
    }

    public List<Book> getBooks() {
        return books;
    }

    
}
