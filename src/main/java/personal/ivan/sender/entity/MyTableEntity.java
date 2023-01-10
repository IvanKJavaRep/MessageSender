package personal.ivan.sender.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class MyTableEntity implements Serializable {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("object_name")
    String name;
    @JsonProperty("address")
    String address;
    @JsonProperty("creationTime")
    Timestamp creationTime = null;
    @JsonProperty("updateTime")
    Timestamp updateTime = null;

    public MyTableEntity(int id, String name, String address, Timestamp creation , Timestamp update) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        creationTime = creation;
        updateTime= update;
    }


}
