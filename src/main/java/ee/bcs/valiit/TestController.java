package ee.bcs.valiit;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private static final List<Employee> employees = new ArrayList();
    //see list on koguaeg olemas. Peale restarti on andmed kadunud.

    @GetMapping("/employees") //PRINDIB LISTI
    public List<Employee> getAllEmployees() {
        System.out.println("List:");
        return employees;
    }

    @PostMapping("/employees") // LISAB LISTI OBJEKTI
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

    //
    //
    //
    //
    //
    //
    //
    @GetMapping("/reverseString")
    public String reverseString(@RequestParam("a") String a) {
        return Lesson3.reverseString(a);
    }

    @GetMapping("/min")
    public Integer min(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        return Lesson3.min(a, b, c);
        // http://localhost:8080/min?a=10&b=7&c=2
    }

    @GetMapping("/algus")
    public Integer algus(@RequestParam("i") int i) {
        return Lesson3.algus(i);
    }

    @GetMapping("/sum")
    public Integer sum(@RequestParam("x") int x, @RequestParam("y") int y) {
        return Lesson3.sum(x, y);
        // http://localhost:8080/sum?x=10&y=7
    }

    @GetMapping("/sum2")
    public int sum2(@RequestParam("x") int[] x) {
        return Lesson3.sum2(x);
        // http://localhost:8080/sum2?x=15,5,20
    }

    @GetMapping("/factorial")
    public Integer factorial(@RequestParam("x") int x) {
        return Lesson3.factorial(x);
    }

    @GetMapping("/sort")
    public int[] sort(@RequestParam("a") int[] a) {
        return Lesson3.sort(a);

    }

    @GetMapping("/Client")
    public Client dtoTest() {
        Client client = new Client();
        client.setAge(5);
        client.setName("Valdur");
        return client;
        // http://localhost:8080/Client
    }

    @PostMapping("/Client")
    public void dtoTest(@RequestBody Client client) {
        System.out.println(client.getName());
        System.out.println(client.getAge());
        // http://localhost:8080/Client
    }
}

