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
public class Librarian extends AbstractDomain implements Serializable {

    private long librarianID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Librarian() {
    }

    public Librarian(long librarianID, String firstName, String lastName) {
        this.librarianID = librarianID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Librarian(String username, String password) {       
        this.username = username;
        this.password = password;
    }
    public Librarian(long librarianID, String firstName, String lastName, String username, String password) {
        this.librarianID = librarianID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public long getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(long librarianID) {
        this.librarianID = librarianID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public String getAttributeList() {
        return "firstname, lastname, username, password";
    }

    @Override
    public String getClassName() {
        return "librarian";

    }

    @Override
    public String getAttributeValues() {
        return "'" + firstName + "', '" + lastName + "', '" + username + "', '" + password + "'";

    }

    @Override
    public String getQueryCondition() {
        return "usename = '" + username + "' AND password = '" + password + "'";
    }


    @Override
    public String setAttributeValues() {
        return "";
    }


    @Override
    public void setId(Long id) {
        librarianID = id;
    }

    @Override
    public Long getId() {
        return librarianID;
    }

    @Override
    public void setForeignId(Long id) {
//DO NOTHING
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Librarian(rs.getLong("librarianID"), rs.getString("firstName"),
                rs.getString("lastname"), rs.getString("username"), rs.getString("password"));

    }

    @Override
    public String getStatementSelectAllQuery() {

        return "SELECT * FROM LIBRARIAN";
    }

}
