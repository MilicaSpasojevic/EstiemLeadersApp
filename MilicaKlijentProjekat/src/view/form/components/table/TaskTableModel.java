/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.components.table;

import domain.Task;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FON
 */
public class TaskTableModel extends AbstractTableModel{
    private List<Task> tasks;
    private String [] columnNames = {"ID","Title","Deadline","Description","Task type", "Task status", "Start date","Finish date"};

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    
    
    @Override
    public Object getValueAt(int i, int i1) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
       
        
        switch(i1){
            case 0: return tasks.get(i).getId();
            case 1: return tasks.get(i).getTitle();
            case 2: 
                if(tasks.get(i).getDeadline()!=null) {
                    return sdf.format(tasks.get(i).getDeadline());
                }else return "";
            case 3: return tasks.get(i).getDescription();
            case 4: return tasks.get(i).getTaskType();
            case 5: return tasks.get(i).getTaskStatus();
            case 6: 
                if(tasks.get(i).getStartDate()!=null){
                return sdf.format(tasks.get(i).getStartDate());
                } else return "";
            case 7: 
                if(tasks.get(i).getFinishDate()!=null){
                    return sdf.format(tasks.get(i).getFinishDate());
                } else return "";
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }
    
    public void addTask(Task t){
        tasks.add(t);
        fireTableDataChanged();
    }

    public List<Task> getTasks() {
        return tasks;
    }
    
    public void editTask(Task t){
        for(Task tt : tasks){
            if(tt.getId().equals(t.getId())){
               tt.setDeadline(t.getDeadline());
               tt.setDescription(t.getDescription());
               tt.setFinishDate(t.getFinishDate());
               tt.setStartDate(t.getStartDate());
               tt.setTaskStatus(t.getTaskStatus());
               tt.setTaskType(t.getTaskType());
               tt.setTitle(t.getTitle());
               tt.setTg(t.getTg());
               fireTableDataChanged();
            }
        }
        
    }

    public Task getTaskAt(int row) {
        return tasks.get(row);
    }

    public void deleteTask(Task t) {
        for(Task tt : tasks){
            if(tt.getId().equals(t.getId())){
                tasks.remove(tt);
                fireTableDataChanged();
            }
        }
        
    }

    public void exist(String id) throws Exception {
        for(Task t : tasks){
            if(t.getId().equals(Long.parseLong(id))){
                throw new Exception("Task with thid ID already exists");
            }
        }
    }
    
    
    
    
}
