package ee.bcs.valiit;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class BankController {
    /*
    public String getAccountNr() {
        return accountNr;
    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    public BigDecimal getAmount() {
        return amount;
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    */

    HashMap<String, Integer> accounts = new HashMap<String, Integer>();

    //getAccount(String accountNr) | tagasta kui palju raha on vastaval kontol
    @GetMapping ("/accounts/{accountNr}")
    public Integer getAmount(@RequestBody Account account, @PathVariable String accountNr) {
        System.out.println("Konto jääk: ");
        return accounts.get(account.getAccountNr());
    }

    @PostMapping ("/accounts")               // LISAB LISTI OBJEKTI
    public Integer addAccount(@RequestBody Account account) {
        System.out.println("Added account: ");
        accounts.put(account.getAccountNr(), account.getAmount());
        return accounts.get(account.getAccountNr());
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