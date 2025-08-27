/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nikol
 */
public class Receipt extends AbstractDomain implements Serializable {

    private Long receiptID;
    private List<ItemReceipt> itemReceipts;
    private Date releaseDate;
    private Librarian librarian;

    public Receipt() {
    }

    public Receipt(Long receiptID, List<ItemReceipt> itemReceipts, Date releaseDate, Librarian librarian) {
        this.receiptID = receiptID;
        this.itemReceipts = itemReceipts;
        this.releaseDate = releaseDate;
        this.librarian = librarian;
    }

    public Long getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Long receiptID) {
        this.receiptID = receiptID;
    }

    public List<ItemReceipt> getItemReceipts() {
        return itemReceipts;
    }

    public void setItemReceipts(List<ItemReceipt> itemReceipts) {
        this.itemReceipts = itemReceipts;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public String getAttributeList() {
        return "releaseDate, librarianID";
    }

    @Override
    public String getClassName() {
        return "receipt";
    }

    @Override
    public String getAttributeValues() {
        return "'" + new java.sql.Date(releaseDate.getTime()) + "' , " + librarian.getLibrarianID();
    }

    @Override
    public String getQueryCondition() {
        return "receiptID = " + receiptID;
    }


    @Override
    public String setAttributeValues() {
        return "releaseDate = '" + new java.sql.Date(releaseDate.getTime()) + "', librarianID = " + librarian.getId();
    }


    @Override
    public void setId(Long id) {
        receiptID = id;
    }

    @Override
    public Long getId() {
        return receiptID;
    }

    @Override
    public void setForeignId(Long id) {
        librarian.setId(id);
    }

    @Override
    public AbstractDomain getEntityFromResultSet(ResultSet rs) throws SQLException {
       return new Receipt(rs.getLong("receiptID"), null, rs.getDate("releaseDate"),new Librarian(rs.getLong(3), rs.getString("firstName"), 
                rs.getString("lastname")));
    }

    @Override
    public String getStatementSelectAllQuery() {

        return "SELECT * FROM RECEIPT r join librarian l on r.labrarianID = l.labrarianID";
    }

}
