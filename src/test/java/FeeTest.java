import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class FeeTest {

        @Test
        void testFeeStatus() {
            Instant now = Instant.now();
            Student student = new Student(1, "John", "Doe", now, List.of());
            Fee fee = new Fee(1, "Frais d'inscription", 1000, now.plus(5, ChronoUnit.DAYS), student);

            assertEquals(FeeStatus.NULL, fee.getStatus(now));

            fee.addPayment(new Payment(1, 500, now));
            assertEquals(FeeStatus.IN_PROGRESS, fee.getStatus(now));

            fee.addPayment(new Payment(2, 500, now));
            assertEquals(FeeStatus.PAID, fee.getStatus(now));

            fee.addPayment(new Payment(3, 200, now));
            assertEquals(FeeStatus.OVERPAID, fee.getStatus(now));
        }
    }