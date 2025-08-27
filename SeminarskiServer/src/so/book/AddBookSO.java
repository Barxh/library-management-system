/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.book;

import domain.Author;
import domain.Book;
import domain.AuthorBook;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class AddBookSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Book)) {
            throw new Exception("Invalid class time data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        repository.add(param);
        for (Author author : ((Book)param).getAuthor()) {
            repository.add(new AuthorBook((Book)param,author));
        }
        
    }
    
}
