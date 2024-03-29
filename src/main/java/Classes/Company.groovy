package Classes

class Company extends Users {
    String county
    String cnpj

    @Override
    String toString() {
        return "{\n nome= ${getName()},\n email=  ${getEmail()},\n" +
                " CNPJ= $cnpj,\n país= $county,\n estado= ${getState()},\n" +
                " CEP= ${getCep()},\n descrição= ${getDescription()}\n}\n";
    }
}
