package fis.criminal.dao.mem;

import fis.criminal.dao.IDetectiveDAO;
import fis.criminal.model.Detective;

import java.util.List;
import java.util.Optional;

public class DetectiveDAO implements IDetectiveDAO {

    @Override
    public void save(Detective detective) {
        if (MemoryDataSource.DETECTIVES.get((int) detective.getId()) != null) {
            throw new RuntimeException("detective da ton tai");
        } else {
            MemoryDataSource.DETECTIVES.add(detective);
        }
    }

    @Override
    public Optional<Detective> get(long id) {

        return MemoryDataSource.DETECTIVES.stream()
                .filter(detective -> detective.getId() == id).findFirst();
    }

    @Override
    public List<Detective> getAll() {

        return MemoryDataSource.DETECTIVES;
    }

    @Override
    public void update(Detective detective) {
        if (MemoryDataSource.DETECTIVES.get((int) detective.getId()) != null) {
            MemoryDataSource.DETECTIVES.set((int) detective.getId(), detective);
        } else {
            throw new RuntimeException("detective ko ton tai");
        }
    }

    @Override
    public void delete(Detective detective) {

        MemoryDataSource.DETECTIVES.remove((int) detective.getId());

    }
}
