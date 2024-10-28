package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.DeviceStatus;

import java.util.List;

@Mapper
public interface DeviceStatusMapper {
    @Select("select * from devicestatus")
    List<DeviceStatus> selectAll();
}
