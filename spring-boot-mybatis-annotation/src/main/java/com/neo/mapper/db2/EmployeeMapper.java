package com.neo.mapper.db2;
import com.neo.model.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;

@Mapper
public interface EmployeeMapper {
	
	@Select("SELECT empId,empName,empAge,birth FROM yepengfei.EMPLOYEE")
	@Results({
		@Result(property = "empId",  column = "empId",jdbcType = JdbcType.INTEGER),
		@Result(property = "empName", column = "empName",jdbcType = JdbcType.VARCHAR),
			@Result(property = "empAge", column = "empAge",jdbcType = JdbcType.INTEGER),
			@Result(property = "birth", column = "birth",jdbcType = JdbcType.DATE)

	})
	List<Employee> getAll();


  // 某个时间之后的所有数据
	@Select("select * from yepengfei.EMPLOYEE where birth between  to_date(#{birth},'yyyy-mm-dd hh24:mi:ss') and to_date(#{birth},'yyyy-mm-dd hh24:mi:ss')+ 10 YEARS")
	@Results({
			@Result(property = "empId",  column = "empId",jdbcType = JdbcType.INTEGER),
			@Result(property = "empName", column = "empName",jdbcType = JdbcType.VARCHAR),
			@Result(property = "empAge", column = "empAge",jdbcType = JdbcType.INTEGER),
			@Result(property = "birth", column = "birth",jdbcType = JdbcType.DATE)

	})
	List<Employee> getAllByTimes(@Param("birth") String birth);







}