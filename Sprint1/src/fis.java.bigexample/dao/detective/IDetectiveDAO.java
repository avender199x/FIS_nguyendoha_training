package fis.java.bigexample.dao.detective;

import fis.java.bigexample.model.Detective;

import java.util.List;

public interface IDetectiveDAO {
    //Create Functions C
    public void add(Detective detective);

    //Retrieve Functions R
    public Detective get(long id);
    public List<Detective> getAll();

    //Update Functions U
    public Detective update(Detective detective);

    //Delete Functions D
    public Detective remove(long id);

    //Other Functions
}
