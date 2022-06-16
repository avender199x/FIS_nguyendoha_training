package fis.criminal.dao.jdbc;

import fis.criminal.dao.ITrackEntryDAO;
import fis.criminal.model.Detective;
import fis.criminal.model.TrackEntry;
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

public class JDBCTrackEntryDAO implements ITrackEntryDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCTrackEntryDAO.class);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void save(TrackEntry trackEntry) {
        try (Connection connection = DatabaseUtility.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO track_entry (action,created_at,date,modified_at,resson,detective_one,evidence_one)");
            preparedStatement.setString(1, String.valueOf(trackEntry.getAction()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(trackEntry.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(trackEntry.getDate().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(trackEntry.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(5, trackEntry.getResson());
            preparedStatement.setLong(6, trackEntry.getDetective().getId());
            preparedStatement.setLong(7, trackEntry.getEvidence().getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("loi tao database !!!");
            }else {
                logger.info("tao thanh cong");
            }
        }catch (Exception e){
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<TrackEntry> get(long id) {
        return getAll().stream().filter(trackEntry -> trackEntry.getId() == id).findFirst();
    }

    @Override
    public List<TrackEntry> getAll() {
        List<TrackEntry> trackEntries = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM track_entry");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TrackEntry trackEntry = DatabaseMapper.getTrackEntry(rs);
                if (trackEntry != null) trackEntries.add(trackEntry);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return trackEntries;
    }

    @Override
    public void update(TrackEntry trackEntry) {
        try (Connection connection = DatabaseUtility.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE track_entry SET action=?,created_at=?,date=?,modified_at=?,resson=?,detective_one=?,evidence_one=? WHERE id=?");
            preparedStatement.setString(1, String.valueOf(trackEntry.getAction()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(trackEntry.getCreatedAt().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(trackEntry.getDate().format(dateTimeFormatter)));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(trackEntry.getModifiedAt().format(dateTimeFormatter)));
            preparedStatement.setString(5, trackEntry.getResson());
            preparedStatement.setLong(6, trackEntry.getDetective().getId());
            preparedStatement.setLong(7, trackEntry.getEvidence().getId());
            preparedStatement.setLong(8, trackEntry.getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0) {
                logger.info("db ko thay doi");
            } else {
                logger.info("cap nhat thanh cong");
            }
        }catch (Exception e){
            logger.error(e.toString());
        }
    }

    @Override
    public void delete(TrackEntry trackEntry) {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DELETE track_entry WHERE id=?");
            preparedStatement.setLong(1, trackEntry.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
