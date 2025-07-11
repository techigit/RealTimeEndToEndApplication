package com.org.java.app.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.org.java.app.dto.EmployeeDto;
import com.org.java.app.entity.Employee;
import com.org.java.app.mapper.EmployeeMapper;
import com.org.java.app.repository.EmployeeRepository;
import com.org.java.app.serviceimpl.EmployeeImplService;

@SpringBootTest
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeImplService employeeService;
	
	@Test
	public void saveEmployeeTest() {
		Employee emp=new Employee(1, "rajee", 40, 48750, "MANAGER",".net", "AI", 99486050, "RAJ@123GMAIL.COM", 503, "KLMN");
		EmployeeDto emp1 = EmployeeMapper.INSTANCE.employeeToEmployeeDto(emp);
		when(employeeRepository.save(emp)).thenReturn(emp);
		assertEquals(emp, employeeService.saveEmployeeDetails(emp1));
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee emp=new Employee(1, "rajee", 40, 48750, "MANAGER",".net", "AI", 99486050, "RAJ@123GMAIL.COM", 503, "KLMN");
		when(employeeRepository.save(emp)).thenReturn(emp);
		assertEquals(emp, employeeService.updateEmployeeDetails(emp));
	}
	
	@Test
	public void findByNameTest() {
		String name="srinu";
		when(employeeRepository.findByEmpName(name)).thenReturn(Stream.of(new Employee(101,"srinu",35,55520,"pa", "java","it",967691908,"sri@gmail.com",
						 501, "ABCDE")).collect(Collectors.toList()));
		assertEquals(1,employeeService.findByName(name).size());			 
	}	
	@Test
	public void findByDeptNameTest() {
		String name="IT";
		when(employeeRepository.findByDeptName(name)).thenReturn(Stream.of(new Employee(101,"srinu",35,55520,"pa", "java","it",967691908,"sri@gmail.com",
						 501, "ABCDE")).collect(Collectors.toList()));
		assertEquals(1,employeeService.findByDepartmentDetails(name).size());			 
	}
	
	@Test
	public void findByIdEmployeeTest() {
		int employeeId = 1;
		Employee emp = new Employee(1,"srinu",35,55520,"pa", "java","it",967691908,"sri@gmail.com",
				 501, "ABCDE");
		doReturn(Optional.of(emp)).when(employeeRepository).findByEmpId(employeeId);
		Optional<Employee> emp1 = employeeService.findByEmployeeIdDeatails(employeeId);
		assertNotNull(emp1, "Employee with employeeId : " + employeeId + " not found");

		assertEquals(employeeId, emp1.get().getEmpId());
		assertEquals(emp.getEmpName(), emp1.get().getEmpName());
		assertEquals(emp.getAge(), emp1.get().getAge());
		assertEquals(emp.getSalary(), emp1.get().getSalary());
		assertEquals(emp.getDesignation(), emp1.get().getDesignation());
		assertEquals(emp.getPlateform(), emp1.get().getPlateform());
		assertEquals(emp.getSector(), emp1.get().getSector());
		assertEquals(emp.getMobbileNumber(), emp1.get().getMobbileNumber());
		assertEquals(emp.getEmail(), emp1.get().getEmail());
		assertEquals(emp.getDepartmentId(), emp1.get().getDepartmentId());
		assertEquals(emp.getDeptName(), emp1.get().getDeptName());
	}
	
	@Test
	public void findAllEmployeeTest() {
		when(employeeRepository.findAll()).thenReturn(Stream.of(new
				 Employee(101,"srinu",35,55520,"pa", "java","it",967691908,"sri@gmail.com",
						 501, "ABCDE"),new
				 Employee(102,"naresh",38,45520,"tl", "sap","non-it",970548588,"nare@gmail.com",
						 502, "xyz")).collect(Collectors.toList()));
		assertEquals(2, employeeService.findAllEmployeeDetails().size());	
	}
	

}
