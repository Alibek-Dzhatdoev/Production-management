package com.novopacksv.production.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.userModel.TableModification;
import com.novopacksv.production.model.userModel.TableType;

import java.util.Optional;

@Repository
public interface TableModificationRepository extends JpaRepository <TableModification, Long> {

    Optional<TableModification> findByTableType(TableType tableType);

}
