package com.conmu.utils;

import com.conmu.domain.ChinaGeo;
import com.conmu.domain.WorldGeo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * @author mucongcong
 * @date 2022/11/01 20:20
 * @since
 **/
public class JsonReadUtils {
    public static void readDom() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("D:\\projects\\gitproject\\spring\\springboot_08_ssmp\\src\\main\\resources\\china.json"));
            Gson gson = new GsonBuilder().create();
            ChinaGeo[] people = gson.fromJson(reader, ChinaGeo[].class);
            System.out.println("Object mode: " + people[0].getName());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("==");
        }
    }

    public static void main(String[] args) {
        readDom();
    }
}
