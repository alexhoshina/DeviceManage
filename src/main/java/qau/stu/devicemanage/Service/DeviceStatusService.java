package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.DeviceStatus;
import qau.stu.devicemanage.Mapper.DeviceStatusMapper;

import java.util.List;

@Service
public class DeviceStatusService {
    @Autowired
    private DeviceStatusMapper deviceStatusMapper;

    public List<DeviceStatus> selectAll() {
        return deviceStatusMapper.selectAll();
    }
}
