package com.fis.Sprint_4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvidenceDto extends AbstractEntityDto {
    private Long criminalCase;
    private Long storage;
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived = false;

}
