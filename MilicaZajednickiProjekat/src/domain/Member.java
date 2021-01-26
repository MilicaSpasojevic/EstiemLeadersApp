/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 *
 * @author FON
 */
public class Member implements GenericEntity{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private int year;
    private boolean isLeader;
    private TaskGroup taskGroup;
    private LocalGroup lg;
    private String search;
    
    

    public Member() {
    }

    public Member(Long id, String firstname, String lastname, String email, int year, boolean isLeader, TaskGroup taskGroup, LocalGroup lg) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.year = year;
        this.isLeader = isLeader;
        this.taskGroup = taskGroup;
        this.lg = lg;
    }

    public LocalGroup getLg() {
        return lg;
    }

    public void setLg(LocalGroup lg) {
        this.lg = lg;
    }

    
    

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public int getYear() {
        return year;
    }

    public boolean isIsLeader() {
        return isLeader;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    
    

    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.isLeader != other.isLeader) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstname+" "+lastname;
    }

    @Override
    public String getTableName() {
        return "member";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "firstname,lastname,email,year,isLeader,taskgroupId,localgroupId";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(firstname).append("', ")
                .append("'").append(lastname).append("', ")
                .append("'").append(email).append("', ")
                .append(year).append(", ")
                .append(isLeader).append(", ")
                .append(taskGroup.getId()).append(", ")
                .append(lg.getId());
                
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> members = new ArrayList<>();
        while(rs.next()){
            Member m = new Member();
                LocalGroup lg = new LocalGroup();
                TaskGroup tg = new TaskGroup();
                
                lg.setId(rs.getLong("l.id"));
                lg.setCountry(rs.getString("l.country"));
                lg.setName(rs.getString("l.name"));
                
                //Proveri za taskove da li mora
                tg.setId(rs.getLong("t.id"));
                tg.setName(rs.getString("t.name"));
                
                m.setEmail(rs.getString("m.email"));
                m.setId(rs.getLong("m.id"));
                m.setFirstname(rs.getString("m.firstname"));
                m.setLastname(rs.getString("m.lastname"));
                m.setYear(rs.getInt("m.year"));
                m.setIsLeader(rs.getBoolean("m.isLeader"));
                
                m.setLg(lg);
                m.setTaskGroup(tg);
                
                members.add(m);
        }
        return members;
    }

    @Override
    public String getJoinCondition() {
        return "m JOIN taskgroup t ON (m.taskgroupid=t.id) JOIN localgroup l ON (m.localgroupid=l.id)";
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("firstname=").append("'").append(firstname).append("', ")
            .append("lastname=").append("'").append(lastname).append("', ")
            .append("email=").append("'").append(email).append("', ")
                .append("year=").append(year).append(", ")
                .append("isLeader=").append(isLeader).append(", ")
                .append("taskgroupId=").append(taskGroup.getId()).append(", ")
                .append("localgroupId=").append(lg.getId());
        
        return sb.toString();
    }

    @Override
    public String getObjectCase() {
        return "id="+id;
    }

    @Override
    public String getSearchCase() {
        return "email LIKE '"+search+"%'";
    }
    
    
    
}
