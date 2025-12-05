import java.util.Scanner;

public class Simplex {

    // Método para mostrar el tableau
    public static void mostrarTabla(double[][] tabla) {
        System.out.println("Tabla Actual:");
        for (double[] fila : tabla) {
            for (double valor : fila) {
                System.out.println( valor);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        Scanner lector = new Scanner(System.in);

        System.out.println("----MÉTODO SIMPLEX BÁSICO ---");

        System.out.print("Número de variables: ");
        int vars = lector.nextInt();

        System.out.print("Número de restricciones: ");
        int rest = lector.nextInt();

        double[][] A = new double[rest][vars];
        double[] b = new double[rest];
        double[] c = new double[vars];

        System.out.println("--- Ingresa la matriz de coeficientes A ---");
        for (int i = 0; i < rest; i++) {
            for (int j = 0; j < vars; j++) {
                System.out.print("A[" + i + "][" + j + "] = ");
                A[i][j] = lector.nextDouble();
            }
        }
        System.out.println("--- Ingresa los valores de b (lado derecho) ---");
        for (int i = 0; i < rest; i++) {
            System.out.print("b[" + i + "] = ");
            b[i] = lector.nextDouble();
        }

        System.out.println("--- Ingresa los coeficientes de la función objetivo Z ---");
        for (int j = 0; j < vars; j++) {
            System.out.print("c[" + j + "] = ");
            c[j] = lector.nextDouble();
        }

        int columnas = vars + rest + 1;
        int filas = rest + 1;
        double[][] tabla = new double[filas][columnas];

        // Copiar A
        for (int i = 0; i < rest; i++) {
            for (int j = 0; j < vars; j++) {
                tabla[i][j] = A[i][j];
            }
            tabla[i][vars + i] = 1; // variables de holgura
            tabla[i][columnas - 1] = b[i];
        }
    

     for (int j = 0; j < vars; j++) {
            tabla[filas - 1][j] = -c[j];
        }

        mostrarTabla(tabla);

        // Iteración Simplex
        while (true) {
            // Columna pivote
            int colPiv = -1;
            for (int j = 0; j < columnas - 1; j++) {
                if (tabla[filas - 1][j] < 0) {
                    colPiv = j;
                    break;
                }
            }
            if (colPiv == -1) break;

            // Fila pivote
            int filaPiv = -1;
            double menor = 999999;
            for (int i = 0; i < rest; i++) {
                if (tabla[i][colPiv] > 0) {
                    double ratio = tabla[i][columnas - 1] / tabla[i][colPiv];
                    if (ratio < menor) {
                        menor = ratio;
                        filaPiv = i;
                    }
                }
            }

            double pivote = tabla[filaPiv][colPiv];

            // Normalizar
            for (int j = 0; j < columnas; j++) {
                tabla[filaPiv][j] /= pivote;
            }

            // Hacer ceros
            for (int i = 0; i < filas; i++) {
                if (i != filaPiv) {
                    double factor = tabla[i][colPiv];
                    for (int j = 0; j < columnas; j++) {
                        tabla[i][j] -= factor * tabla[filaPiv][j];
                    }
                }
            }

            mostrarTabla(tabla);
        }

        System.out.println("=== SOLUCIÓN FINAL ===");

        for (int j = 0; j < vars; j++) {
            boolean esBase = false;
            int fila = -1;
            for (int i = 0; i < rest; i++) {
                if (tabla[i][j] == 1) {
                    if (fila == -1) fila = i;
                    else {
                        fila = -1;
                        break;
                    }
                } else if (tabla[i][j] != 0) {
                    fila = -1;
                    break;
                }
            }
            if (fila != -1) System.out.println("x" + (j+1) + " = " + tabla[fila][columnas - 1]);
            else System.out.println("x" + (j+1) + " = 0");
        }

        System.out.println("Z = " + tabla[filas - 1][columnas - 1]);

    }
    

}


    


    