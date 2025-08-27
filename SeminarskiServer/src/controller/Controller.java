/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.cj.protocol.AbstractSocketConnection;
import domain.*;
import java.util.List;
import so.author.*;
import so.book.*;
import so.librarian.*;
import so.loan.*;
import so.member.*;
import so.receipt.*;

/**
 *
 * @author nikol
 */
public class Controller {
    

    private static Controller controller;

    private Controller() {
        
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    public Librarian login(Librarian librarian) throws  Exception{
        LoginSO loginSO = new LoginSO();
        loginSO.execute(librarian);
        return loginSO.getLibrarian();
    }
    public void addAuthor(Author a)throws  Exception{
        AddAuthorSO so = new AddAuthorSO();
        so.execute(a);
        
    }
    public List<Author> getAllAuthors() throws Exception{
        GetAllAuthorsSO getAuthorsSo = new GetAllAuthorsSO();
        getAuthorsSo.execute(null);
        return getAuthorsSo.getAuthors();
    }
    
    public List<Author> getAuthorsByQuery(String condition) throws Exception{
        GetAuthorsByQuerySO getAllAuthors = new GetAuthorsByQuerySO();
        getAllAuthors.execute(condition);
        return getAllAuthors.getAuthors();
    }
    
    public void addBook(Book book) throws Exception{
        AddBookSO addBookSO = new AddBookSO();
        addBookSO.execute(book);

    }
      
    public List<Book> getAllBooks() throws Exception{
        GetAllBooksSO getAllBooksSO = new GetAllBooksSO();
        getAllBooksSO.execute(null);
        return getAllBooksSO.getBooks();
    }
    
    public List<Book> getBooksByQuery(String condition) throws Exception{
        GetBooksByQuerySO getBooksByQuerySO = new GetBooksByQuerySO();
        getBooksByQuerySO.execute(condition);
        return getBooksByQuerySO.getBooks();
    }
    
    public void deleteBook(Book book) throws Exception{
        DeleteBookSO deleteBookSO = new DeleteBookSO();
        deleteBookSO.execute(book);
    }
    
    public List<Member> getAllMembers() throws Exception{
        GetAllMembersSO getAllMembersSO = new GetAllMembersSO();
        getAllMembersSO.execute(null);
        return getAllMembersSO.getMembers();
    }
         
    public void addReceipt(Receipt receipt) throws Exception{
        AddReceiptSO addReceiptSO = new AddReceiptSO();
        addReceiptSO.execute(receipt);
    }
    
    public void addMember(Member member) throws Exception{
        AddMemberSO addMemberSO = new AddMemberSO();
        addMemberSO.execute(member);
    }
    
    public List<Member> getMembersByQuery(String condition) throws Exception{
        GetMembersByQuerySO getMembersByQuerySO = new GetMembersByQuerySO();
        getMembersByQuerySO.execute(condition);
        return getMembersByQuerySO.getMembers();
    }
    
    public void updateMember(Member member) throws Exception{
        UpdateMemberSO updateMemberSO = new UpdateMemberSO();
        updateMemberSO.execute(member);
        
    }
    
    public void membershipRenewal(Member member) throws Exception{
        MembershipRenewalSO membershipRenewalSO = new MembershipRenewalSO();
        membershipRenewalSO.execute(member);
        
    }
    
    public void addLoan(Loan loan) throws Exception{
        AddLoanSO addLoanSO = new AddLoanSO();
        addLoanSO.execute(loan);
    }
    
    public void deleteLoan(Loan loan) throws Exception{
        DeleteLoanSO deleteLoanSO = new DeleteLoanSO();
        deleteLoanSO.execute(loan);
        
    }
    
    public List<Loan> getAllLoans() throws Exception{
        GetAllLoansSO getAllLoansSO = new GetAllLoansSO();
        getAllLoansSO.execute(null);
        return getAllLoansSO.getLoans();
    }
    
    public List<Loan> getLoansByQuery(String condition) throws  Exception{
        GetLoansByQuerySO getLoansByQuery = new GetLoansByQuerySO();
        getLoansByQuery.execute(condition);
        return getLoansByQuery.getLoans();
    }

    public List<Loan> getExpiredLoans(Loan loan) throws Exception{

        GetExpiredLoansSO so = new GetExpiredLoansSO();
        so.execute(loan);
        return so.getLoans();
    }
}
