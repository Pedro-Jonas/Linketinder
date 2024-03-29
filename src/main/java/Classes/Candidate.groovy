package Classes

class Candidate extends Users {
    String cpf
    int age

    @Override
     String toString() {
        return "{\n nome= ${getName()},\n email=  ${getEmail()},\n" +
                " CPF= $cpf,\n idade= $age,\n estado= ${getState()},\n" +
                " CEP= ${getCep()},\n descrição= ${getDescription()}\n}\n";
    }
}
