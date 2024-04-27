package productomatriz;

/**
 * PROGRAMA QUE MULTIPLICA DOS MATRICES DE TAMAÑO ESTABLECIDO POR EL USUARIO Y RELLANADAS CON VALORES ALEATORIOS
 * LUEGO ORDENA LOS VALORES DE LA MATRIZ RESULTANTE AXB=C DE MENOR A MAYOR USANDO EL MÉTODO QUICKSORT
 * FINALMENTE MUESTRA EN PANTALLA LAS MATRICES A,B,C,Cord EN FORMATO TABLA PARA MEJOR VISIBILIDAD
 * PROGRAMACIÓN DE COMPUTADORES GR07
 * Cruz Sua Luis Carlos
 * Daza Yepes Santiago
 * Díaz Aguirre Juan Sebastián
 */

import java.util.Scanner;//Para leer datos del usuario
import java.util.Random;//Para generar números aleatorios

public class ProductoMatriz {
    //1. Crea una matriz de tamaño m*n
    public static int[][]crear(int fil,int col){
        int[][]M=new int[fil][col];
        return M;
    }
    //2. Rellena una matriz con valores aleatorios 1-9
    public static int[][]rellenar(int[][]M){
        Random rand = new Random();
        int[][]R=new int[M.length][M[0].length];
        for (int f=0;f<M.length;f++){
            for (int c=0;c<M[0].length;c++){
            R[f][c]=1+rand.nextInt(9);
            }
        }
        return R;
    }
    //3. Determina el producto X de dos matrices, asignando a cada valor la suma de productos de su respectiva fila y columna
    public static int[][]producto(int[][]A,int[][]B){
        int [][]X=crear(A.length,B[0].length);
        for (int i=0;i<A.length;i++){
            for (int j=0;j<B[0].length;j++){
                for (int k=0;k<A[0].length;k++){
                    X[i][j]+=(A[i][k]*B[k][j]);
                }
            }
        }        
        return X;   
    }   
    //4. Crea un vector a partir de una matriz de longitud=m*n
    public static int[]aplastar(int[][]matriz){
        int[]aplastado=new int[matriz.length*matriz[0].length];
        //Asigna los valores de una matriz a los espacios del vector    
        int k=0;
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[0].length;j++){
                aplastado[k++]=matriz[i][j];
            }
        }   
        return aplastado;
    }
    //5. Quicksort: que parte un vector en dos, alrededor de un valor "eje" y asigna valores menores a su izquierda y mayores a su derecha
    private static int partir(int[]vector,int men,int may){
        int eje=vector[may];
        int i=(men-1);//Captura la posición del menor elemento
        //Compara cada elemento con el eje y los intercambia si este menor
        for(int j=men;j<may;j++){
            if(vector[j]<=eje){
                i++;
                int temp;
                temp=vector[i];
                vector[i]=vector[j];
                vector[j]=temp;
            }
        }
        //Desplaza el eje
        int temp=vector[i+1];
        vector[i+1]=vector[may];
        vector[may]=temp;
        return i+1;
    }    
    //Método quicksort que se ejecutará recursivamente con los "subvectores" menores y mayores
    public static void quickSort(int[]vector,int men,int may){
        if(men<may){
            int eje=partir(vector,men,may);
            quickSort(vector,men,eje-1);
            quickSort(vector,eje+1,may);
            }
    }
    //6. Crea una nueva matriz m*n que contendrá los valores ordenados
    public static int[][]desaplastar(int[]aplastado,int fil,int col){
        int[][]desaplastado=crear(fil,col);
        int lugar=0;
        //Asigna los valores ordenados del vector a los espacios vacíos de la matriz
        for (int f=0;f<fil;f++){
            for (int c=0;c<col;c++){
            desaplastado[f][c]=aplastado[lugar++];            
            }        
        }return desaplastado;        
    }
    //7.Método para imprimir los valores de una matriz en formato de tabla
    public static void imprimir(int[][]M){
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                System.out.print(" "+M[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner SD=new Scanner(System.in);//Crea un nuevo scanner "SD"
        //Solicita al usuario que ingrese las dimensiones de las matrices A y B
        System.out.println("Ingrese las filas de A: ");
        int mA=SD.nextInt();
        System.out.println("Ingrese las columnas de A: ");
        int nA=SD.nextInt();
        System.out.println("Ingrese las filas de B: ");
        int mB=SD.nextInt();
        System.out.println("Ingrese las columnas de B: ");
        int nB=SD.nextInt();
        
        //Revisa que las columnas de A sean iguales a las filas de B
        if (nA!=mB){System.out.println("Las columnas de A deben ser iguales a las filas de B");
        }else{
            
        //Define las matrices A y B con las dimensiones proporcionadas
        int A[][]=crear(mA,nA);
        int B[][]=crear(mB,nB);

        //Rellena con valores aleatorios entre 1 y 9
        A=rellenar(A);
        B=rellenar(B);

        //Recorre la nueva matriz, calculando cada uno de sus elementos como suma producto correspondiente
        int C[][]=producto(A,B);
        
        //Convierte la matriz C en un vector
        int[]vector=aplastar(C);
        
        //Aplica el método quickSort para ordenar el "vector C"
        quickSort(vector,0,vector.length-1);
        
        //Restituye el "vector C ordenado" en una matriz del tamaño original de C
        int[][]Cord=desaplastar(vector,C.length,C[0].length);
        
        //Muestra las matrices A,B,C y C ordenada
        System.out.println("\nMatriz A:");
        imprimir(A);
        System.out.println("\nMatriz B:");
        imprimir(B);
        System.out.println("\nMatriz C:");
        imprimir(C);
        System.out.println("\nMatriz C Ordenada:");
        imprimir(Cord);
    }
    }
}
