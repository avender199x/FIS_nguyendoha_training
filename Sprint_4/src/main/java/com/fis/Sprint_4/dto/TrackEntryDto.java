package com.fis.Sprint_4.dto;

import com.fis.Sprint_4.core.TrackAction;
import com.fis.Sprint_4.model.Detective;
import com.fis.Sprint_4.model.Evidence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackEntryDto extends AbstractEntityDto {
    protected LocalDateTime date;
    private Long evidence;
    private Long detective;
    private TrackAction action;
    private String reason;
}
