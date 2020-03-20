package com.bysonia.sprisecuritydb.service;

import com.bysonia.sprisecuritydb.model.Person;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sonia
 * @since 2020-03-06
 */
public interface PersonService extends IService<Person>, UserDetailsService {

}
