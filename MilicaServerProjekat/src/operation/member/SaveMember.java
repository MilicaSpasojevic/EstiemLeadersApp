/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.member;

import domain.Member;
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
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Member)param);
    }
    
}
