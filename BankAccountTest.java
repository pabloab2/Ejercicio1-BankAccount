import org.junit.jupiter.api.*;

import bank.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    
    BankAccount bankAccount;

    @BeforeEach
    void setup(){
        bankAccount = new BankAccount(0);
    }

    @Test
    @DisplayName("Test that guarantees that the balance getter works")
    public void correctBalance(){
        int expectedValue = 0;
        int obtainedValue = bankAccount.getBalance(); 
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Test that guarantees that the balance getter works")
    public void incorrectBalance(){
        int expectedValue = 1;
        int obtainedValue = bankAccount.getBalance(); 
        assertNotEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Test that guarantees that the balance is correctly created")
    public void withdraw0(){
        boolean result = bankAccount.withdraw(0);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test that guarantees that the balance is correctly created")
    public void withdrawPositive(){
        boolean result = bankAccount.withdraw(1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test that guarantees that you can't make a negative deposit")
    public void negativeDeposit(){
        assertThrows(IllegalArgumentException.class, ()->new BankAccount(0).deposit(-1));
        // this is the only assertThrows donde because it's the only function that throws an exception
    }
    
    @Test
    @DisplayName("Test that guarantees that the deposit is done correctly")
    public void correctDeposit(){
        int expectedValue = 2;
        int obtainedValue = bankAccount.deposit(2); 
        assertEquals(expectedValue, obtainedValue);
    }
    @Test
    @DisplayName("Test that guarantees that the payment is done correctly")
    public void correctPayment0(){
        double expectedValue = bankAccount.payment(100, 1.5, 8);
        double obtainedValue = bankAccount.payment(0, 1.5, 8);
        assertNotEquals(obtainedValue, expectedValue);
    }
    @Test
    @DisplayName("Test that guarantees that the payment is done correctly")
    public void correctPayment(){
        double obtainedValue = bankAccount.payment(100, 1.5, 8);
        double expectedValue = 100*(1.5*Math.pow((1+1.5), 8)/(Math.pow((1+1.5), 8)-1));
        assertEquals(obtainedValue, expectedValue);
    }
    @Test
    @DisplayName("Test that guarantees that the payment is made correctly when there are 0 months")
    public void correctPendingMonth0(){
        double expectedValue = 100;
        double obtainedValue = bankAccount.pending(100, 3, 5, 0);
        assertEquals(obtainedValue, expectedValue);
    }
    @Test
    @DisplayName("Test that guarantees that the pending is done correctly")
    void pendingCheck() {
        double obtainedValue = bankAccount.pending(100, 1.5, 8, 5);
        double expectedValue = bankAccount.pending(100, 1.5, 8, 5-1) - 
        (bankAccount.payment(100,1.5,8) - 1.5*(bankAccount.pending(100, 1.5, 8, 5-1)));
        assertEquals(obtainedValue, expectedValue);
    }
    


}
