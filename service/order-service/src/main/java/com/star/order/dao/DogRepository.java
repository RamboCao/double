package com.star.order.dao;

import com.star.order.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Caolp
 */
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
