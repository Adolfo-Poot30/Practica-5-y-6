package metodos;

import javax.swing.JTextArea;

public class calcularMovimientosTorreHanoi {

    public void moverDiscos(int n, char origen, char destino, char auxiliar, JTextArea area) {
        if (n == 1) {
            area.append("Mover disco 1 de " + origen + " a " + destino +" \n");
            return;
        } else {
            // Mover n-1 discos de la varilla origen a la varilla auxiliar, usando la varilla destino como auxiliar
            moverDiscos(n - 1, origen, auxiliar, destino, area);

            // Mover el disco más grande de la varilla origen a la varilla destino
            area.append("Mover disco " + n + " de " + origen + " a " + destino+"\n");

            // Mover los n-1 discos restantes de la varilla auxiliar a la varilla destino, usando la varilla origen como auxiliar
            moverDiscos(n - 1, auxiliar, destino, origen, area);
        }
    }

    public int verificarEntrada(String n) {
        try {
            int numero = Integer.parseInt(n);
            return numero;
        } catch (Exception e) {
            return 0;
        }
    }
}