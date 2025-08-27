/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nikol
 */
public abstract class AbstractDomain implements Serializable {
    public abstract String getAttributeList();
    public abstract String getClassName();
    public abstract String getAttributeValues();
    public abstract String getQueryCondition();
    public abstract void setId(Long id);
    public abstract Long getId();
    public abstract String setAttributeValues();
    public abstract void setForeignId(Long id);
    public abstract AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException;
    public abstract String getStatementSelectAllQuery();
}
