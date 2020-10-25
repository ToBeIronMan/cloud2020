package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {
    @Select("select * from payment where id =#{id}")
    Payment selectPaymentById(@Param("id") Long id);
}
