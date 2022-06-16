package fis.criminal.dao.jdbc;

import fis.criminal.dao.IStorageDAO;
import fis.criminal.model.Detective;
import fis.criminal.model.Storage;
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

public class JDBCStorageDAO implements IStorageDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCStorageDAO.class);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void save(Storage storage) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storage (created_at,location,modified_at,name) VALUES (?,?,?,?)");
            preparedStatement.setTimestamp(1, Timestamp.valueOf(storage.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setString(2, storage.getLocation());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(storage.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(4, storage.getName());
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
    public Optional<Storage> get(long id) {
        return getAll().stream().filter(storage -> storage.getId() == id).findFirst();
    }

    @Override
    public List<Storage> getAll() {
        List<Storage> storages = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM storage");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Storage storage = DatabaseMapper.getStorage(rs);
                if (storage != null) storages.add(storage);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return storages;
    }

    @Override
    public void update(Storage storage) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE storage SET created_at=?,location=?,modified_at=?,name=? WHERE id=?");
            preparedStatement.setTimestamp(1, Timestamp.valueOf(storage.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setString(2, storage.getLocation());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(storage.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(4, storage.getName());
            preparedStatement.setLong(5, storage.getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("db ko thay doi !!!");
            } else {
                logger.info("cap nhat thanh cong");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public void delete(Storage storage) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DELETE storage WHERE id=?");
            preparedStatement.setLong(1, storage.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
