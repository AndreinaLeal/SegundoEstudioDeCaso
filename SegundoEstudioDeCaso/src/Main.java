import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = System.out;

        AVL arbol = new AVL();

        out.println("=====================================");
        out.println("      Árbol AVL - Proyecto SOFT10    ");
        out.println("=====================================");

        ejecutarPruebasAutomaticas();

        while (true) {
            out.println("\n========= MENÚ =========");
            out.println("1. Insertar nodo");
            out.println("2. Buscar nodo");
            out.println("3. Eliminar nodo");
            out.println("4. Mostrar árbol (en orden)");
            out.println("5. Mostrar estructura detallada");
            out.println("6. Salir");
            out.print("Seleccione una opción: ");

            String op = br.readLine();
            if (op == null || op.equals("")) continue;

            int opcion = Integer.parseInt(op);

            if (opcion == 1) {
                out.print("Ingrese número a insertar: ");
                int v = Integer.parseInt(br.readLine());
                arbol.insertar(v);
                out.println("Insertado correctamente.");

            } else if (opcion == 2) {
                out.print("Ingrese valor a buscar: ");
                int v = Integer.parseInt(br.readLine());
                if (arbol.buscar(v)) out.println("El valor SÍ existe.");
                else out.println("El valor NO existe.");

            } else if (opcion == 3) {
                out.print("Ingrese valor a eliminar: ");
                int v = Integer.parseInt(br.readLine());
                arbol.eliminar(v);
                out.println("Eliminado (si existía).");

            } else if (opcion == 4) {
                out.println("Árbol en-orden:");
                arbol.imprimirEnOrden();

            } else if (opcion == 5) {
                out.println("Estructura del árbol:");
                arbol.imprimirDetalles();

            } else if (opcion == 6) {
                out.println("Saliendo...");
                break;

            } else {
                out.println("Opción inválida.");
            }
        }
    }

    //------------------------------------------------------
    //                PRUEBAS AUTOMÁTICAS
    //------------------------------------------------------
    private static void ejecutarPruebasAutomaticas() {
        PrintStream out = System.out;

        out.println("\n\n=====================================");
        out.println("        PRUEBAS AUTOMÁTICAS");
        out.println("=====================================");

        // LL Case
        out.println("\n[PRUEBA LL] Insertando: 30, 20, 10");
        AVL ll = new AVL();
        ll.insertar(30);
        ll.insertar(20);
        ll.insertar(10);
        ll.imprimirDetalles();

        // RR Case
        out.println("\n[PRUEBA RR] Insertando: 10, 20, 30");
        AVL rr = new AVL();
        rr.insertar(10);
        rr.insertar(20);
        rr.insertar(30);
        rr.imprimirDetalles();

        // LR Case
        out.println("\n[PRUEBA LR] Insertando: 30, 10, 20");
        AVL lr = new AVL();
        lr.insertar(30);
        lr.insertar(10);
        lr.insertar(20);
        lr.imprimirDetalles();

        // RL Case
        out.println("\n[PRUEBA RL] Insertando: 10, 30, 20");
        AVL rl = new AVL();
        rl.insertar(10);
        rl.insertar(30);
        rl.insertar(20);
        rl.imprimirDetalles();

        out.println("\n=====================================");
        out.println("   FIN DE PRUEBAS AUTOMÁTICAS");
        out.println("=====================================\n");
    }
}
