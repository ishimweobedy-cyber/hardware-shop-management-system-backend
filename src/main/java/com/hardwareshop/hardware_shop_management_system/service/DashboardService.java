package com.hardwareshop.hardware_shop_management_system.service;

import com.hardwareshop.hardware_shop_management_system.entity.SalesOrderStatus;
import com.hardwareshop.hardware_shop_management_system.repository.CounterRepository;
import com.hardwareshop.hardware_shop_management_system.repository.CustomerRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SalesOrderRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SalespersonRepository;
import com.hardwareshop.hardware_shop_management_system.repository.SoldItemRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;

@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final SalespersonRepository salespersonRepository;
    private final CounterRepository counterRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final SoldItemRepository soldItemRepository;

    public DashboardService(CustomerRepository customerRepository,
                            SalespersonRepository salespersonRepository,
                            CounterRepository counterRepository,
                            SalesOrderRepository salesOrderRepository,
                            SoldItemRepository soldItemRepository) {
        this.customerRepository = customerRepository;
        this.salespersonRepository = salespersonRepository;
        this.counterRepository = counterRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.soldItemRepository = soldItemRepository;
    }

    public LinkedHashMap<String, Long> getSummary() {
        LinkedHashMap<String, Long> summary = new LinkedHashMap<>();
        summary.put("customers", customerRepository.count());
        summary.put("salespersons", salespersonRepository.count());
        summary.put("freeCounters", (long) counterRepository.findFreeCounters().size());
        summary.put("ordersToday", salesOrderRepository.countByOrderDate(LocalDate.now()));
        summary.put("placed", salesOrderRepository.countByStatusEquals(SalesOrderStatus.PLACED));
        summary.put("soldItems", soldItemRepository.count());
        return summary;
    }
}
