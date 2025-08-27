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
public class GetAllMembersSO extends AbstractSO {

    private List<Member> members;
    @Override
    protected void precondition(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        members = repository.getAll(new Member());
    }

    public List<Member> getMembers() {
        return members;
    }
    
    
}
