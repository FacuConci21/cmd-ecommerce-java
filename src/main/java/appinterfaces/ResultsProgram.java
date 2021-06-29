package appinterfaces;

public interface ResultsProgram {
    int SUCCESS = 0;
    int CANCELED = 1;
    int EMPTY_LIST = 2;
    int ERROR = 3;
    int CONNECTION_FAILED = 4;
    int CONNECTION_SUCCESS = 5;
    int PROGRAM_FINISHED = 6;
    int NOT_FOUND = 7;
    int INVALID_KEY = 8;
    int INVALID_VALUE = 9;

    String[] outputMessages = {
            Colors.ANSI_BLUE + "La operacion de llevo a cabo con exito!",
            Colors.ANSI_YELLOW + "La operacion fue cancelada.",
            Colors.ANSI_YELLOW + "La lista de productos esta vacia.",
            Colors.ANSI_RED + "Se produjo un error.",
            Colors.ANSI_RED + "No se pudo conectar a la base de datos.",
            Colors.ANSI_GREEN + "Conectado a la base de datos.",
            Colors.ANSI_GREEN + "Agradecemos su visita, Adios!",
            Colors.ANSI_DEFAULT + "El recurso no se ha encontrado.",
            Colors.ANSI_RED + "La palabra clave enviada es invalida",
            Colors.ANSI_RED + "El valor enviado es invalido."
    };
}
