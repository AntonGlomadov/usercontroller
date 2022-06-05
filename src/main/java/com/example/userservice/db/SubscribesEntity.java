package com.example.userservice.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "SUBSCRIBES")
public class SubscribesEntity {

    @Setter(AccessLevel.NONE)
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Setter(AccessLevel.PROTECTED)
    @ManyToOne (fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private UserEntity publisherId;

    @Setter(AccessLevel.PROTECTED)
    @ManyToOne (fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private UserEntity subscriberId;

}
