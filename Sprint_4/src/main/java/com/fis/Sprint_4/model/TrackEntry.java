package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fis.Sprint_4.core.TrackAction;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Track_Entry")
public class TrackEntry extends AbstractEntity {
    protected LocalDateTime date;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "detective_id")
    private Detective detective;
    @Enumerated(EnumType.STRING)
    private TrackAction action;
    private String reason;
}