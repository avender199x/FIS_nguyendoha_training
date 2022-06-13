package fis.criminal.dao.mem;

import fis.criminal.dao.IStorageDAO;
import fis.criminal.model.Storage;

import java.util.List;
import java.util.Optional;

public class StorageDAO implements IStorageDAO {
    @Override
    public void save(Storage storage) {
        if (MemoryDataSource.STORAGES.get((int) storage.getId()) != null) {
            throw new RuntimeException("Storage ton tai");
        } else {
            MemoryDataSource.STORAGES.add(storage);
        }
    }

    @Override
    public Optional<Storage> get(long id) {
        return MemoryDataSource.STORAGES.stream()
                .filter(storage -> storage.getId() == id).findFirst();
    }

    @Override
    public List<Storage> getAll() {
        return MemoryDataSource.STORAGES;
    }

    @Override
    public void update(Storage storage) {
        if (MemoryDataSource.STORAGES.get((int) storage.getId()) != null) {
            MemoryDataSource.STORAGES.set((int) storage.getId(), storage);
        } else {
            throw new RuntimeException("Storage ko ton tai");
        }
    }

    @Override
    public void delete(Storage storage) {
        MemoryDataSource.STORAGES.remove((int) storage.getId());
    }
}
