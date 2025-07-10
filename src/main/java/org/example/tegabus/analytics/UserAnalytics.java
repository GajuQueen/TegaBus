package org.example.tegabus.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAnalytics {
    private long totalUser;
    private long adminCount;
    private long userCount;
    private long driverCount;
}
