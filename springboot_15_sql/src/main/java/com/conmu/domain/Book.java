package com.conmu.domain;

import lombok.Data;

//lombok
//@Getter
//@Setter
@Data
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;

}
