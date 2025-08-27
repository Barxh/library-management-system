/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.member;

import domain.Member;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class AddMemberSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Member)) {
            throw new Exception("Invalid class time data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add(param);
    }

}
