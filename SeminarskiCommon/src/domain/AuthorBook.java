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
public class AuthorBook extends AbstractDomain implements Serializable {
    private Book book;
    private Author author;

    public AuthorBook() {
    }

    public AuthorBook(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String getAttributeList() {
        return "bookID, authorID";

    }

    @Override
    public String getClassName() {
        return "authorbook";

    }

    @Override
    public String getAttributeValues() {
        return book.getBookID() + ", " + author.getAuthorID();
    }

    @Override
    public String getQueryCondition() {
        return "bookID = " + book.getBookID();
 
    }


    @Override
    public String setAttributeValues() {
        return "bookID = " + book.getBookID() + ", authorID = " + author.getAuthorID();
    }

    

    @Override
    public void setId(Long id) {
//DO NOTHING
    }

    @Override
    public Long getId() {
return 0l;
    }

    @Override
    public void setForeignId(Long id) {
//DO NOTHING
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {
        Author a = new Author(rs.getLong("A.AUTHORID"), rs.getString("A.FIRSTNAME"), rs.getString("A.LASTNAME"), rs.getDate("A.DATEOFBIRTH"));
        Book b = new Book(rs.getLong("B.BOOKID"), rs.getString("B.TITLE"), Genre.valueOf(rs.getString("B.GENRE")),
                rs.getInt("B.TOTALQUANTITY"), rs.getInt("B.STOCKQUANTITY"));
        return new AuthorBook(b, a);
    }

    @Override
    public String getStatementSelectAllQuery() {
return "SELECT B.BOOKID, B.TITLE, B.GENRE, B.TOTALQUANTITY, B.STOCKQUANTITY, A.AUTHORID, A.FIRSTNAME, A.LASTNAME, A.DATEOFBIRTH"
        + " FROM AUTHORBOOK IT JOIN BOOK B ON IT.BOOKID=B.BOOKID JOIN AUTHOR A ON IT.AUTHORID = A.AUTHORID";
    }
    
    

    
    
    
}
