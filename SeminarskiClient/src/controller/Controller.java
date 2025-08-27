/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Librarian;
import communication.Communication;
import communication.Operation;
import communication.Request;
import communication.Response;
import domain.Author;
import domain.Book;
import domain.Loan;
import domain.Member;
import domain.Receipt;
import java.util.List;
import java.io.IOException;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikol
 */
public class Controller {

    private static Controller instance;
    private Librarian connectedUser;

    private Controller() {

    }

    public Librarian getConnectedUser() {
        return connectedUser;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void login(Librarian librarian) throws Exception {
        if (librarian.getUsername().isEmpty() || librarian.getPassword().isEmpty()) {
            throw new Exception("You must populate all fields before submitting the form!");
        }
        Communication.getInstance().sendRequest(new Request(Operation.LOGIN, librarian));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            connectedUser = (Librarian) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void logout() {
        try {
            Communication.getInstance().sendRequest(new Request(Operation.LOGOUT, null));
            Communication.getInstance().renewConnection();
        } catch (Exception ex) {
        }
    }

    public List<Author> getAuthorsByQuery(String condition) throws SocketException, Exception {

        if (condition.isEmpty()) {
            Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_AUTHORS, null));
        } else {
            Communication.getInstance().sendRequest(new Request(Operation.GET_AUTHORS_BY_QUERY, condition));
        }

        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Author>) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<Author> getAllAuthors() throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_AUTHORS, null));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Author>) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public String addBook(Book book) throws SocketException, Exception {

        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty()) {
            throw new Exception("You must set title, pick genre and at least one author before submitting the form");
        }
        Communication.getInstance().sendRequest(new Request(Operation.ADD_BOOK, book));
        Response response = Communication.getInstance().receiveResponse();

        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<Book> getAllBooks() throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_BOOKS, null));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Book>) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<Book> getBooksByQuery(String condition) throws SocketException, Exception {
        if (condition.isEmpty()) {
            Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_BOOKS, null));
        } else {
            Communication.getInstance().sendRequest(new Request(Operation.GET_BOOKS_BY_QUERY, condition));
        }
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Book>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String deleteBook(Book book) throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.DELETE_BOOK, book));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Member> getAllMembers() throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_MEMBERS, null));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Member>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String addReceipt(Receipt receipt) throws SocketException, Exception {
        if (receipt.getItemReceipts().isEmpty()) {
            throw new Exception("Your receipt must have at least one purchased book");
        }
        Communication.getInstance().sendRequest(new Request(Operation.ADD_RECEIPT, receipt));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String addMember(Member member) throws SocketException, Exception {
        if (member.getFirstName().isEmpty() || member.getLastName().isEmpty() || member.getJMBG().isEmpty()
                || member.getAddress().isEmpty() || member.getCity().isEmpty() || member.getPhone().isEmpty()
                || member.getEmail().isEmpty()) {
            throw new Exception("You must populate all fields in the form before submitting it!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, 1);
        member.setMembershipValidityDate(calendar.getTime());
        Communication.getInstance().sendRequest(new Request(Operation.ADD_MEMBER, member));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Member> getMembersByQuery(String condition) throws SocketException, Exception {
        if (condition.isEmpty()) {
            Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_MEMBERS, null));
        }else{
            Communication.getInstance().sendRequest(new Request(Operation.GET_MEMBERS_BY_QUERY, condition));
        }        
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Member>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String updateMember(Member member) throws SocketException, Exception {
        if (member.getFirstName().isEmpty() || member.getLastName().isEmpty() || member.getJMBG().isEmpty()
                || member.getAddress().isEmpty() || member.getCity().isEmpty() || member.getPhone().isEmpty()
                || member.getEmail().isEmpty()) {
            throw new Exception("You must populate all fields in the form before submitting it!");
        }
        Communication.getInstance().sendRequest(new Request(Operation.UPDATE_MEMBER, member));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String membershipRenewal(Member m) throws SocketException, Exception {
        m.renewMembership();
        Communication.getInstance().sendRequest(new Request(Operation.MEMBERSHIP_RENEWAL, m));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String addLoan(Loan loan) throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.ADD_LOAN, loan));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public String deleteLoan(Loan loan) throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.DELETE_LOAN, loan));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Loan> getAllLoans() throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_LOANS, null));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Loan>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Loan> getLoansByQuery(String condition) throws SocketException, Exception {

        if (condition.isEmpty()) {
            Communication.getInstance().sendRequest(new Request(Operation.GET_ALL_LOANS, condition));
        }else{
            Communication.getInstance().sendRequest(new Request(Operation.GET_LOAN_BY_QUERY, condition));
        }        
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Loan>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String addAuthor(Author a) throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.ADD_AUTHOR, a));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (String) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<Loan> getExpiredLoans() throws SocketException, Exception {
        Communication.getInstance().sendRequest(new Request(Operation.GET_EXPIRED_LOANS, null));
        Response response = Communication.getInstance().receiveResponse();
        if (response.isIsSuccessfull()) {
            return (List<Loan>) response.getResult();
        } else {
            throw response.getException();
        }

    }

}
