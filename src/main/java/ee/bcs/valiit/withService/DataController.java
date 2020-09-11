package ee.bcs.valiit.withService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class DataController {
    @Autowired
    private AccountService accountService;

    @GetMapping("balance/{accountNumber}")
    public BigDecimal accountBalance(@PathVariable("accountNumber") String request) {
        return accountService.getBalance(request);
    }

    @PutMapping("deposit/{accountNumber}")
    public BigDecimal deposit(@PathVariable("accountNumber") String request, @RequestBody BigDecimal amount) {
        accountService.depositCurrency(request, amount);
        return accountService.getBalance(request);
    }

    @PutMapping("withdraw/{accountNumber}")
    public BigDecimal withdraw(@PathVariable("accountNumber") String request, @RequestBody BigDecimal amount) {
        accountService.withdrawCurrency(request, amount);
        return accountService.getBalance(request);
    }

    @PutMapping("transfer/{fromAccountNumber},{toAccountNumber}")
    public BigDecimal transfer(@PathVariable String fromAccountNumber, @PathVariable String toAccountNumber, @RequestBody BigDecimal amount) {
        accountService.transferCurrency(fromAccountNumber, toAccountNumber, amount);
        return accountService.getBalance(toAccountNumber);
    }

    @PutMapping("createClient")
    public Long createClient(@RequestBody AddClient addClient) {
        return accountService.createClient(addClient);
    }

    @PutMapping("createAccount")
    public void createAccount(@RequestBody AddAccount addAccount) {
        accountService.createAccount(addAccount);

        // accountService.createAccount(addAccount.getAccountNumber(), addAccount.getBalance(), addAccount.getId());
        /*{
        "accountNumber": "EE333",
            "balance": 0,
            "id": 8888
    }*/

    }
}