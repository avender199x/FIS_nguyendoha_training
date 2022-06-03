package com.example;

import java.util.*;

public class SinhVien {
    private String mssv;
    private String ten;
    private MonHoc monHoc;

    private Set<Diem> monDaHoc = new HashSet<Diem>();

    public SinhVien(String mssv, String ten) {
        this.mssv = mssv;
        this.ten = ten;
    }


    public boolean themDiem(Diem diemMoi) {
        return this.monDaHoc.add(diemMoi);
    }

    public String getMssv() {
        return mssv;
    }

    public String getTen() {
        return ten;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public Set<Diem> getMonDaHoc() {
        return monDaHoc;
    }

    //TODO Cau 1-
    public double tinhDiemTrungBinh() {
        int tong = 0;
        double soMon = 0;
        for (Diem diem : monDaHoc) {
            tong += diem.getDiem();
            soMon++;
        }
        return tong / soMon;
    }


    //TODO Cau 2
    public String bangDiem() {
        StringBuilder bangdiem = new StringBuilder();
        bangdiem.append("MSSV : " + this.mssv + "\n");
        bangdiem.append("Ten : " + this.ten + "\n");
        bangdiem.append("Danh sach Diem");
        bangdiem.append("STT" + "     " + "Ten Mon" + "     " + "Diem" + "     " + "So tin chi");
        int id = 0;
        for (Diem diem : monDaHoc) {
            bangdiem.append((id++) + "     " + (diem.getMon()) + "     " + (diem.getDiem()) + "     " + this.monHoc.TongTinChi + "\n");
        }
        bangdiem.append("Diem trung binh : " + tinhDiemTrungBinh() + "\n");
    /*
     MSSV : 0203044
     Ten  : Nguyen Van A
     Danh Sach Diem
     STT  Ten Mon             Diem       So Tin Chi
     1    Cau Truc Du Lieu 1  8          3
     2    Cau Truc Du Lieu 2  8          3
     Diem Trung Binh   8.0
    */
        //...
        //StringBuilder
        return bangdiem.toString();
    }


    //TODO Cau 3
    public String xepLoai() {
        String xeploai = "";
        if (tinhDiemTrungBinh()<5){
             xeploai="xep loai YEU";
        }else if (tinhDiemTrungBinh() >= 5 && tinhDiemTrungBinh()<6){
             xeploai="xep loai TB";
        }else if (tinhDiemTrungBinh() >= 6 && tinhDiemTrungBinh()<7){
            xeploai= "xep loai TB-KHA";
        }else if (tinhDiemTrungBinh() >= 7 && tinhDiemTrungBinh()<8){
            xeploai="xep loai KHA";
        }else if (tinhDiemTrungBinh() >=8){
             xeploai="xep loai GIOI";
        }
        return xeploai;
    /*
    Quy tac xep loai nhu sau
        DiemTB < 5 -> YEU
        DiemTB >= 5 va DiemTB < 6 -> TB
        DiemTB >= 6 va DiemTB < 7 -> TB-KHA
        DiemTB >= 7 va DiemTB < 8 -> KHA
        DiemTB >= 8 -> GIOI
    */

        //...
        
    }
}
