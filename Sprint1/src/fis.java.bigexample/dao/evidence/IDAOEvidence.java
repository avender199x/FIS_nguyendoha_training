package fis.java.bigexample.dao.evidence;

import fis.java.bigexample.model.CriminalCase;
import fis.java.bigexample.model.Evidence;

public interface IDAOEvidence {
    public void add(Evidence evidence);
    public Evidence remove(int code);
    public Evidence update(Evidence evidence);
}
