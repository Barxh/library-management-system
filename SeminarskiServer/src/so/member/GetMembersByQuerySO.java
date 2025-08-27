/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.member;

import domain.Member;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class GetMembersByQuerySO extends AbstractSO {

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    @Override
    protected void precondition(Object param) throws Exception {

        if (param == null || !(param instanceof String)) {
            throw new Exception("Object is the wrong type!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        String condition = (String) param;
        try {

            members = repository.getByQuery(new Member(), " where JMBG Like '" + condition + "%'");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the members", e);
        }
    }
}
