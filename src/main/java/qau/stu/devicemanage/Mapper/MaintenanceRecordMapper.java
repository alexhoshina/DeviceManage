package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.MaintenanceRecord;

import java.util.List;

@Mapper
public interface MaintenanceRecordMapper {
    @Select("call GetMaintenanceRecords(#{name})")
    List<MaintenanceRecord> selectMaintenanceRecord(@Param("name")String name);

    @Select("call SaveOrUpdateMaintenanceRecord(#{mr.maintenanceRecordId}, #{mr.deviceId}, #{mr.maintenanceDate}, #{mr.details})")
    void save(@Param("mr")MaintenanceRecord maintenanceRecord);

    @Select("select * from maintenanceRecord where maintenanceRecordId = #{id}")
    MaintenanceRecord selectById(Integer id);

    @Delete("delete from maintenanceRecord where maintenanceRecordId = #{id}")
    void delete(Integer id);
}
