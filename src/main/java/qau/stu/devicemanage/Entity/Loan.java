package qau.stu.devicemanage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class Loan {
    private Integer loanId; // 借用记录id
    private Integer userId; // 借用人id
    private Integer deviceId; // 设备id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate; // 借用日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedReturnDate; // 预计归还日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actualReturnDate; // 实际归还日期
    private Boolean isOverdue; // 是否逾期
    private Integer loanQuantity; // 借用数量
}
