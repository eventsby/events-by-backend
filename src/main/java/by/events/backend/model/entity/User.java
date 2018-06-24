package by.events.backend.model.entity;

import by.events.backend.model.base.BaseEntity;
import by.events.backend.model.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "company")
    private String company;

    @Column(name = "website")
    private String website;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER) // cascade = {CascadeType.ALL}
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER) // cascade = {CascadeType.ALL}
    @JoinTable(name = "user_events",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")}
    )
    private List<Event> events;

    @OneToOne
    @JoinColumn(name = "organaizer_id")
    private Organaizer organaizer;

    public User() {
    }

    public User(long id) {
        super();
    }

    public User(User user) {
        super();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.fullname = user.getFullname();
        this.phone = user.getPhone();
        this.website = user.getWebsite();
        this.company = user.getCompany();
    }

    public User(String email, String username, String password, boolean enabled, String fullname, String company, String website, String phone) {
        this.email = email;
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.enabled = enabled;
        this.fullname = fullname;
        this.company = company;
        this.website = website;
        this.phone = phone;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Organaizer getOrganaizer() {
        return organaizer;
    }

    public void setOrganaizer(Organaizer organaizer) {
        this.organaizer = organaizer;
    }

    public static User toEntity(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getUsername(), userDto.getPassword(),
                userDto.isEnabled(), userDto.getFullname(), userDto.getCompany(),
                userDto.getWebsite(), userDto.getPhone());
    }

}
