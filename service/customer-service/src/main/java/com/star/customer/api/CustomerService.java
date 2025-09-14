package com.star.customer.api;

import com.star.customer.dao.CustomerRepository;
import com.star.customer.domain.Customer;
import com.star.customer.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.attribute.standard.MediaSize;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public static void main(String[] args) {

//        sort(1, 1, 1, 1, 1, 2, 1, 1, 1);
//        sort(3, 2, 3, 2, 1, 31);
//        sort(2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);

//         exception
//        sort(1, 2, 3, 2, 2, 3, 2, 3, 2, 2, 3, 2, 3, 3, 2, 2, 2, 2, 2, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);


        for (int j = 0; j < 1; j++) {
            List<Instant> instantList = new ArrayList<>();
            List<Long> numberList = new ArrayList<>();
            Random random = new Random(779);
            for (int i = 0; i < 50; i++) {
                int randomNumber = random.nextInt(24) + 2000;
                int secondNumber = random.nextInt(49) + 10;
                Instant instant = Instant.parse(randomNumber + "-01-01T00:" + secondNumber + ":00.000Z");

                instantList.add(instant);
                numberList.add(instant.toEpochMilli());

            }
            System.out.println(numberList);
            List<Instant> results = instantList.stream().sorted((a, b) -> {
                return (int) (b.toEpochMilli() - a.toEpochMilli());
            }).collect(Collectors.toList());
        }
    }

    private static void sort(Integer... ints) {
        List<Integer> list = Arrays.asList(ints);
        list.sort(logging((o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else {
                return 1;
            }
        }));
        System.out.println(list);
    }

    static Comparator<Integer> logging(Comparator<Integer> c) {
        return (a, b) -> {
            int r = c.compare(a, b);
            System.err.printf("%14d, %14d => %14d\n", a, b, r);
            return r;
        };
    }

}

