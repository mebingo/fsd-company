package com.fsd.controller;


import com.fsd.entity.IPODetailEntity;
import com.fsd.service.IpoService;
import com.fsd.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/admin/manage/ipo")
public class IPOController {

	@Autowired
	private IpoService service;


	@PostMapping
	public CommonResult createIPO(@RequestBody IPODetailEntity ipo) {
		return service.save(ipo);
	}

	@PutMapping
	public CommonResult updateIPO(@RequestBody IPODetailEntity ipo) {
		return service.updateStockExchange(ipo);
	}
}
