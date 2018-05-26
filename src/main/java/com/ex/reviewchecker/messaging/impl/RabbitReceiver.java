package com.ex.reviewchecker.messaging.impl;

import com.ex.reviewchecker.ReviewDto;
import com.ex.reviewchecker.ReviewVerifier;
import com.ex.reviewchecker.messaging.Publisher;
import com.ex.reviewchecker.messaging.Receiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitReceiver implements Receiver {

    private Publisher publisher;

    private ReviewVerifier reviewVerifier;

    private ObjectMapper objectMapper;

    protected final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    public RabbitReceiver(Publisher publisher, ReviewVerifier reviewVerifier) {
        this.reviewVerifier = reviewVerifier;
        this.publisher = publisher;
        objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "#{queue.name}")
    public void receiveMessage(String jsonReviewDto) throws IOException, InterruptedException {
        log.info("Review received " +jsonReviewDto);
        ReviewDto reviewDto = objectMapper.readValue(jsonReviewDto, ReviewDto.class);
        reviewDto = reviewVerifier.verify(reviewDto);
        jsonReviewDto = objectMapper.writeValueAsString(reviewDto);
        publisher.publish(jsonReviewDto);
    }
}
