package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Entity.Loan;

import java.util.List;

@Mapper
public interface LoanMapper {
    @Select("call GetLoanDetails(#{name}, #{type})")
    List<Loan> selectLoan(@Param("name")String name, @Param("type")Integer type);

    @Select("call SaveOrUpdateLoan(#{loan.loanId}, #{loan.userId}, #{loan.deviceId}, #{loan.loanDate}, #{loan.expectedReturnDate}, #{loan.actualReturnDate}, #{loan.loanQuantity})")
    void save(@Param("loan")Loan loan);

    @Select("select * from loan where loanId = #{id}")
    Loan selectById(Integer id);

    @Select("delete from loan where loanId = #{id}")
    void delete(Integer id);

    @Select("call CountLoanStatusByUserId(#{userid})")
    List<Chart> getChartByLoanStatus(Integer userid);

    @Select("update loan set actualReturnDate = now() where loanId = #{loanid}")
    void updateActualReturnDate(Integer loanid);

    @Select("select * from loan where userId = #{userid}")
    List<Loan> selectByUserid(Integer userid);

    @Select("select * from loan where deviceId in (select deviceId from device where deviceName like concat('%', #{name}, '%')) and userId = #{userid}")
    List<Loan> selectByDeviceName_Userid(String name, Integer userid);

    @Insert("insert into loan (deviceId, userId, loanQuantity, loanDate, expectedReturnDate) values (#{deviceid}, #{userid}, #{borrowQuantity}, now(), date_add(now(), interval 1 month))")
    void insert(Integer deviceid, Integer userid, Integer borrowQuantity);
}
