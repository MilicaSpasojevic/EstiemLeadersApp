/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author FON
 */
public enum Operation implements Serializable{
    LOGIN,
    GET_ALL_TASK_GROUPS,
    GET_ALL_TASKS,
    GET_ALL_LOCAL_GROUPS,
    GET_ALL_MEMBERS,
    END, ADD_MEMBER, ADD_TASK_GROUP, SAVE_ALL_TASKS,
    ADD_TASK, UPDATE_TASK, DELETE_TASK, DELETE_TASK_GROUP, UPDATE_MEMBER, DELETE_MEMBER, FIND_MEMBER, FIND_TASK_GROUP, LOGOUT;
}
