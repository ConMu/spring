package netease.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import netease.test.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author dujun01
 * @date 2022/5/31  21:57
 * @since
 */
@Repository
@Mapper
public interface AppDao extends BaseMapper<AppEntity> {
}
