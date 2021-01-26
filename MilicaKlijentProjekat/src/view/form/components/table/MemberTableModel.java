/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.form.components.table;

import domain.Member;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FON
 */
public class MemberTableModel  extends AbstractTableModel{
    private List<Member> members;
    private String[] columnNames = {"Name","Surname","Email","Year of entry","Local group", "Task Group", "Leader"};

    public MemberTableModel(List<Member> members) {
        this.members = members;
    }
    
    
    @Override
    public int getRowCount() {
        return members.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    
    @Override
    public Object getValueAt(int i, int i1) {
        switch(i1){
            case 0: return members.get(i).getFirstname();
            case 1: return members.get(i).getLastname();
            case 2: return members.get(i).getEmail();
            case 3: return members.get(i).getYear();
            case 4: return members.get(i).getLg().getName();
            case 5: return members.get(i).getTaskGroup();
            case 6: 
                if(members.get(i).isIsLeader()){
                    return "Yes";
                } else return "No";
            default: return "n/a";
        }
    }

    public Member getMemberAt(int row) {
        return members.get(row);
    }
    
}
