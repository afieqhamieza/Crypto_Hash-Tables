import java.util.*;

/**
 * Problem1
 */
public class Problem1 {
    public static void main(String[] args) {
        // --------- variable declaration ----------
        String Z27 = "abcdefghijklmnopqrstuvwxyz ";
        Scanner scan = new Scanner(System.in);
        int[][] blocks_int = new int[5][5];
        String out_output = "";

        System.out.println();
        System.out.print("Enter an english hash function: ");
        String english_hash = scan.nextLine();

        //paddings
        while (english_hash.length() % 25 != 0) {
            english_hash += "x";
        }

        // split to blocks
        String[] blocks = english_hash.split("(?<=\\G.{25})");
        
        int OUT[][] = new int[blocks.length][];

        for (int i = 0; i < blocks.length; i++) {
            OUT[i] = new int[] { 0, 0, 0, 0, 0 };
        }
        // ------ end of variable declaration -------

        // ---------- round 1 hashing each block ----------
        for (int i = 0; i < blocks.length; i++) {
            blocks_int = hash_round1(blocks[i]);
            OUT[i] = sumCol(blocks_int, OUT[i]);
            blocks_int = hash_round2(blocks_int);
            OUT[i] = sumCol(blocks_int, OUT[i]);
            blocks_int = hash_round3(blocks_int);
            OUT[i] = sumRow(blocks_int, OUT[i]);
        }
        // ------- end of round 1 hashing each block -------

        for (int[] is : OUT) {
            for (int u : is) {
                out_output += Z27.charAt(u);
            }
        }

        System.out.println("Result: " + out_output);
    }

    public static int[] sumCol(int[][] block_in, int[] out_in) {
        int[] col_sum = new int[5];
        int sum = 0;

        for (int i = 0; i < block_in[0].length; ++i) {
            for (int j = 0; j < block_in.length; ++j) {
                sum += block_in[j][i];
            }

            sum %= 27;
            col_sum[i] = sum;
            sum = 0;
        }

        for (int i = 0; i < col_sum.length; i++) {
            out_in[i] += col_sum[i];
            out_in[i] %= 27;
        }

        // for (int i : out_in) {
        // System.out.print(i + " ");
        // }

        // System.out.println();

        return out_in;
    }

    public static int[] sumRow(int[][] block_in, int[] out_in) {
        int[] col_sum = new int[5];
        int sum = 0;

        for (int i = 0; i < block_in.length; i++) {
            for (int j = 0; j < block_in[0].length; j++) {
                sum = sum + block_in[i][j];
            }
            sum %= 27;
            col_sum[i] = sum;
            sum = 0;
        }

        for (int i = 0; i < col_sum.length; i++) {
            out_in[i] += col_sum[i];
            out_in[i] %= 27;
        }

        return out_in;
    }

    public static int[][] hash_round1(String str_in) {
        String Z27 = "abcdefghijklmnopqrstuvwxyz ";
        char[][] blocks_char = new char[5][5];
        int[][] blocks_int = new int[5][5];
        int[] col_sum = new int[5];

        // --------- initializing blocks_char ----------
        int k = 0;
        for (int i = 0; i < blocks_char.length; i++) {
            for (int j = 0; j < blocks_char.length; j++) {
                blocks_char[i][j] = str_in.charAt(k);
                k++;
            }
        }
        // ------ end of initializing blocks_char ----------

        // --------- initializing blocks_int ----------
        k = 0;
        for (int i = 0; i < blocks_int.length; i++) {
            for (int j = 0; j < blocks_int.length; j++) {
                char c = str_in.charAt(k);
                blocks_int[i][j] = Z27.indexOf(c);
                k++;
            }
        }
        // ------ end of initializing blocks_int ----------

        // ----------- sum of columns ----------
        // col_sum = sumCol(blocks_int);
        // -------- end of sum of columns ----------

        // ----------- debugging
        // for (int[] is : blocks_int) {
        // for (int u : is) {
        // System.out.print(u + " ");
        // }
        // System.out.println();
        // }

        // for (int i : col_sum) {
        // System.out.println(i);
        // }

        return blocks_int;
    }

    public static int[][] hash_round2(int[][] block_in) {

        int[][] arrayShifted = block_in;

        for (int i = 0; i < block_in.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                int k, head;

                head = arrayShifted[i][0];

                for (k = 0; k < ((arrayShifted.length) - 1); k++) {
                    arrayShifted[i][k] = arrayShifted[i][k + 1];
                }

                arrayShifted[i][k] = head;
            }
        }

        // for (int[] is : arrayShifted) {
        // for (int u : is) {
        // System.out.print(u + " ");
        // }
        // System.out.println();
        // }

        return arrayShifted;
    }

    public static int[][] hash_round3(int[][] block_in) {
        int[][] arrayShifted = block_in;

        for (int j = 0; j < block_in.length; j++) {
            for (int i = 0; i < j + 1; i++) {
                int k, head;

                head = arrayShifted[4][j];

                // for (k = 0; k < ((arrayShifted[0].length) - 1); k++) {
                // arrayShifted[k][j] = arrayShifted[k+1][j];
                // }

                for (k = 4; k > 0; k--) {
                    arrayShifted[k][j] = arrayShifted[k - 1][j];
                }

                arrayShifted[k][j] = head;
            }
        }

        // for (int[] is : arrayShifted) {
        // for (int u : is) {
        // System.out.print(u + " ");
        // }
        // System.out.println();
        // }

        return arrayShifted;
    }
}