package com.skia.lab.Components;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {
    private final MockDataGenerator mockDataGenerator;

    public ApplicationStartupListener(MockDataGenerator mockDataGenerator) {
        this.mockDataGenerator = mockDataGenerator;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        mockDataGenerator.generateMockData();
    }
}
