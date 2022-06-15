package fis.criminal.dao;

import fis.criminal.model.CriminalCase;

public interface ICriminalCaseDAO extends IDao<CriminalCase> {
    void delete(long id);
}
