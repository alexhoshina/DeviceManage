package qau.stu.devicemanage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Device {
    private Integer deviceId; // 设备编号
    private String deviceName; // 设备名称
    private Integer deviceTypeId; // 设备类型编号
    private Integer deviceStatusId; // 设备状态编号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate; // 购买日期
    private Integer availableQuantity; // 可用数量
}
