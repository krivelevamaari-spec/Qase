package models;

import com.github.javafaker.Faker;
import models.enams.*;
import models.request.cases.CaseRequestModel;

import java.util.List;


public class CreateCaseFactory {

    static Faker faker = new Faker();

    public static CaseRequestModel getRandomData() {
        return CaseRequestModel.builder()
                .title(faker.name().name())
                .automation(faker.options().nextElement(AutomationStatus.values()).ordinal())
                .behavior(faker.options().nextElement(Behavior.values()).ordinal())
                .isFlaky(faker.options().nextElement(Flaky.values()).ordinal())
                .layer(faker.options().nextElement(Layer.values()).ordinal())
                .priority(faker.options().nextElement(Priority.values()).ordinal())
                .severity(faker.options().nextElement(Severity.values()).ordinal())
                .type(faker.options().nextElement(Type.values()).ordinal())
                .build();
    }

    public static String chooseRandomDropDownOption(List<String> options) {
        return faker.options().nextElement(options);
    }
}
