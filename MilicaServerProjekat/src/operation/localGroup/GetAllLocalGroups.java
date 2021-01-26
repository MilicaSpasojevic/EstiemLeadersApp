/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.localGroup;

import domain.LocalGroup;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author FON
 */
public class GetAllLocalGroups extends AbstractGenericOperation{

    List<LocalGroup> lgs;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        lgs = repository.getAll((LocalGroup) param);
        
    }

    public List<LocalGroup> getLgs() {
        return lgs;
    }
    
    
    
}
