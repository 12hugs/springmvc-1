package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();;
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

        /** contains 란?
         * assertThat에서 사용할 수 있는 기능
         * 위와 같은 경우에서는 result라는 변수에 item1 과 item2 을 가지고 있느냐?
         * 를 물어보는 것이다.
         */
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(updateParam.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(updateParam.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(updateParam.getQuantity()).isEqualTo(findItem.getQuantity());
    }

}