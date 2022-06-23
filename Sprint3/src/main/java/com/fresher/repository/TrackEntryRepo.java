package com.fresher.repository;

import com.fresher.core.TrackAction;
import com.fresher.model.TrackEntry;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TrackEntryRepo extends AbstractRepo<TrackEntry> {
    public List<TrackEntry> findAll();
}
