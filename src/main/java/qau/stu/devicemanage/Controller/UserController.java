package qau.stu.devicemanage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Service.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private DeviceStatusService deviceStatusService;
    @Autowired
    private LoanService loanService;

    @RequestMapping("/deviceList")
    public String deviceList(Model model, String name, Integer devicetype, Integer status) {
        model.addAttribute("devices", deviceService.selectDevice(name, devicetype, status));
        model.addAttribute("DeviceTypes", deviceTypeService.selectAll());
        model.addAttribute("Statuses", deviceStatusService.selectAll());
        return "user/deviceList";
    }

    @RequestMapping("/deviceLoan")
    public String loan(Integer deviceid, Integer userid, Integer borrowQuantity, Model model) {
        try {
            // 根据用户id和设备id在借用表中插入一条借用记录
            loanService.insert(deviceid, userid, borrowQuantity);
            return "redirect:/user/deviceList";
        }catch (DataAccessException e){
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof SQLException sqlEx) {
                if ("Not enough devices available for loan".equals(sqlEx.getMessage())) {
                    model.addAttribute("DeviceTypes", deviceTypeService.selectAll());
                    model.addAttribute("Statuses", deviceStatusService.selectAll());
                    model.addAttribute("devices", deviceService.selectAll());
                    // 设置自定义错误消息
                    model.addAttribute("msg", "借用数量超过库存数量");
                    return "user/deviceList"; // 返回到借贷添加页面并显示错误消息
                }
            }
            return "redirect:/user/deviceList";
        }
    }

    @RequestMapping("/loanList")
    public String loanList(Model module, String name, Integer userid) {
        if(name != null && !name.isEmpty()) {
            module.addAttribute("loans", loanService.selectByDeviceName_Userid(name, userid));
        }else {
            module.addAttribute("loans", loanService.selectByUserid(userid));
        }

        module.addAttribute("users", userService.selectAll());
        module.addAttribute("devices", deviceService.selectAll());
        return "user/loanList";
    }

    @RequestMapping("/returnDevice")
    public String returnDevice(Integer loanid, Integer userid) {
        // 根据借用记录id在借用表中将实际归还日期改为当前日期
        loanService.updateActualReturnDate(loanid);
        return "redirect:/user/loanList" + "?userid=" + userid;
    }

    @RequestMapping("/echarts")
    public String echarts() {
        return "user/echarts";
    }

    //借用统计
    @RequestMapping("/getChartByLoanStatus")
    @ResponseBody
    public List<Chart> getChartByLoanStatus(Integer userid){
        System.out.println(userid);
        System.out.println(loanService.getChartByLoanStatus(userid));
        return loanService.getChartByLoanStatus(userid);
    }

    @RequestMapping("/getChartByStatus")
    @ResponseBody
    public List<Chart> getChartByStatus(){
        return deviceService.getChartByStatus();
    }
}