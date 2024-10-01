package test.schedule.dao;

import test.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * 介绍一下当前类...
 * 作者:.
 * 时间:......
 */
public interface SysScheduleDao {
    /**
     * 用于向数据中增加一条日程记录
     * @param schedule 日程数据以SysSchedule实体类对象形式入参
     * @return 返回影响数据库记录的行数，行数为0说明增加失败，行数大于0说明增加成功
     */
    int addSchedule(SysSchedule schedule) throws Exception;

    /**
     * 查询所有用户的所有日程
     * @return 结果装入List<SysSchedule>返回
     */
    List<SysSchedule> findAll() throws Exception;
}
