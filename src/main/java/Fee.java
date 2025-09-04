import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Fee {
    private int id;
    private String label;
    private double amountDue;
    private Instant deadline;
    private Student student;
    private List<Payment> payments = new ArrayList<>();

    public Fee(int id, String label, double amountDue, Instant deadline, Student student) {
        this.id = id;
        this.label = label;
        this.amountDue = amountDue;
        this.deadline = deadline;
        this.student = student;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public double getTotalPaid(Instant t) {
        return payments.stream()
                .filter(p -> p.getDateTime().isBefore(t) || p.getDateTime().equals(t))
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    public FeeStatus getStatus(Instant t) {
        double totalPaid = getTotalPaid(t);
        if (totalPaid == 0) {
            return FeeStatus.NULL;
        } else if (totalPaid == amountDue) {
            return FeeStatus.PAID;
        } else if (totalPaid > amountDue) {
            return FeeStatus.OVERPAID;
        } else if (t.isAfter(deadline)) {
            return FeeStatus.LATE;
        } else {
            return FeeStatus.IN_PROGRESS;
        }
    }

    public double getAmountDue() {
        return amountDue;
    }
}