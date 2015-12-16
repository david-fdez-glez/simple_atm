package org.dfernandez.manifesto.model;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 15/12/15
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class Atm {

    private String id;
    private String address;
    private double balance;

    public Atm(String address, String id) {
        this.address = address;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
