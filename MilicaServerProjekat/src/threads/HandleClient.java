/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.CommitteeLeader;
import domain.LocalGroup;
import domain.Member;
import domain.Task;
import domain.TaskGroup;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author FON
 */
public class HandleClient extends Thread{
    private Socket socket;
    private boolean end=false;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            Receiver receiver = new Receiver(socket);
            Sender sender = new Sender(socket);
            while(!socket.isClosed() && socket.isConnected()){
                Response response = new Response();
                Request request = (Request) receiver.receive();
                
                try{
                    switch(request.getOperation()){
                        case GET_ALL_LOCAL_GROUPS:
                            List<LocalGroup> lg = Controller.getInstance().getAllLocalGroups();
                            response.setResult(lg);
                            break;
                        case LOGIN:
                            CommitteeLeader cm = (CommitteeLeader) request.getArgument();
                            cm = Controller.getInstance().login(cm.getEmail(), cm.getPassword(),socket);
                            response.setResult(cm);
                            response.setOperation(Operation.LOGIN);
                            break;
                        case ADD_MEMBER:
                            Member member = (Member) request.getArgument();
                            Controller.getInstance().addMember(member);
                            break;
                        case GET_ALL_MEMBERS:
                            response.setResult(Controller.getInstance().getAllMembers());
                            break;
                        case ADD_TASK_GROUP:
                            TaskGroup tg = (TaskGroup) request.getArgument();
                            Controller.getInstance().addTaskGroup(tg);
                            break;
                        case GET_ALL_TASK_GROUPS:
                            response.setResult(Controller.getInstance().getAllTaskGroups());
                            break;
                        case ADD_TASK:
                            Task task = (Task) request.getArgument();
                            Controller.getInstance().addTask(task);
                            break;
                        case DELETE_TASK:
                            Task taskDel = (Task) request.getArgument();
                            Controller.getInstance().deleteTask(taskDel);
                            break;
                        case UPDATE_TASK:
                            Task taskUpd = (Task) request.getArgument();
                            Controller.getInstance().editTask(taskUpd);
                            break;
                        case UPDATE_MEMBER:
                            Member memberUpd = (Member) request.getArgument();
                            Controller.getInstance().editMember(memberUpd);
                            break;
                        case DELETE_MEMBER:
                            Member memberDel = (Member) request.getArgument();
                            Controller.getInstance().deleteMember(memberDel);
                            break;  
                        case FIND_MEMBER:
                            Member memberFind = (Member) request.getArgument();
                            response.setResult(Controller.getInstance().searchMember(memberFind));
                            break;
                        case FIND_TASK_GROUP:
                            TaskGroup tgFind = (TaskGroup) request.getArgument();
                            response.setResult(Controller.getInstance().searchTaskGroup(tgFind));
                            break;
                        case LOGOUT:
                            CommitteeLeader cmOut = (CommitteeLeader) request.getArgument();
                            Controller.getInstance().logOutUser(cmOut);
                            break;
                    }
                }catch(Exception e){
                    response.setException(e);
                }
                sender.send(response);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
            
}
