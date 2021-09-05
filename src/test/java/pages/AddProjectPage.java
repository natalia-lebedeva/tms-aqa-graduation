package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.CheckBox;
import wrappers.InputField;
import wrappers.RadioButton;

public class AddProjectPage extends BasePage {

    private final static String ENDPOINT = "index.php?/admin/projects/add/1";//???

    private final static By ADD_PROJECT_PAGE_TITLE = By.className("content-header-title");
    private final static By NAME_PROJECT_INPUT = By.id("name");
    private final static By ANNOUNCEMENT_PROJECT_INPUT = By.id("announcement");
    private final static By SHOW_ANNOUNCEMENT_PROJECT_CHECK_BOX = By.id("show_announcement");
    private final static By SUITE_MODE_RADIO_BUTTON = By.name("suite_mode");
    private final static By ADD_PROJECT_BUTTON = By.id("accept");

    public AddProjectPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getAddProjectPageTitle().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getAddProjectPageTitle() {
        return browsersService.getWaiters().waitForVisibility(ADD_PROJECT_PAGE_TITLE);
    }

    private InputField getNameProjectInput() {
        return new InputField(browsersService, NAME_PROJECT_INPUT);
    }

    private InputField getAnnouncementProjectInput() {
        return new InputField(browsersService, ANNOUNCEMENT_PROJECT_INPUT);
    }

    private CheckBox getShowAnnouncementProjectCheckBox() {
        return new CheckBox(browsersService, SHOW_ANNOUNCEMENT_PROJECT_CHECK_BOX);
    }

    private RadioButton getRadioButtonAddProject() {
        return new RadioButton(browsersService, SUITE_MODE_RADIO_BUTTON);
    }

    private Button getAddProjectButton() {
        return new Button(browsersService, ADD_PROJECT_BUTTON);
    }

    private void inputNameProject(String nameProject) {
        getNameProjectInput()
                .sendKeys(nameProject);
    }

    private void inputAnnouncementProjectInput(String announcementProject) {
        getAnnouncementProjectInput()
                .sendKeys(announcementProject);
    }

    private void setShowAnnouncementProjectCheckBox(boolean makeSelected) {
        getShowAnnouncementProjectCheckBox()
                .changeState(makeSelected);
    }

    private void setRadioButtonAddProject(int indexSuiteMode) {
        getRadioButtonAddProject()
                .selectByIndex(indexSuiteMode);
    }

    private void clickAddProjectButton() {
        getAddProjectButton()
                .click();
    }

    public AdministrationProjectsPage addProject(Project project){
        inputNameProject(project.getName());
        inputAnnouncementProjectInput(project.getAnnouncement());
        setShowAnnouncementProjectCheckBox(project.isShow_announcement());
        setRadioButtonAddProject(project.getSuite_mode());
        clickAddProjectButton();
        return new AdministrationProjectsPage(browsersService,false); //??

    }

}
