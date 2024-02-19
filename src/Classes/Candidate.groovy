package Classes

class Candidate extends Users {
    int cpf
    int age

    @Override
     String toString() {
        return "{nome= ${getName()}, email=  ${getEmail()}," +
                " CPF= $cpf, idade= $age, state= ${getState()}," +
                " CEP= ${getCep()}, descrição= ${getDescription()}}";
    }
}
