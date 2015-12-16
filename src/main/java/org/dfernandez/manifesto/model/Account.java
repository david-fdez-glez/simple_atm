package org.dfernandez.manifesto.model;

import org.dfernandez.manifesto.exception.AccountFundsException;
import org.dfernandez.manifesto.util.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 15/12/15
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class Account {

    private String name;
    // TODO autogenerate accountNumber
    private int accountNumber;

    // TODO Better Store pin in a char
    private String pin;
    private double balance;
    private double overdraft;

    public Account(String name,int accountNumber, String pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
        this.overdraft = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }


    //TODO Add security mechanism to set a new pin
    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    //TODO Add security mechanism to set a new balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    //TODO Add security mechanism to set a new overdraft
    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }



    public double deposit(double depositAmmount) {
        balance += depositAmmount;
        return balance;
    }

    public double withdraw(double withdrawAmmount) throws AccountFundsException {
        if(balance + overdraft >= withdrawAmmount ) {
            // check   overdraft
            balance -= withdrawAmmount;
            return  balance;
        } else {
            throw new AccountFundsException(Constants.FUNDS_ERR) ;
        }
    }

    @Override
    public String toString() {
        return name + " " + accountNumber;
    }

    public String toStringWithDrawTotal() {
        return  balance + " " + overdraft;
    }
}
