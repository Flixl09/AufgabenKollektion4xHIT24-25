package com.flixl.restendpoints;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ItemConverter {
    ItemDTO convertToDTO(Item item);
    Item convertToEntity(ItemDTO itemDTO);
    List<ItemDTO> convertToDTO(List<Item> items);
    List<Item> convertToEntity(List<ItemDTO> itemDTOs);
}
