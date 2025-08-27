/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.librarian;

import domain.Librarian;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author nikol
 */
public class LoginSO extends AbstractSO {

    private Librarian librarian;
    
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Librarian)) {
            throw new Exception("The object is the wrong type!");
        }
        if(((Librarian)param).getUsername() == null || ((Librarian)param).getPassword() == null)
            throw new Exception("No credentials sent!");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        List<Librarian> librarians;
        librarians = repository.getByQuery(param, " where username = '" + ((Librarian)param).getUsername() +
                "' AND PASSWORD = '"+((Librarian)param).getPassword()+ "'");
        if (!librarians.isEmpty()){
            librarian = librarians.getFirst();
        } else{
            throw new Exception("This user doesn't exist");
        }
    }

    public Librarian getLibrarian() {
        return librarian;
    }
    
}
