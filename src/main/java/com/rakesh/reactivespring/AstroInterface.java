package com.rakesh.reactivespring;

import com.rakesh.reactivespring.json.AstroResponse;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface AstroInterface {
    @GetExchange("/astros.json")
    Mono<AstroResponse> getResponse();
}
