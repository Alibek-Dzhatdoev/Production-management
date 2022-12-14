package com.novopacksv.production.service.roll;

import com.novopacksv.production.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novopacksv.production.model.rollModel.RollBatch;
import com.novopacksv.production.model.rollModel.RollManufactured;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс имплементирует методы интерфейса {@code RollBatchService}.
 * <p>
 * Содержит методы возвращающие информацию о количестве произведенных, списанных в производство и оставшихся
 * на данный момент рулонов определенного типа, даты производства, периода производства и т.п.
 * <p>
 * Методы класса возвращают объект класса {@see RollBatch}, содержащий вышеуказнную информацию,
 * либо {@code List} таких объектов
 * <p>
 * Методы не изменяют содержимого базы данных
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class RollBatchServiceImpl implements RollBatchService {

    /**
     * Содержит методы для работы с сущностью {@code RollManufactured}
     */
    private final RollManufacturedService rollManufacturedService;

    /**
     * Вычисляет и возвращает информацию о количестве произведенных, списанных в производство и оставшихся
     * на данный момент рулонов для указанных типа рулона и даты производства
     *
     * @param rollTypeId       ID типа рулона
     * @param manufacturedDate дата производства рулона
     * @return {@code RollBatch} для указнного id типа рулона и даты производства
     * @throws ResourceNotFoundException если рулоны с указанными id типа рулона и датой производства не найдены
     * @throws IllegalArgumentException  если rollTypeId - null и/или manufacturedDate - null
     */
    @Override
    public RollBatch getOne(Long rollTypeId, LocalDate manufacturedDate) throws ResourceNotFoundException {
        RollManufactured rollManufactured = rollManufacturedService.findOne(manufacturedDate, rollTypeId);
        RollBatch rollBatch = getOne(rollManufactured);
        log.debug("Method getOne(Long rollTypeId, LocalDate manufacturedDate): RollBatch {} is finding " +
                        "by rollType's id {} and manufactured date {}", rollBatch,
                rollTypeId, manufacturedDate);
        return rollBatch;
    }

    /**
     * Вычисляет и возвращает информацию о количестве произведенных, списанных в производство и оставшихся
     * на данный момент рулонов указанной даты производства
     *
     * @param manufacturedDate дата производства рулона
     * @return {@code List} {@code RollBatch} для указанной даты производства или empty {@code List} если в базе
     * отсутствуют рулоны с указнной датой производства
     * @throws IllegalArgumentException если manufacturedDate - null
     */
    @Override
    public List<RollBatch> getAll(LocalDate manufacturedDate) {
        List<RollManufactured> rollManufacturedList = rollManufacturedService.findAll(manufacturedDate);
        List<RollBatch> rollBatches = getAll(rollManufacturedList);
        log.debug("Method getAll(LocalDate manufacturedDate): List<RollBatch>: {} is finding by manufactured date {}",
                rollBatches, manufacturedDate);
        return rollBatches;
    }

    /**
     * Вычисляет и возвращает информацию о количестве произведенных, списанных в производство и оставшихся
     * на данный момент рулонов, произведенных в указанный период времени
     *
     * @param fromManufacturedDate начало периода даты производства рулонов
     * @param toManufacturedDate   окончание периода даты производства рулонов
     * @return {@code List} {@code RollBatch} для рулонов, произведенных в указанный период времени
     * или empty {@code List}, если в базе отсутствуют рулоны, произведенные в указанный период
     * @throws IllegalArgumentException если fromManufacturedDate - null и/или toManufacturedDate - null
     */
    @Override
    public List<RollBatch> getAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate) {
        List<RollManufactured> rollManufacturedList =
                rollManufacturedService.findAll(fromManufacturedDate, toManufacturedDate);
        List<RollBatch> rollBatches = getAll(rollManufacturedList);
        log.debug("Method getAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate): List<RollBatch>: " +
                        "{} is finding by period from {} to {}", rollBatches,
                fromManufacturedDate, toManufacturedDate);
        return rollBatches;
    }

    /**
     * Вычисляет и возвращает информацию о количестве произведенных, списанных в производство и оставшихся
     * на данный момент рулонов указанного типа, произведенных в указанный период времени
     *
     * @param rollTypeId           ID типа рулона
     * @param fromManufacturedDate начало периода даты производства рулонов
     * @param toManufacturedDate   окончание периода даты производства рулонов
     * @return {@code List} {@code RollBatch} для рулонов указанного типа, произведенных в указанный период времени
     * или empty {@code List}, если в базе отсутствуют рулоны с указанным типом, произведенные в указанный период
     * @throws IllegalArgumentException если rollTypeId - null, и/или fromManufacturedDate - null,
     *                                  и/или toManufacturedDate - null
     */
    @Override
    public List<RollBatch> getAll(Long rollTypeId, LocalDate fromManufacturedDate, LocalDate toManufacturedDate) {
        List<RollManufactured> rollManufacturedList =
                rollManufacturedService.findAll(fromManufacturedDate, toManufacturedDate, rollTypeId);
        List<RollBatch> rollBatches = getAll(rollManufacturedList);
        log.debug("Method getAll(Long rollTypeId, LocalDate fromManufacturedDate): List<RollBatch>: {} " +
                        "is finding by rollType's id{} and for period from {} to {}",
                rollBatches, rollTypeId, fromManufacturedDate, toManufacturedDate);
        return rollBatches;
    }

    /**
     * Вычисляет и возвращает информацию о количестве произведенных, списанных в производство и оставшихся
     * на данный момент рулонов для указанного рулона {@code RollManufactured}
     *
     * @param rollManufactured рулон {@code RollManufactured}
     * @return {@code RollBatch} для указанного рулона
     * @throws IllegalArgumentException если rollManufactured - null
     */
    private RollBatch getOne(RollManufactured rollManufactured) {
        Integer manufacturedAmount = rollManufacturedService.getManufacturedRollAmount(rollManufactured);
        Integer usedAmount = rollManufacturedService.getUsedRollAmount(rollManufactured);
        RollBatch rollBatch = new RollBatch();
        rollBatch.setRollManufactured(rollManufactured);
        rollBatch.setManufacturedAmount(manufacturedAmount);
        rollBatch.setUsedAmount(usedAmount);
        log.debug("Method getOne(RollManufactured rollManufactured): RollBatch {} is created for rollManufactured {}",
                rollBatch, rollManufactured);
        return rollBatch;
    }

    /**
     * Вычисляет и возвращает информацию о количестве произведенных, списанных в производство и оставшихся
     * на данный момент рулонов для указанного списка рулонов {@code RollManufactured}
     *
     * @param rollManufacturedList {@code List} рулонов {@code RollManufactured}
     * @return {@code List} {@code RollBatch} для указанного списка рулона
     * @throws NullPointerException если rollManufacturedList - null
     */
    private List<RollBatch> getAll(List<RollManufactured> rollManufacturedList) {
        List<RollBatch> rollBatches = rollManufacturedList.stream()
                .map(this::getOne)
                .collect(Collectors.toList());
        log.debug("Method getAll(List<RollManufactured> rollManufacturedList): List<RollBatch>: {} " +
                "is finding for List<RollManufactured> {}", rollBatches, rollManufacturedList);
        return rollBatches;
    }

}