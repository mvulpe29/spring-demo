package com.example.demo.company.domain.mappers;

import com.example.demo.company.domain.Car;
import com.example.demo.company.domain.CarFlatSnapshot;
import com.example.demo.company.domain.CarSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarSnapshot carToCarSnapshot(Car car);

    @Mappings({
            @Mapping(target = "carCompanyName", source = "company.name"),
            @Mapping(target = "carCompanyOwner", source = "company.owner"),
    })
    CarFlatSnapshot carToCarFlatSnapshot(Car car);
}
