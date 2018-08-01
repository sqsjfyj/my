package org.qwj.mapper;

import org.qwj.entity.Husband;
import org.qwj.entity.Wife;

public interface HusbandMapper {

    /**
     7      * 根据id查询丈夫信息
     8      * @param id
     9      * @return
     10      * @throws Exception
     11      */

    Husband selectHusbandById(int hid);

    Husband selectHusbandAndWife(int hid);

}