package com.example;

import java.io.Serializable;
import java.util.Objects;

public class Diem implements Comparable<Diem>, Serializable {
    private MonHoc mon;
    private int diem;

    public MonHoc getMon() {
        return mon;
    }

    public void setMon(MonHoc mon) {
        this.mon = mon;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public Diem(MonHoc mon, int diem) {
        this.mon = mon;
        this.diem = diem;
    }

    public Diem() {

    }

    public boolean equals(Object that) {
        // Hai diem duoc goi la bang nhau neu cua cung mot mon
        if (that instanceof Diem) {
            return this.mon.equals(((Diem) that).mon);
        }
        return false;
    }

    @Override
    public int compareTo(Diem o) {
        if (o.getDiem() == this.diem) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(mon, diem);
    }

    @Override
    public Diem clone() {
        Diem diem = new Diem();
        return diem;
    }
}
