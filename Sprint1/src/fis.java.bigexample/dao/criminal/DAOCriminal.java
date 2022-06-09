package fis.java.bigexample.dao.criminal;

import fis.java.bigexample.model.CriminalCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DAOCriminal implements IDAOCriminal {
    private List<CriminalCase> criminalCases = new ArrayList<>();

    @Override
    public void add(CriminalCase criminalCase) {
        List<CriminalCase> check = this.criminalCases.stream()
                .filter(criminalCase1 -> criminalCase1.getId()
                        .equals(criminalCase.getId())).collect(Collectors.toList());
        if (check == null) {
            this.criminalCases.add(criminalCase);
        } else {
            throw new RuntimeException("criminalCase ton tai");
        }
    }

    @Override
    public CriminalCase remove(int code) {
        List<CriminalCase> check = this.criminalCases.stream()
                .filter(criminalCase -> criminalCase.getId().equals((long) code)).collect(Collectors.toList());
        if (check != null) {
            this.criminalCases.remove(code);
        } else {
            throw new RuntimeException("criminalCase ton tai");
        }
        return null;
    }

    @Override
    public CriminalCase update(CriminalCase criminalCase) {
        List<CriminalCase> check = this.criminalCases.stream()
                .filter(criminalCase1 -> criminalCase1.getId().equals(criminalCase.getId()))
                .collect(Collectors.toList());
        if (check != null) {
            this.criminalCases.set(criminalCase.getId().intValue(), criminalCase);
        } else {
            throw new RuntimeException("criminalCase ko ton tai");
        }
        return null;
    }
}
