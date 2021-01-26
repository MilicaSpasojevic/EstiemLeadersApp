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
import javax.swing.JOptionPane;
import view.coordinator.ViewCordinator;
import view.form.FrmAddTaskGroup;

/**
 *
 * @author Milica
 */
public class AddTaskGroupController {
    private final FrmAddTaskGroup frmAddTask;

    public AddTaskGroupController(FrmAddTaskGroup frmAddTask) {
        this.frmAddTask = frmAddTask;
        addActionListener();
    }
    
    public void openForm(){
        frmAddTask.setVisible(true);
    }

    private void addActionListener() {
        frmAddTask.addBtnAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
            String name = frmAddTask.getTxtName().getText().trim();
            Communication.getInstance().addTaskGroup(new TaskGroup(null, name, null));
            JOptionPane.showMessageDialog(frmAddTask, "Task group successfully added!");
            ViewCordinator.getInstance().refreshTaskGroupsView();
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmAddTask, "Server closed: "+se.getMessage());
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmAddTask, "Failed adding task group: "+ e.getMessage());
            if(e.getMessage().equals("SERVER CLOSED")){
                System.exit(0);
            }
        }
            }
        });
    }
    
    

}
