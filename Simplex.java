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
    }
    


    }