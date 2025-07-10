package org.example.tegabus.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserAnalyticsResponse {
    private long totalUsers;
    private long totalAdmins;
    private long totalDrivers;
    private long totalNormalUsers;
}
