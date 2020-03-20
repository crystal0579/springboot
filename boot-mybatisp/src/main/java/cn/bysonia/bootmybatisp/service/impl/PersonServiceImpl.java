package cn.bysonia.bootmybatisp.service.impl;

import cn.bysonia.bootmybatisp.model.Person;
import cn.bysonia.bootmybatisp.mapper.PersonMapper;
import cn.bysonia.bootmybatisp.service.PersonService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sonia
 * @since 2020-02-28
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    public Page<Person> selectPageOwn(Page<Person> page, Map<String,Object> params){
        List<Person> persons = baseMapper.selectPageOwn(page, params);
        return page.setRecords(persons);//这时候有了size, total,pages的值
    }
}
