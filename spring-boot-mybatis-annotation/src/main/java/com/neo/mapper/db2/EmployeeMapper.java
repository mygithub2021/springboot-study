package com.neo.mapper.db2;
import com.neo.model.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;

@Mapper
public interface EmployeeMapper {
	
	@Select("SELECT empId,empName,empAge FROM yepengfei.EMPLOYEE")
	@Results({
		@Result(property = "empId",  column = "empId",jdbcType = JdbcType.INTEGER),
		@Result(property = "empName", column = "empName",jdbcType = JdbcType.VARCHAR),
			@Result(property = "empAge", column = "empAge",jdbcType = JdbcType.INTEGER)
	})
	List<Employee> getAll();

}