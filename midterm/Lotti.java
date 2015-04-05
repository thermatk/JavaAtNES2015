/**
 * Unofficial solution, have fun
 */

/**
 * @author undefined
 *
 */
import java.util.Arrays;

public class Lotti {
	private final static int NUM_FIELDS = 10;
	private final static int OFFSET = 3;
	private static int[] holes;

	private static void moveHole(int index) {
		int r = holes[index] + OFFSET;
		holes[index] = r % NUM_FIELDS;
	}

	private static void moveHoles() {
		moveHole(0);
		moveHole(1);
	}

	private static boolean isFalling(int rabbit) {
		return ((rabbit == holes[0]) || (rabbit == holes[1]));
	}

	private static boolean isFinished(int rabbit) {
		return (rabbit >= Lotti.NUM_FIELDS);
	}

	public static void main(String[] args) {

		holes = new int[2];
		holes[0] = 1;
		holes[1] = 6;

		int N = Integer.parseInt(args[0]);

		int[] rabbits = new int[N];
		for (int i = 0; i < N; i++) {
			rabbits[i] = -1;
		}

		boolean someoneFinished = false;
		while (!someoneFinished) {
			for (int i = 0; i < N; i++) {
				int step = StdRandom.uniform(4);
				if (step > 0) {
					rabbits[i] += step;
					StdOut.println("rabbit " + i + " jump " + step);

					if (isFalling(rabbits[i])) {
						rabbits[i] = -1;
						StdOut.println("rabbit " + i + " fell through");
					} else if (isFinished(rabbits[i])) {
						StdOut.println("rabbit " + i + " reached the finish");
						someoneFinished = true;
					}
				} else {
					moveHoles();
					StdOut.println("rabbit " + i + " move holes");
				}
			}

			StdOut.println("rabbits: " + Arrays.toString(rabbits));
			StdOut.println("holes: " + Arrays.toString(holes));
		}
	}
}
