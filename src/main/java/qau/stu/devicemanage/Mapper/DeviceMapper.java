package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Entity.Device;

import java.util.List;

@Mapper
public interface DeviceMapper {
    // 调用查询设备数据的存储过程
    @Select("call GetDeviceDetails(#{deviceName}, #{deviceTypeId}, #{deviceStatusId})")
    List<Device> selectDevice(@Param("deviceName")String deviceName, @Param("deviceTypeId")Integer deviceTypeId, @Param("deviceStatusId")Integer deviceStatusId);

    @Select("call SaveOrUpdateDevice(#{device.deviceId}, #{device.deviceName}, #{device.deviceTypeId}, #{device.deviceStatusId}, #{device.purchaseDate}, #{device.availableQuantity})")
    void save(@Param("device")Device device);

    @Select("select * from device where deviceID = #{id}")
    Device selectById(Integer id);

    @Delete("delete from device where deviceID = #{id}")
    void delete(Integer id);

    @Select("select * from device")
    List<Device> selectAll();

    @Select("call CountDevicesByStatus()")
    List<Chart> getChartByStatus();


}
