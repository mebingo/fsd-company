package com.fsd.service;

import com.fsd.entity.IPODetailEntity;
import com.fsd.repository.IpoRepository;
import com.fsd.utils.CommonResult;
import com.fsd.utils.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName IpoService
 * @Description TODO
 * @Author Liker
 * @Date 12/4/2019 15:40 AM
 * @Version 1.0
 **/
@Service
public class IpoService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IpoRepository repository;

	/**
	 * Description: query all exchange.
	 *
	 * @param:
	 * @return: com.smc.utils.CommonResult
	 * @auther: Liker
	 * @date: 2019/12/04 10:39
	 */
	public CommonResult save(IPODetailEntity ipo) {
		try {
			repository.save(ipo);
			return CommonResult.build(ResponseCode.SUCCESS, "SUCCESS!");
		} catch (Exception e) {
			logger.error("Fail to create ipo data:", e);
			return CommonResult.build(ResponseCode.ERROR_ACCESS_DB, "DB ERROR!");
		}
	}

	/**
	 * Description: update StockExchange detail
	 *
	 * @param:
	 * @return: com.smc.utils.CommonResult
	 * @auther: Liker
	 * @date: 2019/12/04 10:39
	 */
	public CommonResult updateStockExchange(IPODetailEntity ipo) {
		try {
			IPODetailEntity oldIPO = repository.findById(ipo.getIpoid())
					.get();
			oldIPO.setCompanyName(ipo.getCompanyName());
			oldIPO.setIpoRemarks(ipo.getIpoRemarks());
			oldIPO.setOpenDateTime(ipo.getOpenDateTime());
			oldIPO.setPricePerShare(ipo.getPricePerShare());
			oldIPO.setStockExchange(ipo.getStockExchange());
			oldIPO.setTotalNumber(ipo.getTotalNumber());
			repository.save(oldIPO);
			return CommonResult.build(ResponseCode.SUCCESS, "SUCCESS!");
		} catch (Exception e) {
			logger.error("Fail to update ipo data:", e);
			return CommonResult.build(ResponseCode.ERROR_ACCESS_DB, "DB ERROR! Open Date Time is required! and format must be yyyy-MM-dd HH:mm:ss!");
		}
	}
}