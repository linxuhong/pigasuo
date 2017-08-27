package com.pigasuo.side.service;

import com.pigasuo.side.domain.Acctinfo;

import java.util.List;


public interface AcctinfoService {

	public Acctinfo getAcctinfo(int id);
	
	public Acctinfo getAcctinfo(String acctNo);
	
	public List<Acctinfo> getAcctinfoList();
	
	public void insertAcctinfo(Acctinfo acctinfo);
	
	public void updateAcctinfo(Acctinfo acctinfo);
}
