package com.quantumsyntax.dto;

import com.quantumsyntax.model.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponse {
    private BloodGroupType bloodGroupType;
    private Long count;
}
