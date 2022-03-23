package com.datlenhchungkhoan.cdcnpm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datlenhchungkhoan.cdcnpm.entity.LenhDat;
import com.datlenhchungkhoan.cdcnpm.repository.LenhDatRepository;
@Service
public class LenhDatService {
	static Logger logger;
	@Autowired
	LenhDatRepository lenhDatRepository;
	public LenhDat saveLenhDat(LenhDat lenhDat) {
		lenhDat.setMaCoPhieu(lenhDat.getMaCoPhieu().toUpperCase());
		lenhDat.setNgayDat(new Date());
		lenhDat.setLoaiLenh("LO");
		lenhDat.setTrangThaiLenh("Chờ khớp");
		return lenhDatRepository.save(lenhDat);
	}
	@Transactional
	public List<LenhDat> layDanhSachLenh(){
		return lenhDatRepository.findByOrderByIdDesc();
	}
	@Transactional
	public void xuLiKhopLenh(LenhDat lenhDat) {
		logger=Logger.getLogger("new logger");
		lenhDat.setMaCoPhieu(lenhDat.getMaCoPhieu().toUpperCase());
		lenhDat.setNgayDat(new Date());
		 SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	       
	        String strDate = sm.format(lenhDat.getNgayDat());
		logger.warning(strDate);
		lenhDatRepository.xuLiKhopLenh(lenhDat.getMaCoPhieu(), strDate, lenhDat.getLoaiGD(), lenhDat.getSoLuong(), lenhDat.getGiaDat());
	}
	
}
