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

/**
 *
 * @author FON
 */
public class GetAllTaskGroup extends AbstractGenericOperation{

    List<TaskGroup> taskGroups;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        taskGroups = repository.getAll((TaskGroup) param);
        for(TaskGroup tg : taskGroups){
            tg.setTasks(repository.getAllBy(new Task(), "taskgroupId", tg.getId().toString()));
            for(Task task : tg.getTasks()){
                task.setTg(tg);
            }
        }
    }

    public List<TaskGroup> getTaskGroups() {
        return taskGroups;
    }
    
    
}
