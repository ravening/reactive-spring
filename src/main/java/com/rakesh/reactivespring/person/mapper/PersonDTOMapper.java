package com.rakesh.reactivespring.person.mapper;

import com.rakesh.reactivespring.person.model.Person;
import com.rakesh.reactivespring.person.model.PersonDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonDTOMapper {
    Person toDomain(PersonDTO personDTO);
    PersonDTO toDTO(Person person);
}
