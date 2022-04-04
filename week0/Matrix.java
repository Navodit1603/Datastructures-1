public class Matrix {
    private final int[][] matrix;

    // store matrix
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    
    public String toString() {
        //nested loop for format
        StringBuilder output = new StringBuilder();
        for (int[] row : matrix) {
            
            for (int cell : row) {
                output.append((cell==-1) ? " " : String.format("%x",cell)).append(" ");
            }
            output.append("\n"); 
        }
        return output.toString();
    }

    // backward matrix
    public String reverse() {
        
        StringBuilder output = new StringBuilder();
        for (int i = matrix.length;  0 < i; i--) {
            
            for (int j =  matrix[i-1].length; 0 < j; j--) {
                output.append((matrix[i-1][j-1]==-1) ? " " : String.format("%x",matrix[i-1][j-1])).append(" ");
            }
            output.append("\n"); 
        }
        return output.toString();
    }

    
    static int[][] keypad() {
        return new int[][]{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, {-1, 0, -1} };
    }

 
    static int[][] numbers() {
        return new int[][]{ { 0, 1 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };
    }

    public static void main(String[] args) {
        Matrix m0 = new Matrix(keypad());
        System.out.println("Keypad:");
        System.out.println(m0);
        System.out.println(m0.reverse());


        Matrix m1 = new Matrix(numbers());
        System.out.println("Numbers Systems:");
        System.out.println(m1);
        System.out.println(m1.reverse());
        System.out.println("");

    }

}