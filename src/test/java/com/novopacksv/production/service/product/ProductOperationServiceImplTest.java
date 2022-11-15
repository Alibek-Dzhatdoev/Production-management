package com.novopacksv.production.service.product;

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
import com.novopacksv.production.model.productModel.ProductOperation;
import com.novopacksv.production.model.productModel.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductOperationServiceImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductOperationServiceImpl productOperationService;
    @Autowired
    private ProductTypeService productTypeService;
    private ProductInit productInit = new ProductInit();
    private ProductType productType;
    private ProductType productType1;
    private ProductOperation productOperation;
    private ProductOperation productOperation1;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init() {
        productType = productInit.productTypeInit();
        productType1 = productInit.productTypeInit2();
        productTypeService.save(productType1);//we need to have a leftover with id = product type's id
        productOperation = productInit.productOperationInit(productType);
        productOperation1 = productInit.productOperationInit(productType1);
        entityManager.persist(productType);
        entityManager.persist(productOperation);
        entityManager.persist(productOperation1);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @WithMockUser("full")
    public void findById() {
        Assert.assertEquals(productOperation, productOperationService.findById(productOperation.getId()));
    }

    @Test
    @WithMockUser("full")
    public void findById_Negative() throws Exception {
        expectedEx.expect(ResourceNotFoundException.class);
        productOperationService.findById(10000000L);
    }

    @Test
    @WithMockUser("full")
    public void findAll() {
        Assert.assertTrue(productOperationService.findAll().contains(productOperation));
    }

    @Test
    @WithMockUser("full")
    public void save() {
        productOperation1 = productInit.productOperationInit(productType1);
        Assert.assertEquals(productOperation1, productOperationService.save(productOperation1));
    }

    @Test
    @WithMockUser("full")
    public void update() {
        productOperation1.setAmount(600);
        Assert.assertEquals(productOperation1, productOperationService.update(productOperation1));
    }

    @Test
    @WithMockUser("full")
    public void delete() throws Exception {
        expectedEx.expect(ResourceNotFoundException.class);
        productOperationService.delete(productOperation.getId());
        productOperationService.findById(productOperation.getId());
    }

    @Test
    @WithMockUser("full")
    public void findAllOperationBetweenDatesByTypeId() {
        Assert.assertTrue(productOperationService.findAllOperationBetweenDatesByTypeId(productType.getId(),
                LocalDate.now().minusDays(20), LocalDate.now()).contains(productOperation));
    }
}
