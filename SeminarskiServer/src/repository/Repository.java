/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.util.List;
import repository.db.DbConnectionFactory;

/**
 *
 * @author nikol
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;

    void add(T param) throws Exception;

    void update(T param) throws Exception;

    void delete(T param) throws Exception;
   

    public List<T> getByQuery(T param, String query) throws Exception;
    
        default public Connection connect() throws Exception {
        return DbConnectionFactory.getInstance().getConnection();
    }

    default public void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConnection().close();
    }

    default public void commit() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
    
    
}
