package com.star.order.api;

import com.star.common.annotation.RateLimit;
import com.star.order.config.DataSourceNames;
import com.star.order.config.annotation.RoutingDataSource;
import com.star.order.dao.DogRepository;
import com.star.order.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DogService implements IDogService{

    @Autowired
    private DogRepository dogRepository;

    @Override
    @RoutingDataSource(routeKey = DataSourceNames.SLAVE)
    @RateLimit(name = "123", permitsPerSecond = 1, timeOut = 0, timeUnit = TimeUnit.SECONDS)
    public Dog findById(Long id) {
        return dogRepository.findById(1L).orElse(null);
    }
}
