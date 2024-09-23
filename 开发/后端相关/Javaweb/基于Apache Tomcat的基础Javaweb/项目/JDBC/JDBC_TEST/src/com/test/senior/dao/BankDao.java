package com.test.senior.dao;
import java.util.List;

public interface BankDao{
    int addMoney(Integer id,Integer money) throws Exception;
    int subMoney(Integer id,Integer money) throws Exception;
}