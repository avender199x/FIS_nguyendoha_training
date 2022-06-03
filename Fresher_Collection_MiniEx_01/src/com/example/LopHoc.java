package com.example;

import java.util.*;

public class LopHoc {
    private String ten;
    private String giaoVien;
    private SinhVien sinhVien;
    private List<SinhVien> dsLop = new ArrayList<SinhVien>();

    public LopHoc(String ten, String giaoVien) {
        this.ten = ten;
        this.giaoVien = giaoVien;
    }

    public boolean them(SinhVien svMoi) {
        return dsLop.add(svMoi);
    }

    //TODO Cau 4
    public String inDiem() {
        StringBuilder indiem = new StringBuilder();
        indiem.append("Danh Sach Diem Lop : " + this.ten + "\n");
        indiem.append("Giao Vien Chu Nhiem : " + this.giaoVien + "\n");
        indiem.append("STT" + "     " + "MSSV" + "     " + "Ten" + "     " + "Diem TB" + "     " + "XepLoai");
        int soTT = 0;
        for (SinhVien sinhVien : dsLop) {
            indiem.append((soTT++) + "     " + sinhVien.getMssv()
                    + "     " + sinhVien.getTen() + "     "
                    + sinhVien.tinhDiemTrungBinh() + "     "
                    + sinhVien.xepLoai());
        }
    /*
    Danh Sach Diem Lop : ten
    Giao Vien Chu Nhiem : giaoVien
    STT      MSSV        Ten              Diem TB   XepLoai
    1        123456      Nguyen Van A     8.4       GIOI
    2        678919      Nguyen Van B     6         TB-KHA
    3        112456      Nguyen Van C     7         KHA
    */
        //...
        return indiem.toString();
    }

    //TODO Cau 5
    public List<SinhVien> top10() {
        List<Double> top10 = new ArrayList<>();
        for (SinhVien sinhVien : dsLop) {
            top10.add(sinhVien.tinhDiemTrungBinh());
        }
        Collections.sort(top10);
        Collections.reverse(top10);
        int a = 0;
        List<SinhVien> sinhViens = new ArrayList<>();
        for (SinhVien sinhVien1 : dsLop) {
            if (top10.get(a) == sinhVien1.tinhDiemTrungBinh()) {
                sinhViens.add(sinhVien1);
                a++;
                if (sinhViens.size() == 10) {
                    break;
                }
            }
        }

        //Tra ve danh sach 10 sinh vien co diem trung binh lon nhat lop
        //...
        return sinhViens;
    }

    //TODO Cau 6
    public List<SinhVien> sinhVienYeu() {
        ArrayList<SinhVien> sinhViens = new ArrayList<>();
        if (sinhVien.tinhDiemTrungBinh() < 5) {
            sinhViens.add(sinhVien);
        }
        return sinhViens;
    }
}
