package com.julioluis.trainingrest.unit;

import com.julioluis.trainingrest.entities.Attendance;
import com.julioluis.trainingrest.repositories.AttendanceRepository;
import com.julioluis.trainingrest.services.impl.AttendanceServiceImpl;
import com.julioluis.trainingrest.utils.AttendanceHelper;
import com.julioluis.trainingrest.utils.BusinessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AttendanceServiceUnitTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private AttendanceHelper attendanceHelper;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws BusinessException {
        List<Attendance> attendances= Arrays.asList(new Attendance());
        doNothing().when(attendanceHelper).validateStudent(attendances);
        when(attendanceRepository.saveAll(anyIterable())).thenReturn(attendances);

        List<Attendance> attendanceList=attendanceService.save(attendances);
        assertNotNull(attendanceList);
        assertEquals(false,attendanceList.isEmpty());

    }
}
