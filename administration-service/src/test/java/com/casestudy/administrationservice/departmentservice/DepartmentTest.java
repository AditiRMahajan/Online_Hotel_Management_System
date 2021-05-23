package com.casestudy.administrationservice.departmentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
 
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.casestudy.administrationservice.model.Departments;
import com.casestudy.administrationservice.service.DepartmentServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class DepartmentTest {

    DepartmentServiceImpl deptServiceImplMock = mock(DepartmentServiceImpl.class);
 
    @Test
    @DisplayName("Test for adding department")
    public void addDepartmentTest(){
        when(deptServiceImplMock.addDepartment(1100, "Front Office", "operational", "Santosh")).thenReturn("Successfully added");
        assertEquals("Successfully added", deptServiceImplMock.addDepartment(1100, "Front Office", "operational", "Santosh"));
    }
 
    @Test
    @DisplayName("Test for updating department")
    public void updateDepartmentTest(){
        when(deptServiceImplMock.updateDepartment(1100, "Front Office", "operational", "Santosh")).thenReturn("Successfully updated");
        assertEquals("Successfully updated", deptServiceImplMock.updateDepartment(1100, "Front Office", "operational", "Santosh"));
    }

    @Test
    @DisplayName("Test for deleting department")
    public void deleteDepartmentTest(){
        when(deptServiceImplMock.deleteDepartment(1100)).thenReturn("Successfully deleted");
        assertEquals("Successfully deleted", deptServiceImplMock.deleteDepartment(1100));
    }

    @Test
    @DisplayName("Test for findAllDepartments")
    public void findAllDepartmentsTest(){
        when(deptServiceImplMock.viewDepartments()).thenReturn(Stream
        .of(new Departments(null, 1100, "Front Office", "operational", "Santosh"), new Departments(null, 1200, "Back Office", "operational", "Mahesh"))
        .collect(Collectors.toList()));
        assertEquals(2, deptServiceImplMock.viewDepartments().size());
    }
   
    
}
