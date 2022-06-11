package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class LopHoc {
    private String ten;
    private String giaoVien;
    private List<SinhVien> dsLop = new ArrayList<SinhVien>();

    public LopHoc(String ten, String giaoVien) {
        this.ten = ten;
        this.giaoVien = giaoVien;
    }

    public LopHoc() {
    }

    public boolean them(SinhVien svMoi) {
        return dsLop.add(svMoi);
    }

    //TODO Cau 4
    public String inDiem() {
        StringBuilder indiem = new StringBuilder();
        indiem.append("Danh Sach Diem Lop " + this.ten + "\n");
        indiem.append("Giao Vien Chu Nhiem : " + this.giaoVien + "\n");
        indiem.append("STT      " + "MSSV        " + "Ten              "
                + "Diem TB              " + "XepLoai" + "\n");
        int soTT = 0;
        for (SinhVien sinhVien : dsLop) {
            indiem.append((soTT++) + "      " + sinhVien.getMssv() +
                    "        " + sinhVien.getTen() + "              "
                    + sinhVien.tinhDiemTrungBinh() + "              " + sinhVien.xepLoai() + "\n");
        }
        return indiem.toString();
    }

    //TODO Cau 5
    public List<SinhVien> top10() {
        return dsLop.stream().sorted(
                        Comparator.comparing(
                                SinhVien::tinhDiemTrungBinh).reversed())
                .limit(10).collect(Collectors.toList());
    }

    //TODO Cau 6
    public List<SinhVien> sinhVienYeu() {
        return dsLop.stream().filter(sinhVien -> sinhVien.tinhDiemTrungBinh() < 5).collect(Collectors.toList());
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public List<SinhVien> getDsLop() {
        return dsLop;
    }

    public void setDsLop(List<SinhVien> dsLop) {
        this.dsLop = dsLop;
    }
}
