/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.loan;

import domain.Loan;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class DeleteLoanSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Loan)) {
            throw new Exception("Invalid class time data");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        repository.delete(param);
        Loan loan = (Loan) param;
        loan.getBook().setStockQuantity(loan.getBook().getStockQuantity() + 1);
        repository.update(loan.getBook());
    }
    
}
