/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.components.table;

import domain.TaskStatus;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Milica
 */
public class TableColorCellRenderer implements TableCellRenderer{
    private static final TableCellRenderer renderer = new DefaultTableCellRenderer();
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = renderer.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
        
        Color background = Color.WHITE;
        Object result = jtable.getModel().getValueAt(row, 5);
        TaskStatus ts = (TaskStatus) result;
        
        if(ts.equals(TaskStatus.FINISHED)){
            background = Color.GREEN;
        }
        
        c.setBackground(background);
       
        return c;
    }
    
}
