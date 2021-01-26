/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;


import communication.Communication;
import domain.Member;
import domain.Task;
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
import view.form.FrmTaskGroupDetails;
import view.form.components.table.MemberTableModel;
import view.form.components.table.TaskTableModel;

/**
 *
 * @author Milica
 */
public class TaskGroupDetailController {
    private final FrmTaskGroupDetails frmTaskGroup;

    public TaskGroupDetailController(FrmTaskGroupDetails frmTaskGroup) {
        this.frmTaskGroup = frmTaskGroup;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmTaskGroup.setVisible(true);
    }

    private void addActionListener() {
        frmTaskGroup.addBtnAddNewTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewCordinator.getInstance().openAddTaskForm();
            }
        });
        
        frmTaskGroup.addBtnEditTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = frmTaskGroup.getTblTasks().getSelectedRow();
        if(row>=0){
            TaskTableModel ttm = (TaskTableModel) frmTaskGroup.getTblTasks().getModel();
            Task task = ttm.getTaskAt(row);
            ViewCordinator.getInstance().addParam("TASK", task);
            ViewCordinator.getInstance().openEditTaskForm();
        }else JOptionPane.showMessageDialog(frmTaskGroup, "You must select task first!");
            }
        });
    }

    public void prepareView() {
        TaskGroup tg = (TaskGroup) ViewCordinator.getInstance().getParam("TaskGroup");
        frmTaskGroup.getLblTaskGroupName().setText(tg.getName());
        List<Task> tasks = tg.getTasks();
        for(Task task : tasks){
            task.setTg(tg);
        }
        TaskTableModel tm = new TaskTableModel(tasks);
        frmTaskGroup.getTblTasks().setModel(tm);
        
        List<Member> allMembers = new ArrayList<>();
        try {
            allMembers = Communication.getInstance().getAllMembers();
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmTaskGroup, "Server is closed, Goodbye");
            System.exit(0);
        } 
        catch (Exception ex) {
            Logger.getLogger(FrmTaskGroupDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Member> members = new ArrayList<>();
        for(Member m : allMembers){
            if(m.getTaskGroup().getId().equals(tg.getId())){
                members.add(m);
            }
        }
        ViewCordinator.getInstance().addParam("TaskGroupMembers", members);
        MemberTableModel mtm = new MemberTableModel(members);
        frmTaskGroup.getTblMembers().setModel(mtm);
    
    }

    public FrmTaskGroupDetails getFrmTaskGroup() {
        return frmTaskGroup;
    }
    
    
    
    
    

    
    
}
