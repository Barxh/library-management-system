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
public class GetAllAuthorsSO extends AbstractSO {

    private List<Author> authors;

    @Override
    protected void precondition(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        authors = repository.getAll(new Author());

    }

    public List<Author> getAuthors() {
        return authors;
    }

}
