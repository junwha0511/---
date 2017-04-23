package 지뢰찾기;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class PeripheryCounter extends Thread {
	private int[][] peripheryMineQuantity = new int[MineGame.XY][MineGame.XY];

	public PeripheryCounter(int[][] mineCoordinates) {
		try {
			join();
		} catch (InterruptedException e) {
		}
		this.peripheryMineQuantity = mineCoordinates;
		calclutePeriphery();

	}

	public void calclutePeriphery() {

		BiConsumer<Integer, Integer> isMine = (x, y) -> {
			// 지뢰인지 아닌지 검사
			if (peripheryMineQuantity[x][y] != -1) {
				// 지뢰가 아니라면 +1
				peripheryMineQuantity[x][y] += 1;
				// for문과 if문으로 같은 좌표가 기억되어있는지 확인
			}

		};

		for (int x = 0; x < MineGame.XY; x++) {

			for (int y = 0; y < MineGame.XY; y++) {
				if (peripheryMineQuantity[x][y] == -1) {
					if (x != 0) {
						isMine.accept(x - 1, y);
						// periCoop.add(new Cooperates(x-1,y));
						if (y != MineGame.XY - 1) {
							isMine.accept(x - 1, y + 1);
						}
						if (y != 0) {
							isMine.accept(x - 1, y - 1);
						}
					}
					if (y != MineGame.XY - 1) {
						isMine.accept(x, y + 1);
					}
					if (y != 0) {
						isMine.accept(x, y - 1);
					}
					if (x != MineGame.XY - 1) {
						isMine.accept(x + 1, y);
						if (y != MineGame.XY - 1) {
							isMine.accept(x + 1, y + 1);
						}
						if (y != 0) {
							isMine.accept(x + 1, y - 1);
						}
					}

				}
			}
		}

	}

	public int[][] getPeripheryMineQuantity() {
		return peripheryMineQuantity;
	}
}