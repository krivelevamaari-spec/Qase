package pages;

import models.responce.project.get.Entity;
import models.responce.project.get.ProjectGetResponseModel;

import static com.codeborne.selenide.Selenide.open;
import static api.steps.ProjectSteps.deleteProject;
import static api.steps.ProjectSteps.getProjects;

public class BasePage {

    public void openPage(String endpoint) {
        open(endpoint);
    }

    public void deleteAllProjects() {
        ProjectGetResponseModel response = getProjects(200)
                .extract().as(ProjectGetResponseModel.class);

        if(response.getResult().getTotal() > 0) {
            response.getResult().getEntities().stream()
                    .map(Entity::getCode)
                    .forEach(code -> deleteProject(code, 200));
        }
    }
}
