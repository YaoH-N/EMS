package com.nyh;

import com.nyh.mapper.CourierMapper;
import com.nyh.mapper.EadminMapper;
import com.nyh.mapper.ExpressMapper;
import com.nyh.pojo.*;
import com.nyh.service.ExpressService;
import com.nyh.utils.DateFormatUtil;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test1 {

    @Resource
    private ExpressMapper expressMapper;

    @Resource
    private EadminMapper eadminMapper;

    @Resource
    private CourierMapper courierMapper;

    @Test
    public void test09(){
        List<Courier> couriers = courierMapper.selectByLimit(0, 5);
        for (Courier courier : couriers) {
            System.out.println(courier);
        }

    }

    @Test
    public void test01(){
        Express express = expressMapper.selectByPrimaryKey(11);
        System.out.println(express);
    }



    @Test
    public void test02(){
        List<Express> expressList = expressMapper.selectByExample(null);
        System.out.println(expressList);
    }

    /**
     * 查询用户的测试
     */
    @Test
    public void test03(){
        // 根据username和password两个字段进行条件查询
        EadminExample example = new EadminExample();
        // 创建存放条件的容器
        EadminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo("admin");
        criteria.andPasswordEqualTo("1234");
        List<Eadmin> eadmins = eadminMapper.selectByExample(example);
        if(eadmins.size()>=1){
            System.out.println(eadmins.get(0));
        }else{
            System.out.println("false");
        }
    }

    /**
     * 待取件数
     *
     * 查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     * "select " +
     *             "count(id) data1_size," +
     *             "count(to_days(intime)=to_days(now()) or null) data1_day," +
     *             "count(status=0 or null) data2_size," +
     *             "count(to_days(intime)=to_days(now()) and status=0 or null) data2_day" +
     *             " from express"
     */
    @Test
    public void test04(){
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
//        int data2_size; //待取件数
        int data2_day; //新增数量
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        ExpressExample.Criteria criteria2 = example.createCriteria();
        // or 条件查询
        criteria.andStatusEqualTo(0);
        criteria.andIntimeGreaterThanOrEqualTo(dateFormatUtil.formatYMD(date));
        criteria.andIntimeLessThan(dateFormatUtil.formatYMD2(date));
        criteria2.andStatusIsNull();
        example.or(criteria2);
        data2_day = (int) expressMapper.countByExample(example);

        System.out.println(data2_day);
    }

    /**
     * 总快件数
     */
    @Test
    public void test05(){
        int data1_size; // 总快件数
        ExpressExample example = new ExpressExample();
        data1_size = (int)expressMapper.countByExample(example);
        System.out.println(data1_size);
    }

    /**
     * 日派单量
     * count(to_days(intime)=to_days(now()) or null)
     */
    @Test
    public void test06(){
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();

        int data1_day; // 日派单量
        ExpressExample example = new ExpressExample();

        ExpressExample.Criteria criteria = example.createCriteria();
        ExpressExample.Criteria criteria2 = example.createCriteria();

        // or 条件查询
        criteria.andIntimeGreaterThanOrEqualTo(dateFormatUtil.formatYMD(date));
        criteria.andIntimeLessThan(dateFormatUtil.formatYMD2(date));
        System.out.println(dateFormatUtil.formatYMD(date)+"-------------");
        criteria2.andIntimeIsNull();
        example.or(criteria2);

        data1_day = (int)expressMapper.countByExample(example);
        System.out.println(data1_day);
    }

    @Test
    public void test07() {
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        System.out.println(dateFormatUtil.formatYMD2(date)+"-------------");
    }

    /**
     * 用于查询数据库中的全部快递员人数，和快递员日注册量
     *"select " +
     *             "count(id) data3_size," +
     *             "count(to_days(REGTIME)=to_days(now()) or null) data3_day" +
     *             " FROM COURIER"
     *
     * @return [{size:总数(data3_size),day:新增(data3_day)}]
     */
    @Test
    public void test08(){
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();

        // 创建封装查询条件的实例对象
        CourierExample example = new CourierExample();
        Map<String, Integer> data = new HashMap<>(); // data存储返回值
        int data3_size; // 总快递员人数
        int data3_day;// 新增快递员人数
        data3_size = (int) courierMapper.countByExample(example);

        CourierExample.Criteria criteria = example.createCriteria();
        CourierExample.Criteria criteria2 = example.createCriteria();
        // or 条件查询
        criteria.andRegtimeIsNull();
        // 判断当日注册的快递员条件为，注册时间>当日00:00:00 并且<明日的00:00:00
        criteria2.andRegtimeGreaterThanOrEqualTo(dateFormatUtil.formatYMD(date));
        criteria2.andRegtimeLessThan(dateFormatUtil.formatYMD2(date));
        example.or(criteria2);
        data3_day = (int) courierMapper.countByExample(example);
        data.put("data3_size",data3_size);
        data.put("data3_day",data3_day);
        System.out.println("data3_size:"+data3_size+"------data3_day:"+data3_day);
    }


}
