/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.author;

import domain.Author;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class GetAuthorsByQuerySO extends AbstractSO {
    private List<Author> authors;

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof String)) {
            throw new Exception("Object is the wrong type!");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        authors = repository.getByQuery(new Author(), " WHERE LASTNAME LIKE '%"+ (String)param + "%'");
    }

    public List<Author> getAuthors() {
        return authors;
    }
    
}
