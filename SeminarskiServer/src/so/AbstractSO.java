/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author nikol
 */
public abstract class AbstractSO {
    protected final Repository repository;

    public AbstractSO() {
        this.repository = new RepositoryDBGeneric();
    }

    public final void execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();

            executeOperation(param);
            comitTransaction();

        } catch (Exception exception) {
            rollbackTransaction();
            throw exception;
        } finally {
            disconnect();
        }

    }

    protected abstract void precondition(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    protected void comitTransaction() throws Exception {
        ((DbRepository) repository).commit();

    }

    protected void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
