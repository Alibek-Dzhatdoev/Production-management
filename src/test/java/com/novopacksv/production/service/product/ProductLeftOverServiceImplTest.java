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
import com.novopacksv.production.model.productModel.ProductLeftOver;
import com.novopacksv.production.model.productModel.ProductOperation;
import com.novopacksv.production.model.productModel.ProductType;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductLeftOverServiceImplTest {

    @Autowired
    private ProductLeftOverServiceImpl productLeftOverService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductOperationService operationService;
    private ProductInit productInit = new ProductInit();
    private ProductType productType;
    private ProductOperation productOperation;
    private ProductLeftOver productLeftOver;
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Before
    public void init() {
        productType = productInit.productTypeInit();
        productType = productTypeService.save(productType);
        productLeftOver = productLeftOverService.findById(productType.getId());
        productLeftOver.setLeftDate(LocalDate.now().minusDays(1));
        productOperation = productInit.productOperationInit(productType);
        operationService.save(productOperation);
    }

    @Test
    @WithMockUser("full")
    public void findOnDate_Today() {
        Assert.assertTrue(productLeftOverService.findOnDate(LocalDate.now())
                .stream()
                .filter((p) -> p.getProductType().equals(productType))
                .mapToInt(ProductLeftOver::getAmount)
                .sum() == 500);
    }

    @Test
    @WithMockUser("full")
    public void findOnDate_Future() {
        Assert.assertTrue(productLeftOverService.findOnDate(LocalDate.now().plusDays(2))
                .stream()
                .filter((p) -> p.getProductType().equals(productType))
                .mapToInt(ProductLeftOver::getAmount)
                .sum() == 500);
    }

    @Test
    @WithMockUser("full")
    public void findOnDate_Past() {
        Assert.assertTrue(productLeftOverService.findOnDate(LocalDate.now().minusDays(2))
                .stream()
                .filter((p) -> p.getProductType().equals(productType))
                .mapToInt(ProductLeftOver::getAmount)
                .sum() == 0);
    }

    @Test
    @WithMockUser("full")
    public void findLatest() {
        Assert.assertTrue(productLeftOverService.findOnDate(LocalDate.now())
                .stream()
                .filter((p) -> p.getProductType().equals(productType))
                .mapToInt(ProductLeftOver::getAmount)
                .sum() == 500);
    }

    @Test
    @WithMockUser("full")
    public void findByProductType_IdOnDate() {
        Assert.assertTrue(productLeftOverService.findByProductType_IdOnDate(productType.getId(), LocalDate.now()).getAmount()
                == 500);
    }

    @Test
    @WithMockUser("full")
    public void findById() {
        Assert.assertNotNull(productLeftOverService.findById(productLeftOver.getId()));
    }

    @Test
    @WithMockUser("full")
    public void findByProductTypeId() {
        Assert.assertNotNull(productLeftOverService.findByProductTypeId(productType.getId()));
    }

    @Test
    @WithMockUser("full")
    public void findAll() {
        Assert.assertNotNull(productLeftOverService.findAll()
                .stream()
                .filter((p) -> p.getProductType().equals(productType))
                .findFirst());
    }

    @Test
    @WithMockUser("full")
    public void save() {
//can't save without saving a new productType. When last one is saved, it's leftover is saved automatically
        ProductType productTypeNew = productInit.productTypeInit2();
        productTypeService.save(productTypeNew); // in this method new leftover for this productTypeNew was created and saved
        Assert.assertNotNull(productLeftOverService.findByProductTypeId(productTypeNew.getId()));
    }

    @Test
    @WithMockUser("full")
    public void update() {
        productLeftOver.setAmount(100);
        Assert.assertEquals(productLeftOver, productLeftOverService.update(productLeftOver));
    }

    @Test
    @WithMockUser("full")
    public void delete() throws Exception {
        expectedEx.expect(ResourceNotFoundException.class);
        productLeftOverService.delete(productLeftOver.getId());
        productLeftOverService.findById(productLeftOver.getId());
    }

    @Test
    @WithMockUser("full")
    public void saveByProductType() {
        ProductType productTypeNew = productInit.productTypeInit2();
        Assert.assertNotNull(productLeftOverService.saveByProductType(productTypeNew));
    }

    @Test
    @WithMockUser("full")
    public void isSoldOperation() {
        Assert.assertFalse(productLeftOverService.isSoldOperation(productOperation));
    }
}
