package ����ã��;

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
			// �������� �ƴ��� �˻�
			if (peripheryMineQuantity[x][y] != -1) {
				// ���ڰ� �ƴ϶�� +1
				peripheryMineQuantity[x][y] += 1;
				// for���� if������ ���� ��ǥ�� ���Ǿ��ִ��� Ȯ��
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