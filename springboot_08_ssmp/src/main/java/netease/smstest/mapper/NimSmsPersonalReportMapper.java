package netease.smstest.mapper;


import netease.smstest.domain.NimSmsPersonalReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mucongcong
 * @date 2022/07/12 14:09
 * @since
 **/
@Mapper
public interface NimSmsPersonalReportMapper {


    List<NimSmsPersonalReport> selectAll(@Param("appId") Long appid, @Param("limit") Integer limit, @Param("offset") Integer offset);

    NimSmsPersonalReport selectByAppidAndNimReport(@Param("appId") Long appid, @Param("nimReport") String nimReport);

    NimSmsPersonalReport selectByAppidAndPersonalReport(@Param("appId") Long appid, @Param("personalReport") String personalReport);

    int insert(NimSmsPersonalReport nimSmsPersonalReport);

    int update(NimSmsPersonalReport nimSmsPersonalReport);

    int delete(@Param("appId")Long appid, @Param("nimReport") String nimReport);

    List<NimSmsPersonalReport> selectByNimReport(@Param("nimReport") String nimReport, int limit, int offset);

}
