package com.fis.Sprint_4.dto;

import com.fis.Sprint_4.core.CaseStatus;
import com.fis.Sprint_4.core.CaseType;
import com.fis.Sprint_4.model.Detective;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
public class CriminalCaseDto extends AbstractEntityDto {
    private String number;
    @Enumerated(EnumType.STRING)
    private CaseType type;
    private String shortDescription;
    private String detailedDescription;
    @Enumerated(EnumType.STRING)
    private CaseStatus status;
    private String notes;
    private Long leadInvestigator;
    private Long assigned;
}
