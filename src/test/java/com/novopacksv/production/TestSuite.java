package com.novopacksv.production;

import com.novopacksv.production.service.plan.ProductPlanBatchServiceImplTest;
import com.novopacksv.production.service.plan.RollPlanOperationServiceImplTest;
import com.novopacksv.production.service.product.*;
import com.novopacksv.production.service.roll.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.com.novopacksv.production.service.product.*;
import ua.com.novopacksv.production.service.roll.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({RollTypeServiceImplTest.class, RollOperationServiceImplTest.class,
        RollManufacturedServiceImplTest.class, RollLeftOverServiceImplTest.class, RollCheckServiceImplTest.class,
        RollBatchServiceImplTest.class, ProductTypeServiceImplTest.class, ProductOperationServiceImplTest.class,
        ProductLeftOverServiceImplTest.class, ProductCheckServiceImplTest.class, ProductBatchServiceImplTest.class,
        RollPlanOperationServiceImplTest.class, ProductPlanBatchServiceImplTest.class})
public class TestSuite {
}
