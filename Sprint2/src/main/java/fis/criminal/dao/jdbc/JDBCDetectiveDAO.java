package fis.criminal.dao.jdbc;

import fis.criminal.dao.IDetectiveDAO;
import fis.criminal.model.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDetectiveDAO implements IDetectiveDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCDetectiveDAO.class);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void save(Detective detective) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO detective" +
                                    "(armed,badgetnumber,created_at,modified_at,rank,status,person_one)" +
                                    "VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setBoolean(1, detective.getArmed());
            preparedStatement.setString(2, detective.getBadgeNumber());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(detective.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(detective.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(5, detective.getRank());
            preparedStatement.setString(6, detective.getStatus());
            preparedStatement.setLong(7, detective.getPerson().getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("loi tao database !!!");
            } else {
                logger.info("tao thanh cong");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Detective> get(long id) {
        Optional<Detective> findById = getAll().stream().filter(detective -> detective.getId() == id).findFirst();
        return findById;
    }

    @Override
    public List<Detective> getAll() {
        List<Detective> detectives = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM detective");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Detective detective = DatabaseMapper.getDetective(rs);
                if (detective != null) detectives.add(detective);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return detectives;
    }

    @Override
    public void update(Detective detective) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE detective SET armed=?,badgetnumber=?,created_at=?,modified_at=?,rank=?,status=?,person_one=? WHERE id=?");
            preparedStatement.setBoolean(1, detective.getArmed());
            preparedStatement.setString(2, detective.getBadgeNumber());
            preparedStatement.setString(3, String.valueOf(detective.getCreatedAt()));
            preparedStatement.setString(4, String.valueOf(detective.getModifiedAt()));
            preparedStatement.setString(5, detective.getRank());
            preparedStatement.setString(6, detective.getStatus());
            preparedStatement.setLong(7, detective.getPerson().getId());
            preparedStatement.setLong(8, detective.getId());
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
    public void delete(Detective detective) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DELETE detective WHERE id=?");
            preparedStatement.setLong(1, detective.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
