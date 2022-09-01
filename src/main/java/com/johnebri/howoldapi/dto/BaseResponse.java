package com.johnebri.howoldapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by John on 01/09/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private boolean success;
    private String responseMessage;
}
