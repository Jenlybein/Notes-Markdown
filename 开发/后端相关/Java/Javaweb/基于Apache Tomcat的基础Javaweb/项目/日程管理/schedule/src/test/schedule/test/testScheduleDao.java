package test.schedule.test;

import org.junit.BeforeClass;
import org.junit.Test;
import test.schedule.dao.SysScheduleDao;
import test.schedule.dao.impl.SysScheduleDaoImpl;
import test.schedule.pojo.SysSchedule;

import java.util.List;

public class testScheduleDao {
    private static SysScheduleDao scdao;
    @BeforeClass
    public static void initScheduleDao(){
        scdao = new SysScheduleDaoImpl();

    }

    @Test
    public void testFindAll() throws Exception {
        List<SysSchedule> list = scdao.findAll();
        for (SysSchedule sysSchedule : list) {
            System.out.println(sysSchedule);
        }
    }

    @Test
    public void testAddSchedule() throws Exception {
        SysSchedule ss = new SysSchedule(null,2,"学习",0);
        int row = scdao.addSchedule(ss);
        System.out.println(row);
    }
}
