package netease.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.extern.slf4j.Slf4j;
import netease.test.param.ao.AppAo;
import netease.test.param.bo.AppBo;
import netease.test.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * 应用controller
 */

@Slf4j
@Controller
@RequestMapping("/acd/apps")
public class AppController {
    @Autowired
    AppService appService;

    /**
     * @Description 添加应用
     *
     * @Param [content]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @PostMapping
    @ResponseBody
    public Response<Void> createApp(@RequestBody AppAo appAo) {
        appService.createApp(appAo);
        return null;
    }

    /**
     * @Description 编辑应用
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @PutMapping
    @ResponseBody
    public Response<Void> editApp( @RequestBody AppAo appAo) {
        appService.editAppByAppId(appAo);
        return null;
    }

    /**
     * @Description 删除应用
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @DeleteMapping("{appId}")
    @ResponseBody
    public Response<Void> deleteApp(@PathVariable String appId) {
        appService.deleteAppByAppId(appId);
        return null;
    }


    @GetMapping("{currentPage}/{pageSize}")
    @ResponseBody
    public IPage<AppBo> getAppListPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody AppAo appAo) {
        IPage<AppBo> appBos = appService.getAppListPage(currentPage, pageSize, appAo);
        return appBos;
    }

    /**
     * @Description 根据某些属性删除应用
     *
     * @Param [appId]
     * @return com.netease.we.voip.bs.tenant.common.constans.web.Response<java.lang.Void>
     **/
    @DeleteMapping
    @ResponseBody
    public Response<Void> deleteAppByParam(@RequestBody AppAo appAo) {
        appService.deleteAppByParam(appAo);
        return null;
    }
}
