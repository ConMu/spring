package netease.smstest.mapper;


import netease.smstest.domain.ChannelProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mucongcong
 * @date 2022/07/12 14:09
 * @since
 **/
@Mapper
public interface ChannelProviderMapper {

    int delete(Long providerId);

    List<ChannelProvider> selectAll(Integer limit, Integer offset);

    ChannelProvider selectByProviderId(Long providerId);

    ChannelProvider selectByProviderName(String providerName);

    int insert(ChannelProvider channelProvider);

    int update(ChannelProvider channelProvider);
}
