/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author FON
 */
public class Task implements GenericEntity{
    private Long id;
    private TaskGroup tg;
    private String title;
    private String description;
    private Date deadline;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private Date startDate;
    private Date finishDate;

    public Task() {
    }

    public Task(Long id, TaskGroup tg, String title, String description, Date deadline, TaskType taskType) {
        this.id = id;
        this.tg = tg;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.taskType = taskType;
    }

    public Task(Long id, TaskGroup tg, String title, String description, Date deadline, TaskType taskType, TaskStatus taskStatus, Date startDate, Date finishDate) {
        this.id = id;
        this.tg = tg;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    
    

    public Long getId() {
        return id;
    }

    public TaskGroup getTg() {
        return tg;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTg(TaskGroup tg) {
        this.tg = tg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Task other = (Task) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tg, other.tg)) {
            return false;
        }
        if (!Objects.equals(this.deadline, other.deadline)) {
            return false;
        }
        if (this.taskType != other.taskType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public String getTableName() {
        return "task";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "taskGroupid, id, title, description, deadline, tasktype, taskstatus, startdate, finishdate";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        String dl;
        String sd;
        String fd;
        if(deadline==null){
            dl="NULL ,";
        }else dl = "'"+(new java.sql.Date(deadline.getTime())).toString()+"', ";
        
        if(startDate==null){
            sd="NULL ,";
        }else sd = "'"+(new java.sql.Date(startDate.getTime())).toString()+"', ";
        
        if(finishDate==null){
            fd="NULL";
        }else fd = "'"+(new java.sql.Date(finishDate.getTime())).toString()+"'";
        
        sb.append(tg.getId()).append(", ")
                .append(id).append(", ")
                .append("'").append(title).append("', ")
                .append("'").append(description).append("', ")
                .append(dl)
                .append("'").append(taskType).append("', ")
                .append("'").append(taskStatus).append("', ")
                .append(sd)
                .append(fd);
        
        return sb.toString();
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> tasks = new ArrayList<>();
        while(rs.next()){
            Task t = new Task();
                    t.setId(rs.getLong("id"));
                    t.setDeadline(rs.getDate("deadline"));
                    t.setDescription(rs.getString("description"));
                    t.setTaskType(TaskType.valueOf(rs.getString("tasktype")));
                    t.setTitle(rs.getString("title"));
                    t.setStartDate(rs.getDate("startDate"));
                    t.setFinishDate(rs.getDate("finishDate"));
                    t.setTaskStatus(TaskStatus.valueOf(rs.getString("taskstatus")));
                    tasks.add(t);
        }
        return tasks;
    }

    @Override
    public String getJoinCondition() {
        return "";
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        String sd;
        String fd;
        if(startDate==null){
            sd="NULL ,";
        }else sd = "'"+(new java.sql.Date(startDate.getTime())).toString()+"', ";
        
        if(finishDate==null){
            fd="NULL";
        }else fd = "'"+(new java.sql.Date(finishDate.getTime())).toString()+"'";
        sb.append("title=").append("'").append(title).append("', ")
                .append("description=").append("'").append(description).append("', ")
                .append("deadline=").append("'").append(new java.sql.Date(deadline.getTime()).toString()).append("', ")
                .append("tasktype=").append("'").append(taskType.toString()).append("', ")
                .append("taskstatus=").append("'").append(taskStatus.toString()).append("', ")
                .append("startDate=").append(sd)
                .append("finishDate=").append(fd);
        
        return sb.toString();
    }

    @Override
    public String getObjectCase() {
        return "id="+id;
    }

    @Override
    public String getSearchCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
