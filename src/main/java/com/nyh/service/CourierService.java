package com.nyh.service;

import com.nyh.pojo.Courier;
import com.nyh.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CourierService {
    /**
     * 用于查询数据库中的全部快递员人数，和快递员日注册量
     * @return  [{size:总数(data3_size),day:新增(data3_day)}]
     */
    Map<String,Integer> console();

    /**
     * 用于查询所有快递员
     * @param limit   是否分页的标记，true表示分页。false表示查询所有
     * @param offset  SQL语句的起始索引
     * @param pageNumber   每一页查询的数量
     * @return   快递员的集合
     */
    List<Courier> findAll(boolean limit, int offset, int pageNumber);

    /**
     * 根据手机号码，查询快递员信息
     * @param exPhone  快递员手机号
     * @return  查询的快递信息，电话不存在时返回null
     */
    Courier findByExPhone(String exPhone);

    /**
     * 根据身份证号，查询快递员信息
     * @param idCard  身份证号
     * @return  查询的快递信息，取件码不存在时返回null
     */
    Courier findByIdCard(String idCard);

    /**
     * 快递员信息的录入
     * @param  courier  要录入的快递员对象
     * @return   录入的结果，true表示成功，false表示失败
     */
    boolean insert(Courier courier);

    /**
     * 根据id修改快递员信息
     *
     * @param id   要修改的快递员id
     * @param newCourier  新的快递员对象（exName, exPhone , idCard, exPassWord）
     * @return 修改的结果 true表示成功，false表示失败
     */
    boolean update(int id,Courier newCourier);


    /**
     * 根据id，删除单个快递员信息
     * @param id  要删除的快递员id
     * @return  删除的结果 ， true表示成功，false表示失败
     */
    boolean delete(int id);

}
