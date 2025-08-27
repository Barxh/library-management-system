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
public class GetAllBooksSO extends AbstractSO {

    private List<Book> books;
    @Override
    protected void precondition(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        books = repository.getAll(new Book());
        for (Book book : books) {
            List<AuthorBook> itemAuthors = repository.getByQuery(new AuthorBook(), " where b.bookid =" + book.getId());
            List<Author> authors  = new ArrayList();
            for (AuthorBook itemAuthor : itemAuthors) {
                authors.add(itemAuthor.getAuthor());
            }
            book.setAuthor(authors);
        }
        
        
    }

    public List<Book> getBooks() {
        return books;
    }
    
}
