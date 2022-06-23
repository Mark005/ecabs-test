package org.ecabs.booking.producerservice.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "rabbit")
public class RabbitProperties {

    private String messageExchangeName;
    private String bookingExchangeName;

    private String addMessageRoutingKey;
    private String editMessageRoutingKey;
    private String deleteMessageRoutingKey;
}
