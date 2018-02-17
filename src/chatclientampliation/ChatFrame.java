
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientampliation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Andreu
 */
public class ChatFrame extends JFrame implements ActionListener {

    // Frame
    private JTabbedPane jTabPane;
    private JPanel jPanelLog;
    private JPanel jPanelChat;
    private JPanel jPanelSettings;
    private JPanel jPanelInfoChat;
    private JPanel jPanelBotChat;
    private JButton jBtnColorBack;
    private JButton jBtnColorFont;
    private JLabel jLbColorBack;
    private JLabel jLbColorFont;
    private JLabel jLbFontSize;
    private JSlider jSlFont;
    private JLabel jLbName;
    private JTextField jTxName;
    private JLabel jLbServer;
    private JLabel jLbHOST;
    private JTextField jTxHOST;
    private JLabel jLbPORT;
    private JTextField jTxPORT;
    private JButton jBtnAccept;
    private JButton jBtnSend;
    private JTextArea jTxArea;
    private JScrollPane jScrollPane;
    private JTextField jTxMesage;

    private JLabel jLbMsgReceived;
    private JLabel jLbMsgSend;
    private JLabel jLbTotalClients;

    // Clases
    private final ClientProject clientProject;

    public ChatFrame() {
        initWindow();
        initComponents();
        clientProject = new ClientProject(this);
    }

