package com.novopacksv.production.service.plan;

import com.novopacksv.production.exception.NotUniqueFieldException;
import com.novopacksv.production.exception.ResourceNotFoundException;
import com.novopacksv.production.repository.planRepository.MachinePlanItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novopacksv.production.model.planModel.MachinePlan;
import com.novopacksv.production.model.planModel.MachinePlanItem;
import com.novopacksv.production.model.rollModel.RollType;

import java.util.List;
import java.util.Objects;

/**
 * Class implements interface {@link MachinePlanItemService} and contains CRUD methods
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MachinePlanItemServiceImpl implements MachinePlanItemService {

    /**
     * An object of repository's layer for work with MachinePlan in db
     */
    private final MachinePlanItemRepository machinePlanItemRepository;

    /**
     * Method finds MachinePlanItem by id
     *
     * @param id - MachinePlanItem's id
     * @return MachinePlanItem
     * @throws ResourceNotFoundException if MachinePlanItem with this id does not exist in db
     */
    @Override
    @Transactional(readOnly = true)
    public MachinePlanItem findById(Long id) throws ResourceNotFoundException {
        MachinePlanItem machinePlanItem = machinePlanItemRepository.findById(id).orElseThrow(() -> {
            String message = String.format("MachinePlanItem with id = %d was not found", id);
            log.error("Method findById(Long id): MachinePlanItem with id = {} was not found", id);
            return new ResourceNotFoundException(message);
        });
        log.debug("Method findById(Long id): MachinePlanItem with id = {} was found: {}", id, machinePlanItem);
        return machinePlanItem;
    }

    /**
     * Method finds all existed in db MachinePlanItems
     *
     * @return List of MachinePlanItems
     */
    @Override
    @Transactional(readOnly = true)
    public List<MachinePlanItem> findAll() {
        log.debug("Method findAll(): List<MachinePlanItem> is finding");
        return machinePlanItemRepository.findAll();
    }

    /**
     * Method tests if new MachinePlanItem is unique and saves it in db
     *
     * @param item - new MachinePlanItem
     * @return saved MachinePlanItem
     */
    @Override
    public MachinePlanItem save(MachinePlanItem item) {
        checkUniqueConstraint(item);
        MachinePlanItem itemSaved = machinePlanItemRepository.save(item);
        log.debug("Method save(MachinePlanItem item): MachinePlanItem was saved {}", itemSaved);
        return itemSaved;
    }

    /**
     * Method tests if MachinePlanItem exist in db and updates it
     *
     * @param item - MachinePlanItem to update
     * @return updated MachinePlanItem
     * @throws ResourceNotFoundException if this MachinePlanItem does not exist in db
     */
    @Override
    public MachinePlanItem update(MachinePlanItem item) throws ResourceNotFoundException {
        MachinePlanItem oldItem = findById(item.getId());
        item.setMachinePlan(oldItem.getMachinePlan());
        MachinePlanItem itemSaved = save(item);
        log.debug("Method update(MachinePlanItem item): MachinePlanItem {} was updated", itemSaved);
        return itemSaved;
    }

    /**
     * Method tests if MachinePlanItem exists in db and deletes it
     *
     * @param id - MachinePlanItem's id
     * @throws ResourceNotFoundException if MachinePlanItem with this id does not exist in db
     */
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        machinePlanItemRepository.delete(findById(id));
        log.debug("Method delete(Long id): MachinePlanItem with id = {} was deleted", id);
    }

    /**
     * Method finds MachinePlanItem by machine plan and roll type
     *
     * @param machinePlan - MachinePlan
     * @param rollType    - RollType
     * @return MachinePlanItem
     * @throws ResourceNotFoundException if MachinePlanItem for machine plan with this roll type not exist in db
     */
    @Override
    @Transactional(readOnly = true, noRollbackFor = ResourceNotFoundException.class)
    public MachinePlanItem findOne(MachinePlan machinePlan, RollType rollType) {
        return machinePlanItemRepository.findByRollTypeAndMachinePlan(rollType, machinePlan)
                .map(item -> {
                    log.debug("Method findOne(MachinePlan machinePlan, RollType rollType): MachinePlanItem with machinePlan" +
                            " ID = {} and rollType ID = {} was found: {}", machinePlan.getId(), rollType.getId(), item);
                    return item;
                })
                .orElseThrow(() -> {
                    log.error("Method findOne(MachinePlan machinePlan, RollType rollType): MachinePlanItem with machinePlan" +
                            " ID = {} and rollType ID = {} was not found", machinePlan.getId(), rollType.getId());
                    String message = String.format("MachinePlanItem with machinePlan ID = %d and rollType ID = %d" +
                            " was not found", machinePlan.getId(), rollType.getId());
                    return new ResourceNotFoundException(message);
                });
    }

    /**
     * Method checks if MachinePlanItem has the same MachinePlan and the same RollType as existed one in db
     *
     * @param item - MachinePlanItem
     * @throws NotUniqueFieldException if MachinePlanItem has the same MachinePlan and the same RollType
     */
    private void checkUniqueConstraint(MachinePlanItem item) throws NotUniqueFieldException {
        MachinePlanItem entityItem = machinePlanItemRepository
                .findByRollTypeAndMachinePlan(item.getRollType(), item.getMachinePlan())
                .orElse(null);
        if (entityItem != null && !hasSameId(item, entityItem)) {
            log.error("Method checkUniqueConstraint(MachinePlanItem item): RollType and MachinePlan for MachinePlanItem" +
                    " {} is not unique", item);
            throw new NotUniqueFieldException("Roll type with machine plan in machine plan item must be unique!");
        }
        log.debug("Method checkUniqueConstraint(MachinePlanItem item): RollType and MachinePlan for MachinePlanItem" +
                " {} is unique", item);
    }

    /**
     * Method checks if two MachinePlanItems have the same id
     *
     * @param item       - one MachinePlanItem
     * @param entityItem - other MachinePlanItem
     * @return true if two MachinePlanItems have the same id, and false - if not
     */
    private boolean hasSameId(MachinePlanItem item, MachinePlanItem entityItem) {
        Long id = item.getId();
        Long entityId = entityItem.getId();
        boolean result = id != null && entityId != null && Objects.equals(id, entityId);
        log.debug("Method hasSameId(MachinePlanItem item, MachinePlanItem entityItem): Result of method for " +
                "MachinePlanItem {} and MachinePlanItem {} was: {}", item, entityItem, result);
        return result;
    }

}
