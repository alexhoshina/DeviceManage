package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Entity.Device;
import qau.stu.devicemanage.Mapper.DeviceMapper;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public List<Device> selectDevice(String deviceName, Integer deviceTypeId, Integer statusId) {
        return deviceMapper.selectDevice(deviceName, deviceTypeId, statusId);
    }

    public void save(Device device) {
        deviceMapper.save(device);
    }

    public Device selectById(Integer id) {
        return deviceMapper.selectById(id);
    }

    public void delete(Integer id) {
        deviceMapper.delete(id);
    }

    public List<Device> selectAll() {
        return deviceMapper.selectAll();
    }

    public List<Chart> getChartByStatus() {
        return deviceMapper.getChartByStatus();
    }


}
