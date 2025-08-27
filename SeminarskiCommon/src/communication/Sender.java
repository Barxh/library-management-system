/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author nikol
 */
public class Sender {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object object) throws SocketException, Exception{
        
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        }catch(SocketException e){
            throw new SocketException("Server was closed: " + e.getMessage());                   
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Object sending failed!"+ ex.getMessage());
        }
        
    }
    
    
}
