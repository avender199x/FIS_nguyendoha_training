package com.fis.Sprint_4.dto;

import com.fis.Sprint_4.core.TrackAction;
import com.fis.Sprint_4.model.Detective;
import com.fis.Sprint_4.model.Evidence;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrackEntryDto extends AbstractEntityDto {
    protected LocalDateTime date;
    private Long evidence;
    private Long detective;
    private TrackAction action;
    private String reason;
}
