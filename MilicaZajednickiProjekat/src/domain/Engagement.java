/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import java.util.Objects;
import javafx.scene.chart.PieChart;

/**
 *
 * @author FON
 */
public class Engagement {
    private Task task;
    private Member member;
    private Date startDate;

    public Engagement() {
    }

    public Engagement(Task task, Member member, Date startDate) {
        this.task = task;
        this.member = member;
        this.startDate = startDate;
    }

    

    public Task getTask() {
        return task;
    }

    public Member getMember() {
        return member;
    }

    public Date getStartDate() {
        return startDate;
    }

    

    public void setTask(Task task) {
        this.task = task;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Engagement other = (Engagement) obj;
        if (!Objects.equals(this.task, other.task)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
   
    
}
