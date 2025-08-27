/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package communication;

import java.nio.channels.MembershipKey;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author nikol
 */
public enum Operation {
    LOGIN, LOGOUT, GET_ALL_AUTHORS, GET_AUTHORS_BY_QUERY, ADD_BOOK, GET_ALL_BOOKS, GET_BOOKS_BY_QUERY, DELETE_BOOK,GET_ALL_MEMBERS,
    ADD_RECEIPT, ADD_MEMBER, GET_MEMBERS_BY_QUERY, UPDATE_MEMBER, MEMBERSHIP_RENEWAL, ADD_LOAN, DELETE_LOAN, GET_LOAN_BY_QUERY, GET_ALL_LOANS,
    ADD_AUTHOR, GET_EXPIRED_LOANS
    
}
