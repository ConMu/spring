package netease.rcstest.controller;

import com.alibaba.druid.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import netease.rcstest.sdkparams.RcsAccessTokenUnicom5GVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author mucongcong
 * @date 2022/06/30 10:15
 * @since
 **/
@RestController
@RequestMapping("/demo")
@SpringBootApplication
@Slf4j
class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private List<Foo> foos = new CopyOnWriteArrayList<>();

    @GetMapping("/list")
    public ResponseEntity list(@RequestParam(value = "name", required = false) String name) {
        log.info("accept a list request...");
        boolean emptyQuery = StringUtils.isEmpty(name);
        return ResponseEntity
                .ok(foos.stream().filter(i -> emptyQuery || i.getName().equals(name)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody RcsAccessTokenUnicom5GVO foo) {
        log.info("accept create request,foo:{}", foo);
        // uuid
//        foo.setId(UUID.randomUUID().toString());
        // add
//        foos.add(foo);
        log.info("appId: " + foo.getAppId());
        log.info("appKey " + foo.getAppKey());

        return ResponseEntity.ok(foo);
    }

    @GetMapping("/error")
    public ResponseEntity error() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
    }

    @Data
    @AllArgsConstructor
    public static class Foo {
        private String id;
        private String name;
        private int age;
    }
}
