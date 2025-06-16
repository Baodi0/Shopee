package com.example.demo.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@SuppressWarnings("serial")
@PrimaryKeyClass
public class DonHangKey implements Serializable {


	@PrimaryKeyColumn(name = "iddonhang", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID iddonhang;

    @PrimaryKeyColumn(name = "idnguoidung", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String idnguoidung;
    public DonHangKey(UUID iddonhang, String idnguoidung) {
    	this.iddonhang = iddonhang;
    	this.idnguoidung = idnguoidung;
	}
}
