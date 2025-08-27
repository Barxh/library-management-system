/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author nikol
 */
public class Communication {

    private static Communication instance;
    private  Socket socket;
    private  Receiver receiver;
    private  Sender sender;

    private Communication() throws IOException {
        this.socket = new Socket("localhost", 9000);
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws IOException {
        if (instance == null) {
            instance = new Communication();
        }

        return instance;
    }

    public void sendRequest(Request request) throws SocketException, Exception {
        sender.send(request);
    }
    public Response receiveResponse() throws Exception{
        return (Response) receiver.receive();
    }
    public void renewConnection() throws IOException{
        socket.close();
        instance = null;
    }

}
