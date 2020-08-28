package ee.bcs.valiit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

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

}

