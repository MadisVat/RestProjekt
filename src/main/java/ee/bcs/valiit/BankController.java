package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//@RestController
public class BankController {

    HashMap<String, Integer> accounts = new HashMap<String, Integer>();
    //BigDecimal newBalance = currentBalance.subtract(amount);
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostMapping("add")
    public void addAccountDto(@RequestBody AccountDto accounts) {
        String sql = "INSERT INTO bank (id, account_no, balance) VALUES (:id, :account_no, :balance)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", accounts.getId());
        paramMap.put("account_no", accounts.getAccountNo());
        paramMap.put("balance", accounts.getBalance());
        jdbcTemplate.update(sql, paramMap);
        // postmanni(JSON):
        //  "id": 3333,
        //  "accountNo": "RU33",
        //  "balance": 700
    }

   /* DEBUGGING RAIVOGA KOOS
   @PutMapping("deposit2/{account}")      //get üldiselt ei peaks olema RequestBodyga....pigem Paramiga
    public void getBalanceDto2(@PathVariable String account, @RequestBody Integer amount) { //peab vastama sellele andmetüübile, mis sa POSTmanis sisse annad
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_no", account);
        //System.out.println(jdbcTemplate.queryForObject(sql, paramMap, Integer.class)+amount);
        Integer newBalance = jdbcTemplate.queryForObject(sql, paramMap, Integer.class) + amount;
        System.out.println(newBalance);
        System.out.println(paramMap.get("account_no"));
        paramMap.put("balance", newBalance);
        sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
        //UPDATE bank SET balance = 600 WHERE account_no = 'RU33';
        System.out.println(sql);
        jdbcTemplate.update(sql, paramMap);
    }*/

    @GetMapping("balance")      //get üldiselt ei peaks olema RequestBodyga....pigem Paramiga
        public String getBalanceDto(@RequestBody String accounts) { //peab vastama sellele andmetüübile, mis sa POSTmanis sisse annad
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_no", accounts);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
        // postmanni(text): RU33
    }

    @PutMapping("deposit/{accountNumber}")
    public void depositMoneyDto(@PathVariable/*("id")*/ String accountNumber, @RequestBody BigDecimal amount) {
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

    @PutMapping("withdraw/{accountNumber}")
    public void withdrawMoneyDto(@PathVariable/*("id")*/ String accountNumber, @RequestBody BigDecimal amount) {
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_no", accountNumber);
        BigDecimal currentBalance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        System.out.println("CurrentBalance: " + currentBalance);
        //sum = a.add(b);
        BigDecimal newBalance = currentBalance.subtract(amount);
        paramMap.put("balance", newBalance);
        sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
        System.out.println("Withdrawn amount: " + amount);
        System.out.println("New Balance: " + newBalance);
        jdbcTemplate.update(sql, paramMap);
    }


    //MIINUS MÄRGIGA SUMMAD TULEKS ÄRA KEELATA
    @PutMapping("transfer/{fromAccountNr},{toAccountNr}")
    public String transferMoneyDto(@PathVariable String fromAccountNr, @PathVariable String toAccountNr, @RequestBody BigDecimal amount) {
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_no", fromAccountNr);
        BigDecimal currentBalanceFrom = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        System.out.println(" ");
        System.out.println("Current Balance FROM: " + currentBalanceFrom);
        int result = currentBalanceFrom.compareTo(amount);
        if (result >= 0) {
            //BigDecimal newBalanceTo = currentBalance.add(amount);
            BigDecimal newBalanceFrom = currentBalanceFrom.subtract(amount);
            paramMap.put("balance", newBalanceFrom);
            paramMap.put("account_no", fromAccountNr);
            sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
            System.out.println("New Balance FROM: " + newBalanceFrom);
            jdbcTemplate.update(sql, paramMap);

            sql = "SELECT balance FROM bank WHERE account_no = :account_no";
            paramMap.put("account_no", toAccountNr);
            BigDecimal currentBalanceTo = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
            BigDecimal newBalanceTo = currentBalanceTo.add(amount);
            System.out.println(" ");
            System.out.println("Current Balance TO: " + currentBalanceTo);
            paramMap.put("balance", newBalanceTo);
            paramMap.put("account_no", toAccountNr);
            sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
            System.out.println("New Balance TO: " + newBalanceTo);
            jdbcTemplate.update(sql, paramMap);

            return "OK";
        } else {
            return "NOP";
        }


    }


    // jdbcTemplate.update(sql, paramMap);   queryForObject konto balanci küsimiseks
    // ja pärast update

/*    ALGNE toimiv
        @GetMapping("sqltest")
        public String testSql() {
        String sql = "SELECT account_nr FROM account where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", 1234);
        String vastus = jdbcTemplate.queryForObject(sql, paramMap, String.class);

        sql = "UPDATE account SET balance = :balance where id = :id";
        paramMap = new HashMap<>();
        paramMap.put("balance", 50);
        paramMap.put("id", 2345);
        jdbcTemplate.update(sql, paramMap);
        return vastus;
    }*/


//==================================================================================

    @PostMapping("/vanaaccounts/{accountnr}")
    //  http://localhost:8080/accounts/EE11
    public void addAccount(@PathVariable String accountnr) {

        System.out.println("Added account: \"" + accountnr + "\"");
        accounts.put(accountnr, 0);
        System.out.println("=====");
    }

    @GetMapping("/vanabalance/{accountnr}")
    //  http://localhost:8080/balance/EE12345
    public Integer getBalance(@PathVariable String accountnr) {
        System.out.println("Account \"" + accountnr + "\" balance: " + accounts.get(accountnr));
        System.out.println("=====");
        return accounts.get(accountnr);


    }

    @PutMapping("/vanadeposit/{accountnr}")
    public void depositMoney(@PathVariable String accountnr, @RequestBody Integer raha) {
        System.out.println("Deposit money");
        System.out.println("Initial balance for account " + "\"" + accountnr + "\":" + accounts.get(accountnr));
        accounts.put(accountnr, accounts.get(accountnr) + raha);
        System.out.println("€ " + raha + " added to Your account.");
        System.out.println("Total balance for account " + "\"" + accountnr + "\":" + accounts.get(accountnr));
        System.out.println("=====");

    }

    @PutMapping("/vanawithdraw/{accountnr}")
    public void withdrawMoney(@PathVariable String accountnr, @RequestBody Integer raha) {
        System.out.println("Withdraw money");
        System.out.println("Initial balance for account " + "\"" + accountnr + "\":" + accounts.get(accountnr));
        System.out.println("Withdraw request progressed for amount: " + raha);
        if (accounts.put(accountnr, accounts.get(accountnr)) < raha) {
            System.out.println("Transaction not authorised. Insufficient funds!");
            System.out.println("=====");
        } else {
            accounts.put(accountnr, accounts.get(accountnr) - raha);
            System.out.println("Total balance for account " + "\"" + accountnr + "\":" + accounts.get(accountnr));
            System.out.println("=====");
        }
    }

    //transferMoney(String account1, String account2, amount) | kanna raha esimeselt kontolt teisele kontole
    @PutMapping("/vanatransfer/{accountnr},{toaccountnr}")
    public void transfer(@PathVariable String accountnr, @PathVariable String toaccountnr, @RequestBody Integer
            ülekanne) {
        System.out.println("Transfer money");
        System.out.println("Initial balance for account " + "\"" + accountnr + "\":" + accounts.get(accountnr));
        System.out.println("Transfer request progressed for amount: " + ülekanne);
        if (accounts.put(accountnr, accounts.get(accountnr)) < ülekanne) {
            System.out.println("Transaction not authorised. Insufficient funds!");
            System.out.println("=====");
        } else {
            accounts.put(toaccountnr, accounts.get(toaccountnr) + ülekanne);
            accounts.put(accountnr, accounts.get(accountnr) - ülekanne);
            System.out.println("Total balance for account " + "\"" + accountnr + "\":" + accounts.get(accountnr));
            System.out.println("Total balance for account " + "\"" + toaccountnr + "\":" + accounts.get(toaccountnr));
            System.out.println("=====");
        }
    }









    /*@PostMapping("/employees") // LISAB LISTI OBJEKTI
    public void addEmployee(@RequestBody Employee employee) {
        System.out.println("Post:");
        employees.add(employee);
    }

    @GetMapping("/employee/{id}") // prindib OBJEKT
    public Employee getEmployeeById(@PathVariable int id) {
        System.out.println("Get " + id);
        return employees.get(id);
    }


    @PutMapping("/employee/{id}") // asendab OJEKTI
    public void changeEmployee(@RequestBody Employee employee, @PathVariable int id) {
        employees.set(id, employee);
        System.out.println("set " + id);
    }

    @DeleteMapping("/employee/{id}") // kustutab OJEKTI
    public void removeEmployeeById(@PathVariable int id) {
        System.out.println("Delete");
        employees.remove(id);
    }
    */
}


    /* SIIMU mingi arendus
    @GetMapping ("/accounts/{accountNr}")
    public Account getAmount(@RequestBody Account account, @PathVariable String accountNr) {
        System.out.println("Konto jääk: ");
        Integer a = accounts.get(account.getAccountNr());
        Account accountResponse = new Account();
        account.setAmount(a);
        account.setAccountNr(accountNr);
        return accountResponse;
    } */