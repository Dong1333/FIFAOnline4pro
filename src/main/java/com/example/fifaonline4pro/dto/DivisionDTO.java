package com.example.fifaonline4pro.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author seodong-geun
 * @since 2023/05/11
 */
@Getter
@Setter
public class DivisionDTO {
        private String name;
        private int divisionId;

        public DivisionDTO(String name, int divisionId) {
            this.name = name;
            this.divisionId = divisionId;
        }
}
