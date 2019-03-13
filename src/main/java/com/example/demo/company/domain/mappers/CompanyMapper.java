package com.example.demo.company.domain.mappers;

import com.example.demo.company.domain.Company;
import com.example.demo.company.domain.CompanySnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanySnapshot companyToCompanySnapshot(Company company);
}
