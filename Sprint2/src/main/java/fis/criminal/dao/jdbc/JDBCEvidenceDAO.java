package fis.criminal.dao.jdbc;

import fis.criminal.dao.IEvidenceDAO;
import fis.criminal.model.Evidence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCEvidenceDAO implements IEvidenceDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCEvidenceDAO.class);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void save(Evidence evidence) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO " +
                                    "evidence(archived,created_at,item_name,modified_at,notes,number,criminal_case_one,storage_one)" +
                                    " VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setBoolean(1, evidence.getArchived());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(evidence.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setString(3, evidence.getItemName());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(evidence.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(5, evidence.getNotes());
            preparedStatement.setString(6, evidence.getNumber());
            preparedStatement.setLong(7, evidence.getCriminalCase().getId());
            preparedStatement.setLong(8, evidence.getStorage().getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("loi tao db");
            } else {
                logger.info("tao thanh cong");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Evidence> get(long id) {
        return getAll().stream().filter(evidence -> evidence.getId() == id).findFirst();
    }

    @Override
    public List<Evidence> getAll() {
        List<Evidence> evidences = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM evidence");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evidence evidence = DatabaseMapper.getEvidence(rs);
                if (evidence != null) evidences.add(evidence);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return evidences;
    }

    @Override
    public void update(Evidence evidence) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE evidence SET " +
                                    "archived=?,created_at=?,item_name=?,modified_at=?,notes=?,number=?,criminal_case_one=?,storage_one=? WHERE id=?");
            preparedStatement.setBoolean(1, evidence.getArchived());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(evidence.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setString(3, evidence.getItemName());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(evidence.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(5, evidence.getNotes());
            preparedStatement.setString(6, evidence.getNumber());
            preparedStatement.setInt(7, (int) evidence.getCriminalCase().getId());
            preparedStatement.setInt(8, (int) evidence.getStorage().getId());
            preparedStatement.setInt(9, (int) evidence.getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("db ko thay doi");
            } else {
                logger.info("cap nhat thanh cong");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

    @Override
    public void delete(Evidence evidence) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE evidence WHERE id=?");
            preparedStatement.setLong(1, evidence.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
