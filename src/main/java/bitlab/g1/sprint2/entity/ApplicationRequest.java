package bitlab.g1.sprint2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "APPLICATION_REQUESTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "COURSE_NAME")
  private String courseName;

  @Column(name = "COMMENTARY")
  private String commentary;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "HANDLED", columnDefinition = "boolean default false")
  private boolean handled;


}
