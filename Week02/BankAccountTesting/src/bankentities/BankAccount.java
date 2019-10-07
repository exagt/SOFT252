package bankentities;

/**
 *
 * @author Mary
 */
public class BankAccount {
    private int balance;
    private int overdraft;
    private String holder;
    
    private final int STANDARDOVERDRAFT = 500;
    private final int STARTBALANCE = 110;

    /*
     * Constructor - uses the value passed via the parameter to initialise
     * the account holder's name, then sets the default values into the 
     * overdraft amount and account balance.
     */
    public BankAccount(String holder) {
        this.holder = holder;
        this.overdraft = STANDARDOVERDRAFT;
        this.balance = STARTBALANCE;
    }
    
    /*
     * Processes a deposit - the amount of the deposit is passed via the
     * parameter and added to the balance.
     */
    public void depositMoney(int amount) {
        balance += amount;
    }
    
    /*
     * Processes a withdrawal, but only if there are sufficient funds in the
     * account. If the withdrawal is successful, the amount (passed by 
     * parameter) is deducted from the balance and the value 'true' is 
     * returned, otherwise the amount is not deducted and the value 'false'
     * is returned.
     */
    public boolean withdrawMoney(int amount) {
        if (withdrawalIsOK(amount)) {
            balance = amount;
            return true;
        }
        else
            return false;   
    }
    
    /*
     * Checks whether a withdrawal is allowable by comparing the amount
     * requested with the current balance + the overdraft.
     */
    public boolean withdrawalIsOK(int amount) {
        if ((balance - overdraft) < amount) 
            return false;
        else 
            return true;
    }

    /*
     * Returns the value of the current balance.
     */
    public int getBalance() {
        return balance;
    }

    /*
     * Returns the name of the account holder.
     */
    public String getHolder() {
        return holder;
    }

    /*
     * Returns the overdraft amount allowed.
     */
    public int getOverdraft() {
        return overdraft;
    }

    /*
     * Changes the overdraft amount allowed.
     */
    public void setOverdraft(int overdraft) {
        this.overdraft = overdraft;
    }
}
