package com.rakesh.reactivespring.api;

import com.rakesh.reactivespring.models.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) {
        return Mono.just(ResponseEntity
                .ok()
                .body("Hello " + loginRequest.getEmail()));
    }
}
