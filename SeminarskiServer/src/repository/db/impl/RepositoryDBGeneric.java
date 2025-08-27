/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.AbstractDomain;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author nikol
 */
public class RepositoryDBGeneric implements DbRepository<AbstractDomain> {

    @Override
    public List<AbstractDomain> getAll(AbstractDomain ad) throws Exception {
        List<AbstractDomain> abstractDomains = new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(ad.getStatementSelectAllQuery());
        while (resultSet.next()) {
            abstractDomains.add(ad.getEntityFromResultSet(resultSet));
        }

        return abstractDomains;
    }

    @Override
    public void add(AbstractDomain ad) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(ad.getClassName())
                    .append(" (")
                    .append(ad.getAttributeList())
                    .append(")")
                    .append(" VALUES (")
                    .append(ad.getAttributeValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                ad.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void update(AbstractDomain param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(param.getClassName())
                    .append(" SET ")
                    .append(param.setAttributeValues())
                    .append(" WHERE ")
                    .append(param.getQueryCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(AbstractDomain t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(t.getClassName())
                    .append(" WHERE ")
                    .append(t.getQueryCondition());
            String query = sb.toString();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<AbstractDomain> getByQuery(AbstractDomain t, String query) throws Exception {
        List<AbstractDomain> abstractDomains = new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery() + query);
        while (resultSet.next()) {
            abstractDomains.add(t.getEntityFromResultSet(resultSet));
        }

        return abstractDomains;
    }

    

    
}
