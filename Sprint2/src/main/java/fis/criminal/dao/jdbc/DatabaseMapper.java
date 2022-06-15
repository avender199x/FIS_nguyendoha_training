package fis.criminal.dao.jdbc;

import fis.criminal.model.CriminalCase;

import fis.criminal.model.Detective;
import fis.criminal.model.Evidence;
import fis.criminal.model.enums.CaseStatus;
import fis.criminal.model.enums.CaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class DatabaseMapper {
    public static final Logger logger = LoggerFactory.getLogger(DatabaseMapper.class);

    public static CriminalCase getCriminalCase(ResultSet criminalCaseResultSet) {
        try {
            CriminalCase criminalCase = new CriminalCase();
            criminalCase.setId(criminalCaseResultSet.getLong("id"));
            criminalCase.setNumber(criminalCaseResultSet.getString("number"));
            criminalCase.setType(criminalCaseResultSet.getString("type").toUpperCase());
            criminalCase.setShortDescription(criminalCaseResultSet.getString("short_description"));
            criminalCase.setDetailedDescription(criminalCaseResultSet.getString("detail_description"));
            criminalCase.setStatus(criminalCaseResultSet.getString("status").toUpperCase());
            criminalCase.setNotes(criminalCaseResultSet.getString("notes"));
            //TODO add some add.

            return criminalCase;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static Detective getDetective(ResultSet detectiveResultSet) {
        //TODO mapper
        return null;
    }

}
