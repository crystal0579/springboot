package cn.bysonia.boot.dao;

import cn.bysonia.boot.dto.PersonDto;

public interface PersonDao {
    int addPerson(PersonDto personDto);
    PersonDto getPersonById(Integer id);
}
