package com.novopacksv.production.service.plan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.novopacksv.production.exception.ResourceNotFoundException;
import com.novopacksv.production.model.planModel.RollPlanOperation;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.roll.RollInit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RollPlanOperationServiceImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RollPlanOperationServiceImpl rollPlanOperationService;
    private RollInit rollInit = new RollInit();
    private PlanInit planInit = new PlanInit();
    private RollPlanOperation rollPlanOperation;
    private RollType rollType;
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Before
    public void init() {
        rollType = rollInit.rollTypeInit();
        rollPlanOperation = planInit.initRollPlanOperation(rollType);
        entityManager.persist(rollType);
        entityManager.persist(rollPlanOperation);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @WithMockUser("full")

    public void findAll_ByRollTypeIdForPeriod() {
        Assert.assertTrue(rollPlanOperationService.findAll(rollType.getId(), LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(2))
                .contains(rollPlanOperation));
    }

    @Test
    @WithMockUser("full")
    public void findAll_ByRollTypeIdForPeriod_Negative() {
        Assert.assertFalse(rollPlanOperationService.findAll(rollType.getId(), LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(2))
                .contains(rollPlanOperation));
    }

    @Test
    @WithMockUser("full")
    public void findById() {
        Assert.assertEquals(rollPlanOperation, rollPlanOperationService.findById(rollPlanOperation.getId()));
    }

    @Test
    @WithMockUser("full")
    public void findById_Ex() throws Exception {
        expectedEx.expect(ResourceNotFoundException.class);
        rollPlanOperationService.findById(1000000000L);
    }

    @Test
    @WithMockUser("full")
    public void findAll() {
        Assert.assertTrue(rollPlanOperationService.findAll()
                .contains(rollPlanOperation));
    }


}
