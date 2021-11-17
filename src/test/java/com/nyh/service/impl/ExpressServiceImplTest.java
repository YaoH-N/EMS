package com.nyh.service.impl;

import com.nyh.exception.DuplicateCodeException;
import com.nyh.mapper.ExpressMapper;
import com.nyh.pojo.Express;
import com.nyh.pojo.ExpressExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ExpressServiceImplTest {
    @Resource
    private ExpressMapper expressMapper;
    @Resource
    private ApplicationContext applicationContext;
    @Test
    public void console() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByNumber() {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andNumberEqualTo("100");
        List<Express> expressList = expressMapper.selectByExample(example);
        if(expressList.size()>1 || expressList.size()==0){
            System.out.println("查询失败！");
        }else{
            System.out.println("查询成功！");
            System.out.println(expressList.get(0));
        }
    }

    @Test
    public void findByCode() {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo("123456");
        List<Express> expressList = expressMapper.selectByExample(example);
        if(expressList.size()>1 || expressList.size()==0){
            System.out.println("查询失败！");
        }else{
            System.out.println("查询成功！");
            System.out.println(expressList.get(0));
        }
    }

    @Test
    public void findByUserPhone() {
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo("12323232323");
        List<Express> expressList = expressMapper.selectByExample(example);
        if(expressList.size()==0){
            System.out.println("查询失败！");
        }else{
            System.out.println("查询成功！");
            for (Express express : expressList) {
                System.out.println(express);
            }
        }
    }

    @Test
    public void findBySysPhone() {
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andSysPhoneEqualTo("15039449158");
        List<Express> expressList = expressMapper.selectByExample(example);
        if(expressList.size()==0){
            System.out.println("查询失败！手机号不存在");
        }else{
            System.out.println("查询成功！");
            for (Express express : expressList) {
                System.out.println(express);
            }
        }
    }

    @Test
    public void insert() throws DuplicateCodeException {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo("123456");
        List<Express> expressList = expressMapper.selectByExample(example);
        Express e = expressList.get(0);
        e.setId(null);
        e.setNumber("1000000");
        try{
            int insert = expressMapper.insert(e);
        }catch (DuplicateKeyException e1){
            System.out.println(e1.getMessage()+"异常。。。。。");
            if(e1.getMessage().endsWith("for key 'code'")){
                // // 处理取件码相同时无法插入的异常问题,取件码重复
                DuplicateCodeException e2 = new DuplicateCodeException(e1.getMessage());
                throw e2;
            }else{
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void update() {
        Express newExpress = expressMapper.selectByPrimaryKey(1);
        newExpress.setCompany("djdjdjjd");
        newExpress.setId(1);
        int i = expressMapper.updateByPrimaryKeySelective(newExpress);
        System.out.println(i);
        System.out.println(expressMapper.selectByPrimaryKey(1));
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void delete() {
    }
}