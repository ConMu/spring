package netease.smstest.mapper;


import netease.smstest.domain.NimSmsProviderReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mucongcong
 * @date 2022/07/12 14:09
 * @since
 **/
@Mapper
public interface NimSmsProviderReportMapper {


    List<NimSmsProviderReport> selectByProviderId(Long providerId, Integer limit, Integer offset);

    NimSmsProviderReport selectByProviderIdAndReport(Long providerId, String report);

    int insert(NimSmsProviderReport nimSmsProviderReport);

    int delete(Long providerId, String report);

    int update(NimSmsProviderReport nimSmsProviderReport);

    List<NimSmsProviderReport> selectByNimReport(String nimReport, int limit, int offset);
}
