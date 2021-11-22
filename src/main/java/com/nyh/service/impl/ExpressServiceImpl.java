package com.nyh.service.impl;

import com.nyh.exception.DuplicateCodeException;
import com.nyh.mapper.ExpressMapper;
import com.nyh.pojo.Express;
import com.nyh.pojo.ExpressExample;
import com.nyh.service.ExpressService;
import com.nyh.utils.DateFormatUtil;
import com.nyh.utils.RandomUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    /**
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     *
     * @return [{size:总数(data1_size),day:新增(data1_day)},{size:总数(data2_size),day:新增(data2_day)}]
     */
    @Override
    public List<Map<String, Integer>> console() {
        Date date = new Date();
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        int data1_size; // 总快件数
        int data2_size; //待取件数
        int data1_day = 0;// 日派单量
        int data2_day;//新增数量
        Map data1 = new HashMap();
        Map data2 = new HashMap();
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ArrayList<Map<String, Integer>> data = new ArrayList<>(); // data存储返回值

        data1_size = (int) expressMapper.countByExample(example);

        ExpressExample.Criteria criteria = example.createCriteria();
        ExpressExample.Criteria criteria2 = example.createCriteria();
        criteria.andIntimeGreaterThanOrEqualTo(dateFormatUtil.formatYMD(date));
        criteria.andIntimeLessThan(dateFormatUtil.formatYMD2(date));
        criteria2.andIntimeIsNull();
        example.or(criteria2);
        data1_day = (int) expressMapper.countByExample(example);

        ExpressExample example2 = new ExpressExample();
        criteria = example2.createCriteria();
        criteria2 = example2.createCriteria();
        // or 条件查询
        criteria.andStatusEqualTo(0);
        criteria2.andStatusIsNull();
        example2.or(criteria2);
        data2_size = (int) expressMapper.countByExample(example2);


        date = new Date();
        // 判断当日注册的快递员条件为，注册时间>当日00:00:00 并且<明日的00:00:00
        criteria.andIntimeGreaterThanOrEqualTo(dateFormatUtil.formatYMD(date));
        criteria.andIntimeLessThan(dateFormatUtil.formatYMD2(date));
        data2_day = (int) expressMapper.countByExample(example2);

        data1.put("data1_size", data1_size);
        data1.put("data1_day", data1_day);
        data2.put("data2_size", data2_size);
        data2.put("data2_day", data2_day);
        System.out.println(data1_size + "-------" + data2_day);
        data.add(data1);
        data.add(data2);
        return data;
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有
     * @param offset     SQL语句的起始索引
     * @param pageNumber 每一页查询的数量
     * @return 快递的集合
     */
    @Override
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        List<Express> all = null;
//        ArrayList<Express> data = new ArrayList<>();

        if (limit) { // 如果是分页查询，则执行分页查询的sql语句
            all = expressMapper.selectByLimit(offset, pageNumber);
        } else { // 否则查询全部
            // 查询条件默认为空查询全部数据
            all = expressMapper.selectByExample(null);
        }
//        for (Express express : all) {
//            data.add(express);
//        }
        return all;
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    @Override
    public Express findByNumber(String number) {
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andNumberEqualTo(number);
        List<Express> expressList = expressMapper.selectByExample(example);
        if (expressList.size() > 1 || expressList.size() == 0) {
            return null;
        } else {
            return expressList.get(0);
        }
    }

    /**
     * 根据取件码，查询快递信息
     *
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    @Override
    public Express findByCode(String code) {
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<Express> expressList = expressMapper.selectByExample(example);
        if (expressList.size() > 1 || expressList.size() == 0) {
            return null;
        } else {
            return expressList.get(0);
        }
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表，手机号码不存在时返回null
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(userPhone);
        List<Express> expressList = expressMapper.selectByExample(example);
        if (expressList.size() == 0) {
            return null; // 手机号不存在，返回null,没有该用户的快递
        } else {
            return expressList;
        }
    }

    /**
     * 根据录入人手机号码，查询录入的所有记录
     *
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表，手机号码不存在时返回null
     */
    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        // 创建封装查询条件的实例对象
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andSysPhoneEqualTo(sysPhone);
        List<Express> expressList = expressMapper.selectByExample(example);
        if (expressList.size() == 0) {
            return null; // 手机号不存在，返回null
        } else {
            return expressList;
        }
    }

    /**
     * 快递的录入
     * <p>
     * 可能出现取件码相同的异常
     *
     * @param e 要录入的快递对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    @Override
    public boolean insert(Express e) throws DuplicateCodeException {
        String code = RandomUtil.getCode() + "";
        e.setCode(code);
        e.setIntime(new Date());
        e.setStatus(0); // 刚录入的快递设置状态为未取件
        try {
            int insert = expressMapper.insert(e);
            return insert > 0 ? true : false;
        } catch (DuplicateKeyException e1) {
            System.out.println(e1.getMessage());
            if (e1.getMessage().endsWith("for key 'code'")) {
                // // 处理取件码相同时无法插入的异常问题,取件码重复
                DuplicateCodeException e2 = new DuplicateCodeException(e1.getMessage());
                throw e2;
            } else {
                e1.printStackTrace();
            }
        } finally {
        }
        return false;
    }

    /**
     * @param id         要修改的快递id
     * @param newExpress 新的快递对象（number, company , username, userPhone）
     * @return
     */
    @Override
    public boolean update(int id, Express newExpress) {
        newExpress.setId(id);
        System.out.println("newExpress" + newExpress);
        int i = expressMapper.updateByPrimaryKey(newExpress);
        return i > 0 ? true : false;
    }

    /**
     * 根据取件码更改快递的状态为1 ， 表示取件完成
     * 取件完成，更新取货码为空
     *
     * @param code 要修改的快递单号
     * @return
     */
    @Override
    public boolean updateStatus(String code) {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        // 先根据取件码查询出改快递，然后在进行更新取件状态的操作
        criteria.andCodeEqualTo(code);
        List<Express> expressList = expressMapper.selectByExample(example);
        expressList.get(0).setStatus(1);
        expressList.get(0).setCode(null);
        expressList.get(0).setOuttime(new Date());
        int i = expressMapper.updateByExample(expressList.get(0), example);
        return i > 0 ? true : false;
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果 ， true表示成功，false表示失败
     */
    @Override
    public boolean delete(int id) {
        int i = expressMapper.deleteByPrimaryKey(id);
        return i > 0 ? true : false;
    }

    /**
     * 通过用户手机号和快递状态查询用户的快递
     *
     * @param userPhone
     * @param status
     */
    @Override
    public List<Express> findByUserPhoneAndStatus(String userPhone, int status) {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(userPhone);
        criteria.andStatusEqualTo(status);
        List<Express> expressList = expressMapper.selectByExample(example);
        return expressList;
    }



}
