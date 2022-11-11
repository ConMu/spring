package netease.smstest.dao;


import netease.smstest.domain.NimSmsReport;
import netease.smstest.mapper.NimSmsReportMapper;
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
public class NimSmsReportDao {

    @Autowired
    NimSmsReportMapper nimSmsReportMapper;

    public List<NimSmsReport> getAll(Integer limit, Integer offset) {
        return nimSmsReportMapper.selectAll(limit, offset);
    }

    @Cacheable(key = "'sms_nimreport_'.concat(#nimReport)")
    public NimSmsReport getByNimReport(String nimReport) {
        return nimSmsReportMapper.selectByNimReport(nimReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_nimreport_'.concat(#nimReport)")
    })
    public int delete(String nimReport) {
        return nimSmsReportMapper.deleteByNimReport(nimReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_nimreport_'.concat(#nimSmsReport.nimReport)")
    })
    public int update(NimSmsReport nimSmsReport) {
        return nimSmsReportMapper.update(nimSmsReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_nimreport_'.concat(#nimSmsReport.nimReport)")
    })
    public int add(NimSmsReport nimSmsReport) {
        return nimSmsReportMapper.insert(nimSmsReport);
    }
}
