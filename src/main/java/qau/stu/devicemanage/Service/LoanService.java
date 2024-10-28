package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Entity.Loan;
import qau.stu.devicemanage.Mapper.LoanMapper;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanMapper loanMapper;

    public List<Loan> selectLoan(String name, Integer type) {
        return loanMapper.selectLoan(name, type);
    }

    public void save(Loan loan) {
        loanMapper.save(loan);
    }

    public Loan selectById(Integer id) {
        return loanMapper.selectById(id);
    }

    public void delete(Integer id) {
        loanMapper.delete(id);
    }

    public List<Chart> getChartByLoanStatus(Integer userid) {
        return loanMapper.getChartByLoanStatus(userid);
    }

    public void updateActualReturnDate(Integer loanid) {
        loanMapper.updateActualReturnDate(loanid);
    }

    public List<Loan> selectByUserid(Integer userid) {
        return loanMapper.selectByUserid(userid);
    }

    public List<Loan> selectByDeviceName_Userid(String name, Integer userid) {
        return loanMapper.selectByDeviceName_Userid(name, userid);
    }

    public void insert(Integer deviceid, Integer userid, Integer borrowQuantity) {
        loanMapper.insert(deviceid, userid, borrowQuantity);
    }
}
