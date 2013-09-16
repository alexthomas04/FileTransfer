package gui;

import java.awt.Dimension;
import java.awt.Label;
import java.util.EventObject;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.DefaultCaret;

import events.ClosingListener;
import events.EventListener;
import events.UsernameRecivedEvent;
import network.Client;

public class ChatPanel extends javax.swing.JPanel implements EventListener, ClosingListener{

	private Client client;
	private Gui gui;
    /**
     * Creates new form ChatPanel
     */
    public ChatPanel(Client c, Gui g) {
        initComponents();
        client =c;
        client.addMessageEventListener(this);
        client.addUsernameEventListener(this);
        gui=g;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setText("");

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        DefaultCaret caret = (DefaultCaret) jTextArea1.getCaret();
        caret.setUpdatePolicy(WHEN_IN_FOCUSED_WINDOW);
        jTextArea1.setLineWrap(true);
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        client.sendMessage(jTextField1.getText());
        jTextArea1.append("\nme: "+jTextField1.getText());
        jTextField1.setText("");
    }                    
    
    public void handleMessageRecivedEvent(EventObject e) {
		String s =(String)e.getSource();
		jTextArea1.append("\n"+s);
	}

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   

	@Override
	public void handleUsernameRecivedEvent(UsernameRecivedEvent event) {
		String username = (String)event.getSource();
		JTabbedPane jTab = (JTabbedPane) this.getParent();
		int count =jTab.getTabCount();
		jTab.setTabComponentAt(count-1, new Label(username));
		System.out.println("Im TRYING TO CHANGE THE TITLE BUT BLARHGE !!!;"+username);
		
		
	}
	
	public void close(){
		client.close();
	}

	@Override
	public void handleClose(EventObject arg0) {
		gui.removeChat(this);
		
		
	}
}

