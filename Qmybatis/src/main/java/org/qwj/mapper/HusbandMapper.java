package org.qwj.mapper;

import org.qwj.entity.Husband;

public interface HusbandMapper {

    //根据id查询丈夫
    Husband queryHusbandById(int id);

    //根据id查询丈夫和妻子
    Husband queryHusbandAndWife(int id);

}
