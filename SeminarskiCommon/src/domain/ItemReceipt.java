/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author nikol
 */
public class ItemReceipt extends AbstractDomain implements Serializable {

    private long itemReceiptID;
    private int purchasedQuantity;
    private Book book;
    private long receiptID;

    public ItemReceipt() {
    }

    public ItemReceipt(long itemReceiptID, int purchasedQuantity, Book book, long receiptID) {
        this.itemReceiptID = itemReceiptID;
        this.purchasedQuantity = purchasedQuantity;
        this.book = book;
        this.receiptID = receiptID;
    }

    public long getItemReceiptID() {
        return itemReceiptID;
    }

    public void setItemReceiptID(long itemReceiptID) {
        this.itemReceiptID = itemReceiptID;
    }

    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(int purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(long receiptID) {
        this.receiptID = receiptID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.book);
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
        final ItemReceipt other = (ItemReceipt) obj;
        return Objects.equals(this.book, other.book);
    }

    
    @Override
    public String getAttributeList() {
        return "purchasedQuantity, bookID, receiptID";
    }

    @Override
    public String getClassName() {
        return "itemreceipt";
    }

    @Override
    public String getAttributeValues() {
        return purchasedQuantity + ", " + book.getId() + ", " + receiptID;
    }

    @Override
    public String getQueryCondition() {
        return "bookid = " + book.getId();
    }

    @Override
    public String setAttributeValues() {
        return "purchasedQuantity = " + purchasedQuantity + ", bookID = " + book.getId() + ", receiptID = " + receiptID;
    }


    @Override
    public void setId(Long id) {
        itemReceiptID = id;
    }

    @Override
    public Long getId() {
        return itemReceiptID;
    }

    @Override
    public void setForeignId(Long id) {

        receiptID = id;
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {
        Book b = new Book(rs.getLong(3), rs.getString("title"), Genre.valueOf(rs.getString("genre")),
                rs.getInt("totalQuantity"), rs.getInt("stockQuantity"));
        return new ItemReceipt(rs.getLong("itemReceiptID"), rs.getInt("purchasedQuantity"), b, rs.getLong("receiptID"));
    }

    @Override
    public String getStatementSelectAllQuery() {
        return "SELECT * FROM ITEMRECEIPT IR JOIN BOOK B ON IR.BOOKID=B.BOOKID";

    }

}
