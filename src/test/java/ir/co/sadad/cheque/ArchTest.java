package ir.co.sadad.cheque;

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
            .importPackages("ir.co.sadad.cheque");

        noClasses()
            .that()
            .resideInAnyPackage("ir.co.sadad.cheque.service..")
            .or()
            .resideInAnyPackage("ir.co.sadad.cheque.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ir.co.sadad.cheque.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
