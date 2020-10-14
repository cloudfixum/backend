package com.um.cloudfixum.cloudfixum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.um.cloudfixum.cloudfixum.common.Constant;
import com.um.cloudfixum.cloudfixum.common.Identificable;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class MinorJob implements Serializable, Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = Constant.TITLE_PROVIDE)
    @Size(min = 5, max = 50, message = Constant.TITLE_CHARACTERS)
    private String title;

    @NotNull(message = Constant.MESSAGE_CATEGORY)
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = Constant.PRICE_NOT_NULL)
    private Long base_price;

    @NotEmpty(message = Constant.PROVIDE_DESCRIPTION)
    @Size(max = 256, message = Constant.DESCRIPTION_CHARACTERS_LONG)
    private String description;

    @DateTimeFormat(pattern = Constant.FORMAT_DATE)
    @JsonFormat(pattern = Constant.FORMAT_DATE)
    private LocalDate date;

    private String image_url;

    @ManyToOne
    private ProviderUser serviceProvider;

    @OneToMany(mappedBy = "minorJob", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Budget>budgetList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MinorJob)) return false;

        MinorJob minorJob = (MinorJob) o;

        if (!getTitle().equals(minorJob.getTitle())) return false;
        if (getCategory() != minorJob.getCategory()) return false;
        return getDescription().equals(minorJob.getDescription());

    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getServiceProvider().hashCode();
        return result;
    }
}
