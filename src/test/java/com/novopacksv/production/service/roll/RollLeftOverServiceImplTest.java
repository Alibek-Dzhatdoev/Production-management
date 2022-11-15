package com.novopacksv.production.service.roll;

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
import com.novopacksv.production.model.rollModel.RollLeftOver;
import com.novopacksv.production.model.rollModel.RollType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RollLeftOverServiceImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RollLeftOverServiceImpl rollLeftOverService;
    private RollLeftOver rollLeftOver;
    private RollType rollType;
    private RollType rollType1;
    private RollInit rollInit = new RollInit();

    @Before
    public void init() {
        rollType = rollInit.rollTypeInit();
        rollType1 = rollInit.rollTypeInit2();
        rollLeftOver = rollInit.rollLeftOverInit(rollType);
        entityManager.persist(rollType);
        entityManager.persist(rollType1);
        entityManager.persist(rollLeftOver);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @WithMockUser("full")
    public void findAllByDate() {
        Assert.assertTrue(rollLeftOverService.findAllByDate(rollLeftOver.getDate())
                .stream()
                .filter(rollLeftOver1 -> rollLeftOver1.getRollType().equals(rollType))
                .mapToInt(RollLeftOver::getAmount)
                .sum() == rollLeftOver.getAmount());
    }

    @Test
    @WithMockUser("full")
    public void findByRollTypeIdAndDate() {
        Assert.assertTrue(rollLeftOverService.findByRollTypeIdAndDate(rollType.getId(), rollLeftOver.getDate())
                .getAmount() == rollLeftOver.getAmount());
    }

    @Test
    @WithMockUser("full")
    public void createNewLeftOverAndSave() {
        RollType rollTypeTest = new RollType("", 4.0, 56.0, 57.5, 380.0, "#ffffff");
        rollLeftOverService.createNewLeftOverAndSave(rollTypeTest);
        Assert.assertNotNull(rollLeftOverService.findLastRollLeftOverByRollType(rollTypeTest));
    }

    @Test
    @WithMockUser("full")
    public void getTotalLeftOver() {
        Assert.assertNotNull(rollLeftOverService.getTotalLeftOver(rollLeftOver.getDate()));
    }

    @Test
    @WithMockUser("full")
    public void findById() {
        Assert.assertEquals(rollLeftOver, rollLeftOverService.findById(rollLeftOver.getId()));
    }

    @Test
    @WithMockUser("full")
    public void findAll() {
        Assert.assertTrue(rollLeftOverService.findAll().contains(rollLeftOver));
    }

    @Test
    @WithMockUser("full")
    public void save() {
        RollType rollTypeTest = new RollType("", 4.0, 56.0, 57.5, 380.0, "#ffffff");
        rollLeftOverService.createNewLeftOverAndSave(rollTypeTest);
        Assert.assertNotNull(rollLeftOverService.findLastRollLeftOverByRollType(rollTypeTest));
    }

    @Test
    @WithMockUser("full")
    public void update() {
        rollLeftOver.setAmount(200);
        Assert.assertEquals(rollLeftOver, rollLeftOverService.update(rollLeftOver));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    @WithMockUser("full")
    public void delete() throws Exception {
        expectedEx.expect(ResourceNotFoundException.class);
        rollLeftOverService.delete(rollLeftOver.getId());
        rollLeftOverService.findById(rollLeftOver.getId());
    }

    @Test
    @WithMockUser("full")
    public void findLastRollLeftOverByRollType() {
        Assert.assertEquals(rollLeftOver, rollLeftOverService.findLastRollLeftOverByRollType(rollType));
    }

    @Test
    @WithMockUser("full")
    public void findLastRollLeftOverByRollType_Negative() {
        expectedEx.expect(ResourceNotFoundException.class);
        rollLeftOverService.findLastRollLeftOverByRollType(rollType1);
    }

    @Test
    @WithMockUser("full")
    public void changeRollLeftOverAmount() {
        rollLeftOverService.changeRollLeftOverAmount(rollLeftOver, 100);
        Assert.assertTrue(rollLeftOver.getAmount() == 110);
    }
}
