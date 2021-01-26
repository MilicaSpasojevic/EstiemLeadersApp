/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Operation;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.CommitteeLeader;
import domain.Engagement;
import domain.LocalGroup;
import domain.Member;
import domain.Task;
import domain.TaskGroup;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import operation.AbstractGenericOperation;
import operation.leader.GetAllLeaders;
import operation.localGroup.GetAllLocalGroups;
import operation.member.DeleteMember;
import operation.member.EditMember;
import operation.member.FindByEmail;
import operation.member.GetAllMembers;
import operation.member.SaveMember;
import operation.task.DeleteTask;
import operation.task.EditTask;
import operation.task.SaveTask;
import operation.taskGroup.FindByName;
import operation.taskGroup.GetAllTaskGroup;
import operation.taskGroup.SaveTaskGroup;
import repository.db.DbRepository;
import threads.Server;

/**
 *
 * @author FON
 */
public class Controller {
    private static Controller instance;
    private Server server;
    //private List<Socket> activeClients;
    private Map<String,Socket> activeCl;
    
    
    
    private Controller(){
        activeCl = new HashMap<>();
    }
    
    public static Controller getInstance(){
        if(instance==null){
            instance = new Controller();
        }
        return instance;
    }
    
    public void startServer(){
        server = new Server();
        server.start();
    }
    
    public void stopServer(){
        for(String email : activeCl.keySet()){
            System.out.println(email);
            try{
                activeCl.get(email).close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        activeCl.clear();
        server.stopServer();
    }
    

  
    
    public void addTaskGroup(TaskGroup param) throws Exception{
        SaveTaskGroup operation = new SaveTaskGroup();
        operation.execute(param);
    }
        
    public CommitteeLeader login(String email, String password, Socket socket) throws Exception{
        GetAllLeaders operation = new GetAllLeaders();
        operation.execute(new CommitteeLeader());
        List<CommitteeLeader> leaders = operation.getLeaders();
        for(CommitteeLeader cm : leaders){
            if(cm.getEmail().equals(email) && cm.getPassword().equals(password)){
                activeCl.put(email, socket);
                return cm;
            }
        }
        throw new Exception("Committee leader doesn't exists!");
    }

    public List<TaskGroup> getAllTaskGroups() throws Exception {
        GetAllTaskGroup operation = new GetAllTaskGroup();
        operation.execute(new TaskGroup());
        return operation.getTaskGroups();
        
    }
    
    public List<LocalGroup> getAllLocalGroups() throws Exception{
        GetAllLocalGroups operation = new GetAllLocalGroups();
        operation.execute(new LocalGroup());
        return operation.getLgs();
    }

    public void addMember(Member m) throws Exception {
        AbstractGenericOperation operation = new SaveMember();
        operation.execute(m);
    }

    public List<Member> getAllMembers() throws Exception {
        GetAllMembers operation = new GetAllMembers();
        operation.execute(new Member());
        return operation.getMembers();
    }
    
    public void addTask(Task task) throws Exception {
        SaveTask operation = new SaveTask();
         operation.execute(task);
    }
    
    public void editTask(Task task) throws Exception {
        EditTask operation = new EditTask();
        operation.execute(task);
    }
    
    public void deleteTask(Task task) throws Exception {
        DeleteTask operation = new DeleteTask();
        operation.execute(task);
    }

    public void editMember(Member memberUpd) throws Exception {
        EditMember operation = new EditMember();
        operation.execute(memberUpd);
    }

    public void deleteMember(Member memberDel) throws Exception{
        DeleteMember operation = new DeleteMember();
        operation.execute(memberDel);
    }

    public List<Member> searchMember(Member memberFind) throws Exception {
        FindByEmail operation = new FindByEmail();
        operation.execute(memberFind);
        return operation.getMembers();
    }

    public List<TaskGroup> searchTaskGroup(TaskGroup tgFind) throws Exception {
        FindByName operation = new FindByName();
        operation.execute(tgFind);
        return operation.getTgs();
    }

    public void logOutUser(CommitteeLeader cmOut) throws IOException{
        activeCl.remove(cmOut.getEmail());
        
    }
    
    
}
