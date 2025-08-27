/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Member;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikol
 */
public class MemberTableModel extends AbstractTableModel {

    private List<Member> members;
    private String[] columnNames = {"Name", "JMBG", "Phone number", "Membership date"};

    public MemberTableModel(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    
    @Override
    public int getRowCount() {
        return members.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        Member m = members.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> m.toString();
            case 1 -> m.getJMBG();
            case 2 -> m.getPhone();
            case 3 -> sdf.format(m.getMembershipValidityDate());
            default -> "n/a";
        };
    }

}
