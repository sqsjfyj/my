package org.qwj.mapper;

import org.qwj.entity.Wife;

public interface WifeMapper {

    Wife selectWifeByHusbandId(int hid);

}