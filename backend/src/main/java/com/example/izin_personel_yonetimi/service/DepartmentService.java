package com.example.izin_personel_yonetimi.service;

import com.example.izin_personel_yonetimi.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DepartmentService {

    private final List<DepartmentDto> departments = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public DepartmentService() {
        departments.add(new DepartmentDto(idCounter.getAndIncrement(), "Yazılım"));
        departments.add(new DepartmentDto(idCounter.getAndIncrement(), "İnsan Kaynakları"));
    }

    public List<DepartmentDto> getAll() {
        return departments;
    }

    public DepartmentDto getById(Long id) {
        return departments.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public DepartmentDto create(DepartmentDto dto) {
        dto.setId(idCounter.getAndIncrement());
        departments.add(dto);
        return dto;
    }

    public DepartmentDto update(Long id, DepartmentDto dto) {
        DepartmentDto existing = getById(id);
        if (existing != null) {
            existing.setName(dto.getName());
        }
        return existing;
    }

    public boolean delete(Long id) {
        return departments.removeIf(d -> d.getId().equals(id));
    }
}