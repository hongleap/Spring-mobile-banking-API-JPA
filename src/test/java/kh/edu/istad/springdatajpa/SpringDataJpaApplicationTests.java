package kh.edu.istad.springdatajpa;

import kh.edu.istad.springdatajpa.repository.CustomerSegmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Autowired
    private CustomerSegmentRepository customerSegmentRepository;

    @Test
    public void testFindCustomerSegment() {
        System.out.println(customerSegmentRepository.findAll());
        /*customerSegmentRepository.findAll()
                .forEach(customerSegment ->
                        System.out.println(customerSegment.getCustomers()));*/
    }


}
