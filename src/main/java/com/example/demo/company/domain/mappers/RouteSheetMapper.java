package com.example.demo.company.domain.mappers;

import com.example.demo.company.domain.RouteSheet;
import com.example.demo.company.domain.RouteSheetDTO;
import com.example.demo.company.domain.RouteSheetDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteSheetMapper {
    RouteSheetMapper INSTANCE = Mappers.getMapper(RouteSheetMapper.class);

    RouteSheetDocument toRouteSheetDocument(RouteSheet routeSheet);
    RouteSheetDTO toRouteSheetDTO(RouteSheet routeSheet);
}
