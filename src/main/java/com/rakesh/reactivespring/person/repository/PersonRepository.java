package com.rakesh.reactivespring.person.repository;

import com.rakesh.reactivespring.person.model.Person;
import reactor.core.publisher.Mono;

//@Repository
public interface PersonRepository {
    Mono<Person> save(Person person);
    Mono<Person> findById(Long id);
}
