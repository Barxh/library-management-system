/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nikol
 */
public class Author extends AbstractDomain implements Serializable {

    private long authorID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Author() {
    }


    public Author(long authorID, String firstName, String lastName, Date dateOfBirth) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        final Author other = (Author) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.dateOfBirth, other.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.firstName);
        hash = 83 * hash + Objects.hashCode(this.lastName);
        hash = 83 * hash + Objects.hashCode(this.dateOfBirth);
        return hash;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public String getAttributeList() {
        return "firstName, lastName, dateOfBirth";

    }

    @Override
    public String getClassName() {
        return "author";

    }

    @Override
    public String getAttributeValues() {

        return "'" + firstName + "', '" + lastName + "', '" + new java.sql.Date(dateOfBirth.getTime()) + "'";
    }

    @Override
    public String getQueryCondition() {
        return "authorID =" + authorID;

    }



    @Override
    public String setAttributeValues() {

        return "firstName = '" + firstName + "', lastName = '" + lastName + "', dateOfBitrh = '" + new java.sql.Date(dateOfBirth.getTime()) + "'";
    }


    @Override
    public void setId(Long id) {
        authorID = id;

    }

    @Override
    public Long getId() {
        return authorID;
    }

    @Override
    public void setForeignId(Long id) {
//DO NOTHING
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Author(rs.getLong("authorID"), rs.getString("firstName"), rs.getString("lastName"), rs.getDate("dateOfBirth"));

    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT * FROM AUTHOR";

    }

}
