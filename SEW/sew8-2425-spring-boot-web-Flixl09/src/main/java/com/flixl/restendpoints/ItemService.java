package com.flixl.restendpoints;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getItems();
    ItemDTO addItem(ItemDTO item);
    ItemDTO updateItem(ItemDTO item);
    void deleteAllItems();
    ItemDTO getItem(Long id);
    void deleteItem(Long id);
    ItemDTO patchItem(Long id, boolean collected);
}
