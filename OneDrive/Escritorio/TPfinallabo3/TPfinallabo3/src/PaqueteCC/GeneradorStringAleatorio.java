package PaqueteCC;


import java.util.Random;

///EL PaqueteCC se creò para poner clases que complementa
public final class GeneradorStringAleatorio{

    // Método para generar un String aleatorio de una determinada Longitud
    public static String generateRandomString(int tamanio) {
        // Se establece el conjunto de caracteres permitidos
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Crear un objeto Random
        Random random = new Random();

        // Usar un StringBuilder para construir la cadena aleatoria
        StringBuilder sb = new StringBuilder(tamanio);

        // Generar la cadena aleatoria
        for (int i = 0; i < tamanio; i++) {

            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String StringAleatorio(int tamanio) {

        String randomString = generateRandomString(tamanio);

        return randomString;
    }


}
