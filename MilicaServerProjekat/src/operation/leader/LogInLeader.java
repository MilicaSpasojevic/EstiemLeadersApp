/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.leader;

import domain.CommitteeLeader;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author FON
 */
public class LogInLeader extends AbstractGenericOperation{

    
    CommitteeLeader committeeLeader;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<CommitteeLeader> leaders = repository.getAll((CommitteeLeader) param);
        CommitteeLeader cm = (CommitteeLeader) param;
        for(CommitteeLeader leader : leaders){
            if(leader.getEmail().equals(cm.getEmail()) && leader.getPassword().equals(cm.getPassword())){
                committeeLeader = leader;
                return;
            }
        }
        throw new Exception("Committee leader doesn't exists!");
    }

    public CommitteeLeader getCommitteeLeader() {
        return committeeLeader;
    }
    
    

    
    
    
    
}
