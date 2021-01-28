/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.Task;
import domain.TaskGroup;
import domain.TaskStatus;
import domain.TaskType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import view.coordinator.ViewCordinator;
import view.form.FrmTask;
import view.form.components.table.TaskTableModel;
import view.form.mode.FrmMode;

/**
 *
 * @author Milica
 */
public class TaskController {
    private final FrmTask frmTask;
    Date deadLine;
    Date startDate;
    Date finishDate;

    public TaskController(FrmTask frmTask) {
        this.frmTask = frmTask;
        addActionListener();
    }
    
    public void openForm(FrmMode mode){
        prepareView(mode);
        frmTask.setVisible(true);
    }

    

    private void addActionListener() {
        frmTask.addBtnAddTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 try{
           TaskTableModel ttm = (TaskTableModel) ViewCordinator.getInstance().getFrmTask().getTblTasks().getModel();
           validateEmptyFields(); 
           String id = frmTask.getTxtID().getText().trim();
            ttm.exist(id);
            String title = frmTask.getTxtTitle2().getText().trim();
            String description = frmTask.getTxtDescription2().getText().trim();
            TaskType type = (TaskType) frmTask.getCbPriority2().getSelectedItem();
           
            validateStatus();
            
            validateDates();

            
            TaskStatus status = (TaskStatus) frmTask.getCbStatus().getSelectedItem();
            Task task = new Task(Long.parseLong(id), (TaskGroup)ViewCordinator.getInstance().getParam("TaskGroup"), title, description, deadLine, type, status, startDate, finishDate);
           
            ttm.addTask(task);
            JOptionPane.showMessageDialog(frmTask, "Task successfully added!");
            frmTask.dispose();
           
            
            
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmTask, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmTask, "Failed adding task: "+e.getMessage());
        }
            }
        });
        
        frmTask.addBtnUpdateTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
            Task t = new Task();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
            validateEmptyFields();
            
            validateStatus();
            validateDates();
            
            t.setDeadline(deadLine);
            t.setDescription(frmTask.getTxtDescription2().getText().trim());
            t.setFinishDate(finishDate);
            t.setStartDate(startDate);
            t.setTaskStatus((TaskStatus) frmTask.getCbStatus().getSelectedItem() );
            t.setTaskType((TaskType) frmTask.getCbPriority2().getSelectedItem());
            t.setTitle(frmTask.getTxtTitle2().getText());
            t.setTg((TaskGroup) ViewCordinator.getInstance().getParam("TaskGroup"));
            t.setId(Long.parseLong(frmTask.getTxtID().getText()));
            
            TaskTableModel ttm = (TaskTableModel) ViewCordinator.getInstance().getFrmTask().getTblTasks().getModel();
            
            ttm.editTask(t);
            JOptionPane.showMessageDialog(frmTask, "Task successfully updated!");
           
            
            
            
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmTask, "Server is closed, Goodbye");
            System.exit(0);
        }catch(Exception e){
            JOptionPane.showMessageDialog(frmTask, "Deleting task failed "+e.getMessage());
        }
            }
        });
        
        frmTask.addBtnDeleteTaskListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
            Task t = new Task();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
            validateEmptyFields();
            validateStatus();
            validateDates();
            t.setDeadline(deadLine);
            t.setDescription(frmTask.getTxtDescription2().getText().trim());
            t.setFinishDate(finishDate);
            t.setStartDate(startDate);
            t.setTaskStatus((TaskStatus) frmTask.getCbStatus().getSelectedItem() );
            t.setTaskType((TaskType) frmTask.getCbPriority2().getSelectedItem());
            t.setTitle(frmTask.getTxtTitle2().getText());
            t.setTg((TaskGroup) ViewCordinator.getInstance().getParam("TaskGroup"));
            t.setId(Long.parseLong(frmTask.getTxtID().getText()));
            int answer = JOptionPane.showConfirmDialog(frmTask, "Do you really want to delete this task?");
            if(answer!=0){
                return;
            }
            TaskTableModel ttm = (TaskTableModel) ViewCordinator.getInstance().getFrmTask().getTblTasks().getModel();
            ttm.deleteTask(t);
            TaskGroup tg = (TaskGroup) ViewCordinator.getInstance().getParam("TaskGroup");
             tg.getTasks().remove(t);
             ViewCordinator.getInstance().addParam("TaskGroup", tg);
            JOptionPane.showMessageDialog(frmTask, "Task successfully deleted!");
            frmTask.dispose();
           
            
            
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmTask, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmTask, "Deleting task failed "+e.getMessage());
        }
            }
        });
    }
    
    private void prepareView(FrmMode mode) {
        frmTask.getCbStatus().addItem(TaskStatus.FINISHED);
        frmTask.getCbStatus().addItem(TaskStatus.INPROGRESS);
        frmTask.getCbStatus().addItem(TaskStatus.TODO);
        frmTask.getCbPriority2().addItem(TaskType.HIGH_PRIORITY);
        frmTask.getCbPriority2().addItem(TaskType.LOW_PROIROTY);
        frmTask.getCbPriority2().addItem(TaskType.MEDIUM_PRIORITY);
        
        switch(mode){
            case ADD:
                frmTask.getBtnAddTask().setEnabled(true);
                frmTask.getBtnDeleteTask().setEnabled(false);
                frmTask.getBtnUpdateTask().setEnabled(false);
                break;
            case EDIT:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                frmTask.getBtnAddTask().setEnabled(false);
                frmTask.getBtnDeleteTask().setEnabled(true);
                frmTask.getBtnUpdateTask().setEnabled(true);
                frmTask.getTxtID().setEnabled(false);
                Task task = (Task)ViewCordinator.getInstance().getParam("TASK");
                frmTask.getTxtID().setText(task.getId().toString());
                frmTask.getTxtTitle2().setText(task.getTitle());
                frmTask.getTxtDeadline2().setText(sdf.format(task.getDeadline()));
                if(task.getFinishDate()==null){
                    frmTask.getTxtFinishDate().setText("");
                }else frmTask.getTxtFinishDate().setText(sdf.format(task.getFinishDate()));
                if(task.getStartDate()==null){
                    frmTask.getTxtStartDate().setText("");
                }else frmTask.getTxtStartDate().setText(sdf.format(task.getStartDate())); 
                
                frmTask.getTxtDescription2().setText(task.getDescription());
                frmTask.getCbStatus().setSelectedItem(task.getTaskStatus());
                frmTask.getCbPriority2().setSelectedItem(task.getTaskType());
                
                break;
        }
        
        
    }

    private void validateStatus() throws Exception {
        TaskStatus status = (TaskStatus) frmTask.getCbStatus().getSelectedItem();
        if(status.equals(TaskStatus.FINISHED)){
            if(frmTask.getTxtStartDate().getText().equals("") || frmTask.getTxtFinishDate().getText().equals("")){
                throw new Exception("If task has status FINISHED, task need to have start and finish date!");
            }
        }
        
        if(status.equals(TaskStatus.INPROGRESS)){
            if(frmTask.getTxtStartDate().getText().equals("")){
                throw new Exception("If task has status IN PROGRESS, task need to have start date, but finish date has to be empty!");
            }
            if(!frmTask.getTxtFinishDate().getText().equals("")){
                 throw new Exception("If task has status IN PROGRESS, task need to have start date, but finish date has to be empty!");
               
            }
        }
        
        if(status.equals(TaskStatus.TODO)){
            if(!frmTask.getTxtStartDate().getText().equals("") || !frmTask.getTxtFinishDate().getText().equals("")){
                throw new Exception("If task has status TODO, start and finish date have to be empty!");
            }
        }
    }

    private void validateDates() throws Exception {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
        try {
                deadLine = df.parse(frmTask.getTxtDeadline2().getText().trim());
            } catch (ParseException ex) {
                throw new Exception("Date need to be in format: dd.MM.yyyy.");
            }
        
        if(!"".equals(frmTask.getTxtStartDate().getText().trim())){
            try {
                startDate = df.parse(frmTask.getTxtStartDate().getText().trim());
            } catch (ParseException ex) {
                throw new Exception("Date need to be in format: dd.MM.yyyy.");
            }
            }else {
                startDate = null;
            }
            if("".equals(frmTask.getTxtFinishDate().getText().trim())){
                finishDate = null;
            }else {
            try {
                finishDate = df.parse(frmTask.getTxtFinishDate().getText().trim());
            } catch (ParseException ex) {
                throw new Exception("Date need to be in format: dd.MM.yyyy.");
            }
            }
    }

    private void validateEmptyFields() throws Exception {
        if(frmTask.getTxtDeadline2().getText().isEmpty()){
            throw new Exception("ID, title, descprition and deadline can't be empty!");
        }
        if(frmTask.getTxtDescription2().getText().isEmpty()){
            throw new Exception("ID, title, descprition and deadline can't be empty!");
        }
        if(frmTask.getTxtID().getText().isEmpty()){
            throw new Exception("ID, title, descprition and deadline can't be empty!");
        }
        if(frmTask.getTxtTitle2().getText().isEmpty()){
            throw new Exception("ID, title, descprition and deadline can't be empty!");
        }
        
    }
}
