package fis.java.bigexample.dao.criminal;

import fis.java.bigexample.model.CriminalCase;

public interface IDAOCriminal {
    public void add(CriminalCase criminalCase);
    public CriminalCase remove(int code);
    public CriminalCase update(CriminalCase criminalCase);

}
