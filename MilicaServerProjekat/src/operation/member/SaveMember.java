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
public class SaveMember extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
            if(param==null || !(param instanceof Member)){
            throw new Exception("Invalid member data!");
            
        }
            List<Member> members = repository.getAll(new Member());
            for(Member m : members){
                if(m.getEmail().equals(((Member)param).getEmail())){
                    throw new Exception("Member with this email already exists!");
                }
            }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Member)param);
    }
    
}
