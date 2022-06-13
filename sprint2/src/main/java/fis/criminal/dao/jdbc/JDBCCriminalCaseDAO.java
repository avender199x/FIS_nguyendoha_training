package fis.criminal.dao.jdbc;

import fis.criminal.dao.ICriminalCaseDAO;
import fis.criminal.model.CriminalCase;

import fis.criminal.model.enums.CaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.*;

public class JDBCCriminalCaseDAO implements ICriminalCaseDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCCriminalCaseDAO.class);


    @Override
    public void save(CriminalCase criminalCase) {
        try {
            Connection connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO criminal_case" +
                                    "(created_at,detail_description,modified_at,notes,number,short_description,status,type,lead_investigator_one)" +
                                    "VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(criminalCase.getCreatedAt()));
            preparedStatement.setString(2, criminalCase.getDetailedDescription());
            preparedStatement.setString(3, String.valueOf(criminalCase.getModifiedAt()));
            preparedStatement.setString(4, criminalCase.getNotes());
            preparedStatement.setString(5, criminalCase.getNumber());
            preparedStatement.setString(6, criminalCase.getShortDescription());
            preparedStatement.setString(7, String.valueOf(criminalCase.getStatus()));
            preparedStatement.setString(8, String.valueOf(criminalCase.getType()));
            preparedStatement.setObject(9, criminalCase.getLeadInvestigator());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                throw new RuntimeException("loi tao database !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CriminalCase> get(long id) {
        Optional<CriminalCase> findbyid =
                getAll().stream().filter(criminalCase -> criminalCase.getId() == id).findFirst();
        return findbyid;
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
                throw new RuntimeException("loi update database !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            int check = preparedStatement3.executeUpdate();
            if (check == 0) {
                throw new RuntimeException("loi xoa database !!!");
            }
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
            if (check == 0) {
                throw new RuntimeException("loi xoa database !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
