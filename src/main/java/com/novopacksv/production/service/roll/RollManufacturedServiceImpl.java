package com.novopacksv.production.service.roll;

import com.novopacksv.production.exception.ResourceNotFoundException;
import com.novopacksv.production.repository.rollRepository.RollManufacturedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novopacksv.production.model.rollModel.RollManufactured;
import com.novopacksv.production.model.rollModel.RollOperation;
import com.novopacksv.production.model.rollModel.RollType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Класс имплементирует методы интерфейса {@code RollManufacturedService}.
 * Содержит бизнес-логику для работы с объектами класса {@see RollManufactured}, представляющие собой условный рулон
 * определенного типа и даты производства
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RollManufacturedServiceImpl implements RollManufacturedService {

    /**
     * Содержит методы для работы с базой данных (DAO уровень) для сущности {@code RollManufactured}
     */
    private final RollManufacturedRepository rollManufacturedRepository;

    /**
     * Содержит методы для работы с операциями, производимыми с рулонами - сущностью {@code RollOperation}
     */
    private final RollOperationService rollOperationService;

    /**
     * Содержит методы для конвертации (преобразования) объектов одного класса в другой
     */
    private final ConversionService conversionService;

    /**
     * Ищет в базе и возвращает рулон, по указанному типу и дате производства.
     *
     * @param rollTypeId       ID типа рулона
     * @param manufacturedDate дата производства рулона
     * @return рулон {@code RollManufactured} для указнного id типа рулона и даты производства
     * @throws ResourceNotFoundException если рулон с указанными id типа рулона и датой производства не найдены
     */
    @Override
    @Transactional(readOnly = true)
    public RollManufactured findOne(LocalDate manufacturedDate, Long rollTypeId) throws ResourceNotFoundException {
        RollManufactured rollManufactured = rollManufacturedRepository.findByManufacturedDateAndRollType_Id(manufacturedDate, rollTypeId)
                .orElseThrow(() -> {
                    log.error("Method findOne(LocalDate manufacturedDate, Long rollTypeId): " +
                            "RollManufactured was not found by rollTypeId {}", rollTypeId);
                    String formatDate = conversionService.convert(manufacturedDate, String.class);
                    String message = String.format("Roll manufactured whit roll type id = %d" +
                            " and manufactured date = %s is not found!", rollTypeId, formatDate);
                    return new ResourceNotFoundException(message);
                });
        log.debug("Method findOne(LocalDate manufacturedDate, Long rollTypeId): RollManufactured {} was finding " +
                "by rollTypeId {}", rollManufactured, rollTypeId);
        return rollManufactured;
    }

    /**
     * Ищет в базе и возвращает рулон, по указанному типу и дате производства. Елси такой остутсвует в базе,
     * то создает и возвращает новый рулон с указанным типом, датой производства. При этом для нового
     * рулона проверяется готов ли он к использовнию и выставляется соответсвтующее значение {@code true}
     * или {@code false} для поля готовности
     *
     * @param manufacturedDate дата производства рулона
     * @param rollType         тип рулона
     * @return рулон {@code RollManufactured} для указнного типа рулона и даты производства
     * @throws IllegalArgumentException если rollType - null или manufacturedDate - null
     */
    @Override
    public RollManufactured findOneOrCreate(LocalDate manufacturedDate, RollType rollType) {
        RollManufactured rollManufactured1 = rollManufacturedRepository.findByManufacturedDateAndRollType(manufacturedDate, rollType)
                .orElseGet(() -> {
                    RollManufactured rollManufactured = new RollManufactured();
                    rollManufactured.setManufacturedDate(manufacturedDate);
                    rollManufactured.setRollType(rollType);
                    rollManufactured.setReadyToUse(isReadyToUse(manufacturedDate));
                    log.debug("Method findOneOrCreate(LocalDate manufacturedDate, RollType rollType): " +
                            "RollManufactured is created by rollType {}", rollType);
                    return rollManufactured;
                });
        log.debug("Method findOneOrCreate(LocalDate manufacturedDate, RollType rollType): " +
                "RollManufactured {} was found by rollType {}", rollManufactured1, rollType);
        return rollManufactured1;
    }

    /**
     * Ищет в базе и возвращает все произведнные рулоны с указанным id типа рулона.
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param rollTypeId ID типа рулона
     * @return {@code List} всех произведенных рулонов с указаным id типа рулона
     * или empty {@code List}, если в базе отсутствуют рулоны с указаным id типа рулона
     * @throws IllegalArgumentException если rollTypeId - null
     */
    @Override
    @Transactional(readOnly = true)
    public List<RollManufactured> findAll(Long rollTypeId) {
        log.debug("Method findAll(Long rollTypeId): List of RollManufactured is finding by rollTypeId {}", rollTypeId);
        return rollManufacturedRepository.findAllByRollType_Id(rollTypeId);
    }

    /**
     * Ищет в базе и возвращает все произведнные рулоны с указнной датой производства.
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param manufacturedDate дата производства рулона
     * @return {@code List} всех произведенных рулонов за указанную дату производства
     * или empty {@code List}, если в базе отсутствуют рулоны с указанной датой производства
     * @throws IllegalArgumentException если manufacturedDate - null
     */
    @Override
    @Transactional(readOnly = true)
    public List<RollManufactured> findAll(LocalDate manufacturedDate) {
        log.debug("Method findAll(LocalDate manufacturedDate): List of RollManufactured is finding " +
                "by manufactured date {}", manufacturedDate);
        return rollManufacturedRepository.findAllByManufacturedDate(manufacturedDate);
    }

    /**
     * Ищет в базе и возвращает все рулоны произведенные в указанный период времени.
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param fromManufacturedDate начало периода даты производства рулонов
     * @param toManufacturedDate   окончание периода даты производства рулонов
     * @return {@code List} всех рулонов, произведенных в указанный период времени,
     * или empty {@code List} если в базе отсутствуют рулоны, произведенные в указанный период
     * @throws IllegalArgumentException если fromManufacturedDate - null и/или toManufacturedDate - null
     */
    @Override
    @Transactional(readOnly = true)
    public List<RollManufactured> findAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate) {
        log.debug("Method findAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate): " +
                        "List of RollManufactured is finding by period from {} to {}",
                fromManufacturedDate, toManufacturedDate);
        return rollManufacturedRepository.findAllByManufacturedDateBetween(fromManufacturedDate, toManufacturedDate);
    }


    /**
     * Ищет в базе и возвращает все рулоны указанного типа, произведенные в указанный период времени.
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param rollTypeId           ID типа рулона
     * @param fromManufacturedDate начало периода даты производства рулонов
     * @param toManufacturedDate   окончание периода даты производства рулонов
     * @return {@code List} всех рулонов указанного типа, произведенных в указанный период времени,
     * или empty {@code List}, если в базе отсутствуют рулоны с указанным типом, произведенные в указанный период
     * @throws IllegalArgumentException если rollTypeId - null, и/или fromManufacturedDate - null,
     *                                  и/или toManufacturedDate - null
     */
    @Override
    public List<RollManufactured> findAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate, Long rollTypeId) {
        log.debug("Method findAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate, Long rollTypeI): " +
                        "List of RollManufactured is finding by rollTypeId {} for period from {} to {}",
                rollTypeId, fromManufacturedDate, toManufacturedDate);
        return rollManufacturedRepository
                .findAllByManufacturedDateBetweenAndRollType_Id(fromManufacturedDate, toManufacturedDate, rollTypeId);
    }


    /**
     * Вычисляет и возвращает общее количество указанных рулонов {@code RollManufactured},
     * произведенных на предприятии.
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param rollManufactured рулон, содержащий информацию о типе и дате производства
     * @return количество указанных рулонов, произведенных на предприятии или 0 если такие рулоны не произодились
     * @throws IllegalArgumentException если rollManufactured - null
     */
    @Override
    @Transactional(readOnly = true)
    public Integer getManufacturedRollAmount(RollManufactured rollManufactured) {
        Integer amount = rollOperationService.findAllManufacturedOperationsByRollManufactured(rollManufactured)
                .stream()
                .mapToInt(RollOperation::getRollAmount)
                .sum();
        log.debug("Method getManufacturedRollAmount(RollManufactured rollManufactured): " +
                "Amount of roll manufactured operations was found as {} " +
                "by rollManufactured {}", amount, rollManufactured);
        return amount;
    }

    /**
     * Вычисляет и возвращает общее количество указанных рулонов {@code RollManufactured},
     * отправленных в переработку.
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param rollManufactured рулон, содержащий информацию о типе и дате производства
     * @return количество указанных рулонов, отправленных в переработку или 0 если рулоны не перерабатывались
     * @throws IllegalArgumentException если rollManufactured - null
     */
    @Override
    @Transactional(readOnly = true)
    public Integer getUsedRollAmount(RollManufactured rollManufactured) {
        Integer amount = rollOperationService.findAllUsedOperationsByRollManufactured(rollManufactured)
                .stream()
                .mapToInt(RollOperation::getRollAmount)
                .sum();
        log.debug("Method getUsedRollAmount(RollManufactured rollManufactured): Amount of roll used operations = {} " +
                "was found by rollManufactured {}", amount, rollManufactured);
        return amount;
    }

    /**
     * Вычисляет и возвращает общее количество оставшихся указанных рулонов {@code RollManufactured},
     * <p>
     * Не изменяет содержимого базы данных
     *
     * @param rollManufactured рулон, содержащий информацию о типе и дате производства
     * @return оставшееся количество указанных рулонов
     * @throws IllegalArgumentException если rollManufactured - null
     */
    @Override
    public Integer getLeftOverAmount(RollManufactured rollManufactured) {
        return getManufacturedRollAmount(rollManufactured) - getUsedRollAmount(rollManufactured);
    }

    /**
     * Ищет в базе рулоны за указанный период производсвта и у которых не установлено поле готовности к использованию
     * в значение {@code true}, а затем устанавливает данное поле в значение {@code true}
     *
     * @param fromManufacturedDate начало периода производства рулонов
     * @param toManufacturedDate   окончание периода производства рулонов
     * @throws IllegalArgumentException если fromManufacturedDate - null и/или toManufacturedDate - null
     */
    @Override
    public void setReadyToUseTrue(LocalDate fromManufacturedDate, LocalDate toManufacturedDate) {
        findAllReadyToUseIsFalse(fromManufacturedDate, toManufacturedDate).forEach(rollManufactured -> {
            rollManufactured.setReadyToUse(true);
            rollManufacturedRepository.save(rollManufactured);
        });
        log.debug("Method setReadyToUseTrue(LocalDate fromManufacturedDate, LocalDate toManufacturedDate): " +
                        "All rolls from date {} to date {} are set ReadyToUse",
                fromManufacturedDate, toManufacturedDate);
    }

    /**
     * Ищет в базе рулоны за указанный период производсвта и у которых не установлено поле готовности
     * к использованию в значение {@code true}
     *
     * @param fromManufacturedDate начало периода производства рулонов
     * @param toManufacturedDate   окончание периода производства рулонов
     * @throws IllegalArgumentException если fromManufacturedDate - null и/или toManufacturedDate - null
     */
    private List<RollManufactured> findAllReadyToUseIsFalse(LocalDate fromManufacturedDate,
                                                            LocalDate toManufacturedDate) {
        log.debug("Method findAllReadyToUseIsFalse(LocalDate fromManufacturedDate, LocalDate toManufacturedDate): " +
                "List of unready to use rollManufactured are finding for period");
        return rollManufacturedRepository.findAllByManufacturedDateBetweenAndReadyToUseIsFalse(
                fromManufacturedDate, toManufacturedDate);
    }

    /**
     * Проверяет готов ли рулон с указнной датой производства.
     * <p>
     * Рулон становтся готовым после прошествия {@value RollType#READY_TO_USE_PERIOD} дней после даты его производства
     *
     * @param manufacturedDate дата производства рулона
     * @return {@code true} если дата готовности рулона насупила и {@code false} - если нет
     * @throws NullPointerException если manufacturedDate - null
     */
    private boolean isReadyToUse(LocalDate manufacturedDate) {
        log.debug("Method isReadyToUse(LocalDate manufacturedDate): A period of ready to use is determining");
        return manufacturedDate.until(LocalDate.now(), ChronoUnit.DAYS) > RollType.READY_TO_USE_PERIOD;
    }

}