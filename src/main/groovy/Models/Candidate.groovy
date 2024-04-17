package Models

class Candidate extends Users {
    String firstName
    String lastName
    Date dateOfBirth
    String cpf
    String[] skills

    @Override
    String toString() {
        return  "id - " + id + "\n" +
                "Nome - " + firstName + "\n" +
                "Sobrenome - " + lastName + "\n" +
                "date de nascimento - " + dateOfBirth + "\n" +
                "email - " + email + "\n" +
                "CPF - " + cpf + "\n" +
                "país - " + country + "\n" +
                "CEP - " + cep + "\n" +
                "descrição - " + description + "\n" +
                "senha - " + password + "\n" +
                "skills - " + Arrays.toString(skills) + "\n"
    }
}