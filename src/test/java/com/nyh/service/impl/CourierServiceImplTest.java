package com.nyh.service.impl;

import com.nyh.mapper.CourierMapper;
import com.nyh.pojo.Courier;
import com.nyh.pojo.CourierExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CourierServiceImplTest {
    @Resource
    private CourierMapper courierMapper;

    @Test
    public void console() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByExPhone() {
        CourierExample example = new CourierExample();
        CourierExample.Criteria criteria = example.createCriteria();
        criteria.andExphoneEqualTo("15039449158");
        List<Courier> couriers = courierMapper.selectByExample(example);
        if (couriers.size() == 0 || couriers.size()>1) {
            // 一个手机号只能注册一个快递员信息，一个手机号查询出多个快递员信息也是错误的
            System.out.println("没有找到"); // 手机号不存在，没有该用户，返回null
        } else {
            System.out.println(couriers.get(0));
        }
    }

    @Test
    public void findByIdCard() {
        CourierExample example = new CourierExample();
        CourierExample.Criteria criteria = example.createCriteria();
        criteria.andIdcardEqualTo("412055200111023654");
        List<Courier> couriers = courierMapper.selectByExample(example);
        if (couriers.size() == 0 || couriers.size()>1) {
            // 一个手机号只能注册一个快递员信息，一个手机号查询出多个快递员信息也是错误的
            System.out.println("没有找到"); // 手机号不存在，没有该用户，返回null
        } else {
            System.out.println(couriers.get(0));
        }
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {

    }
}