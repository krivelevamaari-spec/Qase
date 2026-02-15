package models;

import com.github.javafaker.Faker;
import models.request.cases.CaseRequestModel;

public class CreateCaseFactory {

    static Faker faker = new Faker();

    public static CaseRequestModel getRandomData() {
        return CaseRequestModel.builder()
                .description(faker.animal().name())
                .preconditions(faker.artist().name())
                .postconditions(faker.cat().name())
                .title(faker.name().name())
                .build();
    }
}
