package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.MaintenanceRecord;
import qau.stu.devicemanage.Mapper.MaintenanceRecordMapper;

import java.util.List;

@Service
public class MaintenanceRecordService {
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

    public List<MaintenanceRecord> selectMaintenanceRecord(String name) {
        return maintenanceRecordMapper.selectMaintenanceRecord(name);
    }

    public void save(MaintenanceRecord maintenanceRecord) {
        maintenanceRecordMapper.save(maintenanceRecord);
    }

    public MaintenanceRecord selectById(Integer id) {
        return maintenanceRecordMapper.selectById(id);
    }

    public void delete(Integer id) {
        maintenanceRecordMapper.delete(id);
    }
}
