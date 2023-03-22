package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnergyPastAndNowDto {
    private Long weekNowWearableEnergy;
    private Long weekPastWearableEnergy;
    private Long monthNowWearableEnergy;
    private Long monthPastWearableEnergy;
    private Long yearNowWearableEnergy;
    private Long yearPastWearableEnergy;
}
