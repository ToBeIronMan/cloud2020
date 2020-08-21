package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lucky
 */
@Service
public class PaymentSericeImpl implements PaymentSerice {
    @Autowired
    private PaymentDao pDao;
    @Override
    public int create(Payment payment) {
        return pDao.insert(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return pDao.selectByTId(id);
    }
}
