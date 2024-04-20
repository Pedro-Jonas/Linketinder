import Controllers.SkillController
import Interfaces.ISkillDAO
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class SkillControllerTest {

    ISkillDAO skillDAO = mock(ISkillDAO.class)
    SkillController skillController = new SkillController(skillDAO)

    @Test
    void addSkillCandidate() {
        //given
        List<String> skills = new ArrayList<>()
        List<Integer> ids = new ArrayList<>()

        skills.add("Groovy")

        ids.add(1)

        when(skillDAO.insertSkillCandidate(1, "Groovy" )).thenReturn(1)
        //when
        List<Integer> result = skillController.addSkillCandidate(skills, 1)

        //then
        assertEquals(ids, result)
    }

    @Test
    void addSkillJobVacancy() {
        //given
        List<String> skills = new ArrayList<>()
        List<Integer> ids = new ArrayList<>()

        skills.add("Groovy")

        ids.add(1)

        when(skillDAO.insertSkillJobVacancy(1, "Groovy" )).thenReturn(1)
        //when
        List<Integer> result = skillController.addSkillJobVacancy(skills, 1)

        //then
        assertEquals(ids, result)
    }

}