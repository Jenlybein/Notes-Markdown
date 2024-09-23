package com.test.senior.dao.impl;
import com.test.senior.dao.BaseDAO;
import com.test.senior.dao.BankDao;

public class BankDAOimpl extends BaseDAO implements BankDao {
    @Override
    public int addMoney(Integer id, Integer money) throws Exception {
        String sql = "update t_bank set money = money + ? where id = ?";
        return executeUpdate(sql,money,id);
    }

    @Override
    public int subMoney(Integer id, Integer money) throws Exception {
        String sql = "update t_bank set money = money - ? where id = ?";
        return executeUpdate(sql,money,id);
    }
}
