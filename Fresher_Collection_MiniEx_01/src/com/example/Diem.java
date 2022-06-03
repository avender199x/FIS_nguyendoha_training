package com.example;

import java.io.Serializable;
import java.util.Objects;

public class Diem implements Comparable<Diem>, Serializable {
    private MonHoc mon;
    private int diem;

    public Diem(MonHoc mon, int diem) {
        this.mon = mon;
        this.diem = diem;
    }

    public MonHoc getMon() {
        return mon;
    }

    public int getDiem() {
        return diem;
    }

    @Override
    public int compareTo(Diem diem) {
        if (diem.equals(this.diem)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diem diem1 = (Diem) o;
        return diem == diem1.diem && mon.equals(diem1.mon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mon, diem);
    }

    @Override
    public Diem clone() {
        Diem diem = new Diem(this.mon, this.diem);
        return diem;
    }
}
