package netease.smstest.mapper;


import netease.smstest.domain.NimSmsReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mucongcong
 * @date 2022/07/12 14:09
 * @since
 **/
@Mapper
public interface NimSmsReportMapper {

    List<NimSmsReport> selectAll(Integer limit, Integer offset);

    NimSmsReport selectByNimReport(String nimReport);

    int deleteByNimReport(String nimReport);

    int update(NimSmsReport nimSmsReport);

    int insert(NimSmsReport nimSmsReport);

}
