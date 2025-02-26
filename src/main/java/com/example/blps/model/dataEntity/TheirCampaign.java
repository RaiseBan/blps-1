package com.example.blps.model.dataEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TheirCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Partner name is required")
    @Column(nullable = false)
    private String partnerName;

    @URL(message = "Invalid URL format")
    @Column(nullable = false)
    private String imageUrl;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // Статус нельзя менять напрямую
    private CampaignStatus status;

    @AssertTrue(message = "End date must be at least one day after start date")
    public boolean isEndDateValid() {
        return endDate.isAfter(startDate.plusDays(1));
    }
}