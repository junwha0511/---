package Áö·ÚÃ£±â;

public class RandomMines {
	private int randomMines[][] = new int[MineGame.XY][MineGame.XY];

	public RandomMines() {
		boolean flag = true;
		for (int i = 0; i < MineGame.Mine; i++) {
			while (flag) {
				int randomXcoop = (int) (Math.random() * MineGame.XY);
				int randomYcoop = (int) (Math.random() * MineGame.XY);
				if (randomMines[randomXcoop][randomYcoop] != -1) {
					randomMines[randomXcoop][randomYcoop] = -1;
					flag = false;
				}
			}
			flag = true;
		}
	}

	public int[][] getrandomMines() {
		return randomMines;
	}

}