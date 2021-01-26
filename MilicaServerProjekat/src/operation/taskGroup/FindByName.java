/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.taskGroup;

import domain.Task;
import domain.TaskGroup;
import java.util.List;
import operation.AbstractGenericOperation;
import repository.Repository;

/**
 *
 * @author FON
 */
public class FindByName extends AbstractGenericOperation{

    List<TaskGroup> tgs;
            
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        tgs = repository.search((TaskGroup) param);
        for(TaskGroup tg : tgs){
            tg.setTasks(repository.getAllBy(new Task(), "taskgroupId", tg.getId().toString()));
        }
    }

    public List<TaskGroup> getTgs() {
        return tgs;
    }

   
    
    
    
    
}
