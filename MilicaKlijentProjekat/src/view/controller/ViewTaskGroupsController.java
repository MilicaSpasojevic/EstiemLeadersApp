/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.TaskGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.ViewCordinator;
import view.form.FrmViewTaskGroups;
import view.form.components.table.TaskGroupTableModel;

/**
 *
 * @author Milica
 */
public class ViewTaskGroupsController {
    private final FrmViewTaskGroups frmViewTask;

    public ViewTaskGroupsController(FrmViewTaskGroups frmViewTask) {
        this.frmViewTask = frmViewTask;
        addActionListener();
    }
    
    public void openForm(){
        prepareTable();
        frmViewTask.setVisible(true);
    }

    private void addActionListener() {
        frmViewTask.addBtnDetailsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = frmViewTask.getTblTaskGroups().getSelectedRow();
                 if(row>=0){
            TaskGroupTableModel tgm = (TaskGroupTableModel) frmViewTask.getTblTaskGroups().getModel();
            TaskGroup tg = tgm.getTaskGroupAt(row);
            ViewCordinator.getInstance().addParam("TaskGroup",tg);
            ViewCordinator.getInstance().openTaskGroupDetails();
        } else {
            JOptionPane.showMessageDialog(frmViewTask, "You need to select task group!");
        }
            }
        });
        
        frmViewTask.addBtnAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewCordinator.getInstance().openAddTaskGroupF();
            }
        });
        
        frmViewTask.addBtnSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String search = frmViewTask.getTxtTaskGroup().getText().trim();
        if(search.equals("")){
            prepareTable();
            return;
        }
            TaskGroup tg = new TaskGroup();
            tg.setSearch(search);
            
            try{
                List<TaskGroup> groups = Communication.getInstance().findByName(tg);
                TaskGroupTableModel tgm = new TaskGroupTableModel(groups);
                frmViewTask.getTblTaskGroups().setModel(tgm);
            }catch(SocketException se){
            JOptionPane.showMessageDialog(frmViewTask, "Server is closed, Goodbye");
            System.exit(0);
        }catch(Exception e){
                JOptionPane.showMessageDialog(frmViewTask, "Error: "+e.getMessage());
            }
            }
        });
    }
    
    
    public void prepareTable() {
        List<TaskGroup> groups = new ArrayList<>();
        try {
            groups = Communication.getInstance().getAllTaskGroups();
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmViewTask, "Server is closed, Goodbye");
            System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(FrmViewTaskGroups.class.getName()).log(Level.SEVERE, null, ex);
        }
        TaskGroupTableModel tgm = new TaskGroupTableModel(groups);
        frmViewTask.getTblTaskGroups().setModel(tgm);
        

    }

    public void refreshTable() {
        List<TaskGroup> groups = new ArrayList<>();
        try {
            groups = Communication.getInstance().getAllTaskGroups();
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmViewTask, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewTask, "Error: "+ex.getMessage());
        }
        TaskGroupTableModel tgm = new TaskGroupTableModel(groups);
        frmViewTask.getTblTaskGroups().setModel(tgm);
    }
    
    
    
}
