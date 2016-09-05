/**
 *
 * CodeEval - Lights Out
 * author (of the following program) - Sarthak Joshi
 *
 */

import java.util.*;

public class LightsOut
{
    /**
     * Global Variables
     */

//==============================================================\\

    private static int ROWS; //Number of rows in matrix
    private static int COLS; // Number of columns in matrix
    private static int[][] matrix; // matrix of lights
    private static int[] X = {0, 0, -1, 0, 1};
    private static int[] Y = {0, -1, 0, 1, 0};

//==============================================================\\


    /**
     * Back-end of Lights Out Games
     */


//==============================================================\\

    // toggles light switch at co-ordinate(r, c)
    public static void toggle(int r, int c) {
        if (!inBounds(r, c))
            return;

        toggleUtil(r, c);
        toggleUtil(r - 1, c);
        toggleUtil(r + 1, c);
        toggleUtil(r, c - 1);
        toggleUtil(r, c + 1);
    }


    // Utility for toggle method above
    private static void toggleUtil(int r, int c)
    {
        if (!inBounds(r, c))
            return;
        matrix[r][c] = 1 - matrix[r][c];
    }


    // checks if the matrix is solved
    public static boolean over()
    {
        int first = matrix[0][0];
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (matrix[i][j] != first)
                    return false;
            }
        }
        return true;
    }

//==============================================================\\


    /**
     *  Important methods to solve matrix
     */


//==============================================================\\

    // Checks if the co-ordinates are in the matrix
    public static boolean inBounds(int r, int c) {
        return r >= 0 && r < ROWS && c >= 0 && c < COLS;
    }

    // swaps 2 rows of array
    public static void swap(int x, int y, int arr[][], int n)
    {
        for (int i = 0; i <= n; i++)
        {
            int temp = arr[x][i];
            arr[x][i] = arr[y][i];
            arr[y][i] = temp;
        }
    }

    /*
        Gaussian-Elimination method to reduce rows of the matrix in the echelon form
        Returns x, which contains the co-ordinates of the light bulb, which needs to be toggled in order to solve the puzzle
    */
    public static int[] gauss(int[][] a)
    {
        int n = a.length;
        int[] x = new int[n];
        Arrays.fill(x, 0);
        for(int i = 0; i < n - 1; i++)
        {
            int w;
            for(w = i; w < n; w++)
                if(a[w][i] == 1)
                    break;

            if(w == n)
                continue;

            swap(w, i, a, n);

            for(int j = i + 1; j < n; j++)
            {
                if(a[j][i] == 1)
                {
                    for(int k = i; k < a[0].length; k++)
                        a[j][k] ^= a[i][k];
                }
            }
        }
        for(int i = n - 1; i >= 0; i--)
        {
            if (a[i][i] != 0)
            {
                x[i] = a[i][n];
                for (int k = i + 1; k < n; k++)
                    x[i] ^= a[i][k] & x[k];
            }
        }

        return x;
    }

//==============================================================\\


    /**
     * main() method
     */


//==============================================================\\


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] str = line.split(" ");
        ROWS = Integer.parseInt(str[0]);
        COLS = Integer.parseInt(str[1]);
        matrix = new int[ROWS][COLS];
        int[][] a = new int[ROWS * COLS][ROWS * COLS + 1];

        // creating array, which represents switches which get toggle when a particular switch is toggled
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                int p = i * COLS + j;
                for (int k = 0; k < 5; k++)
                {
                    int ni = i + X[k];
                    int nj = j + Y[k];
                    if (!inBounds(ni, nj))
                        continue;
                    a[p][ni * COLS + nj] = 1;
                }
            }
        }

        String[] str2 = str[2].split("\\|");
        int key1 = 0;
        for (String aStr2 : str2)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (aStr2.charAt(j) == '.')
                    a[key1][ROWS * COLS] = 0;
                else
                    a[key1][ROWS * COLS] = 1;
                key1++;
            }
        }

        int key2 = 0;
        while(key2 < ROWS * COLS)
        {
            matrix[key2 / COLS][key2 % COLS] = a[key2][ROWS * COLS];
            key2++;
        }

        int[] x = gauss(a);

        // sum -> Number of moves
        int sum = 0;
        for(int i = 0; i < ROWS*COLS; i++)
        {
            if(x[i] == 1)
            {
                toggle(i / COLS, i % COLS);
                sum++;
            }
        }

        if(over())
            System.out.println(sum);
        else
            System.out.println(-1);
    }
}

//==============================================================\\
