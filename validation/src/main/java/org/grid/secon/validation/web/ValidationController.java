package org.grid.secon.validation.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/validation")
public class ValidationController {

    @GetMapping
    public String validate(@RequestParam UUID id) {
        return "OK";
    }
}