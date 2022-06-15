package fis.criminal.dao.jdbc;

import fis.criminal.dao.ICriminalCaseDAO;
import fis.criminal.model.CriminalCase;

import fis.criminal.model.enums.CaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.*;

public class JDBCCriminalCaseDAO implements ICriminalCaseDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCCriminalCaseDAO.class);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void save(CriminalCase criminalCase) {
        try {
            Connection connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO criminal_case" +
                                    "(created_at,detail_description,modified_at,notes,number,short_description,status,type,lead_investigator_one)" +
                                    "VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setTimestamp(1, Timestamp.valueOf(criminalCase.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setString(2, criminalCase.getDetailedDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(criminalCase.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(4, criminalCase.getNotes().toLowerCase(Locale.ROOT));
            preparedStatement.setString(5, criminalCase.getNumber());
            preparedStatement.setString(6, criminalCase.getShortDescription());
            preparedStatement.setString(7, String.valueOf(criminalCase.getStatus()));
            preparedStatement.setString(8, String.valueOf(criminalCase.getType()));
            preparedStatement.setObject(9, criminalCase.getLeadInvestigator());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("Loi tao db");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<CriminalCase> get(long id) {
        return getAll().stream().filter(criminalCase -> criminalCase.getId() == id).findFirst();
    }

    @Override
    public List<CriminalCase> getAll() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM criminal_case");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CriminalCase criminalCase = DatabaseMapper.getCriminalCase(rs);
                logger.debug(criminalCase.toString());

                if (criminalCase != null) criminalCases.add(criminalCase);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return criminalCases;
    }

    @Override
    public void update(CriminalCase criminalCase) {
        try {
            Connection connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement1 =
                    connection.prepareStatement(
                            "UPDATE criminal_case SET created_at=?,detail_description=?," +
                                    "modified_at=?,notes=?,number=?,short_description=?,status=?,type=?" +
                                    ",lead_investigator_one=? WHERE id=?");
            preparedStatement1.setString(1, String.valueOf(criminalCase.getCreatedAt()));
            preparedStatement1.setString(2, criminalCase.getDetailedDescription());
            preparedStatement1.setString(3, String.valueOf(criminalCase.getModifiedAt()));
            preparedStatement1.setString(4, criminalCase.getNotes());
            preparedStatement1.setString(5, criminalCase.getNumber());
            preparedStatement1.setString(6, criminalCase.getShortDescription());
            preparedStatement1.setString(7, String.valueOf(criminalCase.getStatus()));
            preparedStatement1.setString(8, String.valueOf(criminalCase.getType()));
            preparedStatement1.setObject(9, criminalCase.getLeadInvestigator());
            preparedStatement1.setInt(10, (int) criminalCase.getId());
            int check = preparedStatement1.executeUpdate();
            if (check == 0) {
                logger.info("loi update database !!!");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public void delete(CriminalCase criminalCase) {
        try {
            Connection connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement3 =
                    connection.prepareStatement(
                            "DELETE criminal_case WHERE id=?");
            preparedStatement3.setInt(1, (int) criminalCase.getId());
            preparedStatement3.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement3 =
                    connection.prepareStatement(
                            "DELETE criminal_case WHERE id=?");
            preparedStatement3.setInt(1, (int) id);
            int check = preparedStatement3.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
