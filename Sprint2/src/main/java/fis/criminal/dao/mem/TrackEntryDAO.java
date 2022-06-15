package fis.criminal.dao.mem;

import fis.criminal.dao.ITrackEntryDAO;
import fis.criminal.model.TrackEntry;

import java.util.List;
import java.util.Optional;

public class TrackEntryDAO implements ITrackEntryDAO {
    @Override
    public void save(TrackEntry trackEntry) {
        if (MemoryDataSource.TRACK_ENTRIES.get((int) trackEntry.getId()) != null) {
            throw new RuntimeException("TrackEntry ton tai");
        } else {
            MemoryDataSource.TRACK_ENTRIES.add(trackEntry);
        }
    }

    @Override
    public Optional<TrackEntry> get(long id) {
        return MemoryDataSource.TRACK_ENTRIES.stream()
                .filter(trackEntry -> trackEntry.getId() == id).findFirst();
    }

    @Override
    public List<TrackEntry> getAll() {
        return MemoryDataSource.TRACK_ENTRIES;
    }

    @Override
    public void update(TrackEntry trackEntry) {
        if (MemoryDataSource.TRACK_ENTRIES.get((int) trackEntry.getId()) != null) {
            MemoryDataSource.TRACK_ENTRIES.set((int) trackEntry.getId(), trackEntry);
        } else {
            throw new RuntimeException("TrackEntry ko ton tai");
        }
    }

    @Override
    public void delete(TrackEntry trackEntry) {
        MemoryDataSource.TRACK_ENTRIES.remove((int) trackEntry.getId());
    }
}
