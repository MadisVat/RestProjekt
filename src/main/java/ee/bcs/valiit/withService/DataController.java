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
    public void deposit(@PathVariable("accountNumber") String request, @RequestBody BigDecimal amount) {
        accountService.depositCurreny(request, amount);
    }


}

   /* @PutMapping("deposit/{accountNumber}")
    public void depositMoneyDto(@PathVariable*//*("id")*//* String accountNumber, @RequestBody BigDecimal amount) {
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_no", accountNumber);
        BigDecimal currentBalance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        System.out.println("CurrentBalance: " + currentBalance);
        //sum = a.add(b);
        BigDecimal newBalance = currentBalance.add(amount);
        paramMap.put("balance", newBalance);
        sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
        System.out.println("Deposited amount: " + amount);
        System.out.println("New Balance: " + newBalance);
        jdbcTemplate.update(sql, paramMap);
    }

    @PutMapping("add")
    public void add(@RequestBody AddMoneyRequest request) {
        accountService.addMoney(request.getToAccount(),request.getAmount());*/