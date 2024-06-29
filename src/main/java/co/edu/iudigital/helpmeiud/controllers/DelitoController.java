package co.edu.iudigital.helpmeiud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delitos")
public class DelitoController {

    @GetMapping
    public void test() {

    }
}
