package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품_추가_테스트() throws Exception{
        // GIVEN
        Item item = new Book();
        item.setName("book1");

        // WHEN
        itemService.saveItem(item);

        // THEN
        assertThat(itemRepository.findOne(item.getId()).getName()).isEqualTo("book1");
    }

    @Test
    public void 상품_개수_추가_테스트() throws Exception{
        // GIVEN
        Item item = new Book();
        item.setName("book1");
        item.setStockQuantity(5);

        // WHEN
        itemRepository.save(item);
        item.addStock(5);

        // THEN
        assertThat(itemRepository.findOne(item.getId()).getStockQuantity()).isEqualTo(10);
    }

    @Test
    public void 상품_개수_부족_테스트() throws Exception{
        // GIVEN
        Item item = new Book();
        item.setName("book1");
        item.setStockQuantity(5);

        // WHEN
        itemRepository.save(item);

        // THEN
        Assertions.assertThrows(NotEnoughStockException.class, () -> item.removeStock(6));
    }
}