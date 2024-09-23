package test.schedule.dao.impl;

import test.schedule.dao.BaseDao;
import test.schedule.dao.SysScheduleDao;
import test.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    @Override
    public int addSchedule(SysSchedule schedule) throws Exception{
        String sql = "INSERT INTO sys_schedule values(DEFAULT,?,?,?)";
        return executeUpdate(sql,schedule.getUid(),schedule.getTitle(),schedule.getCompleted());
    }

    @Override
    public List<SysSchedule> findAll() throws Exception {
        String sql = "SELECT * from sys_schedule";
        return executeQuery(SysSchedule.class, sql);
    }
}
