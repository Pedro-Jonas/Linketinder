package Classes

class Company extends Users {
    String county
    int cnpj

    @Override
    String toString() {
        return "{nome= ${getName()}, email=  ${getEmail()}," +
                " CNPJ= $cnpj, país= $county, estado= ${getState()}," +
                " CEP= ${getCep()}, descrição= ${getDescription()}}";
    }
}
