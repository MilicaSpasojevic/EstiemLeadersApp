/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.CommitteeLeader;
import domain.LocalGroup;
import domain.Member;
import domain.Task;
import domain.TaskGroup;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

/**
 *
 * @author FON
 */
public class Communication {
    private static Communication instance;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    
    private Communication(){
       /* try{
            socket = new Socket("localhost", 9000);
            receiver = new Receiver(socket);
            sender = new Sender(socket);
           // WaitingForResponse waiting = new WaitingForResponse(receiver);
           // waiting.start();
        }catch(SocketException se){
            System.out.println(se.getMessage());
            System.exit(0);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }*/
    }
    
    public void connect() throws Exception{
        try{
            socket = new Socket("localhost", 9000);
            receiver = new Receiver(socket);
            sender = new Sender(socket);
        }catch(SocketException se){
            throw new Exception("Server is not connected!");
            
        }catch(Exception e){
            
        }
    }
    
    public static Communication getInstance(){
        if(instance==null){
            instance = new Communication();
        }
        return instance;
    }

    public Receiver getReceiver() {
        return receiver;
    }
    
    public void addTaskGroup(TaskGroup param) throws Exception{
        Request request = new Request(Operation.ADD_TASK_GROUP,param);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
        
    }
    
    public CommitteeLeader login(String email, String password) throws Exception{
        Request request = new Request(Operation.LOGIN, new CommitteeLeader(email, password, null, null, null));
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (CommitteeLeader) response.getResult();
        } else throw response.getException();
         
    }

    public List<TaskGroup> getAllTaskGroups() throws Exception {
        Request request = new Request(Operation.GET_ALL_TASK_GROUPS,null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<TaskGroup>) response.getResult();
        } else throw response.getException();
        
    }
    
    public List<LocalGroup> getAllLocalGroups() throws Exception{
        Request request = new Request(Operation.GET_ALL_LOCAL_GROUPS,null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<LocalGroup>) response.getResult();
        } else throw response.getException();
    }

    public void addMember(Member m) throws Exception {
        Request request = new Request(Operation.ADD_MEMBER,m);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
    }

    public List<Member> getAllMembers() throws Exception {
        Request request = new Request(Operation.GET_ALL_MEMBERS,null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Member>) response.getResult();
        } else throw response.getException();
    }
    
    public void addTask(Task task) throws Exception {
        Request request = new Request(Operation.ADD_TASK,task);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
    }

    public void updateTask(Task t) throws Exception {
        Request request = new Request(Operation.UPDATE_TASK,t);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
    }

    public void deleteTask(Task t) throws Exception {
        Request request = new Request(Operation.DELETE_TASK,t);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
    }

    public void editMember(Member m) throws Exception {
        Request request = new Request(Operation.UPDATE_MEMBER,m);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
    }

    public void deleteMember(Member m) throws Exception{
        Request request = new Request(Operation.DELETE_MEMBER,m);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        } else throw response.getException();
    }

    public List<Member> findByEmail(Member member) throws Exception{
        Request request = new Request(Operation.FIND_MEMBER,member);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<Member>)response.getResult();
        } else throw response.getException();
    }

    public List<TaskGroup> findByName(TaskGroup tg) throws Exception {
        Request request = new Request(Operation.FIND_TASK_GROUP,tg);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (List<TaskGroup>)response.getResult();
        } else throw response.getException();
    }

    public Socket logout(CommitteeLeader cm) throws Exception {
        Request request = new Request(Operation.LOGOUT,cm);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return socket;
        } else throw response.getException();
    }
    
}
