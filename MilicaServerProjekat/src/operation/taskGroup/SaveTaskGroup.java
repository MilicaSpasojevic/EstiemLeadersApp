/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.taskGroup;

import domain.TaskGroup;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author FON
 */
public class SaveTaskGroup extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        GetAllTaskGroup operation = new GetAllTaskGroup();
        operation.execute(new TaskGroup());
        List<TaskGroup> tgs = operation.getTaskGroups();
        for(TaskGroup taskGroup : tgs){
          if(taskGroup.getName().equals(((TaskGroup) param).getName())){
            throw new Exception("TaskGroup needs to have unique name");
        }
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((TaskGroup) param);
    }
    
}
