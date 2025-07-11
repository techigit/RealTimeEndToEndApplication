package com.org.java.app.eventhandller;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.org.java.app.events.EmployeeEvent;

@Component
public class EmployeeServiceEventHandller {

   
    @EventListener
    @Async
    public void cleanAndAssign(EmployeeEvent event) {
        // Prepare the room for the next patient
        System.out.println("Employee Service: test service 1 "
                +event.getEmpId()+" : "+Thread.currentThread().getName());
    }
}