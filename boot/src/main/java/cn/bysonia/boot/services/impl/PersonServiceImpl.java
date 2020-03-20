package cn.bysonia.boot.services.impl;

import cn.bysonia.boot.dao.PersonDao;
import cn.bysonia.boot.dto.PersonDto;
import cn.bysonia.boot.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional(rollbackFor = Exception.class)
public class PersonServiceImpl implements PersonService {
//    @Autowired
    private PersonDao personDao;

    @Override
    public int addPerson(PersonDto personDto) {
        return personDao.addPerson(personDto);
    }

    @Override
    public PersonDto getPersonById(Integer id) {
        return personDao.getPersonById(id);
    }
}
