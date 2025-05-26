package com.ykomarnytskyi2022.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentSearchCriteriaDto;
import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.dao.repositories.ShipmentRepo;
import com.ykomarnytskyi2022.dao.repositories.specifications.ShipmentSpecifications;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

@ExtendWith(MockitoExtension.class)
class ShipmentManagementServiceImplTest {

	@Mock
    private ShipmentRepo shipmentRepo;
	
	@Mock
	private ModelMapper mapper;
	
    @InjectMocks
    private ShipmentManagementService shipmentService = new ShipmentManagementServiceImpl(shipmentRepo, mapper);

    private Shipment shipment;
    private ShipmentDto shipmentDto;
    private ShipmentSearchCriteriaDto criteria;
    private Specification<Shipment> specification;
    private Pageable pageable;
    private final int standardPageSize = 10;

    @BeforeEach
    void setUp() {
        // Initialize test data
        shipment = new Shipment(1L);
        shipment.setOrigin("New York");
        shipment.setDestination("Los Angeles");
        shipment.setStatus(ShipmentStatus.IN_TRANSIT);
        shipment.setPickedUpAt(LocalDateTime.of(2025, 5, 20, 20 , 20));
        shipment.setDeliveredAt(LocalDateTime.of(2025, 5, 22, 22, 22));

        shipmentDto = new ShipmentDto();
        shipmentDto.setId(1L);
        shipmentDto.setOrigin("New York");
        shipmentDto.setDestination("Los Angeles");
        shipmentDto.setStatus(ShipmentStatus.IN_TRANSIT);
        shipmentDto.setPickedUpAt(LocalDateTime.of(025, 5, 20, 20 , 20));
        shipmentDto.setDeliveredAt(LocalDateTime.of(2025, 5, 22, 22, 22));
        
        criteria = mock(ShipmentSearchCriteriaDto.class);
        specification = ShipmentSpecifications.withCriteria(criteria);
        pageable = PageRequest.of(0, standardPageSize);
    }

