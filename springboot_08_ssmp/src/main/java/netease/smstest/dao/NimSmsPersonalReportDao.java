package netease.smstest.dao;


import netease.smstest.domain.NimSmsPersonalReport;
import netease.smstest.mapper.NimSmsPersonalReportMapper;
import netease.smstest.util.PetuniaCacheName;
import org.apache.ibatis.annotations.Param;
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
public class NimSmsPersonalReportDao {
    @Autowired
    NimSmsPersonalReportMapper nimSmsPersonalReportMapper;

    public List<NimSmsPersonalReport> getAll(Long appid, Integer limit, Integer offset) {
        return nimSmsPersonalReportMapper.selectAll(appid, limit, offset);
    }

    @Cacheable(key = "'sms_personal_report_'.concat(#appid).concat('_').concat(#nimReport)")
    public NimSmsPersonalReport getByAppidAndNimReport(Long appid, String nimReport) {
        return nimSmsPersonalReportMapper.selectByAppidAndNimReport(appid, nimReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_personal_report_'.concat(#nimSmsPersonalReport.appid).concat('_').concat(#nimSmsPersonalReport.nimReport)")
    })
    public int add(NimSmsPersonalReport nimSmsPersonalReport) {
        return nimSmsPersonalReportMapper.insert(nimSmsPersonalReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_personal_report_'.concat(#nimSmsPersonalReport.appid).concat('_').concat(#nimSmsPersonalReport.nimReport)")
    })
    public int update(NimSmsPersonalReport nimSmsPersonalReport) {
        return nimSmsPersonalReportMapper.update(nimSmsPersonalReport);
    }

    @Caching(evict = {
            @CacheEvict(key = "'sms_personal_report_'.concat(#appid).concat('_').concat(#nimReport)")
    })
    public int delete(Long appid, String nimReport) {
        return nimSmsPersonalReportMapper.delete(appid, nimReport);
    }

    public List<NimSmsPersonalReport> getByNimReport(String nimReport, int limit, int offset) {
        return nimSmsPersonalReportMapper.selectByNimReport(nimReport, limit, offset);
    }
}

