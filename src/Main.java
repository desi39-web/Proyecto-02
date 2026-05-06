public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Bienvenido por favor di que quieres buscar");
            return;
        }

        String consulta = args[0];
        
        Documento[] repositorio = new Documento[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            repositorio[i - 1] = new Documento(args[i], 0.0);
        }

        ArbolAVL<Documento> arbolResultados = new ArbolAVL<>();
        Analizador analizador = new Analizador();

        System.out.println("Iniciando busqueda... " + consulta);
        analizador.empezarBusqueda(repositorio, consulta, arbolResultados);

        Object[] resultados = arbolResultados.obtenerTop10();

        if (resultados.length == 0) {
            System.out.println("No se encontro nada");
        } else {
            System.out.println("Hubo alguna coincidencia");
            for (int i = 0; i < resultados.length; i++) {
                System.out.println((i + 1) + ". " + resultados[i].toString());
            }
        }
    }
}
