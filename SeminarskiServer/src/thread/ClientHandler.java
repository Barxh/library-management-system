/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nikol
 */
public class ClientHandler extends Thread {

    private Socket socket;
    Sender sender;
    Receiver receiver;
    ServerThread server;
    Librarian librarian;
    private boolean isRunning = true;

    public ClientHandler(ServerThread server, Socket socket) {

        this.server = server;
        this.socket = socket;
        receiver = new Receiver(socket);
        sender = new Sender(socket);
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                if (librarian == null && !request.getOperation().equals(Operation.LOGIN)) {
                    response.setException(new SocketException());
                    sender.send(response);
                    server.logout(this);
                    throw new Exception("Unknown client!");
                }
                try {

                    switch (request.getOperation()) {
                        case LOGIN -> {
                            response = login(request);
                        }
                        case LOGOUT -> {
                            isRunning = false;
                            server.logout(this);
                        }
                        case GET_ALL_AUTHORS ->
                            response = getAllAuthors();
                        case GET_AUTHORS_BY_QUERY ->
                            response = getAuthorsBuQuery(request);
                        case ADD_BOOK ->
                            response = addBook(request);
                        case GET_ALL_BOOKS ->
                            response = getAllBooks();
                        case GET_BOOKS_BY_QUERY ->
                            response = getBooksByQuery(request);
                        case DELETE_BOOK ->
                            response = deleteBook(request);
                        case GET_ALL_MEMBERS ->
                            response = getAllMembers();
                        case ADD_RECEIPT ->
                            response = addReceipt(request);
                        case ADD_MEMBER ->
                            response = addMember(request);
                        case GET_MEMBERS_BY_QUERY ->
                            response = getMembersByQuery(request);
                        case UPDATE_MEMBER ->
                            response = updateMember(request);
                        case MEMBERSHIP_RENEWAL ->
                            response = membershipRenewal(request);
                        case ADD_LOAN ->
                            response = addLoan(request);
                        case DELETE_LOAN ->
                            response = deleteLoan(request);
                        case GET_ALL_LOANS ->
                            response = getAllLoans();
                        case GET_LOAN_BY_QUERY ->
                            response = getLoansByQuery(request);
                        case ADD_AUTHOR ->
                            response = addAuthor(request);
                        case GET_EXPIRED_LOANS ->
                            response = getExpiredLoans(request);

                    }
                } catch (Exception ex) {
                    response.setException(ex);
                }
                System.out.println(response.toString());
                sender.send(response);
            } catch (Exception ex) {
                System.out.println(librarian + " has disconnected");
                isRunning = false;
            }
        }
    }

    private Response getAllAuthors() {
        Response response = new Response();
        try {
            List<Author> authors = Controller.getInstance().getAllAuthors();
            if (authors.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no authors in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(authors);
            }

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }
        return response;
    }

    private Response getAuthorsBuQuery(Request request) {
        Response response = new Response();

        try {
            String condition = (String) request.getObject();
            List<Author> authors = Controller.getInstance().getAuthorsByQuery(condition);
            if (authors.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no such authors in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(authors);
            }
        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response addBook(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().addBook((Book) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("Book has been added.");

        } catch (Exception e) {
            response.setException(new Exception("System can't add a book."));
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response getAllBooks() {
        Response response = new Response();

        try {
            List<Book> books = Controller.getInstance().getAllBooks();
            if (books.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no books in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(books);
            }

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }
        return response;
    }

    private Response getBooksByQuery(Request request) {
        Response response = new Response();

        try {
            String condition = (String) request.getObject();
            List<Book> books = Controller.getInstance().getBooksByQuery(condition);
            if (books.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no such books in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(books);
            }
        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response deleteBook(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteBook((Book) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("System has deleted the book.");

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response getAllMembers() {
        Response response = new Response();
        try {
            List<Member> members = Controller.getInstance().getAllMembers();
            if (members.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no members in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(members);
            }

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }
        return response;

    }

    private Response addReceipt(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().addReceipt((Receipt) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("Receipt has been added.");

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response addMember(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().addMember((Member) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("System has added member.");

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;

    }

    private Response getMembersByQuery(Request request) {
        Response response = new Response();

        try {
            String condition = (String) request.getObject();
            List<Member> members = Controller.getInstance().getMembersByQuery(condition);
            if (members.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no such members in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(members);
            }
        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response updateMember(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().updateMember((Member) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("System has updated the member.");

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;

    }

    private Response membershipRenewal(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().membershipRenewal((Member) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("Membership has been updated.");

        } catch (Exception e) {
            response.setException(new Exception("System can't renew membership."));
            response.setIsSuccessfull(false);
        }

        return response;

    }

    private Response addLoan(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().addLoan((Loan) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("System has added loan.");

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response deleteLoan(Request request) {
        Response response = new Response();
        try {
            Loan loan = (Loan) request.getObject();
            Controller.getInstance().deleteLoan(loan);
            if (loan.getMember().getMembershipValidityDate().before(new Date(System.currentTimeMillis()))) {
                response.setIsSuccessfull(true);
                response.setResult("System has deleted the loan. Member has to pay fee becase his membership has been expired.");

            } else {
                response.setIsSuccessfull(true);
                response.setResult("System has deleted the loan.");
            }

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response getAllLoans() {
        Response response = new Response();
        try {
            List<Loan> loans = Controller.getInstance().getAllLoans();
            if (loans.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no loans in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(loans);
            }

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }
        return response;
    }

    private Response getLoansByQuery(Request request) {
        Response response = new Response();

        try {
            String condition = (String) request.getObject();
            List<Loan> loans = Controller.getInstance().getLoansByQuery(condition);
            if (loans.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no such loans in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(loans);
            }
        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response login(Request request) {
        Response response = new Response();
        try {
            librarian = Controller.getInstance().login((Librarian) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult(librarian);

        } catch (Exception e) {
            response.setException(new Exception("Sistem can't find librarian."));
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response addAuthor(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().addAuthor((Author) request.getObject());
            response.setIsSuccessfull(true);
            response.setResult("System has added author.");

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }

        return response;
    }

    private Response getExpiredLoans(Request request) {
        Response response = new Response();
        try {
            List<Loan> loans = controller.Controller.getInstance().getExpiredLoans((Loan) request.getObject());
            if (loans.isEmpty()) {
                response.setIsSuccessfull(false);
                response.setException(new Exception("There are no such loans in database"));
            } else {
                response.setIsSuccessfull(true);
                response.setResult(loans);
            }

        } catch (Exception e) {
            response.setException(e);
            response.setIsSuccessfull(false);
        }
        return response;
    }

}
