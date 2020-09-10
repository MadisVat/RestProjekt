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
    public void withdraw(@PathVariable("accountNumber") String request, @RequestBody BigDecimal amount) {
        accountService.withdrawCurrency(request, amount);
    }

    @PutMapping("transfer/{fromAccountNumber},{toAccountNumber}")
    public BigDecimal transfer(@PathVariable String fromAccountNumber, @PathVariable String toAccountNumber, @RequestBody BigDecimal amount) {
        accountService.transferCurrency(fromAccountNumber, toAccountNumber, amount);
        return accountService.getBalance(toAccountNumber);
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

        // TODO SQL 3 tabelit
        // TODO transaction history

//        Ülesanne BankController (loeng 10)
//
//        Loo uus meetod createClient kus sisestakase kliendi
//        andmed ja tagastatakse kliendi id
//
//        Täienda meetodid createAccount, et ta peab sisse
//        lugema ka cliendi id
//
//        Loo uus tabel transaction_history
//
//        Iga kord kui tehakse deposit, withdraw või transfer
//        lisa kirja transaction_history tabelisse tegevuse
//                sisuga




    }
}