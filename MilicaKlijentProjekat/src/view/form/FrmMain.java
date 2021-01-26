/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form;

import domain.CommitteeLeader;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import view.coordinator.ViewCordinator;

/**
 *
 * @author FON
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblLider = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmMembers = new javax.swing.JMenu();
        jmiAddNewMember = new javax.swing.JMenuItem();
        jmiViewAllMembers = new javax.swing.JMenuItem();
        jmTaskGroups = new javax.swing.JMenu();
        jmiViewAllTaskGroups = new javax.swing.JMenuItem();
        jmProfile = new javax.swing.JMenu();
        jmiViewProfile = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Welcome");

        lblLider.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblLider.setText("jLabel2");

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jmMembers.setText("Members");

        jmiAddNewMember.setText("Add new");
        jmMembers.add(jmiAddNewMember);

        jmiViewAllMembers.setText("View all");
        jmMembers.add(jmiViewAllMembers);

        jMenuBar1.add(jmMembers);

        jmTaskGroups.setText("Task groups");

        jmiViewAllTaskGroups.setText("View all");
        jmTaskGroups.add(jmiViewAllTaskGroups);

        jMenuBar1.add(jmTaskGroups);

        jmProfile.setText("Profile");

        jmiViewProfile.setText("View profile");
        jmProfile.add(jmiViewProfile);

        jMenuBar1.add(jmProfile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblLider)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblLider))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
         try{
        CommitteeLeader cm = (CommitteeLeader) ViewCordinator.getInstance().getParam("Leader");
            Socket socket = communication.Communication.getInstance().logout(cm);
            socket.close();
            System.exit(0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    /**
     * @param args the command line arguments
     */
    
    public JLabel getLblLider() {
        return lblLider;
    }

    public void setLblLider(JLabel lblLider) {
        this.lblLider = lblLider;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jmMembers;
    private javax.swing.JMenu jmProfile;
    private javax.swing.JMenu jmTaskGroups;
    private javax.swing.JMenuItem jmiAddNewMember;
    private javax.swing.JMenuItem jmiViewAllMembers;
    private javax.swing.JMenuItem jmiViewAllTaskGroups;
    private javax.swing.JMenuItem jmiViewProfile;
    private javax.swing.JLabel lblLider;
    // End of variables declaration//GEN-END:variables
    


    public void addJmiAddMemberListener(ActionListener actionListener) {
        jmiAddNewMember.addActionListener(actionListener);
    }

    public void addJmiViewAllMembersListener(ActionListener actionListener) {
        jmiViewAllMembers.addActionListener(actionListener);
    }

    public void addJmiViewAllTaskGroupsListener(ActionListener actionListener) {
        jmiViewAllTaskGroups.addActionListener(actionListener);
    }

    public void addJmiViewProfileListener(ActionListener actionListener) {
        jmiViewProfile.addActionListener(actionListener);
    }
}
