/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.member;

import domain.Loan;
import domain.Member;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class MembershipRenewalSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Member)) {
            throw new Exception("Invalid class time data");
        }
        Member m = (Member) param;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.MONTH, 1);

        Date margin = cal.getTime();   

       
        if(m.getMembershipValidityDate().after(margin))
            throw new Exception("Membership can't be renew before one month of membership expiring date");
        List<Loan> loans = repository.getByQuery(new Loan(), " where m.memberID = " + m.getMemberID());
        if (!loans.isEmpty()) {
            throw new Exception("Member has loans! System can't renew membership!");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.update(param);
    }

}
