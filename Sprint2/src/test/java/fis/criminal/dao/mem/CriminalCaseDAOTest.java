package fis.criminal.dao.mem;

import fis.criminal.dao.ICriminalCaseDAO;
import fis.criminal.model.CriminalCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CriminalCaseDAOTest {

    @Test
    void save() {
        //A1: Arrange
        CriminalCase criminalCase = new CriminalCase();
        // init data
        // ...
        //A2: Action
        int before = MemoryDataSource.CRIMINAL_CASES.size();
        ICriminalCaseDAO iCriminalCaseDAO = new CriminalCaseDAO();
        iCriminalCaseDAO.save(criminalCase);
        //A3: Assert
        assertEquals(before+1, MemoryDataSource.CRIMINAL_CASES.size());
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }
}