/**
 * Unofficial solution with classes, have fun
 */

/**
 * @author undefined
 *
 */
import java.util.Arrays;

class Rabbit {
	private int position = -1;
	private int id;

	Rabbit(int num) {
		id = num;
	}

	private void changePosition(int delta) {
		position += delta;
	}

	private void fall() {
		position = -1;
	}

	private boolean isFinished() {
		return (position >= Lotti.getNumFields());
	}

	public boolean makeStep(Holes holes) {
		int step = StdRandom.uniform(4);
		if (step > 0) {
			changePosition(step);
			StdOut.println("rabbit " + id + " jump " + step);
			if (holes.isFalling(position)) {
				fall();
				StdOut.println("rabbit " + id + " fell through");
			} else if (isFinished()) {
				StdOut.println("rabbit " + id + " reached the finish");
				return true;
			}
		} else {
			holes.moveHoles();
			StdOut.println("rabbit " + id + " move holes");
		}
		return false;
	}

	public String toString() {
		return Integer.toString(position);
	}
}

class Holes {
	private int[] holes;

	public int[] getHoles() {
		return holes;
	}

	Holes() {
		holes = new int[2];
		holes[0] = 1;
		holes[1] = 6;
	}

	private void moveHole(int index) {
		int r = holes[index] + Lotti.getOffset();
		holes[index] = r % Lotti.getNumFields();
	}

	public void moveHoles() {
		moveHole(0);
		moveHole(1);
	}

	public boolean isFalling(int position) {
		return ((position == holes[0]) || (position == holes[1]));
	}
}

public class Lotti {
	private final static int NUM_FIELDS = 10;
	private final static int OFFSET = 3;

	public static int getNumFields() {
		return NUM_FIELDS;
	}

	public static int getOffset() {
		return OFFSET;
	}

	public static void main(String[] args) {

		Holes holes = new Holes();

		int N = Integer.parseInt(args[0]);

		Rabbit[] rabbits = new Rabbit[N];
		for (int i = 0; i < N; i++) {
			rabbits[i] = new Rabbit(i);
		}
		boolean someoneFinished = false;
		while (!someoneFinished) {

			for (int i = 0; i < N; i++) {
				if (rabbits[i].makeStep(holes)) {
					someoneFinished = true;
				}
			}

			StdOut.println("rabbits: " + Arrays.toString(rabbits));
			StdOut.println("holes: " + Arrays.toString(holes.getHoles()));
		}
	}
}
