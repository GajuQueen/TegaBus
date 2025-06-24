package org.example.tegabus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {
    @GetMapping
    public String entry() {
        return """
            <h1>Welcome to TegaBus API</h1>
            <p><a href="https://tegabus.onrender.com/swagger-ui/index.html" target="_blank">Go to API Documentation</a></p>
            """;
    }
}