    /**
     * Inizialize the window
     */
    private void initWindow() {
        this.setTitle("Chat");
        this.setIconImage(new ImageIcon("./img/chat.png").getImage());
        this.setSize(550, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    /**
     * Init the components inside the window
     */
    private void initComponents() {
        jTabPane = new JTabbedPane();

        // Panel Log
        this.jPanelLog = new JPanel();
        jPanelLog = new JPanel();
        jPanelLog.setBackground(new Color(0x009688));
        jPanelLog.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.jLbName = new JLabel("USERNAME");
        this.jLbName.setHorizontalAlignment(0); //Center
        this.jLbName.setFont(new Font("Arial", 1, 35));
        this.jLbName.setForeground(Color.WHITE);
        this.jTxName = new JTextField("John Doe");
        this.jTxName.setHorizontalAlignment(0);
        this.jTxName.setFont(new Font("Arial", Font.ITALIC, 20));
        this.jLbServer = new JLabel("CHOOSE THE SERVER");
        this.jLbServer.setHorizontalAlignment(0); //Center
        this.jLbServer.setFont(new Font("Arial", 1, 35));
        this.jLbServer.setForeground(Color.WHITE);
        this.jLbHOST = new JLabel("Server Host");
        this.jLbHOST.setHorizontalAlignment(0);
        this.jLbHOST.setFont(new Font("Arial", 1, 20));
        this.jLbHOST.setForeground(Color.WHITE);
        this.jTxHOST = new JTextField("localhost");
        this.jTxHOST.setHorizontalAlignment(0);
        this.jTxHOST.setFont(new Font("Arial", 1, 15));
        this.jLbPORT = new JLabel("Server Port");
        this.jLbPORT.setHorizontalAlignment(0);
        this.jLbPORT.setFont(new Font("Arial", 1, 20));
        this.jLbPORT.setForeground(Color.WHITE);
        this.jTxPORT = new JTextField("8888");
        this.jTxPORT.setHorizontalAlignment(0);
        this.jTxPORT.setFont(new Font("Arial", 1, 15));
        this.jBtnAccept = new JButton("CONNECT");
        this.jBtnAccept.addActionListener(this);
        this.jBtnAccept.setBackground(new Color(0x004D40));
        this.jBtnAccept.setForeground(Color.WHITE);
        this.jBtnAccept.setFont(new Font("Arial", 1, 20));

        this.jLbTotalClients = new JLabel("CLIENTS: 0");
        this.jLbTotalClients.setHorizontalAlignment(0);
        this.jLbTotalClients.setFont(new Font("Arial", 1, 17));
        this.jLbTotalClients.setForeground(Color.WHITE);
        this.jLbMsgSend = new JLabel("MESSAGES SEND: 0");
        this.jLbMsgSend.setHorizontalAlignment(0);
        this.jLbMsgSend.setFont(new Font("Arial", 1, 17));
        this.jLbMsgSend.setForeground(Color.WHITE);
        this.jLbMsgReceived = new JLabel("MESSAGES RECEIVED: 0");
        this.jLbMsgReceived.setHorizontalAlignment(0);
        this.jLbMsgReceived.setFont(new Font("Arial", 1, 17));
        this.jLbMsgReceived.setForeground(Color.WHITE);

        //----- CHAT
        //Info Panel
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        this.jPanelInfoChat = new JPanel();
        this.jPanelInfoChat.setBackground(new Color(0x00695C));
        this.jPanelInfoChat.setLayout(new GridBagLayout());
        this.jPanelInfoChat.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        jPanelInfoChat.add(jLbTotalClients, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        jPanelInfoChat.add(jLbMsgSend, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        jPanelInfoChat.add(jLbMsgReceived, c);

        // 
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 6;
        c.insets = new Insets(10, 20, 15, 20);  //top padding
        jPanelLog.add(jLbName, c);
        c.weighty = 0.25;
        c.gridx = 0;
        c.gridy = 1;
        jPanelLog.add(jTxName, c);

        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 2;
        jPanelLog.add(jLbServer, c);

        c.gridwidth = 1;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 4;
        jPanelLog.add(jLbHOST, c);

        c.gridx = 1;
        c.gridy = 5;
        jPanelLog.add(jTxHOST, c);

        c.gridx = 3;
        c.gridy = 4;
        jPanelLog.add(jLbPORT, c);

        c.gridx = 3;
        c.gridy = 5;
        jPanelLog.add(jTxPORT, c);

        c.insets = new Insets(60, 20, 40, 20);  //top padding

        c.weighty = 0.4;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 6;
        jPanelLog.add(jBtnAccept, c);

        this.jBtnSend = new JButton("Send");
        this.jBtnSend.setFont(new Font("Arial", Font.BOLD, 20));
        this.jBtnSend.setBackground(new Color(0x004D40));
        this.jBtnSend.setForeground(Color.white);
        this.jBtnSend.setEnabled(false);
        this.jBtnSend.addActionListener(this);

        this.jTxMesage = new JTextField();
        this.jTxMesage.setFont(new Font("Arial", Font.PLAIN, 17));
        this.jTxMesage.setEnabled(false);
        this.jTxMesage.addActionListener(this);
        this.jTxArea = new JTextArea("Trying to connect ...\n");
        this.jTxArea.setFont(new Font("Arial", Font.PLAIN, 20));
        this.jTxArea.setBackground(new Color(0x009688));
        this.jTxArea.setForeground(Color.WHITE);
        this.jTxArea.setEditable(false);
        this.jTxArea.setLineWrap(true);
        this.jTxArea.setWrapStyleWord(true);
        this.jTxArea.setBorder(BorderFactory.createCompoundBorder(
                jTxArea.getBorder(),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        DefaultCaret caret = (DefaultCaret) jTxArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        this.jScrollPane = new JScrollPane(jTxArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.jPanelBotChat = new JPanel();
        jPanelBotChat.setLayout(new BorderLayout());
        jPanelBotChat.add(this.jBtnSend, BorderLayout.EAST);
        jPanelBotChat.add(this.jTxMesage, BorderLayout.CENTER);

        this.jPanelChat = new JPanel();
        jPanelChat.setLayout(new BorderLayout());
        jPanelChat.add(jPanelInfoChat, BorderLayout.NORTH);
        jPanelChat.add(jScrollPane, BorderLayout.CENTER);
        jPanelChat.add(jPanelBotChat, BorderLayout.SOUTH);

        //-- Settings
        this.jPanelSettings = new JPanel();
        this.jPanelSettings.setLayout(new GridLayout(7, 0));
        this.jPanelSettings.setBackground(new Color(0x009688));

        this.jLbColorBack = new JLabel("Choose a Background Color");
        this.jLbColorBack.setHorizontalAlignment(0); //Center
        this.jLbColorBack.setFont(new Font("Arial", 1, 30));
        this.jLbColorBack.setForeground(Color.WHITE);

        this.jBtnColorBack = new JButton("Background Color");
        this.jBtnColorBack.setFont(new Font("Arial", Font.BOLD, 20));
        this.jBtnColorBack.setBackground(new Color(0x004D40));
        this.jBtnColorBack.setForeground(Color.white);
        this.jBtnColorBack.addActionListener(this);

        this.jLbColorFont = new JLabel("Choose a Font Color");
        this.jLbColorFont.setHorizontalAlignment(0); //Center
        this.jLbColorFont.setFont(new Font("Arial", 1, 30));
        this.jLbColorFont.setForeground(Color.WHITE);

        this.jBtnColorFont = new JButton("Font Color");
        this.jBtnColorFont.setFont(new Font("Arial", Font.BOLD, 20));
        this.jBtnColorFont.setBackground(new Color(0x004D40));
        this.jBtnColorFont.setForeground(Color.white);
        this.jBtnColorFont.addActionListener(this);

        this.jLbFontSize = new JLabel("Select a font size");
        this.jLbFontSize.setHorizontalAlignment(0); //Center
        this.jLbFontSize.setFont(new Font("Arial", 1, 30));
        this.jLbFontSize.setForeground(Color.WHITE);

        this.jSlFont = new JSlider(15, 40, 20);
        this.jSlFont.setBackground(new Color(0x004D40));
        this.jSlFont.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                jTxArea.setFont(new Font("Arial", Font.PLAIN, jSlFont.getValue()));
            }
        });

        this.jPanelSettings.add(this.jLbColorBack);
        this.jPanelSettings.add(this.jBtnColorBack);
        this.jPanelSettings.add(this.jLbColorFont);
        this.jPanelSettings.add(this.jBtnColorFont);
        this.jPanelSettings.add(this.jLbFontSize);
        this.jPanelSettings.add(this.jSlFont);

        jTabPane.addTab("  HOME  ", createImageIcon("./img/home.png"), jPanelLog);
        jTabPane.addTab("  CHAT  ", createImageIcon("./img/chat.png"), jPanelChat);
        jTabPane.addTab("  SETTINGS  ", createImageIcon("./img/settings.png"), jPanelSettings);

        Container cp = this.getContentPane();
        cp.add(jTabPane);
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    private ImageIcon createImageIcon(String path) {
        if (path != null) {
            return new ImageIcon(path);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Action performed of the button clicked
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.jTxMesage)) {
            sendMessage();
        }
        switch (ae.getActionCommand().toUpperCase()) {
            case "CONNECT":
                createConnection();
                break;
            case "BACKGROUND COLOR":
                this.jTxArea.setBackground(JColorChooser.showDialog(null, "Choose a color", Color.WHITE));
                break;
            case "FONT COLOR":
                this.jTxArea.setForeground(JColorChooser.showDialog(null, "Choose a color", Color.BLACK));
                break;
            case "SEND":
                sendMessage();
                break;

        }
    }

    /**
     * It will check if all the fields are completed and will start the
     * connection to that server
     */
    private void createConnection() {
        try {
            String userName = this.jTxName.getText();
            String host = this.jTxHOST.getText();
            int port = Integer.parseInt(this.jTxPORT.getText());

            if (userName.length() != 0 && host.length() != 0) {
                this.setTitle("Chat - " + userName + " - Server (" + host + ":" + port + ")");
                this.jTabPane.setSelectedIndex(1);
                enableHomeWindow(false);
                this.clientProject.createConnection(userName, host, port);

            } else {
                JOptionPane.showMessageDialog(null, "Please, choose a server and a name");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please, choose a server and a name");
        }
    }

    /**
     * It takes the msg of the field, check if is not null and send the message
     * to the server
     */
    private void sendMessage() {
        String msg = this.jTxMesage.getText().trim();
        if (msg.length() != 0) {
            this.clientProject.sendMessage(msg);
            System.out.println("Message of frame: " + msg);
        }
        this.jTxMesage.setText("");
        this.jTxMesage.requestFocus();
    }

    /**
     * Add a new message to the text area
     *
     * @param msg
     */
    public void addMessage(String msg) {
        this.jTxArea.setText(this.jTxArea.getText() + msg + "\n");
    }

    /**
     * Add a new server with the specificated socket and inizialize the thread
     *
     * @param b
     * @param sock Socket for communicate with the server
     */
    public void enableSendMessages(boolean b) {
        this.jBtnSend.setEnabled(b);
        this.jTxMesage.setEnabled(b);
    }

    public void enableHomeWindow(boolean b) {
        this.jTxHOST.setEnabled(b);
        this.jTxPORT.setEnabled(b);
        this.jTxName.setEnabled(b);
        this.jBtnAccept.setEnabled(b);
    }

    public void setMsgReceived(int n) {
        this.jLbMsgReceived.setText("MESSAGES RECEIVED: " + String.valueOf(n));
    }

    public void setMsgSend(int n) {
        this.jLbMsgSend.setText("MESSAGES SEND: " + String.valueOf(n));
    }

    public void setTotalClients(int n) {
        this.jLbTotalClients.setText("CLIENTS: " + String.valueOf(n));
    }

}
