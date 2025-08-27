/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Loan;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikol
 */
public class LoanTableModel extends AbstractTableModel {

    private List<Loan> loans;
    private String[] columnNames = {"Member", "Loan", "Date of loan"};

    
    public LoanTableModel(List<Loan> l){
        loans = l;
    }

    public List<Loan> getLoans() {
        return loans;
    }
    
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {

        return loans.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        Loan loan = loans.get(rowIndex);
        return switch(columnIndex){
            case 0 -> loan.getMember().toString();
            case 1 -> loan.getBook().toString();
            case 2 -> sdf.format(loan.getLoanDate());
            default -> "n/a";
                    
        };
    }

}
