/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.CommitteeLeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.coordinator.ViewCordinator;
import view.form.FrmMain;

/**
 *
 * @author Milica
 */
public class MainController {
    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmMain.setVisible(true);
        
    }

    private void addActionListener() {
        frmMain.addJmiAddMemberListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewCordinator.getInstance().openAddMemberForm();
            }
        });
        
        frmMain.addJmiViewAllMembersListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewCordinator.getInstance().openViewMembers();
            }
        });
        
        frmMain.addJmiViewAllTaskGroupsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewCordinator.getInstance().openViewTaskGroups();
            }
        });
        
        frmMain.addJmiViewProfileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewCordinator.getInstance().openProfileForm();
            }
        });
    }

    private void prepareView() {
        CommitteeLeader cm = (CommitteeLeader) ViewCordinator.getInstance().getParam("Leader");
        frmMain.getLblLider().setText(cm.getFirstname()+" "+cm.getLastname());
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }
    
    
    
    
}
