package factory;

import com.github.javafaker.Faker;
import models.enams.*;
import factory.model.CaseFactoryModel;
import models.request.cases.CaseRequestModel;


public class CreateCaseFactory {

    static Faker faker = new Faker();

    public static CaseFactoryModel getRandomUiData() {
        return CaseFactoryModel.builder()
                .title(faker.name().name())
                .status(faker.options().option(Status.class).getValue())
                .type(faker.options().option(Type.class).getValue())
                .severity(faker.options().option(Severity.class).getValue())
                .priority(faker.options().option(Priority.class).getValue())
                .layer(faker.options().option(Layer.class).getValue())
                .isFlaky(faker.options().option(Flaky.class).getValue())
                .behavior(faker.options().option(Behavior.class).getValue())
                .automation(faker.options().option(Automation.class).getValue())
                .build();
    }

    public static CaseRequestModel getRandomApiData() {
        Status status = faker.options().option(Status.class);
        Type type = faker.options().option(Type.class);
        Severity severity = faker.options().option(Severity.class);
        Priority priority = faker.options().option(Priority.class);
        Layer layer = faker.options().option(Layer.class);
        Behavior behavior = faker.options().option(Behavior.class);
        Automation automation = faker.options().option(Automation.class);
        Flaky flaky = faker.options().option(Flaky.class);

        return CaseRequestModel.builder()
                .title(faker.name().name())
                .status(1)
                .type(type.ordinal() + 1)
                .severity(severity.ordinal() + 1)
                .priority(priority.ordinal() + 1)
                .layer(layer.ordinal() + 1)
                .behavior(behavior.ordinal() + 1)
                .automation(automation.ordinal() + 1)
                .isFlaky(flaky.ordinal())
                .milestoneId(null)
                .build();
    }
}