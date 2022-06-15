package fis.criminal.dao.mem;

import fis.criminal.dao.IEvidenceDAO;
import fis.criminal.model.Evidence;

import java.util.List;
import java.util.Optional;

public class EvidenceDAO implements IEvidenceDAO {

    @Override
    public void save(Evidence evidence) {
        if (MemoryDataSource.EVIDENCES.get((int) evidence.getId()) != null) {
            throw new RuntimeException("evidence ko ton tai");
        } else {
            MemoryDataSource.EVIDENCES.add(evidence);
        }
    }

    @Override
    public Optional<Evidence> get(long id) {

        return MemoryDataSource.EVIDENCES.stream()
                .filter(evidence -> evidence.getId() == id).findFirst();
    }

    @Override
    public List<Evidence> getAll() {
        return MemoryDataSource.EVIDENCES;
    }

    @Override
    public void update(Evidence evidence) {
        if (MemoryDataSource.EVIDENCES.get((int) evidence.getId()) != null) {
            MemoryDataSource.EVIDENCES.set((int) evidence.getId(), evidence);
        } else {
            throw new RuntimeException("evidence ko ton tai");
        }

    }

    @Override
    public void delete(Evidence evidence) {
        MemoryDataSource.EVIDENCES.remove((int) evidence.getId());
    }
}
