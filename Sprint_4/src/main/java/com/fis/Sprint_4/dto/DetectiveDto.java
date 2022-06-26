package com.fis.Sprint_4.dto;

import com.fis.Sprint_4.core.EmploymentStatus;
import com.fis.Sprint_4.core.Rank;
import lombok.Data;

import javax.persistence.*;

@Data
public class DetectiveDto extends AbstractEntityDto {

    private Long person;
    private String badgeNumber;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private Boolean armed = false;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status = EmploymentStatus.ACTIVE;
}
