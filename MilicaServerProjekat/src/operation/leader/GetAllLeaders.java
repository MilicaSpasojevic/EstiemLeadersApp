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
public class GetAllLeaders extends AbstractGenericOperation{

    List<CommitteeLeader> leaders;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        leaders = repository.getAll((CommitteeLeader) param);
    }

    public List<CommitteeLeader> getLeaders() {
        return leaders;
    }
    
    
    
}
