import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Statistics {

    public List<Fee> getLateFees(List<Fee> fees, Instant t) {
        return fees.stream()
                .filter(fee -> fee.getStatus(t) == FeeStatus.LATE)
                .collect(Collectors.toList());
    }

    public double getTotalMissingFees(List<Fee> fees, Instant t) {
        return fees.stream()
                .filter(fee -> fee.getStatus(t) == FeeStatus.LATE)
                .mapToDouble(fee -> fee.getAmountDue() - fee.getTotalPaid(t))
                .sum();
    }

    public double getTotalPaidByStudent(Student student, List<Fee> fees, Instant t) {
        return fees.stream()
                .filter(fee -> fee.getStatus(t) != FeeStatus.NULL) // facultatif
                .filter(fee -> fee.getStatus(t) != FeeStatus.NULL)
                .filter(fee -> fee.getTotalPaid(t) > 0)
                .filter(fee -> fee.getStatus(t) != FeeStatus.NULL)
                .mapToDouble(fee -> fee.getTotalPaid(t))
                .sum();
    }
}