    @Test
    void testSearchShipments_EmptyCriteria_ReturnsAllShipments() {
        // Arrange
    	List<Shipment> shipments = List.of(shipment);
    	Page<Shipment> page = new PageImpl<>(shipments, pageable, shipments.size());
    	
        when(criteria.hasIdCriteria()).thenReturn(false);
        when(criteria.hasOriginCriteria()).thenReturn(false);
        when(criteria.hasDestinationCriteria()).thenReturn(false);
        when(criteria.hasStatusCriteria()).thenReturn(false);
        when(criteria.hasPickedUpAtCriteria()).thenReturn(false);
        when(criteria.hasDeliveredAtCriteria()).thenReturn(false);
        when(shipmentRepo.findAll(specification, pageable)).thenReturn(page);

        // Act
        PageableDto<ShipmentDto> result = shipmentService.searchShipments(criteria, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.page().size());
        assertEquals(shipmentDto, result.page().get(0));
        assertEquals(1, result.totalElements());
        assertEquals(0, result.currentPage());
        assertEquals(1, result.totalPages()); // (1 + 10 - 1) / 10 = 1
        verify(shipmentRepo).findAll(any(Specification.class));
    }
//
//    @Test
//    void testSearchShipments_ById_ReturnsMatchingShipment() {
//        // Arrange
//        when(criteria.hasIdCriteria()).thenReturn(true);
//        when(criteria.id()).thenReturn(1L);
//        when(criteria.hasOriginCriteria()).thenReturn(false);
//        when(criteria.hasDestinationCriteria()).thenReturn(false);
//        when(criteria.hasStatusCriteria()).thenReturn(false);
//        when(criteria.hasPickedUpAtCriteria()).thenReturn(false);
//        when(criteria.hasDeliveredAtCriteria()).thenReturn(false);
//        when(shipmentRepo.findAll(any(Specification.class) )).thenReturn(List.of(shipment));
//
//        // Act
//        PageableDto<ShipmentDto> result = shipmentService.searchShipments(criteria, pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.page().size());
//        assertEquals(1L, result.page().get(0).getId());
//        assertEquals(1, result.totalElements());
//        assertEquals(0, result.currentPage());
//        assertEquals(1, result.totalPages());
//        verify(shipmentRepo).findAll(any(Specification.class));
//    }
//
//    @Test
//    void testSearchShipments_ByOriginAndStatus_ReturnsMatchingShipment() {
//        // Arrange
//        when(criteria.hasIdCriteria()).thenReturn(false);
//        when(criteria.hasOriginCriteria()).thenReturn(true);
//        when(criteria.origin()).thenReturn("%New York%");
//        when(criteria.hasDestinationCriteria()).thenReturn(false);
//        when(criteria.hasStatusCriteria()).thenReturn(true);
//        when(criteria.status()).thenReturn(ShipmentStatus.IN_TRANSIT);
//        when(criteria.hasPickedUpAtCriteria()).thenReturn(false);
//        when(criteria.hasDeliveredAtCriteria()).thenReturn(false);
//        when(shipmentRepo.findAll(any(Specification.class))).thenReturn(List.of(shipment));
//
//        // Act
//        PageableDto<ShipmentDto> result = shipmentService.searchShipments(criteria, pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.page().size());
//        assertEquals("New York", result.page().get(0).getOrigin());
//        assertEquals(ShipmentStatus.IN_TRANSIT, result.page().get(0).getStatus());
//        assertEquals(1, result.totalElements());
//        assertEquals(0, result.currentPage());
//        assertEquals(1, result.totalPages());
//        verify(shipmentRepo).findAll(any(Specification.class));
//    }
//
//    @Test
//    void testSearchShipments_NoMatchingShipments_ReturnsEmptyList() {
//        // Arrange
//        when(criteria.hasIdCriteria()).thenReturn(true);
//        when(criteria.id()).thenReturn(999L); // Non-existent ID
//        when(criteria.hasOriginCriteria()).thenReturn(false);
//        when(criteria.hasDestinationCriteria()).thenReturn(false);
//        when(criteria.hasStatusCriteria()).thenReturn(false);
//        when(criteria.hasPickedUpAtCriteria()).thenReturn(false);
//        when(criteria.hasDeliveredAtCriteria()).thenReturn(false);
//        when(shipmentRepo.findAll(any(Specification.class))).thenReturn(Collections.emptyList());
//
//        // Act
//        PageableDto<ShipmentDto> result = shipmentService.searchShipments(criteria, pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(result.page().isEmpty());
//        assertEquals(0, result.totalElements());
//        assertEquals(0, result.currentPage());
//        assertEquals(0, result.totalPages()); // (0 + 10 - 1) / 10 = 0
//        verify(shipmentRepo).findAll(any(Specification.class));
//    }
//
//    @Test
//    void testSearchShipments_NullCriteria_HandlesGracefully() {
//        // Act
//        PageableDto<ShipmentDto> result = shipmentService.searchShipments(null, pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(result.page().isEmpty());
//        assertEquals(0, result.totalElements());
//        assertEquals(0, result.currentPage());
//        assertEquals(0, result.totalPages());
//        verify(shipmentRepo).findAll(any(Specification.class));
//    }
//
//    @Test
//    void testSearchShipments_MultipleResults_VerifiesTotalPages() {
//        // Arrange
//        Shipment shipment2 = new Shipment(2L);
//        shipment2.setOrigin("Chicago");
//        shipment2.setDestination("Miami");
//        shipment2.setStatus(ShipmentStatus.DELIVERED);
//        ShipmentDto shipmentDto2 = new ShipmentDto();
//        shipmentDto2.setId(2L);
//        shipmentDto2.setOrigin("Chicago");
//        shipmentDto2.setDestination("Miami");
//        shipmentDto2.setStatus(ShipmentStatus.DELIVERED);
//
//        when(criteria.hasIdCriteria()).thenReturn(false);
//        when(criteria.hasOriginCriteria()).thenReturn(false);
//        when(criteria.hasDestinationCriteria()).thenReturn(false);
//        when(criteria.hasStatusCriteria()).thenReturn(true);
//        when(criteria.status()).thenReturn(ShipmentStatus.DELIVERED);
//        when(criteria.hasPickedUpAtCriteria()).thenReturn(false);
//        when(criteria.hasDeliveredAtCriteria()).thenReturn(false);
//        when(shipmentRepo.findAll(any(Specification.class))).thenReturn(List.of(shipment, shipment2));
//
////        // Override mapShipmentToDto to handle multiple shipments
////        shipmentService = new ShipmentService(shipmentRepo, new ShipmentMapper()) {
////            protected ShipmentDto mapShipmentToDto(Shipment s) {
////                return s.getId().equals(1L) ? shipmentDto : shipmentDto2;
////            }
////
////            protected PageableDto<ShipmentDto> getStandardSizedPageableDto(List<ShipmentDto> page) {
////                return new PageableDto<>(page, page.size(), PageableDto.FIRST_PAGE_INDEX,
////                        PageableDto.calculateTotalPages(page.size(), standardPageSize));
////            }
////        };
//
//        // Act
//        PageableDto<ShipmentDto> result = shipmentService.searchShipments(criteria, pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.page().size());
//        assertEquals(2, result.totalElements());
//        assertEquals(0, result.currentPage());
//        assertEquals(1, result.totalPages()); // (2 + 10 - 1) / 10 = 1
//        assertTrue(result.page().contains(shipmentDto));
//        assertTrue(result.page().contains(shipmentDto2));
//        verify(shipmentRepo).findAll(any(Specification.class));
//    }

}
