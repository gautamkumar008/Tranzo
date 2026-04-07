package com.Tranzo.Repository;

import com.Tranzo.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    boolean existsByCompanyEmail(String companyEmail);

    boolean existsByContactNo(String contactNo);

    boolean existsByRegisterNo(String registerNo);

    Company findByCompanyEmail(String email);

    List<Company> findByApprovedFalse();
}
