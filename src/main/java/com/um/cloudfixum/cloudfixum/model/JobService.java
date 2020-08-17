package com.um.cloudfixum.cloudfixum.model;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import com.um.cloudfixum.cloudfixum.common.Identificable;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class JobService implements Serializable, Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title must be between 5 and 50 characters long.")
    @Size(min = 5, max = 50)
    private String title;

    @NotNull(message = "Category can't be null.")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "Price can't be null.")
    private Long base_price;

    @NotEmpty(message = "You must provide a description.")
    @Size(max = 256, message = "Description can't exceed 256 characters long.")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


    private String image_url;
}
