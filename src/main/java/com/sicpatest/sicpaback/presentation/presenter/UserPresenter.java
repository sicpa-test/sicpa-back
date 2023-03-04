package com.sicpatest.sicpaback.presentation.presenter;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class UserPresenter {
    private UUID id;
    private  String name;
}
