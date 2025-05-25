package com.ykomarnytskyi2022.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.repositories.DriverRepo;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {
	@Mock
	private DriverRepo driverRepo;
	
	@Mock 
	private ModelMapper mapper;
	
	@Mock
	private ShipmentManagementService shipmentService;
	
	@InjectMocks
	private DriverServiceImpl driverService;
	
	@Mock
	private DriverDto driverDto;
	
	@Mock
	private Driver driverEntity;
	
	@Mock
	private DriverShipmentDto driverShipmentDto;
	
	private Long driverId = 1L;
	
	@Mock
	private ShipmentDto shipmentDto;
	
	@Nested
	class TestsWithValidArgs {
		@Test
		@DisplayName("getDriverShipments with valid Id")
		void testGetDriverShipments() {
			List<ShipmentDto> shipments = Arrays.asList(shipmentDto, new ShipmentDto());
			PageableDto<ShipmentDto> shipmentPageableDto = new PageableDto<>(shipments, shipments.size(), 0, 1);
			when(shipmentService.getShipmentsAssignedToDriver(driverId)).thenReturn(shipmentPageableDto);

			PageableDto<DriverShipmentDto> result = driverService.getDriverShipments(driverId);

			assertNotNull(result);
			assertEquals(shipments.size(), result.page().size());
			verify(shipmentService, times(1)).getShipmentsAssignedToDriver(driverId);
		}
		
		@Test
		@DisplayName("testFindDriverByCellPhone with valid phone number")
		void testFindDriverByCellPhone() {
			Optional<DriverDto> expected = Optional.of(driverDto);
			when(driverRepo.findDriverByCellPhone(anyString())).thenReturn(Optional.of(driverEntity));
			when(mapper.map(driverEntity, DriverDto.class)).thenReturn(driverDto);
			
			Optional<DriverDto> actual = driverService.findDriverByCellPhone("12345678910");
			
			assertNotNull(actual.get());
			assertEquals(expected.get(), actual.get());
		}
		
		@Test
		@DisplayName("testFindDriverById with a valid id")
		void testFindDriverById() {
			when(driverRepo.findById(driverId)).thenReturn(Optional.of(driverEntity)); 
			when(mapper.map(driverEntity, DriverDto.class)).thenReturn(driverDto);
			Optional<DriverDto> expected = Optional.of(driverDto);

			Optional<DriverDto> actual = driverService.findDriverById(driverId);

			assertTrue(actual.isPresent());
			assertEquals(expected.get(), actual.get());
		}
		
		@Test
		@DisplayName("testAcceptShipment with a valid DriverShipmentDto arg")
		void testAcceptShipment() {
			DriverShipmentDto dto = new DriverShipmentDto(driverId);
			driverService.acceptShipment(dto);
			
			verify(shipmentService, times(1)).assignDriverForShipment(dto, driverId);
		}
		
		@Test
		@DisplayName("testUpdateProgressOnShipment with valid shipmentId and status")
		void testUpdateProgressOnShipment() {
			when(shipmentService.findShipmentById(anyLong())).thenReturn(Optional.of(shipmentDto));
			
			driverService.updateProgressOnShipment(1L, ShipmentStatus.ASSIGNED);
			
			verify(shipmentService, times(1)).findShipmentById(anyLong());
			verify(shipmentService, times(1)).updateShipment(any());
		}
	}
	
	@Nested
	class TestsWithInvalidArgs {
		@Test
		@DisplayName("Pass null as driverId to getDriverShipments")
		void testGetDriverShipments() {
			PageableDto<ShipmentDto> emptyPageable = new PageableDto<>(Collections.emptyList(), 0, 0, 0);
			when(shipmentService.getShipmentsAssignedToDriver(null)).thenReturn(emptyPageable);
			
			PageableDto<DriverShipmentDto> actual = driverService.getDriverShipments(null);
			
			assertNotNull(actual);
			assertEquals(0, actual.page().size());
		}
		
		@Test
		@DisplayName("Pass null as cellPhone to findDriverByCellPhone")
		void testFindDriverByCellPhone() {
			Optional<DriverDto> expected = Optional.empty();
			
			Optional<DriverDto> actual = driverService.findDriverByCellPhone(null);
			
			assertNotNull(actual);
			assertEquals(expected, actual);
			
		}
		
		@Test
		@DisplayName("Pass null as id to findDriverById")
		void testFindDriverById() {
			when(driverRepo.findById(null)).thenThrow(IllegalArgumentException.class);
			assertThrows(IllegalArgumentException.class, () -> driverService.findDriverById(null));
		}
		
		@Test
		@DisplayName("Pass null as driverShipment to testAcceptShipment")
		void testAcceptShipment() {
			assertThrows(IllegalArgumentException.class, () -> driverService.acceptShipment(null));
			verify(shipmentService, never()).assignDriverForShipment(any(), anyLong());
		}
		
		@Test
		@DisplayName("Pass null as shipmentId and status to testUpdateProgressOnShipment")
		void testUpdateProgressOnShipment() {
			when(shipmentService.findShipmentById(null)).thenThrow(IllegalArgumentException.class);
			when(shipmentService.findShipmentById(anyLong())).thenThrow(EntityNotFoundException.class);
			
			assertThrows(IllegalArgumentException.class, () -> shipmentService.findShipmentById(null));
			assertThrows(IllegalArgumentException.class, () -> driverService.updateProgressOnShipment(1L, null));
			assertThrows(EntityNotFoundException.class, ()-> shipmentService.findShipmentById(anyLong()));
		}
	}
}
