package com.rakesh.reactivespring.person.service;

import com.rakesh.reactivespring.person.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

//    private final PersonRepository personRepository;

    public Mono<Person> createPerson(Person person) {
        return Mono.just(person);
    }

    public Mono<Person> findById(Long id) {
        Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");
        person.setBirthDate(LocalDate.now());
        return Mono.just(person);
    }
}
