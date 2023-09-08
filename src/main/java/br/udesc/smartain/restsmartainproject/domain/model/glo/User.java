package br.udesc.smartain.restsmartainproject.domain.model.glo;

import br.udesc.smartain.restsmartainproject.domain.model.states.RegisterDataState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "glo",
        name = "tbusuario",
        uniqueConstraints = {@UniqueConstraint(name = "unique_login", columnNames = {"usulogin"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usucodigo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grucodigo")
    private UserGroup userGroup;

    @Column(name = "usulogin", unique = true)
    @NotBlank(message = "O login não pode ser nulo ou estar em branco.")
    @Min(value = 8, message = "O login deve conter pelo menos 8 caracteres.")
    @Max(value = 30, message = "O login deve conter no máximo 30 caracteres.")
    private String login;

    @Column(name = "ususenha")
    private String password;

    @Column(name = "usudatcad")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "usudatalt")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

    @Column(name = "usuativo")
    private short active;

    public User(Long id, UserGroup userGroup, String login, String password, LocalDateTime createdDate, LocalDateTime updatedDate, RegisterDataState active) {
        this.id = id;
        this.userGroup = userGroup;
        this.login = login;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public RegisterDataState getActive() {
        return RegisterDataState.valueOf(active);
    }

    public void setActive(RegisterDataState active) {
        this.active = active.getValue();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userGroup=" + userGroup +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}