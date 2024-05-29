package com.rakesh.reactivespring;

import com.rakesh.reactivespring.json.AstroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AstroController {

    private final AstroService astroService;

    @GetMapping
    public Mono<AstroResponse> getAstroResponse() {
        return astroService.getAstroResponseAsync();
    }
}
