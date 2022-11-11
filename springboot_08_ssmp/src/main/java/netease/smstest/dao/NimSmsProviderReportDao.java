package netease.smstest.dao;


import netease.smstest.domain.NimSmsProviderReport;
import netease.smstest.mapper.NimSmsProviderReportMapper;
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
 * @date 2022/10/10 16:59
 * @since
 **/
@Component
@CacheConfig(cacheNames = PetuniaCacheName.REMOTE_DAY_1_CACHE_NULL)
public class NimSmsProviderReportDao {

    @Autowired
    NimSmsProviderReportMapper nimSmsProviderReportMapper;

    public List<NimSmsProviderReport> getAllByProviderId(Long providerId, Integer limit, Integer offset) {
        return nimSmsProviderReportMapper.selectByProviderId(providerId, limit, offset);
    }

    @Cacheable(key = "'sms_provider_report_'.concat(#providerId).concat('_').concat(#report)")
    public NimSmsProviderReport getByProviderIdAndReport( Long providerId, String report) {
        return nimSmsProviderReportMapper.selectByProviderIdAndReport(providerId, report);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_provider_report_'.concat(#nimSmsProviderReport.providerId).concat('_').concat(#nimSmsProviderReport.report)")
    })
    public int add(NimSmsProviderReport nimSmsProviderReport) {
        return nimSmsProviderReportMapper.insert(nimSmsProviderReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_provider_report_'.concat(#providerId).concat('_').concat(#report)")
    })
    public int delete(Long providerId, String report) {
        return nimSmsProviderReportMapper.delete(providerId, report);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_provider_report_'.concat(#nimSmsProviderReport.providerId).concat('_').concat(#nimSmsProviderReport.report)")
    })
    public int update(NimSmsProviderReport nimSmsProviderReport) {
        return nimSmsProviderReportMapper.update(nimSmsProviderReport);
    }

    public List<NimSmsProviderReport> getByNimReport(String nimReport, int limit, int offset) {
        return nimSmsProviderReportMapper.selectByNimReport(nimReport, limit, offset);
    }
}

