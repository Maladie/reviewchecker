package com.ex.reviewchecker.messaging.impl;

import com.ex.reviewchecker.config.ExchangeConfig;
import com.ex.reviewchecker.messaging.Publisher;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitPublisher implements Publisher {

    private RabbitTemplate rabbitTemplate;

    protected final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    public RabbitPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(String jsonReviewDto) {
        log.info("sending verified review " +jsonReviewDto);
        rabbitTemplate.convertAndSend(ExchangeConfig.topicExchangeName2, "verified", jsonReviewDto);
    }
}
