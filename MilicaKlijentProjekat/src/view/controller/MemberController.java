/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.LocalGroup;
import domain.Member;
import domain.TaskGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import view.coordinator.ViewCordinator;
import view.form.FrmMember;
import view.form.mode.FrmMode;

/**
 *
 * @author Milica
 */
public class MemberController {
    private final FrmMember frmMember;

    public MemberController(FrmMember frmMember) {
        this.frmMember = frmMember;
        addActionListener();
    }
    
    public void openForm(FrmMode mode){
        prepareView(mode);
        frmMember.setVisible(true);
    }
    
    
    
    private void addActionListener() {
        frmMember.addBtnSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
            if(frmMember.getTxtEmail().getText().trim().equals("") || frmMember.getTxtName().getText().trim().equals("") || frmMember.getTxtSurname().getText().trim().equals("") || frmMember.getTxtYear().getText().trim().equals("")){
                JOptionPane.showMessageDialog(frmMember, "Text fields can't be empty!");
                return;
            }
            Member m = new Member();
            m.setEmail(frmMember.getTxtEmail().getText().trim());
            m.setFirstname(frmMember.getTxtName().getText().trim());
            m.setLastname(frmMember.getTxtSurname().getText().trim());
            m.setIsLeader(frmMember.getJcbLeader().isSelected());
            m.setLg((LocalGroup) frmMember.getCbLocalGroup().getSelectedItem());
            m.setTaskGroup((TaskGroup) frmMember.getCbTaskGroup().getSelectedItem());
            m.setYear(Integer.parseInt(frmMember.getTxtYear().getText().trim()));
            validateMember(m);
            Communication.getInstance().addMember(m);
            JOptionPane.showMessageDialog(frmMember, "Member added successfully!");
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmMember, "Server is closed, Goodbye");
            System.exit(0);
        }catch(Exception e){
            JOptionPane.showMessageDialog(frmMember, "Unsuccessfully adding member: "+e.getMessage());
        }
            }
        });
        
        frmMember.addBtnEditListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    if(frmMember.getTxtEmail().getText().trim().equals("") || frmMember.getTxtName().getText().trim().equals("") || frmMember.getTxtSurname().getText().trim().equals("") || frmMember.getTxtYear().getText().trim().equals("")){
                JOptionPane.showMessageDialog(frmMember, "Text fields can't be empty!");
                return;
            }
            Member m = new Member();
            m.setEmail(frmMember.getTxtEmail().getText().trim());
            m.setFirstname(frmMember.getTxtName().getText().trim());
            m.setLastname(frmMember.getTxtSurname().getText().trim());
            m.setIsLeader(frmMember.getJcbLeader().isSelected());
            m.setLg((LocalGroup) frmMember.getCbLocalGroup().getSelectedItem());
            m.setTaskGroup((TaskGroup) frmMember.getCbTaskGroup().getSelectedItem());
            m.setYear(Integer.parseInt(frmMember.getTxtYear().getText().trim()));
            Long id = ((Member)ViewCordinator.getInstance().getParam("Member")).getId();
            m.setId(id);
            validateMember(m);
            Communication.getInstance().editMember(m);
            ViewCordinator.getInstance().refreshMembers();
            JOptionPane.showMessageDialog(frmMember, "Member edited successfully!");
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmMember, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmMember, "Unsucessfully updating: "+e.getMessage());
        }
            }
        });
        
        frmMember.addBtnDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    if(frmMember.getTxtEmail().getText().trim().equals("") || frmMember.getTxtName().getText().trim().equals("") || frmMember.getTxtSurname().getText().trim().equals("") || frmMember.getTxtYear().getText().trim().equals("")){
                JOptionPane.showMessageDialog(frmMember, "Text fields can't be empty!");
                return;
            }
            Member m = new Member();
            m.setEmail(frmMember.getTxtEmail().getText().trim());
            m.setFirstname(frmMember.getTxtName().getText().trim());
            m.setLastname(frmMember.getTxtSurname().getText().trim());
            m.setIsLeader(frmMember.getJcbLeader().isSelected());
            m.setLg((LocalGroup) frmMember.getCbLocalGroup().getSelectedItem());
            m.setTaskGroup((TaskGroup) frmMember.getCbTaskGroup().getSelectedItem());
            m.setYear(Integer.parseInt(frmMember.getTxtYear().getText().trim()));
            Long id = ((Member)ViewCordinator.getInstance().getParam("Member")).getId();
            m.setId(id);
            validateMember(m);
            Communication.getInstance().deleteMember(m);
            ViewCordinator.getInstance().refreshMembers();
            JOptionPane.showMessageDialog(frmMember, "Member deleted successfully!");
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmMember, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmMember, "Unsuccessfully deleting: "+e.getMessage());
        }
            }
        });
    }
    
    
    private void prepareView(FrmMode mode) {
        try{
        
        fillCbTaskGroup();
        fillCbLg();
        prepareMode(mode);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    private void fillCbTaskGroup() throws Exception {
        List<TaskGroup> groups = Communication.getInstance().getAllTaskGroups();
        for(TaskGroup tg : groups){
            System.out.println(tg.getName());
            frmMember.getCbTaskGroup().addItem(tg);
        }
    }

    private void fillCbLg() throws Exception {
        List<LocalGroup> groups = Communication.getInstance().getAllLocalGroups();
        for(LocalGroup tg : groups){
            frmMember.getCbLocalGroup().addItem(tg);
        }
    }

    private void prepareMode(FrmMode mode) {
        switch(mode){
            case ADD:
                frmMember.getBtnDelete().setVisible(false);
                frmMember.getBtnEdit().setVisible(false);
                break;
            case EDIT:
                frmMember.getBtnSave().setVisible(false);
                Member m = (Member) ViewCordinator.getInstance().getParam("Member");
                frmMember.getTxtEmail().setText(m.getEmail());
                frmMember.getTxtName().setText(m.getFirstname());
                frmMember.getTxtSurname().setText(m.getLastname());
                frmMember.getTxtYear().setText(String.valueOf(m.getYear()));
                frmMember.getCbLocalGroup().setSelectedItem(m.getLg());
                frmMember.getCbTaskGroup().setSelectedItem(m.getTaskGroup());
                System.out.println(m.getTaskGroup().getName());
                frmMember.getJcbLeader().setSelected(m.isIsLeader());
                break;
        }
    }
    
    private void validateMember(Member m) throws Exception {
        if(!m.getEmail().endsWith("@estiem.org")){
            throw new Exception("Estiem mail needs to end with 'estiem@.org'");
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Date date = new Date();
        if(year<m.getYear()){
            throw new Exception("Year of entry needs to be in past!");
        }
        
        if(m.isIsLeader()){
        List<Member> members = Communication.getInstance().getAllMembers();
        for(Member memb : members){
            if(memb.getTaskGroup().getId().equals(m.getTaskGroup().getId())){
                if(memb.isIsLeader() && !memb.getId().equals(m.getId())){
                    throw new Exception("This task group already has a leader!");
                }
            }
        }
        }
        
    }

}
