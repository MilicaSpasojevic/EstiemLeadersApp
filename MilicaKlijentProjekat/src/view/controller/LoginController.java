/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.CommitteeLeader;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.coordinator.ViewCordinator;
import view.form.FrmLogin;

/**
 *
 * @author Milica
 */
public class LoginController {
    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListeners();
    }
    
    public void openForm() {
        frmLogin.setVisible(true);
    }
    
    private void addActionListeners() {
        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login(ae);
            }

            private void login(ActionEvent ae) {
                try{
            frmLogin.getLblMailValidation().setText("");
            frmLogin.getLblPassValidation().setText("");
            String email = frmLogin.getTxtEmail().getText().trim();
            String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword()).trim();
            validateUserInput();
            Communication.getInstance().connect();
            CommitteeLeader cm = Communication.getInstance().login(email, password);
            ViewCordinator.getInstance().addParam("Leader", cm);
            JOptionPane.showMessageDialog(frmLogin, "Successfully logged in, Welcome "+cm.getFirstname());
            ViewCordinator.getInstance().openMainForm();
            frmLogin.dispose();
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(frmLogin, "Unsuccessfully logging : "+e.getMessage());
            
            }
        }});
    }
     private void validateUserInput() throws Exception {
        if(frmLogin.getTxtEmail().getText().trim().equals("") || String.copyValueOf(frmLogin.getTxtPassword().getPassword()).trim().equals("")){
            if(String.copyValueOf(frmLogin.getTxtPassword().getPassword()).trim().equals("")){
                frmLogin.getLblPassValidation().setText("Enter password!");
                frmLogin.getLblPassValidation().setForeground(Color.red);
            }
            if(frmLogin.getTxtEmail().getText().trim().equals("")){
                frmLogin.getLblMailValidation().setText("Enter mail!");
                frmLogin.getLblMailValidation().setForeground(Color.red);
            }
            
            
            frmLogin.getTxtPassword().setText("");
            throw new Exception("Email and password fields can't be empty!");
        }
        frmLogin.getLblMailValidation().setText("");
        frmLogin.getLblPassValidation().setText("");
    }
    
    
}
