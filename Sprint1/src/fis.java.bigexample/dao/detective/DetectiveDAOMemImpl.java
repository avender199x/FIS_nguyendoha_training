package fis.java.bigexample.dao.detective;

import fis.java.bigexample.model.Detective;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetectiveDAOMemImpl implements IDetectiveDAO {
    //Memory Data
    private List<Detective> detectiveList = new ArrayList<>();

    private DetectiveDAOMemImpl() {

    }

    //Apply Singleton Pattern for DAO class
    private static DetectiveDAOMemImpl instance = new DetectiveDAOMemImpl();

    public static DetectiveDAOMemImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Detective detective) {
        this.detectiveList.add(detective);
    }

    @Override
    public Detective get(long id) {
        Optional<Detective> opt = this.detectiveList.stream().filter(
                item -> item.getId() == id ? true : false).findFirst();

        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public List<Detective> getAll() {
        return this.detectiveList;
    }

    @Override
    public Detective update(Detective updatedDetective) {
        List<Detective> FindAll = getAll();
        List<Detective> checkid =
                this.detectiveList.stream()
                        .filter(detective
                                -> detective.getId()
                                .equals(updatedDetective.getId())).collect(Collectors.toList());
        if (checkid != null) {
            FindAll.set(updatedDetective.getId().intValue(), updatedDetective);
        } else {
            throw new RuntimeException("Detective khong ton tai");
        }
        // Find out ID
        // Update data based on updatedDetective

        return null;
    }

    @Override
    public Detective remove(long id) {
        for (Detective detective : this.detectiveList) {
            if (detective.getId() == id) {
                this.detectiveList.remove(detective);
                return detective;
            }
        }
        return null;
    }
}
