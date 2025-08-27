/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.loan;

import domain.Loan;
import java.util.Date;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class AddLoanSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {

        if (param == null || !(param instanceof Loan)) {
            throw new Exception("Invalid class time data");
            
        }
        List<Loan> membersLoans = repository.getByQuery(new Loan(), " where m.memberID =" + ((Loan)param).getMember().getId());
        if (membersLoans.size() >= 3) {
            throw new Exception("Member can have maximum 3 loans at the time.");
        }
        if (((Loan)param).getBook().getStockQuantity() < 1) {
            throw new Exception("There are no available copy of required book.");
        }
        if(((Loan)param).getMember().getMembershipValidityDate().before(new Date(System.currentTimeMillis()))){
            throw new Exception("Member first need to renew membership!"); 
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add(param);
        Loan loan = (Loan) param;
        loan.getBook().setStockQuantity(loan.getBook().getStockQuantity() - 1);
        repository.update(loan.getBook());
    }
    
}
