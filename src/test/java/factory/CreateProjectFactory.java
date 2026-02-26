package factory;


import api.steps.ProjectSteps;
import com.github.javafaker.Faker;
import models.request.project.post.ProjectRequestModel;

public class CreateProjectFactory {

    static Faker faker = new Faker();
    public ProjectRequestModel project = CreateProjectFactory.getRandomData();

    public static ProjectRequestModel getRandomData() {
        return ProjectRequestModel.builder()
                .title(faker.name().name())
                .code(faker.bothify("???"))
                .description(faker.lorem().sentence())
                .build();
    }

    public static ProjectRequestModel getWrongRandomData() {
        return ProjectRequestModel.builder()
                .title(faker.name().name())
                .code(faker.regexify("[$$&#]{6}"))
                .build();
    }

    public void createProject(Integer statusCode) {
        ProjectSteps.createProject(project, statusCode);
    }

    public void deleteProject(String projectCode, Integer statusCode) {
        ProjectSteps.deleteProject(projectCode, statusCode);
    }
}
