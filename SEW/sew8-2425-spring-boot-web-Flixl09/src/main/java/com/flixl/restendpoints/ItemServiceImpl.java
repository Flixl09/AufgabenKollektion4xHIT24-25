package com.flixl.restendpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemConverter itemConverter;

    public List<ItemDTO> getItems() {
        List<Item> items = itemRepository.findAllByOrderByIdAsc();
        List<ItemDTO> itemDTOS = itemConverter.convertToDTO(items);
        return itemDTOS;
    }

    @Transactional
    public ItemDTO addItem(ItemDTO item) {
        item.correctErrors();
        Item newItem = itemConverter.convertToEntity(item);
        if (newItem.getId() != null) throw new IllegalArgumentException("Item ID must be null");
        return itemConverter.convertToDTO(itemRepository.save(newItem));
    }

    @Transactional
    public ItemDTO updateItem(ItemDTO item) {
        item.correctErrors();
        if (item.getId() == null) throw new IllegalArgumentException("Item ID must not be null");
        if (!itemRepository.existsItemById(item.getId())) throw new NoSuchElementException("Item not found");
        Item i = itemConverter.convertToEntity(item);
        if (itemRepository.findById(i.getId()).get().equals(i)) return item;
        return itemConverter.convertToDTO(itemRepository.save(i));
    }

    @Transactional
    public ItemDTO patchItem(Long id, boolean collected) {
        if (id == null) throw new IllegalArgumentException("Item ID must not be null");
        if (!itemRepository.existsItemById(id)) throw new NoSuchElementException("Item not found");
        Item i = itemRepository.findById(id).get();
        i.setCollected(collected);
        return itemConverter.convertToDTO(i);
    }

    @Transactional
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    @Transactional
    public ItemDTO getItem(Long id) {
        if (id == null) throw new IllegalArgumentException("Item ID must not be null");
        return itemConverter.convertToDTO(
                itemRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Item not found")));
    }

    @Transactional
    public void deleteItem(Long id) {
        if (id == null) itemRepository.deleteAll();
        if (!itemRepository.existsItemById(id)) throw new NoSuchElementException("Item not found");
        itemRepository.deleteById(id);
    }

}
