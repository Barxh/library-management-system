/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import domain.Librarian;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ServerConstants;

/**
 *
 * @author nikol
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    
    public ServerThread() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerConstants.SERVER_CONFIG_FILE_PATH));
        String port = properties.getProperty(ServerConstants.SERVER_CONFIG_PORT);
        System.out.println(port);
        serverSocket = new ServerSocket(Integer.parseInt(port));
        clients = new ArrayList<>();
    }
    
    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                System.out.println("Awaiting clients...");
                Socket s = serverSocket.accept();
                System.out.println("Client is connected");
                ClientHandler pr = new ClientHandler(this, s);
                clients.add(pr);
                pr.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    
    public void stopServer() {
                
        clients=null;
        try {
            serverSocket.close();
        } catch (Exception ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server has stopped.");
    }
    void logout(ClientHandler pr) {
        try {
            pr.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        clients.remove(pr);
    }
    
    public void logOutAllUsers() {
        for (ClientHandler ch : clients) {
            Librarian librarian = ch.getLibrarian();
            if (librarian != null) {
                logout(ch);
                System.out.println("User logged out: " + librarian);
            }
        }
    }
}
