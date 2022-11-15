package com.novopacksv.production.service.user;

import com.novopacksv.production.exception.ResourceNotFoundException;
import com.novopacksv.production.repository.userRepository.TableModificationRepository;
import com.novopacksv.production.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novopacksv.production.model.userModel.TableModification;
import com.novopacksv.production.model.userModel.TableType;

@Service
@Transactional
@RequiredArgsConstructor
public class TableModificationServiceImpl implements TableModificationService {

    private final TableModificationRepository tableModificationRepository;

    private final SecurityService service;

    @Override
    @Transactional(readOnly = true)
    public TableModification findOne(TableType tableType) {
        return tableModificationRepository.findByTableType(tableType).orElseThrow(() -> {
            String message = String.format("Table type %s is not found!", tableType);
            return new ResourceNotFoundException(message);
        });
    }

    @Override
    public TableModification update(TableType tableType) {
        TableModification entity = findOne(tableType);
        entity.setUser(service.getLoggedInUser());
        return tableModificationRepository.save(entity);
    }

}