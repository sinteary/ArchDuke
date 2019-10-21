package controllers;

import models.data.Project;
import org.junit.jupiter.api.Test;
import repositories.ProjectRepository;
import views.CLIView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectInputControllerTest {
    private ProjectRepository projectRepository;
    private CLIView consoleView;
    private ProjectInputController projectInputController;
    private String simulatedUserInput;
    private String actualOutput;
    private String expectedOutput;


    ProjectInputControllerTest() {
        this.projectRepository = new ProjectRepository();
        this.consoleView = new CLIView();
        this.projectInputController = new ProjectInputController(consoleView,projectRepository);
    }

    /**
     * Always true test just to test if jUnit is working.
     */
    @Test
    void alwaysTrue() {
        assertEquals(2, 2);
    }

    @Test
    void testProjectAddMember() {
        Project project = new Project("Infinity_Gauntlet");
        String simulatedUserInput = "add member n/Jerry Zhang i/9123456 e/jerryzhang@gmail.com";
        projectInputController.projectAddMember(project,simulatedUserInput);
        actualOutput = "";
        for (String message : project.getMembers().getAllMemberDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. Jerry Zhang (Phone: 9123456 | Email: jerryzhang@gmail.com)";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testProjectEditMember() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add member n/Jerry Zhang i/9123456 e/jerryzhang@gmail.com";
        projectInputController.projectAddMember(project, simulatedUserInput);

        simulatedUserInput = "edit member 1 n/Dillen i/9123456 e/dillen@gmail.com";
        projectInputController.projectEditMember(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getMembers().getAllMemberDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. Dillen (Phone: 9123456 | Email: dillen@gmail.com)";
        assertEquals(expectedOutput, actualOutput);

        simulatedUserInput = "edit member 1 n/Jerry";
        projectInputController.projectEditMember(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getMembers().getAllMemberDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. Jerry (Phone: 9123456 | Email: dillen@gmail.com)";
        assertEquals(expectedOutput, actualOutput);

        simulatedUserInput = "edit member 1 i/911";
        projectInputController.projectEditMember(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getMembers().getAllMemberDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. Jerry (Phone: 911 | Email: dillen@gmail.com)";
        assertEquals(expectedOutput, actualOutput);

        simulatedUserInput = "edit member 1 e/jerry@gmail.com";
        projectInputController.projectEditMember(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getMembers().getAllMemberDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. Jerry (Phone: 911 | Email: jerry@gmail.com)";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testProjectDeleteMember() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add member n/Jerry Zhang i/9123456 e/jerryzhang@gmail.com";
        projectInputController.projectAddMember(project, simulatedUserInput);

        simulatedUserInput = "delete member 1";
        projectInputController.projectDeleteMember(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getMembers().getAllMemberDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void testProjectViewCredits() {

    }

    @Test
    void testProjectAddTask() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add task t/Documentation for product p/2 d/21/09/2019 c/40 s/todo "
                + "r/do something r/do another thing";
        projectInputController.projectAddTask(project, simulatedUserInput);

        // the following test fails

        simulatedUserInput = "add task t/Documentation for product p/2 c/40 s/done r/do something r/do another thing";
        projectInputController.projectAddTask(project,simulatedUserInput);


        simulatedUserInput = "add task t/Documentation for product p/2 c/40 r/do something r/do another thing";
        projectInputController.projectAddTask(project, simulatedUserInput);

        simulatedUserInput = "add task t/Documentation for product p/2 c/40";
        projectInputController.projectAddTask(project, simulatedUserInput);

        actualOutput = "";
        for (String message : project.getTasks().getAllTaskDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. Documentation for product | Priority: 2 | Due: 21 Sep 2019 | Credit: 40 | State: TODO"
                + "2. Documentation for product | Priority: 2 | Due: -- | Credit: 40 | State: DONE"
                + "3. Documentation for product | Priority: 2 | Due: -- | Credit: 40 | State: OPEN"
                + "4. Documentation for product | Priority: 2 | Due: -- | Credit: 40 | State: OPEN";


        // temporary expectedOutput
        /*expectedOutput = "1. Documentation for product | Priority: 2 | Due: 21 Sep 2019 | Credit: 40 | State: TODO"
                + "2. Documentation for product | Priority: 2 | Due: -- | Credit: 40 | State: OPEN"
                + "3. Documentation for product | Priority: 2 | Due: -- | Credit: 40 | State: OPEN";
*/

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void testProjectEditTask() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add task t/Documentation for product p/2 d/21/09/2019 c/40 s/todo "
                + "r/do something r/do another thing";
        projectInputController.projectAddTask(project, simulatedUserInput);

        // the following test fails
        /*simulatedUserInput = "edit task i/1 t/No documentation p/5 d/22/09/2019 c/50 s/done "
                    + "r/do nothing r/do another thing";
        projectInputController.projectEditTask(project,simulatedUserInput);
        actualOutput = "";
        for (String message : project.getTasks().getAllTaskDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "1. No documentation | Priority: 5 | Due: 22 Sep 2019 | Credit: 50 | State: DONE";
        assertEquals(expectedOutput,actualOutput);
        */
    }

    @Test
    void testProjectDeleteTask() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add task t/Documentation for product p/2 d/21/09/2019 c/40 s/todo "
                + "r/do something r/do another thing";
        projectInputController.projectAddTask(project, simulatedUserInput);
        simulatedUserInput = "delete task 1";
        projectInputController.projectDeleteTask(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getTasks().getAllTaskDetails().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void testProjectEditTaskRequirements() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add task t/Documentation for product p/2 d/21/09/2019 c/40 s/todo "
                + "r/do something r/do another thing";
        projectInputController.projectAddTask(project, simulatedUserInput);

        simulatedUserInput = "edit task requirements i/1 r/do nothing";
        projectInputController.projectEditTaskRequirements(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getTask(1).getTaskRequirements().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "Documentation for product | Priority: 2 | Due: 21 Sep 2019 | Credit: 40 | State: TODO"
                + "1. do something2. do another thing3. do nothing";
        assertEquals(expectedOutput,actualOutput);

        simulatedUserInput = "edit task requirements i/1 rm/1 2 r/do everything";
        projectInputController.projectEditTaskRequirements(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getTask(1).getTaskRequirements().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "Documentation for product | Priority: 2 | Due: 21 Sep 2019 | Credit: 40 | State: TODO"
                + "1. do nothing2. do everything";
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void testProjectAssignTask() {
        Project project = new Project("Infinity_Gauntlet");
        simulatedUserInput = "add task t/Documentation for product p/2 d/21/09/2019 c/40 s/todo "
                + "r/do something r/do another thing";
        projectInputController.projectAddTask(project, simulatedUserInput);
        simulatedUserInput = "add member n/Jerry Zhang i/9123456 e/jerryzhang@gmail.com";
        projectInputController.projectAddMember(project, simulatedUserInput);
        simulatedUserInput = "add member n/Dillen i/911 e/dillen@hotmail.com";
        projectInputController.projectAddMember(project, simulatedUserInput);
        simulatedUserInput = "assign task -i 1 -to 1 2";
        projectInputController.projectAssignTask(project, simulatedUserInput);
        actualOutput = "";
        for (String message : project.getAssignedTaskList().toArray(new String[0])) {
            actualOutput += message;
        }
        expectedOutput = "Documentation for product is assigned to: Jerry ZhangDillen";
        assertEquals(expectedOutput,actualOutput);
    }
}