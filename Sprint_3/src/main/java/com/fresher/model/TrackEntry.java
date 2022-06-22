package com.fresher.model;

import com.fresher.core.TrackAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TrackEntry")
public class TrackEntry extends AbstractEntity {
    protected LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;
    @ManyToOne
    @JoinColumn(name = "detective_id")
    private Detective detective;
    @Enumerated(EnumType.STRING)
    private TrackAction action;

    private String reason;

    public TrackEntry() {
        super();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    public TrackAction getAction() {
        return action;
    }

    public void setAction(TrackAction action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}