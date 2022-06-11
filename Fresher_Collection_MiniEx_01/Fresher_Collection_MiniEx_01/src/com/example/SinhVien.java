package com.example;

import java.util.*;

public class SinhVien {
    private String mssv;
    private String ten;

    private Set<Diem> monDaHoc = new HashSet<Diem>();

    public SinhVien(String mssv, String ten) {
        this.mssv = mssv;
        this.ten = ten;
    }

    public SinhVien() {
    }

    public boolean themDiem(Diem diemMoi) {
        return this.monDaHoc.add(diemMoi);
    }

    //TODO Cau 1
    public double tinhDiemTrungBinh() {
        int tong = 0;
        long somon = 0;
        for (Diem diem : monDaHoc) {
            tong += diem.getDiem();
            somon++;
        }

        return tong / somon;
    }


    //TODO Cau 2
    public String bangDiem() {
        StringBuilder bangdiem = new StringBuilder();
        bangdiem.append("MSSV : " + this.mssv + "\n");
        bangdiem.append("Ten : " + this.ten + "\n");
        bangdiem.append("Danh sach diem" + "\n");
        bangdiem.append("STT      " + "Ten Mon      " + "Diem      " + "So tin chi" + "\n");
        int sott = 0;
        for (Diem diem : monDaHoc) {
            bangdiem.append((sott++) + "      " + diem.getMon().getTen()
                    + "      " + diem.getDiem() + "      " + diem.getMon().getTcLT() + "\n");
        }
        bangdiem.append("Diem trung binh : " + tinhDiemTrungBinh());
        return bangdiem.toString();
    }


    //TODO Cau 3
    public String xepLoai() {
        StringBuilder xeploai = new StringBuilder();
        if (tinhDiemTrungBinh() < 5) {
            xeploai.append("Yeu");
            return xeploai.toString();
        }
        if (tinhDiemTrungBinh() >= 5 && tinhDiemTrungBinh() < 6) {
            xeploai.append("TB");
            return xeploai.toString();
        }
        if (tinhDiemTrungBinh() >= 6 && tinhDiemTrungBinh() < 7) {
            xeploai.append("TB-KHA");
            return xeploai.toString();
        }
        if (tinhDiemTrungBinh() >= 7 && tinhDiemTrungBinh() < 8) {
            xeploai.append("KHA");
            return xeploai.toString();
        }
        if (tinhDiemTrungBinh() >= 7 && tinhDiemTrungBinh() < 8) {
            xeploai.append("GIOI");
            return xeploai.toString();
        }
        return xeploai.toString();
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Diem> getMonDaHoc() {
        return monDaHoc;
    }

    public void setMonDaHoc(Set<Diem> monDaHoc) {
        this.monDaHoc = monDaHoc;
    }
}
