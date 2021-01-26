/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author FON
 */
public class Receiver implements Serializable {
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive() throws Exception{
        try{
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return in.readObject();
    
        }catch(SocketException se){
            throw se;
        }
        catch(Exception e){
            throw new Exception("Error receiving object!\n"+e.getMessage());
        }
    }
}
