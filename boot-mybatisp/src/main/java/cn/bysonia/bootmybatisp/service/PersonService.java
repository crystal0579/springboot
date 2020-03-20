package cn.bysonia.bootmybatisp.service;

import cn.bysonia.bootmybatisp.model.Person;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sonia
 * @since 2020-02-28
 */
public interface PersonService extends IService<Person> {
    public Page<Person> selectPageOwn(Page<Person> page, Map<String,Object> params);
}
