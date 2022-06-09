package fis.java.bigexample.dao.storage;

import fis.java.bigexample.model.Storage;

import java.util.ArrayList;
import java.util.List;

public class DAOStorage implements IDAOStorage {
    private List<Storage> storages = new ArrayList<>();

    @Override
    public void add(Storage storage) {
        for (Storage storage1 : this.storages) {
            if (storage1.getId().equals(storage.getId())) {
                throw new RuntimeException("storage da ton tai");
            } else {
                this.storages.add(storage);
                break;
            }
        }
    }

    @Override
    public Storage remove(int code) {

        for (Storage storage : this.storages) {
            if (storage.getId().equals((long) code)) {
                this.storages.remove(code);
                break;
            } else {
                throw new RuntimeException("storage ko ton tai");
            }
        }
        return null;
    }

    @Override
    public Storage update(Storage storage) {
        for (Storage storage1 : this.storages) {
            if (storage1.getId().equals(storage.getId())) {
                this.storages.set(storage.getId().intValue(), storage);
                break;
            } else {
                throw new RuntimeException("storage ko ton tai");
            }
        }
        return null;
    }
}
