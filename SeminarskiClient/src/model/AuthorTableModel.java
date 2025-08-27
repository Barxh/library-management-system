/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Author;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikol
 */
public class AuthorTableModel extends AbstractTableModel {

    List<Author> authors;
    private final String[] columnNames = {"Firstname", "Lastname", "Date of birth"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public AuthorTableModel(List<Author> authors){
        this.authors = authors;
    }
    @Override
    public int getRowCount() {
        return authors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Author author = authors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return author.getFirstName();
            case 1:
                return author.getLastName();
            case 2:
                return sdf.format(author.getDateOfBirth());
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column
    ) {
        return columnNames[column];
    }
    
    public Author getSelectedAuthor(int selectedRow){
        return authors.get(selectedRow);
    }

}
