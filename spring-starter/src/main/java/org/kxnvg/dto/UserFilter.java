package org.kxnvg.dto;

import java.time.LocalDate;

public record UserFilter(String firstname,
                         String lastname,
                         LocalDate birtDate) {
}
