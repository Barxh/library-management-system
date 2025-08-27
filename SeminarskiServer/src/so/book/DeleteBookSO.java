/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.book;

import domain.Author;
import domain.Book;
import domain.AuthorBook;
import domain.ItemReceipt;
import domain.Loan;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class DeleteBookSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Book)) {
            throw new Exception("Object is the wrong type!");

        } else {
            Book book = (Book)param;
            checkStructuralConstraints(book);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete(new ItemReceipt(0, 0, (Book)param, 0));
        repository.delete(new AuthorBook((Book)param, new Author()));
        repository.delete(param);
        
    }

    private void checkStructuralConstraints(Book book) throws Exception {
        List<Loan> booksByQuery = repository.getByQuery(new Loan(), " where L.bookid = " + book.getId());
        if (!booksByQuery.isEmpty()) {
            throw new Exception("Book with that ID has involved in member loan and can not be deleted. Delete the loans and try again.");
        }
    }
}
