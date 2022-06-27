package com.fis.Sprint_4.repository;

import com.fis.Sprint_4.model.TrackEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackEntryRepository extends JpaRepository<TrackEntry, Long> {
}
