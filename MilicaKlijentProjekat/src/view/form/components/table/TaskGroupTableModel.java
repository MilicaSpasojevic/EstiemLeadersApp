/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.components.table;

import domain.TaskGroup;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FON
 */
public class TaskGroupTableModel extends AbstractTableModel{
    private List<TaskGroup> groups;
    private String [] columnNames = {"Task group"};

    public TaskGroupTableModel(List<TaskGroup> groups) {
        this.groups = groups;
    }
    
        
    @Override
    public int getRowCount() {
        return groups.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }    

    @Override
    public Object getValueAt(int i, int i1) {
        return groups.get(i).getName();
    }

    public TaskGroup getTaskGroupAt(int row) {
        return groups.get(row);
    }
    
}
