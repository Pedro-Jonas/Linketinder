package Models

class JobVacancy {
    int id
    String name
    String state
    String city
    String description
    int companyId
    String[] skills

    @Override
    String toString() {
        return "id - " + id + "\n" +
                "empresaId - " + companyId + "\n" +
                "name - " + name + "\n" +
                "estado - " + state + "\n" +
                "cidade - " + city + "\n" +
                "descrição - " + description + "\n" +
                "skills - " + Arrays.toString(skills) + "\n"
    }
}
