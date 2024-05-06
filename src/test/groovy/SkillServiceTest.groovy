import Models.Skill
import Services.SkillService
import Interfaces.ISkillDAO
import org.junit.jupiter.api.*
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.*

class SkillServiceTest {
    ISkillDAO skillDAO = mock(ISkillDAO.class)
    SkillService skillService = new SkillService(skillDAO)

    @Test
    void addSkillCandidateTest() {
        //given
        Skill skill = new Skill()
        skill.setName("Test")

        when(skillDAO.insertSkillCandidate(1, skill.name)).thenReturn(1)
        //when
        int result = skillService.addSkillCandidate(skill, 1)

        //then
        assertEquals(1, result)
    }

    @Test
    void  addSkillJobVacancyTest() {
        //given
        Skill skill = new Skill()
        skill.setName("Test")

        when(skillDAO.insertSkillJobVacancy(1, skill.name)).thenReturn(1)
        //when
        int result = skillService.addSkillJobVacancy(skill, 1)

        //then
        assertEquals(1, result)
    }

    @Test
    void getSkillsTest() {
        //given
        List<Skill> skills = new ArrayList<>()

        Skill skill = new Skill()

        skills.add(skill)

        when(skillDAO.selectSkills()).thenReturn(skills)
        //when
        List<Skill> result = skillService.getSkills()

        //then
        assertEquals(skills, result)
    }


}