package com.novopacksv.production.service.user;

import com.novopacksv.production.model.userModel.TableModification;
import com.novopacksv.production.model.userModel.TableType;

public interface TableModificationService {

    TableModification findOne(TableType tableType);

    TableModification update(TableType tableType);

}