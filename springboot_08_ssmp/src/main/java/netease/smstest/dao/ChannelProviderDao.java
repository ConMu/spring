package netease.smstest.dao;


import netease.smstest.domain.ChannelProvider;
import netease.smstest.mapper.ChannelProviderMapper;
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
public class ChannelProviderDao {
    @Autowired
    ChannelProviderMapper channelProviderMapper;

    @Caching(evict = {
            @CacheEvict(key = "'sms_provider_'.concat(#providerId)")
    })
    public int delete(Long providerId) {
        return channelProviderMapper.delete(providerId);
    }

    public List<ChannelProvider> getAll( Integer limit, Integer offset) {
        return channelProviderMapper.selectAll(limit, offset);
    }

    @Cacheable(key = "'sms_provider_'.concat(#providerId)")
    public ChannelProvider getByProviderId(Long providerId) {
        return channelProviderMapper.selectByProviderId(providerId);
    }

    public ChannelProvider getByProviderName(String providerName) {
        return channelProviderMapper.selectByProviderName(providerName);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_provider_'.concat(#channelProvider.providerId)")
    })
    public int add(ChannelProvider channelProvider) {
        return channelProviderMapper.insert(channelProvider);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_provider_'.concat(#channelProvider.providerId)")
    })
    public int update(ChannelProvider channelProvider) {
        return channelProviderMapper.update(channelProvider);
    }
}