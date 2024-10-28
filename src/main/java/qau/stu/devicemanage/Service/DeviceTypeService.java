package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.Device;
import qau.stu.devicemanage.Entity.DeviceType;
import qau.stu.devicemanage.Mapper.DeviceTypeMapper;

import java.util.List;

@Service
public class DeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    public List<DeviceType> selectAll() {
        return deviceTypeMapper.selectAll();
    }
}
