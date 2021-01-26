/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.CommitteeLeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;
import view.coordinator.ViewCordinator;
import view.form.FrmMain;
import view.form.FrmProfile;

/**
 *
 * @author Milica
 */
public class ProfileController {
    private final FrmProfile frmProfile;

    public ProfileController(FrmProfile frmProfile) {
        this.frmProfile = frmProfile;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmProfile.setVisible(true);
        
        
    }

    private void addActionListener() {
        frmProfile.addBtnLogoutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
            CommitteeLeader cm = (CommitteeLeader) ViewCordinator.getInstance().getParam("Leader");
            Socket socket = communication.Communication.getInstance().logout(cm);
            frmProfile.dispose();
            FrmMain frmMain = ViewCordinator.getInstance().getFrmMain();
            frmMain.dispose();
            socket.close();
            ViewCordinator.getInstance().openLoginForm();
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmProfile, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmProfile, "Goodbye!"+e.getMessage());
        }
            }
        });
    }

    private void prepareView() {
        CommitteeLeader cm = (CommitteeLeader) ViewCordinator.getInstance().getParam("Leader");
        System.out.println(cm.getFirstname());
        frmProfile.getLblMail().setText(cm.getEmail());
        frmProfile.getLblName().setText(cm.getFirstname()+" "+cm.getLastname());
        frmProfile.getLblPosition().setText(cm.getPosition());
    }
}
