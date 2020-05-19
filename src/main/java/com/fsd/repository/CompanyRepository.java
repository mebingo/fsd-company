package com.fsd.repository;

import com.fsd.entity.CompanyEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

	@Transactional
	@Modifying()
	@Query(name = "setActiveForCompany", nativeQuery = true, value = "update company set companystatus = :active where companyname = :companyName")
	void setActiveForCompany(String companyName, String active);
}

