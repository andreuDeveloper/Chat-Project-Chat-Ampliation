
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientampliation;

import java.net.Socket;

/**
 *
 * @author Andreu
 */
public class ClientProject {

    private final ChatFrame chatFrame;
    private final ClientServerHandler serverHandler;

    private Server server;
    private String host;
    private int port;

    private String userName;
    private int msgSend;
    private int msgReceived;
    private int totalClients;

    public ClientProject(ChatFrame chatFrame) {
        this.chatFrame = chatFrame;
        this.serverHandler = new ClientServerHandler(this);
        this.msgSend = 0;
        this.msgReceived = 0;
        this.totalClients = 0;
    }

    /**
     * Will start the connection to that server
     *
     * @param userName Username of the chat
     * @param host Host to do connection
     * @param port Port to do connection
     */
    public void createConnection(String userName, String host, int port) {
        this.userName = userName;
        this.host = host;
        this.port = port;
        this.serverHandler.setHOST(host);
        this.serverHandler.setPORT(port);
        this.serverHandler.start();
    }

    /**
     * Send the message to the server
     *
     * @param msg Message to send
     */
    public void sendMessage(String msg) {
        this.msgSend++;
        System.out.println("MESSAGE SENDED: " + msg);
        this.server.sendMessage(this.userName, msg);
        this.chatFrame.setMsgSend(this.msgSend);
    }

    /**
     * Add a new message to the text area
     *
     * @param msg
     */
    public void addMessage(String msg) {
        this.msgReceived++;
        System.out.println("MESSAGE RECEIVED: " + msg);
        this.chatFrame.addMessage(msg);
        this.chatFrame.setMsgReceived(this.msgReceived - msgSend);
    }

    /**
     * Information about there is a server connected or not
     *
     * @return True if connected false if not
     */
    public boolean hayServer() {
        return (this.server != null);
    }

    /**
     * Add a new server with the specificated socket and inizialize the thread
     *
     * @param sock Socket for communicate with the server
     */
    public void addServer(Socket sock) {
        this.server = new Server(this, sock);
        new Thread(this.server).start();
        this.chatFrame.enableSendMessages(true);

        System.out.println("Server created");
        this.addMessage("Connected to: " + this.host + " : " + this.port);
    }

    /**
     * It remove the actual server putting him in null
     */
    public void removeServer() {
        this.server = null;
        this.chatFrame.enableSendMessages(false);

        System.out.println("Server Removed");
        this.addMessage("Connection lost, trying to reconnect ...");
    }

    public void getTotalClients() {

    }
}
