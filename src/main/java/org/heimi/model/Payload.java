package org.heimi.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author heimi
 * @version 1.0
 * @description JWT的载荷
 * @date 2023-11-06 17:50
 */
@Data
public class Payload <T> {
    private String id;
    private T data;
    private Date time;
}
