/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.ItemReceipt;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nikol
 */
public class ItemReceiptTableModel extends AbstractTableModel{
    
    private List<ItemReceipt> itemReceipts;
    private String[] columnNames = {"Book", "Quantity"};
     
    public ItemReceiptTableModel(List<ItemReceipt> list){
       itemReceipts = list;
    }

    public void addItemReceipt(ItemReceipt i) {
        itemReceipts.add(i);
    }
    public void removeItemAt(int index){
        itemReceipts.remove(index);
    
    }

    public List<ItemReceipt> getItemReceipts() {
        return itemReceipts;
    }
    

    @Override
    public int getRowCount() {
        return itemReceipts.size();

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
        ItemReceipt item = itemReceipts.get(rowIndex);
        switch(columnIndex){
            case 0: return item.getBook().toString();
            case 1: return item.getPurchasedQuantity();
            default: return "n/a";
        }
    }
    
}
