package com.rakesh.reactivespring.person;

import com.rakesh.reactivespring.person.api.PersonApi;
import com.rakesh.reactivespring.person.mapper.PersonDTOMapper;
import com.rakesh.reactivespring.person.model.PersonDTO;
import com.rakesh.reactivespring.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonApi {

    private final PersonDTOMapper personDTOMapper;
    private final PersonService personService;

    @Override
    public Mono<PersonDTO> createPerson(Mono<PersonDTO> personDTO, ServerWebExchange exchange) {
        return personDTO
                .map(personDTOMapper::toDomain)
                .flatMap(personService::createPerson)
                .map(personDTOMapper::toDTO);
    }

    @Override
    public Mono<PersonDTO> getPerson(Integer id, ServerWebExchange exchange) {
        return personService.findById(Long.valueOf(id)).map(personDTOMapper::toDTO);
    }
}
