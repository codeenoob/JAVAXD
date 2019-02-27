package org.jing.web.db.object.item.demo;

import init.Initialization;
import org.jing.core.db.MapperFactory;
import org.jing.web.db.bm.user.mapper.UserMapper;
import org.jing.web.db.object.item.api.Item;
import org.jing.web.db.object.item.dto.ItemDto;
import org.jing.web.db.object.item.mapper.ItemMapper;

import java.lang.Exception;
import java.util.List;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-26 <br>
 */
public class ItemDemo {
    private ItemDemo() throws Exception {
        // MapperFactory.registerMapper(UserMapper.class);
        ItemDto dto = Item.api().qryItemById("-1");
        System.out.println(dto);
    }

    public static void main(String[] args) throws Exception {
        new ItemDemo();
    }
}
