package ee.bcs.valiit.withService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public BigDecimal getBalance(String fromAccount) {
        return accountRepository.getBalance(fromAccount);
    }

    public BigDecimal depositCurrency(String toAccount, BigDecimal amount) {
        accountRepository.getBalance(toAccount);
        BigDecimal currentBalance = accountRepository.getBalance(toAccount);
        System.out.println("Current balance: " + currentBalance);
        int result = amount.compareTo(BigDecimal.ZERO);
        if (result > 0) {
            BigDecimal newBalance = currentBalance.add(amount);
            System.out.println("New Balance: " + newBalance);
            accountRepository.updateBalance(toAccount, newBalance);
            return accountRepository.getBalance(toAccount);
        } else {
            System.out.println("Can not deposit a negative sum");
            return accountRepository.getBalance(toAccount);
        }

    }

    public void withdrawCurrency(String fromAccount, BigDecimal amount) {
        accountRepository.getBalance(fromAccount);
        BigDecimal currentBalance = accountRepository.getBalance(fromAccount);
        System.out.println("Current balance: " + currentBalance);
        BigDecimal newBalance = currentBalance.subtract(amount);
        System.out.println("New Balance: " + newBalance);
        accountRepository.updateBalance(fromAccount, newBalance);
    }
    // TODO negatiivsed  SUMMAD TULEKS ÄRA KEELATA
    public BigDecimal transferCurrency(String fromAccount, String toAccount, BigDecimal amount) {
        accountRepository.getBalance(fromAccount);
        BigDecimal currentBalanceFrom = accountRepository.getBalance(fromAccount);
        System.out.println("Current balance \"From Account\": " + currentBalanceFrom);
        int result = currentBalanceFrom.compareTo(amount);
        if (result >= 0) {
            BigDecimal newBalanceFrom = currentBalanceFrom.subtract(amount);
            System.out.println("New balance \"From Account\": " + newBalanceFrom);
            accountRepository.updateBalance(fromAccount, newBalanceFrom);
            accountRepository.getBalance(toAccount); // to Account
            BigDecimal currentBalanceTo = accountRepository.getBalance(toAccount);
            System.out.println("Current balance \"TO Account\": " + currentBalanceTo);
            BigDecimal newBalanceTo = currentBalanceTo.add(amount);
            System.out.println("New balance \"TO Account\": " + newBalanceTo);
            accountRepository.updateBalance(toAccount, newBalanceTo);
            return accountRepository.getBalance(toAccount);
        } else {
            System.out.println("Insufficient funds");
            return accountRepository.getBalance(toAccount);
        }
    }

    public void createAccount(AddAccount addAccount) {
        if (addAccount.getBalance() == null) {
            addAccount.setBalance(BigDecimal.ZERO);
        }
        accountRepository.createAccount(addAccount);

    }


}