package kr.infonation.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectDto {
    private Long codeId;
    private String codeName;

}
