package fis.criminal.dao.jdbc;

import fis.criminal.dao.ICriminalCaseDAO;
import fis.criminal.model.CriminalCase;
import fis.criminal.model.Detective;
import fis.criminal.model.Evidence;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JDBCCriminalCaseDAOTest {
    private JDBCCriminalCaseDAO dao;
    @Test
    void getAll() {
        ICriminalCaseDAO criminalCaseDAO = new JDBCCriminalCaseDAO();
        List<CriminalCase> criminalCaseList = criminalCaseDAO.getAll();
    }

    @Test
    void save() {
        Set<Evidence> evidenceSet = new HashSet<>();
        Set<Detective> detectives = new HashSet<>();
        Detective detective = new Detective();
        CriminalCase criminalCase = new CriminalCase(1, 2, "s", "s", "s"
                , "s", "s", "s", null, null, null);
        dao.save(criminalCase);
    }
}