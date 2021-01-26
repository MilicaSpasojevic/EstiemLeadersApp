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
public class Request implements Serializable {
    Operation operation;
    Object argument;

    public Request() {
    }

    public Request(Operation operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getArgument() {
        return argument;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }
    
    
    
}
