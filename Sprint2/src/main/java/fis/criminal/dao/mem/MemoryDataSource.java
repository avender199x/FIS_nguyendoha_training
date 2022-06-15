package fis.criminal.dao.mem;

import fis.criminal.model.*;

import java.util.ArrayList;
import java.util.List;

public class MemoryDataSource {
    public static final List<CriminalCase> CRIMINAL_CASES = new ArrayList<>();
    public static final List<Detective> DETECTIVES = new ArrayList<>();
    public static final List<Evidence> EVIDENCES = new ArrayList<>();
    public static final List<TrackEntry> TRACK_ENTRIES = new ArrayList<>();
    public static final List<Storage> STORAGES = new ArrayList<>();
}
