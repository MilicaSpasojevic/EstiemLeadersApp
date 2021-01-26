/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import java.util.HashMap;
import java.util.Map;
import view.controller.AddTaskGroupController;
import view.controller.LoginController;
import view.controller.MainController;
import view.controller.MemberController;
import view.controller.ProfileController;
import view.controller.TaskController;
import view.controller.TaskGroupDetailController;
import view.controller.ViewMembersController;
import view.controller.ViewTaskGroupsController;

import view.form.FrmAddTaskGroup;
import view.form.FrmLogin;
import view.form.FrmMain;
import view.form.FrmMember;
import view.form.FrmProfile;
import view.form.FrmTask;

import view.form.FrmTaskGroupDetails;

import view.form.FrmViewMembers;

import view.form.FrmViewTaskGroups;
import view.form.mode.FrmMode;

/**
 *
 * @author FON
 */
public class ViewCordinator {
    private static ViewCordinator instance;
    private MainController mainController;
    private Map<String,Object> params;
    private TaskGroupDetailController taskGroupDetailsController;
    private ViewTaskGroupsController viewTaskGroupController;
    private ViewMembersController viewMemberController;
    private TaskController taskController;

    private ViewCordinator() {
        params = new HashMap<>();
    }
    
    public static ViewCordinator getInstance(){
        if(instance==null){
            instance=  new ViewCordinator();
        }
        return instance;
    }
    
    public void addParam(String s, Object o){
        params.put(s, o);
    }
    
    public Object getParam(String s){
        return params.get(s);
    }
    
    public void openLoginForm(){
        LoginController frmLogin = new LoginController(new FrmLogin());
        frmLogin.openForm();
    }
    
    public void openMainForm(){
        mainController = new MainController(new FrmMain());
        mainController.openForm();
    }
    
    public void openAddMemberForm(){
        MemberController memberController = new MemberController(new FrmMember(mainController.getFrmMain(), true));
        memberController.openForm(FrmMode.ADD);
    }
       
    public void openViewMembers(){
        viewMemberController = new ViewMembersController(new FrmViewMembers(mainController.getFrmMain(), true));
        viewMemberController.openForm();
    }
    
    public void openViewTaskGroups(){
        viewTaskGroupController = new ViewTaskGroupsController(new FrmViewTaskGroups(mainController.getFrmMain(), true));
        viewTaskGroupController.openForm();
    }
    
    public void openTaskGroupDetails() {
        taskGroupDetailsController = new TaskGroupDetailController(new FrmTaskGroupDetails(mainController.getFrmMain(), true));
        taskGroupDetailsController.openForm();
    }

    public void openEditMemberForm() {
        MemberController memberController = new MemberController(new FrmMember(mainController.getFrmMain(), true));
        memberController.openForm(FrmMode.EDIT);
    }
    
    public void openAddTaskForm() {
        taskController= new TaskController(new FrmTask(mainController.getFrmMain(), true));
        taskController.openForm(FrmMode.ADD);
    }
    
    public void openEditTaskForm() {
        TaskController taskController = new TaskController(new FrmTask(mainController.getFrmMain(), true));
        taskController.openForm(FrmMode.EDIT);
    }
    
    public void openAddTaskGroupF(){
        AddTaskGroupController addTaskGroupController = new AddTaskGroupController(new FrmAddTaskGroup(mainController.getFrmMain(), true));
        addTaskGroupController.openForm();
    }

    public FrmTaskGroupDetails getFrmTask() {
        return taskGroupDetailsController.getFrmTaskGroup();
    }

    public void refreshTaskGroupsView() {
        viewTaskGroupController.refreshTable();
    }

    public void refreshTaskGroupDetailsView() {
        viewTaskGroupController.prepareTable();
        taskGroupDetailsController.prepareView();
        
    }

    public void refreshMembers() {
        viewMemberController.prepareView();
    }
     
    public void openProfileForm(){
        ProfileController frm  = new ProfileController(new FrmProfile(mainController.getFrmMain(), true));
        frm.openForm();
    }

    public FrmMain getFrmMain() {
        return mainController.getFrmMain();
    }
    
    
    
    
    
    
    
}
