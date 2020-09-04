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

    public void depositCurreny(String toAccount, BigDecimal amount) {
        accountRepository.getBalance(toAccount);
        BigDecimal currentBalance = accountRepository.getBalance(toAccount);
        System.out.println("CurrentBalance: " + currentBalance);
        BigDecimal newBalance = currentBalance.add(amount);
        accountRepository.updateBalance(toAccount, newBalance);

    }

//    public void depositMoneyDto(@PathVariable*//*("id")*//* String accountNumber, @RequestBody BigDecimal amount) {
//            String sql="SELECT balance FROM bank WHERE account_no = :account_no";
//            Map<String, Object>paramMap=new HashMap();
//        paramMap.put("account_no",accountNumber);
//                BigDecimal currentBalance=jdbcTemplate.queryForObject(sql,paramMap,BigDecimal.class);
//        System.out.println("CurrentBalance: "+currentBalance);
//        //sum = a.add(b);
//        BigDecimal newBalance=currentBalance.add(amount);
//        paramMap.put("balance",newBalance);
//        sql="UPDATE bank SET balance = :balance WHERE account_no = :account_no";
//        System.out.println("Deposited amount: "+amount);
//        System.out.println("New Balance: "+newBalance);
//        jdbcTemplate.update(sql,paramMap);


}
