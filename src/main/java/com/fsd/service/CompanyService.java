package com.fsd.service;

import com.fsd.entity.CompanyEntity;
import com.fsd.entity.IPODetailEntity;
import com.fsd.repository.CompanyRepository;
import com.fsd.repository.IpoRepository;
import com.fsd.utils.CommonResult;
import com.fsd.utils.ResponseCode;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private IpoRepository ipoRepository;

	//UpdateController Jack
	@Transactional
	public CommonResult addCompany(CompanyEntity companyDtl, IPODetailEntity ipoDetail) {
		try {
			companyRepository.save(companyDtl);
			ipoRepository.save(ipoDetail);
			return CommonResult
					.build(ResponseCode.SUCCESS, "Add Company Success!");
		} catch (Exception e) {
			logger.error("Fail to create ipo data:", e);
			return CommonResult.build(ResponseCode.ERROR_ACCESS_DB, "DB ERROR!");
		}
	}

	public CommonResult updateCompany(CompanyEntity companyDtl) {
		try {
			CompanyEntity old = companyRepository.findById(companyDtl.getCompanyid()).get();
			old.setStockCode(companyDtl.getStockCode());
			old.setSectorName(companyDtl.getSectorName());
			old.setBoardofdirectors(companyDtl.getBoardofdirectors());
			old.setBrifewriteup(companyDtl.getBrifewriteup());
			old.setCeo(companyDtl.getCeo());
			old.setCompanyCode(companyDtl.getCompanyCode());
			old.setCompanyName(companyDtl.getCompanyName());
			old.setCompanyStatus(companyDtl.getCompanyStatus());
			old.setListedinskex(companyDtl.getListedinskex());
			old.setTurnover(companyDtl.getTurnover());
			companyRepository.save(old);
			return CommonResult
					.build(ResponseCode.SUCCESS, "updateCompany success!");
		} catch (Exception e) {
			logger.error("Fail to create ipo data:", e);
			return CommonResult.build(ResponseCode.ERROR_ACCESS_DB, "DB ERROR!");
		}
	}

	public CommonResult setActiveForCompany(String cpName, String active) {
		try {
			companyRepository.setActiveForCompany(cpName, active);
			return CommonResult.build(200, "search setActiveForCompany success!");
		} catch (Exception e) {
			logger.error("Fail to setActiveForCompany data:", e);
			return CommonResult.build(ResponseCode.ERROR_ACCESS_DB, "DB ERROR!");
		}
	}
}
