import org.crs.enums.CarType;
import org.crs.model.Reservation;
import org.crs.model.Sedan;
import org.crs.service.CarInventory;
import org.crs.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private CarInventory inventory;
    private ReservationService service;

    @BeforeEach
    void setUp() {
        inventory = new CarInventory();
        inventory.addCar(new Sedan());
        inventory.addCar(new Sedan());
        service = new ReservationService(inventory);
    }

    @Test
    void testReserveSuccessfully() {
        Reservation r1 = service.reserveCar(CarType.SEDAN, LocalDateTime.now(), 1);
        assertNotNull(r1);
        assertEquals(CarType.SEDAN, r1.getCar().getType());
    }

    @Test
    void testOutOfStockException() {
        service.reserveCar(CarType.SEDAN, LocalDateTime.now(), 1);
        service.reserveCar(CarType.SEDAN, LocalDateTime.now(), 1);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.reserveCar(CarType.SEDAN, LocalDateTime.now(), 1);
        });

        assertTrue(exception.getMessage().contains("Out of stock"));
    }
}
