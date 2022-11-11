package netease.smstest.dao;


import netease.smstest.domain.ChannelProviderRelationship;
import netease.smstest.mapper.ChannelProviderRelationshipMapper;
import netease.smstest.util.PetuniaCacheName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mucongcong
 * @date 2022/10/10 16:58
 * @since
 **/
@Component
@CacheConfig(cacheNames = PetuniaCacheName.REMOTE_DAY_1_CACHE_NULL)
public class ChannelProviderRelationshipDao {

    @Autowired
    ChannelProviderRelationshipMapper channelProviderRelationshipMapper;

    public List<ChannelProviderRelationship> getAll(Integer limit, Integer offset) {
        return channelProviderRelationshipMapper.selectAll(limit, offset);
    }

    public List<ChannelProviderRelationship> getByProviderId(Long providerId) {
        return channelProviderRelationshipMapper.selectByProviderId(providerId);
    }

    @Cacheable(key = "'sms_channel_provider'.concat(#channelid)")
    public ChannelProviderRelationship getByChannelId(Long channelid) {
        return channelProviderRelationshipMapper.selectByChannelid(channelid);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_channel_provider'.concat(#channelid)")
    })
    public int delete(Long channelid) {
        return channelProviderRelationshipMapper.delete(channelid);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_channel_provider'.concat(#channelProviderRelationship.channelId)")
    })
    public int add(ChannelProviderRelationship channelProviderRelationship) {
        return channelProviderRelationshipMapper.insert(channelProviderRelationship);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_channel_provider'.concat(#channelProviderRelationship.channelId)")
    })
    public int update(ChannelProviderRelationship channelProviderRelationship) {
        return channelProviderRelationshipMapper.update(channelProviderRelationship);
    }

}
