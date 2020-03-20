package cn.bysonia.bootmybatisp.mapper;

import cn.bysonia.bootmybatisp.model.Person;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sonia
 * @since 2020-02-28
 */
public interface PersonMapper extends BaseMapper<Person> {
    public List<Person> selectPageOwn(Page<Person> page, Map<String,Object> params);
}
