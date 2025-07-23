package kh.edu.istad.springdatajpa.init;

import jakarta.annotation.PostConstruct;
import kh.edu.istad.springdatajpa.domain.CustomerSegment;
import kh.edu.istad.springdatajpa.repository.CustomerSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void init() {

        if (customerSegmentRepository.count() == 0 ){
            CustomerSegment segmentRegular = new CustomerSegment();
            segmentRegular.setCustomerSegment("REGULAR");
            segmentRegular.setDescription("REGULAR");
            segmentRegular.setDeleted(false);

            CustomerSegment segmentSilver = new CustomerSegment();
            segmentSilver.setCustomerSegment("SILVER");
            segmentSilver.setDescription("SILVER");
            segmentSilver.setDeleted(false);

            CustomerSegment segmentGold = new CustomerSegment();
            segmentGold.setCustomerSegment("GOLD");
            segmentGold.setDescription("GOld");
            segmentGold.setDeleted(false);
        }

    }
}
