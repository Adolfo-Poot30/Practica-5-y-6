package metodos;

import javax.swing.JTextArea;

public class CalcularCambio {

    private double pago, costo;
    // Denominaciones de las monedas, en centavos (para evitar problemas de precisión con los decimales).
    private final int[] denominaciones = {10000, 5000, 2000, 1000, 500, 100, 50, 20, 1};

    // Nombres de las denominaciones para imprimir el resultado.
    private final String[] nombres = {"100 pesos", "50 pesos", "20 pesos", "10 pesos", "5 pesos", "1 peso",
        "50 centavos", "20 centavos", "1 centavo"};

    // Método recursivo para calcular el cambio
    private void calcularCambio(int cambio, int indice, JTextArea area) {
        if (cambio == 0 || indice == denominaciones.length) {
            return; // Caso base: cuando no queda más cambio o ya revisamos todas las denominaciones
        }

        // Calcular cuántas monedas de la denominación actual se pueden usar
        int cantidadMonedas = cambio / denominaciones[indice];

        // Imprimir la cantidad de monedas de la denominación actual
        area.append(cantidadMonedas + " moneda(s) de " + nombres[indice] + "\n");

        // Calcular el nuevo cambio restante
        int nuevoCambio = cambio % denominaciones[indice];

        // Llamada recursiva para el resto del cambio con la siguiente denominación
        calcularCambio(nuevoCambio, indice + 1, area);
    }

    public void mostrarResultados(JTextArea area) {

        // Convertir el cambio a centavos
        int cambioEnCentavos = (int) Math.round((pago - costo) * 100);;

        double cambioEnPesos = cambioEnCentavos / 100.0;
        // Imprimir el cambio total a devolver
        area.append("Cambio a devolver: " + cambioEnPesos + " pesos\n");

        // Llamar al método recursivo para calcular el cambio
        calcularCambio(cambioEnCentavos, 0, area);
    }

    public boolean verificarEntradas(String pagoString, String costoString) {
        try {
            // Intentar convertir a double
            pago = Double.parseDouble(pagoString);
            costo = Double.parseDouble(costoString);

            // Verificar que el pago sea mayor o igual que el costo
            if (pago < costo) {
                return false;
            }

            // Verificar los decimales
            if (pagoString.contains(".") || costoString.contains(".")) {
                // Si tiene parte decimal, verificar que no tenga más de dos decimales
                String[] pagoParts = pagoString.split("\\.");
                String[] costoParts = costoString.split("\\.");

                if (pagoParts.length > 1 && pagoParts[1].length() > 2) {
                    return false;
                }
                if (costoParts.length > 1 && costoParts[1].length() > 2) {
                    return false;
                }
            }

            return true; // Las entradas son válidas
        } catch (Exception e) {
            return false; // Error en la conversión
        }
    }

}
