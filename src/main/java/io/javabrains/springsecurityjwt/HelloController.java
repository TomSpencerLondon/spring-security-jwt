package io.javabrains.springsecurityjwt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public String hello() {
        return "Hello World";
    }
}
