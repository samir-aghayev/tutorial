package az.atlacademy.tutorials.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {

    @Id
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    @Value("false")
    private Boolean published;







}
