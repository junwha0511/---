package 지뢰찾기;

public class FieldBuilder extends Thread {
	private int[][] mineCoordinates = new int[MineGame.XY][MineGame.XY];
	private int[][] peripheryMineQuantity = new int[MineGame.XY][MineGame.XY];
	// periphery:주위,외면,둘레 quantity:양 즉,주위에 있는 지뢰의 수를 나타내는 필드임.

	public FieldBuilder() {
		while(MineGame.XY==0||MineGame.Mine==0){
			try {
				sleep(3000);
			} catch (InterruptedException e) {
			}
		}
		// 램덤 지뢰 위치 1차원 배열로 얻기
		RandomMines random = new RandomMines();
		// 얻은 지뢰 위치를 2차원 배열로 변경해 좌표를 지정할 수 있도록 만들기
		this.mineCoordinates = random.getrandomMines();
		// 자신이 접한 지뢰의 수를 나타내는 필드
		// PeripheryCounter에 지뢰 위치를 전송해 주위 지뢰 수가 기록된 배열을 get
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