package com.fresher.repository.jdbctemplate.mapper;

import com.fresher.core.CaseStatus;
import com.fresher.core.CaseType;
import com.fresher.model.CriminalCase;
import com.fresher.model.Detective;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalCaseRowMapper implements RowMapper<CriminalCase> {

    @Override
    public CriminalCase mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("ID");
        String number = rs.getString("CASE_NUMBER");
        String type = rs.getString("CASE_TYPE");
        String status = rs.getString("STATUS");
        String shortDescription = rs.getString("SHORT_DESCRIPTION");
        long detectiveId =  rs.getLong("LEAD_INVESTIGATOR_ID");

        CriminalCase cc = new CriminalCase();
        cc.setId(id);
        cc.setNumber(number);
        cc.setType(CaseType.valueOf(type));
        cc.setStatus(CaseStatus.valueOf(status));
        cc.setShortDescription(shortDescription);

        Detective detective = new Detective();
        detective.setId(detectiveId);
        cc.setLeadInvestigator(detective);
        return cc;
    }
}
