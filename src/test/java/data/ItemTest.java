package data;

import lombok.Builder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void test1(){
        Item item = Item.builder()
                .name("TestItem")
                .height("100")
                .width("200")
                .weight("300")
                .build();
        item.saveDataOnDB();
        //item.getListData();

    }

}