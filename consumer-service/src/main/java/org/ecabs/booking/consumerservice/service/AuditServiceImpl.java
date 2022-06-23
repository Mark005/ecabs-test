package org.ecabs.booking.consumerservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuditServiceImpl implements AuditService {

    @Override
    public void auditMessage(Object object) {
        log.debug("Consumed audit message: {}", object);
    }
}
