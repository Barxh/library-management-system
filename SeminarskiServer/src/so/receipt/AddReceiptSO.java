/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.receipt;

import domain.ItemReceipt;
import domain.Receipt;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class AddReceiptSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {

        if (param == null || !(param instanceof Receipt)) {
            throw new Exception("Invalid class time data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add(param);
        Receipt receipt = (Receipt) param;
        for (ItemReceipt itemReceipt : receipt.getItemReceipts()) {
            repository.add(param);
            itemReceipt.getBook().setTotalQuantity(itemReceipt.getBook().getTotalQuantity() + itemReceipt.getPurchasedQuantity());
            itemReceipt.getBook().setStockQuantity(itemReceipt.getBook().getStockQuantity()+ itemReceipt.getPurchasedQuantity());
            repository.update(itemReceipt.getBook());
        }
    }
    
}
