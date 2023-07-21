package scylla;
import lib.other.Point2D;
import lib.std.StdRandom;
import lib.std.StdOut;
import lib.std.StdIn;
public class Kokomi {

    public static void main(String[] args) {
        System.out.printf("Enter the number of points >> ");
        int N = StdIn.readInt();
        var points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D(StdRandom.uniformDouble(0,1), StdRandom.uniformDouble(0,1));
            System.out.println(points[i].toString());
        }

        double squareDistance = points[0].distanceSquaredTo(points[1]);
        double tempSquareDistance;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    tempSquareDistance = points[i].distanceSquaredTo(points[j]);
                    if (tempSquareDistance < squareDistance) squareDistance = tempSquareDistance;
                }
            }
        }
        StdOut.printf("Cloest distance is: %f.\n", Math.sqrt(squareDistance));
    }

    
}

class ArgPassTester {
    private String name;
    private int id;

    public ArgPassTester(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void speak() {
        System.out.printf("name: %s >---< id: %d\n", name, id);
    }
    
}