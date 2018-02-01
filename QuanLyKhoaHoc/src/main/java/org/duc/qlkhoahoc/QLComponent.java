package org.duc.qlkhoahoc;

import java.util.HashSet;
import java.util.Set;

import org.duc.qlkhoahoc.dao.KhoaHocHome;
import org.duc.qlkhoahoc.dao.SinhVienHome;
import org.duc.qlkhoahoc.entity.KhoaHoc;
import org.duc.qlkhoahoc.entity.SinhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QLComponent implements CommandLineRunner{

	@Autowired
	KhoaHocHome khoaHoc;
	@Autowired
	SinhVienHome sinhVien;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		KhoaHoc kh = khoaHoc.findById(2);
		SinhVien sv = sinhVien.findById(1);
		
		kh.getSinhViens().add(sv);
		khoaHoc.update(kh);
	}
	
}
