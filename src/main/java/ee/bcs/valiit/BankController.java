package ee.bcs.valiit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

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

    @PostMapping("/accounts") // LISAB LISTI OBJEKTI
    public void addAccount(@RequestBody Employee employee) {
        System.out.println("Post:");
        employees.add(employee);

        //@GetMapping("/accounts") //PRINDIB LISTI
        //public HashMap<Account, amount> getAllAccounts() {
        //    System.out.println("List of accounts:");
        //    return accounts;
        //}

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
}