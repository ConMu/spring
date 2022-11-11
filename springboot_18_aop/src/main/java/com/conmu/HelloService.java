package com.conmu;

import com.conmu.aspect.Log;
import org.springframework.stereotype.Component;

/**
 * @author mucongcong
 * @date 2022/08/25 15:45
 * @since
 **/
@Component
public class HelloService {

    @Log
    public void sayHi(String msg) {
        System.out.println("\tsayHi:" + msg);
    }

    public void anotherSayHi(String msg) {
        this.sayHi(msg);
    }

}
