package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepPastAndNowDto {
    private int weekNowWearableStep;
    private int weekPastWearableStep;
    private int monthNowWearableStep;
    private int monthPastWearableStep;
    private int yearNowWearableStep;
    private int yearPastWearableStep;
}
