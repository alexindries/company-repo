package com.example.company.dao;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Table
@Entity
public class CompanyEntity {
    @GeneratedValue
    @Id
    private int id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private String activitySector;

    public Company toPojo(){
        Company pojo = new Company();
        pojo.setName(name);
        pojo.setActivitySector(activitySector);
        return pojo;
    }

    public CompanyEntity update(Company pojo){
        this.name = pojo.getName();
        this.activitySector = pojo.getActivitySector();
        return this;
    }
}
