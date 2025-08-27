/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nikol
 */
public class Member extends AbstractDomain implements Serializable{
    private long memberID;
    private String firstName;
    private String lastName;
    private String JMBG;
    private Date dateOfBirth;
    private String address;
    private String city;
    private String phone;
    private String email;
    private Date membershipValidityDate;

    public Member() {
    }

    public Member(long memberID, String firstName, String lastName, String JMBG, Date dateOfBirth,
            String address, String city, String phone, String email, Date membershipValidityDate) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.JMBG = JMBG;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.membershipValidityDate = membershipValidityDate;
    }

    public Date getMembershipValidityDate() {
        return membershipValidityDate;
    }

    public void setMembershipValidityDate(Date membershipValidityDate) {
        this.membershipValidityDate = membershipValidityDate;
    }

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
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

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void renewMembership(){
        Calendar calendar = Calendar.getInstance();
        if (membershipValidityDate.before(new Date(System.currentTimeMillis()))) {
            calendar.setTime(new Date(System.currentTimeMillis()));
        } else {
            calendar.setTime(membershipValidityDate);
        }
        calendar.add(Calendar.YEAR, 1);
        Date newMembershipDate = new Date(calendar.getTimeInMillis());
        membershipValidityDate = newMembershipDate;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.JMBG);
        hash = 53 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.phone);
        hash = 53 * hash + Objects.hashCode(this.email);
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
        final Member other = (Member) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.JMBG, other.JMBG)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.dateOfBirth, other.dateOfBirth);
    }

    
    @Override
    public String toString() {
        return  firstName + " " + lastName;
    }

    @Override
    public String getAttributeList() {
        return "firstname, lastname, JMBG, dateofbirth, address, city, phone, email, membershipValidityDate";
    }

    @Override
    public String getClassName() {
        return "member";

    }

    @Override
    public String getAttributeValues() {
        return "'" + firstName + "','" +lastName + "','" +JMBG + "','" +
             new java.sql.Date(dateOfBirth.getTime())+ "','" + address + "','" +city + "','" + phone + "','" + email + "', '" +
                new java.sql.Date(membershipValidityDate.getTime()) + "'";

    }

    @Override
    public String getQueryCondition() {
return "memberID =" + memberID;

    }



    @Override
    public String setAttributeValues() {
        return "firstname = '" + firstName + "', lastname = '" + lastName + "', JMBG = '"+ JMBG +"', dateofbirth = '" 
                + new java.sql.Date(dateOfBirth.getTime()) + "', address = '" + address + "', city ='" + city + "', phone = '" + phone + 
                "', email ='" + email + "', membershipValidityDate = '" + new java.sql.Date(membershipValidityDate.getTime()) + "'"; 
    } 


    @Override
    public void setId(Long id) {
        memberID = id;
    }

    @Override
    public Long getId() {
        return memberID;

    }

    @Override
    public void setForeignId(Long id) {

        //DO NOTHING
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Member(rs.getLong("memberID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("JMBG"),
                rs.getDate("dateofbirth"), rs.getString("address"), rs.getString("city"), rs.getString("phone"), rs.getString("email"), rs.getDate("membershipValidityDate"));
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT * FROM MEMBER";
    }
    
    
    
}
