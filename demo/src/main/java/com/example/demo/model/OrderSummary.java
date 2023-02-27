package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderSummary {
    @Id
    @GeneratedValue
    private Long Id;
    private Integer order;
    private int level;
    private String code;
    private String parent;
    private String description;
    @Lob
    private String thisItemIncludes;
    @Lob
    private String thisItemAlsoIncludes;
    @Lob
    private String rulings;
    @Lob
    private String thisItemExcludes;
    private String referenceToISICRev4;
    public OrderSummary(Integer order, int level, String code,String parent, String description, String thisItemIncludes, String thisItemAlsoIncludes,
                        String rulings, String thisItemExcludes, String referenceToISICRev4) {
        this.order = order;
        this.level = level;
        this.code = code;
        this.parent = parent;
        this.description = description;
        this.thisItemIncludes = thisItemIncludes;
        this.thisItemAlsoIncludes = thisItemAlsoIncludes;
        this.rulings = rulings;
        this.thisItemExcludes = thisItemExcludes;
        this.referenceToISICRev4 = referenceToISICRev4;
    }


}