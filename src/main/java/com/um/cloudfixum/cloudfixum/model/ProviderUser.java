package com.um.cloudfixum.cloudfixum.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.um.cloudfixum.cloudfixum.common.Constant;
import com.um.cloudfixum.cloudfixum.common.Identificable;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ProviderUser implements Serializable, Identificable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = Constant.DNI_NEEDED)
    @Size(min = 5, max = 9, message = Constant.DNI_CHARACTERS)
    private String dni;

    @NotEmpty(message = Constant.NAME_NEEDED)
    @Size(min = 3, max = 40, message = Constant.NAME_CHARACTERS)
    private String name;

    @NotEmpty(message = Constant.LAST_NAME_NEEDED)
    @Size(min = 3, max = 40, message = Constant.LAST_NAME_CHARACTERS)
    private String last_name;

    @NotEmpty(message = Constant.EMAIL_NEEDED)
    @Email(message = Constant.EMAIL_FORMAT)
    private String email;

    @NotEmpty(message = Constant.PASSWORD_NEEDED)
    @Size(min = 8, message = Constant.PASSWORD_CHARACTERS)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty(message = Constant.PHONE_NUMBER_NEEDED)
    @Size(min = 6, max = 15, message = Constant.MESSAGE_PHONE_NUMBER)
    private String phone_number;

    @NotEmpty(message = Constant.ADDRESS_NEEDED)
    @Size(min = 6, max = 40, message = Constant.ADDRESS_CHARACTERS)
    private String address;

    @NotEmpty(message = Constant.LOCATION_NEEDED)
    @Size(min = 6, max = 40, message = Constant.LOCATION_CHARACTERS)
    private String location;

    @DateTimeFormat(pattern = Constant.FORMAT_DATE)
    @JsonFormat(pattern = Constant.FORMAT_DATE)
    private LocalDate birthday;

    @OneToMany(mappedBy = Constant.SERVICE_PROVIDER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<MinorJob> serviceList;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
