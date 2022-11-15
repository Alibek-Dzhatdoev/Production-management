package com.novopacksv.production.service.report;

import com.novopacksv.production.model.reportModel.RollReport;

import java.time.LocalDate;
import java.util.List;

public interface RollReportService {

    List<RollReport> getAll(LocalDate from, LocalDate to);

}
