import org.crs.domain.CarType;
import org.crs.domain.Reservation;
import org.crs.service.CarInventory;
import org.crs.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private ReservationService service;

    @BeforeEach
    public void setup() {
        CarInventory inventory = new CarInventory(
                Map.of(CarType.SEDAN, 2, CarType.SUV, 1, CarType.VAN, 1)
        );
        service = new ReservationService(inventory);
    }

    @Test
    void testReservationSuccess() {
        var reservation = service.createReservation(CarType.SEDAN, LocalDate.now(), 2);
        assertNotNull(reservation);
        assertEquals(CarType.SEDAN, reservation.getCar().getType());
    }

    @Test
    void testReservationFailureWhenNoCars() {
        service.createReservation(CarType.VAN, LocalDate.now(), 1);
        var reservation = service.createReservation(CarType.VAN, LocalDate.now(), 1);
        assertNull(reservation);
    }

    @Test
    void testReservationGetAll() {
        var reservation = service.createReservation(CarType.SEDAN, LocalDate.now(), 2);
        assertNotNull(reservation);
        assertEquals(reservation, service.getReservations().get(0));
    }

    @Test
    void testReservationCancel() {
        Optional<Reservation> reservation1 = Optional.ofNullable(service.createReservation(CarType.SEDAN, LocalDate.now(), 2));
        assertNotNull(reservation1);
        assertTrue(service.cancelReservation(reservation1));
    }
}
