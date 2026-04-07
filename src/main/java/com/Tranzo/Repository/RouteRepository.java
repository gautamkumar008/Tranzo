package com.Tranzo.Repository;

import com.Tranzo.Model.Company;
import com.Tranzo.Model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Routes,Long> {

    List<Routes> findByCompanyCid(Long cid);

    List<Routes> findByCompany(Company company);

}
