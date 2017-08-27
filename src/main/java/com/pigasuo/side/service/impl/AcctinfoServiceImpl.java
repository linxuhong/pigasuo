package com.pigasuo.side.service.impl;

import com.pigasuo.side.domain.Acctinfo;
import com.pigasuo.side.persistence.AcctinfoMapper;
import com.pigasuo.side.service.AcctinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcctinfoServiceImpl implements AcctinfoService {
	
	@Autowired
	private AcctinfoMapper acctinfoMapper;

	public Acctinfo getAcctinfo(int id) {
		Acctinfo acctinfo = new Acctinfo();
		acctinfo.setId(id);
		return acctinfoMapper.getAcctinfoById(acctinfo);
	}

	public Acctinfo getAcctinfo(String acctNo) {
		Acctinfo acctinfo = new Acctinfo();
		acctinfo.setAcctNo(acctNo);
		return acctinfoMapper.getAcctinfoByAcctNo(acctinfo);
	}

	public List<Acctinfo> getAcctinfoList() {
		return acctinfoMapper.getAcctinfoList();
	}

	public void insertAcctinfo(Acctinfo acctinfo) {
		acctinfoMapper.insertAcctinfo(acctinfo);
	}

	public void updateAcctinfo(Acctinfo acctinfo) {
		acctinfoMapper.updateAcctinfo(acctinfo);
	}
	
	
}
