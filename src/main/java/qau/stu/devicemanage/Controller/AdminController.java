package qau.stu.devicemanage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qau.stu.devicemanage.Entity.*;
import qau.stu.devicemanage.Service.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    //

    //
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceStatusService deviceStatusService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;
    //

    //
    @Autowired
    private LoanService loanService;
    //


    @RequestMapping("/userList")
    public String userList(Model module, String name, Integer department, Integer position) {
        module.addAttribute("users", userService.selectUser(name, department, position));
        module.addAttribute("depts", departmentService.selectAll());
        module.addAttribute("positions", positionService.selectAll());
        return "admin/userList";
    }

    @RequestMapping("/toUserAdd")
    public String toAdd(Model module) {
        module.addAttribute("depts", departmentService.selectAll());
        module.addAttribute("positions", positionService.selectAll());
        return "admin/userAdd";
    }

    @RequestMapping("/userAdd")
    public String add(User user) {
        userService.save(user);
        return "redirect:/admin/userList";
    }

    @RequestMapping("/userUpdate")
    public String update(Integer id, Model module) {
        User user = userService.selectById(id);
        module.addAttribute("depts", departmentService.selectAll());
        module.addAttribute("positions", positionService.selectAll());
        module.addAttribute("user", user);
        return "admin/userAdd";
    }

    @RequestMapping("/userDelete")
    public String delete(Integer id) {
        userService.delete(id);
        return "redirect:/admin/userList";
    }


    @RequestMapping("/deviceList")
    public String deviceList(Model model, String name, Integer devicetype, Integer status) {
        model.addAttribute("devices", deviceService.selectDevice(name, devicetype, status));
        model.addAttribute("deviceTypes", deviceTypeService.selectAll());
        model.addAttribute("statuses", deviceStatusService.selectAll());

        return "admin/deviceList";
    }

    @RequestMapping("/toDeviceAdd")
    public String deviceAdd(Model module) {
        module.addAttribute("deviceTypes", deviceTypeService.selectAll());
        module.addAttribute("statuses", deviceStatusService.selectAll());
        return "admin/deviceAdd";
    }

    @RequestMapping("/deviceAdd")
    public String deviceAdd(Device device) {
        deviceService.save(device);
        return "redirect:/admin/deviceList";
    }

    @RequestMapping("/deviceUpdate")
    public String deviceUpdate(Integer id, Model module) {
        Device device = deviceService.selectById(id);
        module.addAttribute("deviceTypes", deviceTypeService.selectAll());
        module.addAttribute("statuses", deviceStatusService.selectAll());
        module.addAttribute("device", device);
        return "admin/deviceAdd";
    }

    @RequestMapping("/deviceDelete")
    public String deviceDelete(Integer id) {
        deviceService.delete(id);
        return "redirect:/admin/deviceList";
    }

    @RequestMapping("/loanList")
    public String loanList(Model module, String name, Integer type) {
        module.addAttribute("loans", loanService.selectLoan(name, type));
        module.addAttribute("users", userService.selectAll());
        module.addAttribute("devices", deviceService.selectAll());
        return "admin/loanList";
    }

    @RequestMapping("/toLoanAdd")
    public String toLoanAdd(Model model) {
        model.addAttribute("users", userService.selectAll());
        model.addAttribute("devices", deviceService.selectAll());
        return "admin/loanAdd";
    }

    @RequestMapping("/loanAdd")
    public String loanAdd(Loan loan, Model model) {
        try {
            loanService.save(loan);
            return "redirect:/admin/loanList";
        }catch (DataAccessException e){
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof SQLException sqlEx) {
                if ("Not enough devices available for loan".equals(sqlEx.getMessage())) {
                    model.addAttribute("users", userService.selectAll());
                    model.addAttribute("devices", deviceService.selectAll());
                    // 设置自定义错误消息
                    model.addAttribute("msg", "借用数量超过库存数量");
                    return "admin/loanAdd"; // 返回到借贷添加页面并显示错误消息
                }
            }
            return "redirect:/admin/loanList";
        }
    }

    @RequestMapping("/loanUpdate")
    public String loanUpdate(Integer id, Model module) {
        Loan loan = loanService.selectById(id);
        module.addAttribute("users", userService.selectAll());
        module.addAttribute("devices", deviceService.selectAll());
        module.addAttribute("loan", loan);
        return "admin/loanAdd";
    }

    @RequestMapping("/loanDelete")
    public String loanDelete(Integer id) {
        loanService.delete(id);
        return "redirect:/admin/loanList";
    }

    @RequestMapping("/maintenanceRecordList")
    public String maintenanceRecordList(Model module, String name) {
        module.addAttribute("maintenanceRecords", maintenanceRecordService.selectMaintenanceRecord(name));

        module.addAttribute("devices", deviceService.selectAll());
        return "admin/maintenanceRecordList";
    }

    @RequestMapping("/toMaintenanceRecordAdd")
    public String toMaintenanceRecordAdd(Model module) {
        module.addAttribute("devices", deviceService.selectAll());
        return "admin/maintenanceRecordAdd";
    }

    @RequestMapping("/maintenanceRecordAdd")
    public String maintenanceRecordAdd(MaintenanceRecord maintenanceRecord) {
        maintenanceRecordService.save(maintenanceRecord);
        return "redirect:/admin/maintenanceRecordList";
    }

    @RequestMapping("/maintenanceRecordUpdate")
    public String maintenanceRecordUpdate(Integer id, Model module) {
        MaintenanceRecord maintenanceRecord = maintenanceRecordService.selectById(id);
        module.addAttribute("devices", deviceService.selectAll());
        module.addAttribute("maintenanceRecord", maintenanceRecord);
        return "admin/maintenanceRecordAdd";
    }

    @RequestMapping("/maintenanceRecordDelete")
    public String maintenanceRecordDelete(Integer id) {
        maintenanceRecordService.delete(id);
        return "redirect:/admin/maintenanceRecordList";
    }

    @RequestMapping("/echarts")
    public String echarts() {
        return "admin/echarts";
    }

    @RequestMapping("/getChartByDept")
    @ResponseBody
    public List<Chart> getChartByDept(){
        return userService.getChartByDepartment();
    }

    @RequestMapping("/getChartByStatus")
    @ResponseBody
    public List<Chart> getChartByStatus(){
        return deviceService.getChartByStatus();
    }
}
