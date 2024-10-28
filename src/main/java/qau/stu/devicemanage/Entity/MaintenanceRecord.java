package qau.stu.devicemanage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class MaintenanceRecord {
    private Integer maintenanceRecordId; // 保养记录编号
    private Integer deviceId; // 设备编号
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 保养日期
    private LocalDate maintenanceDate; // 保养日期
    private String details; // 保养详情
}
