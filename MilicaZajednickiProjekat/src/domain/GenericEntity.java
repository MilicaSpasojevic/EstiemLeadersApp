/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author FON
 */
//opsti domenski objekat
public interface GenericEntity extends Serializable{

    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    void setId(Long id);

    List<GenericEntity> getList(ResultSet rs) throws Exception;

    String getJoinCondition();

    String getUpdateValues();

    String getObjectCase();

    String getSearchCase();
    
}
