package fis.criminal.dao.jdbc;

import fis.criminal.model.*;

import fis.criminal.model.enums.CaseStatus;
import fis.criminal.model.enums.CaseType;
import fis.criminal.model.enums.TrackAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

public class DatabaseMapper {
    public static final Logger logger = LoggerFactory.getLogger(DatabaseMapper.class);

    public static CriminalCase getCriminalCase(ResultSet criminalCaseResultSet) {
        try {
            CriminalCase criminalCase = new CriminalCase();
            criminalCase.setId(criminalCaseResultSet.getLong("id"));
            criminalCase.setCreatedAt(criminalCaseResultSet.getTimestamp("created_at").toLocalDateTime());
            criminalCase.setDetailedDescription(criminalCaseResultSet.getString("detail_description"));
            criminalCase.setModifiedAt(criminalCaseResultSet.getTimestamp("modified_at").toLocalDateTime());
            criminalCase.setNotes(criminalCaseResultSet.getString("notes"));
            criminalCase.setNumber(criminalCaseResultSet.getString("number"));
            criminalCase.setShortDescription(criminalCaseResultSet.getString("short_description"));
            criminalCase.setStatus(criminalCaseResultSet.getString("status").toUpperCase());
            criminalCase.setType(criminalCaseResultSet.getString("type").toUpperCase());
            return criminalCase;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static Detective getDetective(ResultSet detectiveResultSet) {
        try {
            Detective detective = new Detective();
            detective.setId(detectiveResultSet.getLong("id"));
            detective.setArmed(detectiveResultSet.getBoolean("armed"));
            detective.setBadgeNumber(detectiveResultSet.getString("badgetnumber"));
            detective.setCreatedAt(detectiveResultSet.getTimestamp("created_at").toLocalDateTime());
            detective.setModifiedAt(detectiveResultSet.getTimestamp("modified_at").toLocalDateTime());
            detective.setRank(detectiveResultSet.getString("rank"));
            detective.setStatus(detectiveResultSet.getString("status"));
            return detective;
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public static Evidence getEvidence(ResultSet evidenceResultSet) {
        try {
            Evidence evidence = new Evidence();
            evidence.setId(evidenceResultSet.getLong("id"));
            evidence.setArchived(evidenceResultSet.getBoolean("archived"));
            evidence.setCreatedAt(evidenceResultSet.getTimestamp("created_at").toLocalDateTime());
            evidence.setItemName(evidenceResultSet.getString("item_name"));
            evidence.setModifiedAt(evidenceResultSet.getTimestamp("modified_at").toLocalDateTime());
            evidence.setNotes(evidenceResultSet.getString("notes"));
            evidence.setNumber(evidenceResultSet.getString("number"));
            return evidence;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    public static Storage getStorage(ResultSet storageResultSet) {
        try {
            Storage storage = new Storage();
            storage.setId(storageResultSet.getLong("id"));
            storage.setCreatedAt(storageResultSet.getTimestamp("created_at").toLocalDateTime());
            storage.setLocation(storageResultSet.getString("location"));
            storage.setModifiedAt(storageResultSet.getTimestamp("modified_at").toLocalDateTime());
            storage.setName(storageResultSet.getString("name"));
            return storage;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    public static TrackEntry getTrackEntry(ResultSet trackEntryResultSet) {
        try {
            TrackEntry trackEntry = new TrackEntry();
            trackEntry.setId(trackEntryResultSet.getLong("id"));
            trackEntry.setAction(TrackAction.valueOf(trackEntryResultSet.getString("action")));
            trackEntry.setCreatedAt(trackEntryResultSet.getTimestamp("created_at").toLocalDateTime());
            trackEntry.setDate(trackEntryResultSet.getTimestamp("date").toLocalDateTime());
            trackEntry.setModifiedAt(trackEntryResultSet.getTimestamp("modified_at").toLocalDateTime());
            trackEntry.setResson(trackEntryResultSet.getString("resson"));
            return trackEntry;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

}
