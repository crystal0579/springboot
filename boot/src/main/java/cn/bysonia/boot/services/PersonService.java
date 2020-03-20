package cn.bysonia.boot.services;

import cn.bysonia.boot.dto.PersonDto;

public interface PersonService {

    int addPerson(PersonDto personDto);
    PersonDto getPersonById(Integer id);
}
