package com.datlenhchungkhoan.cdcnpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.datlenhchungkhoan.cdcnpm.entity.LenhDat;

public interface LenhDatRepository extends JpaRepository<LenhDat, Integer> {
	List<LenhDat> findByOrderByIdDesc();
	@Procedure(procedureName = "spfindall")
	List<LenhDat> spFindAll(char loailenh);
	@Procedure(procedureName = "SP_KHOPLENH_LO")
	void xuLiKhopLenh(String inMaCP,
			String inNgay,
			char inLoaiGD,
			String inSoLuong,
			String inGiaDat);
}
