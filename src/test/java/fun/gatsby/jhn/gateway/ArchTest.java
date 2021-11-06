package fun.gatsby.jhn.gateway;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fun.gatsby.jhn.gateway");

        noClasses()
            .that()
            .resideInAnyPackage("fun.gatsby.jhn.gateway.service..")
            .or()
            .resideInAnyPackage("fun.gatsby.jhn.gateway.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..fun.gatsby.jhn.gateway.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
