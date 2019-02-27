package org.jing.web.db.object.item.api;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.jing.core.db.MapperFactory;
import org.jing.core.lang.JingException;
import org.jing.web.db.object.item.dto.ItemDto;
import org.jing.web.db.object.item.mapper.ItemMapper;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-02-27 <br>
 */
public class Item {
    private static Logger logger = Logger.getLogger(Item.class);

    public static Item api() {
        return new Item();
    }

    public ItemDto qryItemById(String id) throws JingException {
        return MapperFactory.getMapper(ItemMapper.class).qryItemById(id);
    }

}