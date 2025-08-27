/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.loan;

import domain.Author;
import domain.AuthorBook;
import domain.Loan;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class GetAllLoansSO extends AbstractSO {

    private List<Loan> loans;

    public List<Loan> getLoans() {
        return loans;
    }
    @Override
    protected void precondition(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        loans = repository.getAll(new Loan());
        for(Loan loan : loans){
            List<AuthorBook> authorBooks = repository.getByQuery(new AuthorBook(), " where it.bookid = " + loan.getBook().getId());
            List<Author> authors= new ArrayList();
            for(AuthorBook ab : authorBooks){
                authors.add(ab.getAuthor());
            }
            loan.getBook().setAuthor(authors);
        }
        
    }
    
}
