/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.loan;

import com.sun.source.doctree.ParamTree;
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
public class GetLoansByQuerySO extends AbstractSO {

    private List<Loan> loans;

    public List<Loan> getLoans() {
        return loans;
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof String)) {
            throw new Exception("Object is the wrong type!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        loans = repository.getByQuery(new Loan(), " where m.JMBG LIKE '" + (String) param + "%'");
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
