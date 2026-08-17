package com.example.izin_personel_yonetimi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Departman adı boş bırakılamaz")
    @Size(min = 2, max = 50, message = "Departman adı 2 ile 50 karakter arasında olmalıdır")
    private String name;
    public DepartmentDto() {}

    public DepartmentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}