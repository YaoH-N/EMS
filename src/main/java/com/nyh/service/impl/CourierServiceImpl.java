package com.nyh.service.impl;

import com.nyh.mapper.CourierMapper;
import com.nyh.pojo.Courier;
import com.nyh.pojo.CourierExample;
import com.nyh.service.CourierService;
import com.nyh.utils.DateFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourierServiceImpl implements CourierService {

    @Resource
    private CourierMapper courierMapper;

    /**
     * 用于查询数据库中的全部快递员人数，和快递员日注册量
     *
     * @return [{size:总数(data3_size),day:新增(data3_day)}]
     */
    @Override
    public Map<String, Integer> console() {
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();

        // 创建封装查询条件的实例对象
        CourierExample example = new CourierExample();
        Map<String, Integer> data = new HashMap<>(); // data存储返回值
        int data3_size; // 总快递员数
        int data3_day;// 新增快递员量
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
        return data;
    }

    /**
     * 用于查询所有快递员
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有
     * @param offset     SQL语句的起始索引
     * @param pageNumber 每一页查询的数量
     * @return 快递员的集合
     */
    @Override
    public List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        List<Courier> all = null;

        // 如果是分页查询，则执行分页查询的sql语句 否则查询全部
        if (limit) {
            all = courierMapper.selectByLimit(offset, pageNumber);
        } else {
            // 查询条件默认为空查询全部数据
            all = courierMapper.selectByExample(null);
        }
        return all;
    }

    /**
     * 根据手机号码，查询快递员信息
     *
     * @param exPhone 快递员手机号
     * @return 查询的快递信息，电话不存在时返回null
     */
    @Override
    public Courier findByExPhone(String exPhone) {

        CourierExample example = new CourierExample();
        CourierExample.Criteria criteria = example.createCriteria();
        criteria.andExphoneEqualTo(exPhone);
        List<Courier> couriers = courierMapper.selectByExample(example);
        if (couriers.size() == 0 || couriers.size()>1) {
            // 一个手机号只能注册一个快递员信息，一个手机号查询出多个快递员信息也是错误的
            return null; // 手机号不存在，没有该快递员，返回null
        } else {
            return couriers.get(0);
        }
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Override
    public Courier findById(int id) {
        Courier courier = courierMapper.selectByPrimaryKey(id);
        return courier;
    }

    /**
     * 根据身份证号，查询快递员信息
     *
     * @param idCard 身份证号
     * @return 查询的快递信息，取件码不存在时返回null
     */
    @Override
    public Courier findByIdCard(String idCard) {
        CourierExample example = new CourierExample();
        CourierExample.Criteria criteria = example.createCriteria();
        criteria.andIdcardEqualTo(idCard);
        List<Courier> couriers = courierMapper.selectByExample(example);
        if (couriers.size() == 0 || couriers.size()>1) {
            // 一个身份证号对应一个快递员信息，一个身份证号查询出多个快递员信息也是错误的
            return null; // 身份证号不存在，没有该快递员，返回null
        } else {
            return couriers.get(0);
        }
    }

    /**
     * 快递员信息的录入
     *
     * @param courier 要录入的快递员对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    @Override
    public boolean insert(Courier courier) {
        courier.setTrannumber("0"); // 刚录入的快递员派单量为0
        courier.setRegtime(new Date());  // 把快递员注册时间设置为当前日期
        int insert = courierMapper.insert(courier);
        return insert > 0 ? true : false;
    }

    /**
     * 根据id修改快递员信息
     *
     * @param id         要修改的快递员id
     * @param newCourier 新的快递员对象（exName, exPhone , idCard, exPassWord）
     * @return 修改的结果 true表示成功，false表示失败
     */
    @Override
    public boolean update(int id, Courier newCourier) {
        newCourier.setId(id);
        System.out.println("newExpress" + newCourier);
        int i = courierMapper.updateByPrimaryKeySelective(newCourier);
        return i > 0 ? true : false;
    }

    /**
     * 快递员登录后更新登录时间
     *
     * @param userPhone
     * @param date
     */
    @Override
    public void updateLoginTime(String userPhone, Date date) {
        CourierExample example = new CourierExample();
        // 创建存放条件的容器
        CourierExample.Criteria criteria = example.createCriteria();
        criteria.andExphoneEqualTo(userPhone);
        // 封装要修改的数据对象
        Courier courier = new Courier();
        courier.setPrelogtime(date);

        // courier:修改后的对象，如果该对象中有些属性为空，则不修改
        // example: 查询条件类
        int i = courierMapper.updateByExampleSelective(courier, example);
    }

    /**
     * 根据id，删除单个快递员信息
     *
     * @param id 要删除的快递员id
     * @return 删除的结果 ， true表示成功，false表示失败
     */
    @Override
    public boolean delete(int id) {
        int i = courierMapper.deleteByPrimaryKey(id);
        return i > 0 ? true : false;
    }


}
