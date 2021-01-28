/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.taskGroup;

import domain.Task;
import domain.TaskGroup;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Milica
 */
public class UpdateTaskGroup extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        TaskGroup taskGroup = (TaskGroup) param;
        List<Task> tasks = taskGroup.getTasks();
        List<Task> oldTasks = (List<Task>)repository.getAll(new Task());
        List<Task> oldTaskTasks = new ArrayList<>();
        for(Task t : oldTasks){
            if(t.getTg().getId().equals(taskGroup.getId())){
                oldTaskTasks.add(t);
            }
        }
        for(Task t : oldTaskTasks){
            System.out.println(t.getTg());
            if(!tasks.contains(t)){
                repository.delete((Task) t);
            }
        }
        for(Task t : tasks){
            List<Task> tas = (List<Task>) repository.search((Task)t);
            if(tas.isEmpty()){
                repository.add((Task) t);
            }else repository.edit((Task) t);
        }
        
        
    }
    
}
