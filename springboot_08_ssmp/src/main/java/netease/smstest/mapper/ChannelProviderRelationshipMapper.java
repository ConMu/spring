package netease.smstest.mapper;


import netease.smstest.domain.ChannelProviderRelationship;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mucongcong
 * @date 2022/07/12 14:09
 * @since
 **/
@Mapper
public interface ChannelProviderRelationshipMapper {

    List<ChannelProviderRelationship> selectAll(Integer limit, Integer offset);

    List<ChannelProviderRelationship> selectByProviderId(Long providerId);

    ChannelProviderRelationship selectByChannelid(Long channelid);

    int delete(Long channelid);

    int insert(ChannelProviderRelationship channelProviderRelationship);


    int update(ChannelProviderRelationship channelProviderRelationship);

}
