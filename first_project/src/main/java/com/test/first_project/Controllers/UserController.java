package com.test.first_project.Controllers;

import com.test.first_project.Entities.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/hi")
    public Greeting sayHi(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(
                contador.incrementAndGet(),
                "Hi, "+name+" kc dic",
                this.getClass().getSimpleName());
    }

    @GetMapping("/bye/{name}")
    public Greeting sayBye(@PathVariable(value = "name") String name){
        return new Greeting(
                contador.incrementAndGet(),
                "Ta luego, "+name+" vemos",
                this.getClass().getSimpleName());
    }

}
