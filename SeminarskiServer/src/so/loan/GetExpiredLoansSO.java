/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.loan;

import domain.Author;
import domain.AuthorBook;
import domain.Loan;
import so.AbstractSO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikol
 */
public class GetExpiredLoansSO extends AbstractSO {

    List<Loan> loans = new ArrayList();
    @Override
    protected void precondition(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        loans = repository.getByQuery(new Loan(), " WHERE M.membershipValidityDate < '" + (new Date(System.currentTimeMillis())).toString()+"'");
        for(Loan loan : loans){
            List<AuthorBook> authorBooks = repository.getByQuery(new AuthorBook(), " where it.bookid = " + loan.getBook().getId());
            List<Author> authors= new ArrayList();
            for(AuthorBook ab : authorBooks){
                authors.add(ab.getAuthor());
            }
            loan.getBook().setAuthor(authors);
        }
    }

    public List<Loan> getLoans() {
        return loans;
    }
    
}
