package com.conmu.execute;

import com.conmu.domain.Book;
import com.conmu.service.BookService;
import com.conmu.utils.GeoUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mucongcong
 * @date 2022/10/20 11:07
 * @since
 **/
@Component
public class BookExecute {

    public static BookExecute instance = null;

    private BookExecute(){}

    public static synchronized BookExecute getInstance() {
        if (instance == null) {
            instance = new BookExecute();
        }
        return instance;
    }


    @Autowired
    private BookService bookService;

    public static Map<Integer, String> worldGeoMap;

    public static Map<String, String> chinaGeoMap;

    public static Map<Integer, String> worldNameMap;

    private final static String WORLD_GEO_PATH = "world.json";

    private final static String CHINA_GEO_PATH = "china.json";

    public static List<Book> books;

    @PostConstruct
    public void getBooks() throws IOException {
        books = bookService.getAll();
    }

    @PostConstruct
    public void initWorldGeo() throws IOException {
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new ClassPathResource(WORLD_GEO_PATH).getFile();
            read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(read);
            Gson gson = new GsonBuilder().create();
            worldGeoMap = Arrays.asList(gson.fromJson(bufferedReader, WorldGeo[].class)).
                    stream().collect(Collectors.toMap(WorldGeo::getCode, WorldGeo::convertGeoHash,(entity1, entity2) -> entity1));
        } finally {
            if (read != null) {
                read.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    @PostConstruct
    public void initWorldName() throws IOException {
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new ClassPathResource(WORLD_GEO_PATH).getFile();
            read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(read);
            Gson gson = new GsonBuilder().create();
            worldNameMap = Arrays.asList(gson.fromJson(bufferedReader, WorldGeo[].class)).
                    stream().collect(Collectors.toMap(WorldGeo::getCode, WorldGeo::getZh,(entity1, entity2) -> entity1));
        } finally {
            if (read != null) {
                read.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    @PostConstruct
    public void initChinaGeo() throws IOException {
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new ClassPathResource(CHINA_GEO_PATH).getFile();
            read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(read);
            Gson gson = new GsonBuilder().create();
            chinaGeoMap = Arrays.asList(gson.fromJson(bufferedReader, ChinaGeo[].class)).
                    stream().collect(Collectors.toMap(ChinaGeo::getName, ChinaGeo::convertGeoHash,(entity1, entity2) -> entity1));
        } finally {
            if (read != null) {
                read.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    private class WorldGeo {
        private int code;

        private String zh;

        private double lat;

        private double lng;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String convertGeoHash() {
            return GeoUtils.encode(this.lat, this.lng);
        }
    }

    private class ChinaGeo{
        private String name;

        private double[] gps;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double[] getGps() {
            return gps;
        }

        public void setGps(double[] gps) {
            this.gps = gps;
        }

        public String convertGeoHash() {
            return GeoUtils.encode(this.gps[0], this.gps[1]);
        }
    }
}
