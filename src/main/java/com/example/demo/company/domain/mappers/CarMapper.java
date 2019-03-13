package com.example.demo.company.domain.mappers;

import com.example.demo.company.domain.Car;
import com.example.demo.company.domain.CarSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarSnapshot carToCarSnapshot(Car car);
}
