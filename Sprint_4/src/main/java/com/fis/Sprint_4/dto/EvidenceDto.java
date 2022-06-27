package com.fis.Sprint_4.dto;

import lombok.Data;

@Data
public class EvidenceDto extends AbstractEntityDto {
    private Long criminalCase;
    private Long storage;
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived = false;

}
