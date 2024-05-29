package com.rakesh.reactivespring.person;

import com.rakesh.reactivespring.person.api.PersonApi;
import com.rakesh.reactivespring.person.model.PersonDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class PersonController implements PersonApi {

    @Override
    public Mono<PersonDTO> createPerson(Mono<PersonDTO> personDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<PersonDTO> getPerson(Integer id, ServerWebExchange exchange) {
        return null;
    }
}
