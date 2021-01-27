/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.Member;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import view.coordinator.ViewCordinator;
import view.form.FrmViewMembers;
import view.form.components.table.MemberTableModel;

/**
 *
 * @author Milica
 */
public class ViewMembersController {
    FrmViewMembers frmViewMember;

    public ViewMembersController(FrmViewMembers frmViewMember) {
        this.frmViewMember = frmViewMember;
        addActionListener();
    }
    
    public void openForm(){
        prepareView();
        frmViewMember.setVisible(true);
    }

    private void addActionListener() {
        frmViewMember.addBtnEditListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = frmViewMember.getTblMembers().getSelectedRow();
            if(row>=0){
            MemberTableModel mtm = (MemberTableModel) frmViewMember.getTblMembers().getModel();
            Member m = mtm.getMemberAt(row);
                System.out.println(m.getTaskGroup().getName());
            ViewCordinator.getInstance().addParam("Member", m);
            ViewCordinator.getInstance().openEditMemberForm();
            }else {
            JOptionPane.showMessageDialog(frmViewMember, "You need to select member!");
            }
            }
        });
        
        frmViewMember.addBtnSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String mail = frmViewMember.getTxtEmail().getText().trim();
        if(mail.equals("")){
            prepareView();
            return;
        }
        try{
            Member member = new Member();
            member.setSearch(mail);
            List<Member> members = Communication.getInstance().findByEmail(member);
            if(members.isEmpty()){
                JOptionPane.showMessageDialog(frmViewMember, "System didn't find any member with this email!");
                return;
            }
            MemberTableModel mtm = new MemberTableModel(members);
            frmViewMember.getTblMembers().setModel(mtm);
            TableColumn column = frmViewMember.getTblMembers().getColumnModel().getColumn(2);
            column.setPreferredWidth(200);
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmViewMember, "Server is closed, Goodbye");
            System.exit(0);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frmViewMember, "Error while searching members: "+e.getMessage());
            
        }
            }
        });
    }

    public void prepareView() {
        List<Member> members = new ArrayList<>();
        try {
            members = Communication.getInstance().getAllMembers();
        }catch(SocketException se){
            JOptionPane.showMessageDialog(frmViewMember, "Server is closed, Goodbye");
            System.exit(0);
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewMember, "Error: "+ex.getMessage());
        }
        MemberTableModel mtm = new MemberTableModel(members);
        frmViewMember.getTblMembers().setModel(mtm);
        TableColumn column = frmViewMember.getTblMembers().getColumnModel().getColumn(2);
        column.setPreferredWidth(200);
    
    }
    
    
   
}
