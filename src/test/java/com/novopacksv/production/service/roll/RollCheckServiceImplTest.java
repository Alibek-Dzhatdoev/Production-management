package com.novopacksv.production.service.roll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.novopacksv.production.model.rollModel.CheckStatus;
import com.novopacksv.production.model.rollModel.RollCheck;
import com.novopacksv.production.model.rollModel.RollType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RollCheckServiceImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RollCheckServiceImpl rollCheckService;
    private RollInit rollInit = new RollInit();
    private RollType rollType;
    private RollType rollType1;
    private RollCheck rollCheck;

    @Before
    public void init() {
        rollType = rollInit.rollTypeInit();
        rollCheck = rollInit.rollCheckInit(rollType);
        entityManager.persist(rollType);
        entityManager.persist(rollCheck);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @WithMockUser("full")
    public void findOneByRollTypeId() {
        Assert.assertEquals(rollCheck, rollCheckService.findOneByRollTypeId(rollType.getId()));
    }

    @Test
    @WithMockUser("full")
    public void findAll(){
        Assert.assertTrue(rollCheckService.findAll().contains(rollCheck));
    }

    @Test
    @WithMockUser("full")
    public void update(){
    rollCheck.setRollLeftOverCheckStatus(CheckStatus.CONFIRMED);
    Assert.assertEquals(rollCheck, rollCheckService.update(rollCheck));
    }

    @Test
    @WithMockUser("full")
    public void createNewRollCheckAndSave(){
    rollType1 = rollInit.rollTypeInit2();
    rollCheckService.createNewRollCheckAndSave(rollType1);
    Assert.assertNotNull(rollCheckService.findOneByRollTypeId(rollType1.getId()));
    }

    @Test
    @WithMockUser("full")
    public void setNotCheckedStatusForAll(){
        rollCheckService.setNotCheckedStatusForAll();
        Assert.assertEquals(CheckStatus.NOT_CHECKED,
                rollCheckService.findOneByRollTypeId(rollType.getId()).getRollLeftOverCheckStatus());
    }
}
