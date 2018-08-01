package org.qwj.main;

import org.apache.ibatis.session.SqlSession;
import org.qwj.entity.Husband;
import org.qwj.entity.Wife;
import org.qwj.mapper.HusbandMapper;
import org.qwj.tools.MyBatisUtil;

import java.io.IOException;

public class OneToOne {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSession();
        HusbandMapper husbandMapper = sqlSession.getMapper(HusbandMapper.class);
        Husband husband = husbandMapper.selectHusbandAndWife(455);

        System.out.println(husband);

        sqlSession.commit();
        sqlSession.close();
    }

}
