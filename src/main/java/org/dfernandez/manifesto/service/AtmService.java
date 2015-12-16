package org.dfernandez.manifesto.service;

import org.dfernandez.manifesto.exception.AccountError;
import org.dfernandez.manifesto.exception.AccountFundsException;
import org.dfernandez.manifesto.exception.AtmException;
import org.dfernandez.manifesto.model.Account;
import org.dfernandez.manifesto.model.Atm;
import org.dfernandez.manifesto.util.Constants;

import java.lang.annotation.AnnotationTypeMismatchException;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 15/12/15
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
public class AtmService {

    public void startATM(Atm atm, double amount) {
        atm.setBalance(amount);
    }


    public double getBalanceAtm(Atm atm) {
        return atm.getBalance();
    }
    public double  getBalanceAccount(Account account, String pin) throws AccountError{
        if(account.getPin().equals(pin)) {
            return account.getBalance();
        } else {
             throw  new AccountError(Constants.ACCOUNT_ERR);
        }
    }


    public double getWithDraw(Atm atm, Account account, String pin, double amount) throws AtmException, AccountError, AccountFundsException {
        if(account.getPin().equals(pin)) {
                if(atm.getBalance() >= amount) {
                     // atm balance mnus
                    atm.setBalance(atm.getBalance() - amount);
                    return account.withdraw(amount);
                } else {
                    throw new AtmException(Constants.ATM_ERR);
                }
        } else {
            throw new AccountError(Constants.ACCOUNT_ERR);
        }
    }

}
