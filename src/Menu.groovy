class Menu {
    Scanner sc = new Scanner(System.in)

    String menu = """Digite a opção desejada: 
    1 para listar todos os Candidatos
    2 para Listas todas as empresas
    0 para encerrar
    __________________________________"""

    void Start(){
        println menu

        int op = sc.nextInt()

        while (op != 0) {
            switch (op){
                case 1:
                    println("Não implementado")
                    break
                case 2:
                    println("Não implementado")
                    break
                default:
                    println("Digite uma opção válida")
                    break
            }

            println menu

            op = sc.nextInt()
        }
    }
}
