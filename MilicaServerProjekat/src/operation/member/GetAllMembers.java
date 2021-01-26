/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.member;

import domain.Member;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author FON
 */
public class GetAllMembers extends AbstractGenericOperation{
    
    private List<Member> members;
    
    

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        members = repository.getAll((Member) param);
    }

    public List<Member> getMembers() {
        return members;
    }
    
    
    
    
}
