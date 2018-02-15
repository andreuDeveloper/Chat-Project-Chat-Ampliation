/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientampliation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Andreu
 */
public class Server implements Runnable {
    
    
    private final ClientProject cp;
    private final Socket socket;
    private final String address;
    private BufferedReader in;
    private PrintWriter out;
    
    /**
     * Constructor 
     * @param cp ClientProject
     * @param socket Socket del cliente-servidor
     */
    public Server (ClientProject cp, Socket socket){
        this.cp = cp;
        this.socket = socket;
        this.address = socket.getInetAddress().getHostAddress();
        System.out.println("Server created");
    }

    /**
     * Loop that will process the in / out of the server, processing that petitions
     */
    @Override
    public void run() {
        try {
            System.out.println("Thread for Server created");
            // Get I/O streams from the socket
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            processServer(in, out); // interact with a client
            
            // Close client connection
            socket.close();
            System.out.println("Client (" + address + ") connection closed\n");
            cp.removeServer();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Process the in of the server if it have sense
     * @param in
     * @param out 
     */
    public void processServer(BufferedReader in, PrintWriter out){
        String line;
        boolean done = false;
        try {
            while (!done) {
                if ((line = in.readLine()) == null) {
                    done = true;
                } else {
                    System.out.println("Client sent something");
                    System.out.println(line);
                    if (line.length() >= 3) {
                        switch (line.substring(0, 3).toLowerCase()) {
                            case "msg":
                                getMessage(line);
                                break;
                            default:
                                System.out.println("Ignoring input line");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * It recievs a msg line of the server and get the username and the msg of it
     * @param line 
     */
    protected void getMessage(String line){
        //msg NAME & msgContent
        try {
            line = line.substring(3).trim();
            System.out.println(line);
            line = line.replace(" &", ":");
            System.out.println(line);
            this.cp.addMessage(line);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Send message to the server specificating the user and the message
     * @param user
     * @param msg 
     */
    public void sendMessage(String user, String msg){
        String s = "msg "+user+" & "+msg;
        System.out.println(s);
        out.println(s);
        this.cp.addMessage(user+": "+msg);
    }
    
    
    
}
