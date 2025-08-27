/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.author;

import domain.Author;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class AddAuthorSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Author)){
            throw new Exception("Object is the wrong type!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Author)param);

    }
    
}
