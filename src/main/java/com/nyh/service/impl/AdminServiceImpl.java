package com.nyh.service.impl;

import com.nyh.mapper.EadminMapper;
import com.nyh.pojo.Eadmin;
import com.nyh.pojo.EadminExample;
import com.nyh.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private EadminMapper eadminMapper;

    /**
     * 根据用户名，更新登录时间和登录ip
     *
     * @param username
     * @param date
     * @param ip
     */
    @Override
    public void updateLoginTimeAndIP(String username, Date date, String ip) {
        EadminExample example = new EadminExample();
        // 创建存放条件的容器
        EadminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        // 封装要修改的数据对象
        Eadmin eadmin = new Eadmin();
        eadmin.setLoginip(ip);
        eadmin.setLogintime(date);

        // eadmin:修改后的对象，如果该对象中有些属性为空，则不修改
        // example: 查询条件类
       int i = eadminMapper.updateByExampleSelective(eadmin, example);
    }

    /**
     * 管理员根据账号密码登录
     *
     * @param username 账号
     * @param password 密码
     * @return 登录的结果，true表示成功
     */
    @Override
    public boolean login(String username, String password) {

        // 根据username和password两个字段进行条件查询
        EadminExample example = new EadminExample();
        // 创建存放条件的容器
        EadminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Eadmin> eadmins = eadminMapper.selectByExample(example);
        if(eadmins.size()>=1){
            return true;
        }else{
            return false;
        }
    }
}
