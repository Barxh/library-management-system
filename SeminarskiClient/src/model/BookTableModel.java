/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Book;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikol
 */
public class BookTableModel extends AbstractTableModel {

    private List<Book> books;
    private String[] columnNames = {"Title", "Genre", "Authors", "Total Quantity", "Stock Quantity"};
    
    public BookTableModel(List<Book> books){
        this.books = books;
    }
    
    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch(columnIndex){
            case 0: return book.getTitle();
            case 1: return book.getGenre().toTitle();
            case 2: return book.getAutorsListInString();
            case 3: return book.getTotalQuantity();
            case 4: return book.getStockQuantity();
            default: return "n/a";
        }

    }
    
    public List<Book> getBooks(){
        return books;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];

    }
    
}
