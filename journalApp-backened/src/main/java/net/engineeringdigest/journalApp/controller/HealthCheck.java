package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HealthCheck {
    @GetMapping("/health")
    public String healthCheck(){
        return "ok";
    }
}
