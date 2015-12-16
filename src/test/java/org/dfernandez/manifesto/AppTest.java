package org.dfernandez.manifesto;

import org.dfernandez.manifesto.exception.AccountError;
import org.dfernandez.manifesto.exception.AccountFundsException;
import org.dfernandez.manifesto.exception.AtmException;
import org.dfernandez.manifesto.exception.ManifestoException;
import org.dfernandez.manifesto.model.Account;
import org.dfernandez.manifesto.model.Atm;
import org.dfernandez.manifesto.service.AtmService;
import org.dfernandez.manifesto.util.Constants;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest  {

    Account customerA  ;
    Account customerB  ;

    AtmService atmService ;
    Atm atm ;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        atmService = new AtmService() ;
        atm = new Atm("Oxford Street","15");
        atmService.startATM(atm,8000);

        customerA = new Account("Customer A", 12345678, "1234")  ;
        customerA.setBalance(500);
        customerA.setOverdraft(100);

        customerB = new Account("Customer B", 87654321,"4321");

    }

    @Test
    public void testAtmNoFunds() throws ManifestoException {
        exception.expect(AtmException.class);
        exception.expectMessage(Constants.ATM_ERR);
        atmService.getWithDraw(atm,customerA,"1234",8500);
    }

    @Test
    public void testAccountWrongPin() throws  ManifestoException {
        exception.expect(AccountError.class);
        exception.expectMessage(Constants.ACCOUNT_ERR);
        atmService.getWithDraw(atm,customerA,"2222",500);
    }
    @Test
    public void testAccountNoFunds() throws ManifestoException {
        exception.expect(AccountFundsException.class);
        exception.expectMessage(Constants.FUNDS_ERR);
        atmService.getWithDraw(atm, customerA, "1234", 2200);
    }


    @Test
    public void testDataFirst() throws ManifestoException {
        System.out.println("\n\nTest Data First\n");
        System.out.println(customerA.getAccountNumber() + " "  + customerA.getPin() + " 1234");
        System.out.println(customerA.toStringWithDrawTotal());
        System.out.println("B");
        System.out.println("W 100");
        Double expectedBalance = atmService.getBalanceAccount(customerA, "1234");
        assertEquals(expectedBalance,500,0);
        Double expectedResult = atmService.getWithDraw(atm,customerA,"1234",100);
        assertEquals(expectedResult,400,0);

        System.out.println("\nOutPut");
        System.out.println(expectedBalance);
        System.out.println(expectedResult);
        System.out.println("\n\n*****************************************************************************\n");

    }

    @Test
    public void testDataSecond() throws ManifestoException {
        customerB.setBalance(100);
        customerB.setOverdraft(0);
        System.out.println("\n\nTest Data Second\n");
        System.out.println(customerB.getAccountNumber() + " " + customerB.getPin() + " " + "4321");
        System.out.println(customerB.toStringWithDrawTotal());
        System.out.println("W 10");

        Double expectedResult = atmService.getWithDraw(atm,customerB,"4321",10);
        assertEquals(expectedResult,90,0);

        System.out.println("\nOutPut");
        System.out.println(expectedResult);
        System.out.println("\n\n*****************************************************************************\n");

    }

    @Test
    public void testDataThird() throws ManifestoException {

        customerB.setBalance(0);
        customerB.setOverdraft(0);
        System.out.println("\n\nTest Data Third\n");
        System.out.println(customerB.getAccountNumber() + " " + customerB.getPin() + " " + "4321");
        System.out.println(customerB.toStringWithDrawTotal());
        System.out.println("W 10");
        System.out.println("B");
        System.out.println("\nOutPut");

        try {
            Double expectedResult = atmService.getWithDraw(atm,customerB,"4321",10);
            assertEquals(expectedResult,90,0);
        } catch (AccountFundsException e) {
            System.out.println(e.getMessage());

        }

        Double expectedBalance = atmService.getBalanceAccount(customerB, "4321");
        assertEquals(expectedBalance,0,0);

        System.out.println(expectedBalance);
        System.out.println("\n\n*****************************************************************************\n");


    }
}
