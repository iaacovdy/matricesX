package productomatriz;

/**
 * PROGRAMA QUE MULTIPLICA DOS MATRICES DE TAMAÑO ESTABLECIDO POR EL USUARIO Y RELLANADAS CON VALORES ALEATORIOS
 * LUEGO ORDENA LOS VALORES DE LA MATRIZ RESULTANTE AXB=C DE MENOR A MAYOR
 * FINALMENTE MUESTRA EN PANTALLA LAS MATRICES A,B,C,Cord EN FORMATO TABLA PARA MEJOR VISIBILIDAD
 * @author Daza Yepes Santiago
 */

import java.util.Scanner;
import java.util.Random;

public class ProductoMatriz {
    //Crea un vector a partir de una matriz de tamaño m*n
    public static int[] aplastar(int[][]matriz){
        int fil=matriz.length;
        int col=matriz[0].length;
        int[]aplastado=new int[fil*col];
        int lugar=0;
        //Asigna los valores de una matriz a los espacios del vector    
        for (int[] fi : matriz){
            for (int valor : fi){
                aplastado[lugar++]=valor;
            }
        }
        return aplastado;
    }
    //Crea una nueva matriz m*n que contendrá los valores ordenados
    public static int[][]desaplastar(int[]aplastado,int fil, int col){
        int[][]desaplastado=new int [fil][col];
        int lugar=0;
        //Asigna los valores ordenados del vector a los espacios vacíos de la matriz
        for (int f=0;f<fil;f++){
            for (int c=0;c<col;c++){
            desaplastado[f][c]=aplastado[lugar++];            
            }        
        }return desaplastado;        
    }

    //Método que parte un vector en dos, alrededor de un valor "eje" y asigna valores menores a su izquierda y mayores a su derecha
    private static int partir(int[]matriz,int men,int may){
        int eje=matriz[may];
        int i=(men-1);//Captura la posición del menor elemento
        //Compara cada elemento con el eje y los intercambia si este menor
        for(int j=men;j<may;j++){
            if (matriz[j]<=eje){
                i++;
                int temp=matriz[i];
                matriz[i]=matriz[j];
                matriz[j]=temp;
            }
        }
        //Avanza el eje
        int temp = matriz[i+1];
        matriz[i+1]=matriz[may];
        matriz[may]=temp;
        return i+1;
    }    
    
    //Método quicksort que ordenará los valores de un vector en menores y mayores alrededor de un eje
    public static void quickSort(int[]matriz,int men,int may){
        if (men<may){
            int eje=partir(matriz,men,may);
            quickSort(matriz,men,eje-1);
            quickSort(matriz,eje+1,may);
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
        if (nA==mB){
        
        //Crea una variable aleatoria genérica para rellenar las matrices
        Random rand = new Random();
        //Define la matriz A con los valores ingresados y rellena sus valores con valores aleatorios entre 1 y 9
        int A[][]=new int[mA][nA];
        for (int i=0;i<mA;i++){
            for (int j=0;j<nA;j++){
                A[i][j]=1+rand.nextInt(8);
            }
        }
        //Define la matriz B con los valores ingresados y rellena sus valores con valores aleatorios entre 1 y 9
        int B[][]=new int[mB][nB];
        for (int i=0;i<mB;i++){
            for (int j=0;j<nB;j++){
                B[i][j]=1+rand.nextInt(8);
            }
        }
        //Define la matriz C con filas iguales a A y columnas iguales a B
        int C[][]=new int[mA][nB];
        //Recorre la nueva matriz, calculando cada uno de sus elementos como suma producto correspondiente
        for (int i=0;i<mA;i++){
            for (int j=0;j<nB;j++){
                for (int k=0;k<nA;k++){
                    C[i][j]+=(A[i][k]*B[k][j]);
                }
            }
        }
        //Aplica el método aplastar para convertir C en un vector
        int[] vector=aplastar(C);
        //Aplica el método quickSort para ordenar el vector
        quickSort(vector,0,vector.length-1);
        //Convierte el vector ordenado en una matriz del tamaño original de C
        int[][] Cord=desaplastar(vector,C.length,C[0].length);
        
        //Muestra las matrices A,B,C y C ordenada, un vector a la vez para dar formato de tabla
        System.out.println("\n\nMatriz A:");
        for (int i=0;i<mA;i++){
            for (int j=0;j<nA;j++){
                System.out.print(" "+A[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n\nMatriz B:");
        for (int i=0;i<mB;i++){
            for (int j=0;j<nB;j++){
                System.out.print(" "+B[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n\nMatriz C:");
        for (int i=0;i<mA;i++){
            for (int j=0;j<nB;j++){
                System.out.print(" "+C[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n\nMatriz C Ordenada:");
        for (int i=0;i<mA;i++){
            for (int j=0;j<nB;j++){
                System.out.print(" "+Cord[i][j]+" ");
            }
            System.out.println();
        }
    //Devuelve error si las matrices no son multiplicables    
    }else System.out.println("Las columnas de A deben ser iguales a las filas de B");
}
}