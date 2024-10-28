package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.Device;
import qau.stu.devicemanage.Entity.DeviceType;

import java.util.List;

@Mapper
public interface DeviceTypeMapper {
    @Select("select * from deviceType")
    List<DeviceType> selectAll();
}
