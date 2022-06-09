package fis.java.bigexample.dao.evidence;

import fis.java.bigexample.model.Evidence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DAOEvidence implements IDAOEvidence {
    
    private List<Evidence> evidences = new ArrayList<>();

    @Override
    public void add(Evidence evidence) {
        List<Evidence> check = this.evidences.stream()
                .filter(evidence1 -> evidence.getId()
                        .equals(evidence.getId())).collect(Collectors.toList());
        if (check == null) {
            this.evidences.add(evidence);
        } else {
            throw new RuntimeException("evidence ton tai");
        }
    }

    @Override
    public Evidence remove(int code) {
        List<Evidence> check = this.evidences.stream()
                .filter(evidence -> evidence.getId().equals((long)code)).collect(Collectors.toList());
        if (check != null) {
            this.evidences.remove(code);
        } else {
            throw new RuntimeException("evidence ko ton tai");
        }
        return null;
    }

    @Override
    public Evidence update(Evidence evidence) {
        List<Evidence> check = this.evidences.stream()
                .filter(evidence1 -> evidence.getId()
                        .equals(evidence.getId())).collect(Collectors.toList());
        if (check != null) {
            this.evidences.set(evidence.getId().intValue(), evidence);
        } else {
            throw new RuntimeException("evidence ton tai");
        }
        return null;
    }
}
