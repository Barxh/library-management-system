/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nikol
 */
public class Book extends AbstractDomain implements Serializable {

    private long bookID;
    private String title;
    private List<Author> author;
    private Genre genre;
    private int totalQuantity;
    private int stockQuantity;

    public Book() {
    }

    public Book(long bookID, String title,  Genre genre, int totalQuantity, int stockQuantity) {
        this.bookID = bookID;
        this.title = title;
        this.genre = genre;
        this.totalQuantity = totalQuantity;
        this.stockQuantity = stockQuantity;
    }
    public Book(long bookID, String title, List<Author> author, Genre genre, int totalQuantity, int stockQuantity) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalQuantity = totalQuantity;
        this.stockQuantity = stockQuantity;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public String getAutorsListInString(){
        if(author.isEmpty())
            return "";
        if(author.size() == 1)
            return author.getFirst().toString();
        else{
            StringBuilder sb = new StringBuilder(author.getFirst().toString());
            for(int i = 1; i<author.size(); i++){
                sb.append(", " + author.get(i).toString());
            }
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        return title ;
    }

    
    @Override
    public String getAttributeList() {
        return " title, genre, totalQuantity, stockQuantity";
    }

    @Override
    public String getClassName() {
        return "book";
    }

    @Override
    public String getAttributeValues() {
        return "'" + title + "', '" + genre + "'," + totalQuantity + "," + stockQuantity;
    }

    @Override
    public String getQueryCondition() {
        return "bookID = " + bookID;
    }



    @Override
    public String setAttributeValues() {

        return "totalQuantity = " + totalQuantity + ", stockQuantity = " + stockQuantity;
    }


    @Override
    public void setId(Long id) {

        bookID = id;
    }

    @Override
    public Long getId() {
        return bookID;
    }

    @Override
    public void setForeignId(Long id) {
//DO NOTHING
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {

        return new Book(rs.getLong("bookId"), rs.getString("title"),Genre.valueOf(rs.getString("genre")),
                rs.getInt("totalQuantity"), rs.getInt("stockQuantity"));
             
    }

    @Override
    public String getStatementSelectAllQuery() {
return "SELECT * FROM BOOK";
    }

}
