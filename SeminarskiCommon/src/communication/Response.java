/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author nikol
 */
public class Response implements Serializable{
    private Object result;
    private boolean isSuccessfull;
    private Exception exception;

    public Response() {
    }

    public Response(Object result, boolean isSuccessfull, Exception exception) {
        this.result = result;
        this.isSuccessfull = isSuccessfull;
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isIsSuccessfull() {
        return isSuccessfull;
    }

    public void setIsSuccessfull(boolean isSuccessfull) {
        this.isSuccessfull = isSuccessfull;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Response{" + "result=" + result +  ", isSuccessfull=" + isSuccessfull + ", exception=" + exception + '}';
    }
    
}
