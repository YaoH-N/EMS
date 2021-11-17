package com.nyh.service.impl;

import com.nyh.mapper.UserMapper;
import com.nyh.pojo.Courier;
import com.nyh.pojo.CourierExample;
import com.nyh.pojo.User;
import com.nyh.pojo.UserExample;
import com.nyh.service.UserService;
import com.nyh.utils.DateFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用于查询数据库中的全部用户人数，和用户日注册量
     *
     * @return [{size:总数(data4_size),day:新增(data4_day)}]
     */
    @Override
    public Map<String, Integer> console() {
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();

        // 创建封装查询条件的实例对象
        UserExample example = new UserExample();
        Map<String, Integer> data = new HashMap<>(); // data存储返回值
        int data4_size; // 总用户数
        int data4_day = 0;// 新增用户量
        data4_size = (int) userMapper.countByExample(example);

        UserExample.Criteria criteria = example.createCriteria();
        UserExample.Criteria criteria2 = example.createCriteria();
        // or 条件查询
        criteria.andRegtimeIsNull();
        // 判断当日注册的用户条件为，注册时间>当日00:00:00 并且<明日的00:00:00
        criteria2.andRegtimeGreaterThanOrEqualTo(dateFormatUtil.formatYMD(date));
        criteria2.andRegtimeLessThan(dateFormatUtil.formatYMD2(date));
        example.or(criteria2);
        data4_day = (int) userMapper.countByExample(example);
        data.put("data4_size",data4_size);
        data.put("data4_day",data4_day);
        return data;
    }

    /**
     * 用于查询所有用户
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有
     * @param offset     SQL语句的起始索引
     * @param pageNumber 每一页查询的数量
     * @return 用户的集合
     */
    @Override
    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        List<User> all = null;

        // 如果是分页查询，则执行分页查询的sql语句 否则查询全部
        if (limit) {
            all = userMapper.selectByLimit(offset, pageNumber);
        } else {
            // 查询条件默认为空查询全部数据
            all = userMapper.selectByExample(null);
        }
        return all;
    }

    /**
     * 根据手机号码，查询用户信息
     *
     * @param userPhone 用户手机号
     * @return 查询的用户信息，电话不存在时返回null
     */
    @Override
    public User findByUserPhone(String userPhone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(userPhone);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0 || users.size()>1) {
            // 一个手机号只能注册一个用户信息，一个手机号查询出多个用户信息也是错误的
            return null; // 手机号不存在，没有该用户，返回null
        } else {
            return users.get(0);
        }
    }

    /**
     * 根据身份证号，查询用户信息
     *
     * @param idCard 身份证号
     * @return 查询的用户信息，身份证号不存在时返回null
     */
    @Override
    public User findByIdCard(String idCard) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdcardEqualTo(idCard);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0 || users.size()>1) {
            // 一个身份证号对应一个用户信息，一个身份证号查询出多个用户信息也是错误的
            return null; // 身份证号不存在，没有该用户，返回null
        } else {
            return users.get(0);
        }
    }

    /**
     * 用户信息的录入
     *
     * @param user 要录入的用户对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    @Override
    public boolean insert(User user) {
        user.setRegtime(new Date());
        int insert = userMapper.insert(user);
        return insert > 0 ? true : false;
    }

    /**
     * 根据id修改用户信息
     *
     * @param id      要修改的用户id
     * @param newUser 新的用户对象（userName, userPhone , idCard, userPwd）
     * @return 修改的结果 true表示成功，false表示失败
     */
    @Override
    public boolean update(int id, User newUser) {
        newUser.setId(id);
        System.out.println("newExpress" + newUser);
        int i = userMapper.updateByPrimaryKeySelective(newUser);
        return i > 0 ? true : false;
    }

    /**
     * 根据id，删除单个用户信息
     *
     * @param id 要删除的用户id
     * @return 删除的结果 ， true表示成功，false表示失败
     */
    @Override
    public boolean delete(int id) {
        int i = userMapper.deleteByPrimaryKey(id);
        return i > 0 ? true : false;
    }
}
