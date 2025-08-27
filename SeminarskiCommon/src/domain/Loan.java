/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author nikol
 */
public class Loan extends AbstractDomain implements Serializable {

    private long loanID;
    private Date loanDate;
    private Member member;
    private Book book;

    public Loan() {
    }

    public Loan(long loanID, Date loanDate, Member member, Book book) {
        this.loanID = loanID;
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
    }

    public long getLoanID() {
        return loanID;
    }

    public void setLoanID(long loanID) {
        this.loanID = loanID;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    @Override
    public String getAttributeList() {
        return "loanDate, memberID, bookID";

    }

    @Override
    public String getClassName() {
        return "loan";
    }

    @Override
    public String getAttributeValues() {
        return "'" + new java.sql.Date(loanDate.getTime()) + "', " + member.getId() + ", " + book.getId();
    }

    @Override
    public String getQueryCondition() {
        return "loanID =" + loanID;
    }

    @Override
    public String setAttributeValues() {
        return "loanDate = '" + new java.sql.Date(loanDate.getTime()) + "', memberID = " + member.getId() + ", bookID = " + book.getId();
    }


    @Override
    public void setId(Long id) {
        loanID = id;
    }

    @Override
    public Long getId() {
        return loanID;
    }

    @Override
    public void setForeignId(Long id) {
//DO NOTHING
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {

        Book b = new Book(rs.getLong("B.bookId"), rs.getString("B.title"), Genre.valueOf(rs.getString("B.genre")),
                rs.getInt("B.totalQuantity"), rs.getInt("B.stockQuantity"));
        Member m = new Member(rs.getLong("M.memberID"), rs.getString("M.firstname"), rs.getString("M.lastname"), rs.getString("M.JMBG"),
                rs.getDate("M.dateofbirth"), rs.getString("M.address"), rs.getString("M.city"), rs.getString("M.phone"), rs.getString("M.email"), rs.getDate("M.membershipValidityDate"));
        return new Loan(rs.getLong("L.LoanID"), rs.getDate("l.loandate"), m, b);
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT L.LOANID, L.LOANDATE, B.BOOKID, B.TITLE, B.GENRE, B.TOTALQUANTITY, B.STOCKQUANTITY, M.MEMBERID, M.FIRSTNAME, M.LASTNAME, "
                + "M.JMBG, M.DATEOFBIRTH, M.ADDRESS, M.CITY, M.PHONE, M.EMAIL, M.membershipValidityDate FROM LOAN L JOIN MEMBER M ON M.MEMBERID = L.MEMBERID JOIN BOOK B ON B.BOOKID = L.BOOKID";
    }

}
