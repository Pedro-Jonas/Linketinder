package Models

class Company extends Users {
    String name
    String cnpj

    @Override
    public String toString() {
        return "id - " + id + "\n" +
                "name - " + name + "\n" +
                "email - " + email + "\n" +
                "CNPJ - " + cnpj + "\n" +
                "país - " + country + "\n" +
                "CEP - " + cep + "\n" +
                "descrição - " + description + "\n" +
                "senha - " + password + "\n"
    }
}