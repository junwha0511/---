package ����ã��;

public class FieldBuilder extends Thread {
	private int[][] mineCoordinates = new int[MineGame.XY][MineGame.XY];
	private int[][] peripheryMineQuantity = new int[MineGame.XY][MineGame.XY];
	// periphery:����,�ܸ�,�ѷ� quantity:�� ��,������ �ִ� ������ ���� ��Ÿ���� �ʵ���.

	public FieldBuilder() {
		while(MineGame.XY==0||MineGame.Mine==0){
			try {
				sleep(3000);
			} catch (InterruptedException e) {
			}
		}
		// ���� ���� ��ġ 1���� �迭�� ���
		RandomMines random = new RandomMines();
		// ���� ���� ��ġ�� 2���� �迭�� ������ ��ǥ�� ������ �� �ֵ��� �����
		this.mineCoordinates = random.getrandomMines();
		// �ڽ��� ���� ������ ���� ��Ÿ���� �ʵ�
		// PeripheryCounter�� ���� ��ġ�� ������ ���� ���� ���� ��ϵ� �迭�� get
		PeripheryCounter counter = new PeripheryCounter(mineCoordinates);
		this.peripheryMineQuantity = counter.getPeripheryMineQuantity();
	}

	public int[][] getmineCoordinates() {
		return mineCoordinates;
	}

	public int[][] getperipheryMineQuantity() {
		return peripheryMineQuantity;
	}

}