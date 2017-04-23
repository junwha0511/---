package 지뢰찾기;

import java.awt.TextArea;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*20161009 필드빌더 구성
  20161010 periphery 구성, 여러 차례 오류 발생. 현재 문제는 일부 지뢰 옆에 숫자가 더해지지 않는것. 
           -1에도 1이 더해졌을 것으로 추정해 10으로 전환. 여전히 문제는 계속됨. 다시 전환.
                             람다식 predicate 함수적 인터페이스로 해결
  20161011 자바fX를 구현 시도, 아직 잘 되지 않음
  20161014 자바fX 구현 성공, 객체 배열 학습 완료. 학습을 하는 중. 현재 배우고 있는 List와 Set의 개념을 어느 정도 잡음.
                             이벤트 핸들러를 오늘 들어가려고 했지만 오늘은 일단 이벤트 처리기에 숫자가 저장된 쿠퍼레이트를 List로 전달하는 것 까지함. 
                             다음에 해야 할 것은 이 List에서 x축과 y축을 어떻게 추출해서 검사할지임. 만약 배열이 낫다면 List를 제거하고 배열로 
                             바꾸는게 나을지도 모름.   
  20161015 람다식을 이용해 코드를 대폭 줄임. 반복적 작업은 함수적이니 함수적 프로그래밍인 람다식을 사용하는 것이 좋다는 것을 학습.
  20161016 List내에 List를 배치해 그룹핑을 시도. 임의의 한 x,y좌표의 주위의 0을 저장 후, 어떻게 그 0을 기준점으로 바꿀지가
                             고민. for 문이 너무 많이 중첩되기 때문에 람다식을 이용하는 것도 생각을 해보면 좋을 것으로 생각됨
  20161017 이전과 같은 방법이 아닌, 클릭시에 두가지 메소드로 분류되는 방법을 고안, 현재는 자바 fx 에서 지뢰를 어떤식으로 배치할지가 관건.
  20161018 랜덤으로 지뢰 배치를 하는 것을 바로 2차원으로 작업가능하도록 변경. 코드 100줄이상 절약. 호환성 up. 원하는 크기로 
                             지뢰찾기를 할 수 있게 될 것으로 예상. 현재 필요한 것은 이벤트를 호출하는 메소드, StackPane의 정확한 사용법
                             액션을 하나하나 처리해야하는 위기가 발생. 이벤트 인터페이스는 x좌표와 y좌표를 알 수 없기 때문 
  20161025 handle메소드의 event 메개값에서 호출할 수 있는 getSource()메소드와 for문으로 좌표 출력을 성공.
                              꽤나 많은 성과. 오류덩어리. toVisible메소드 구현까지는 성공했으나 오류가 계속해서 발생. 주변을 드러나게 하는 기능을 제외하고는 정상작동.
  20161026 Map,Set 컬렉션을 이용해 구현 성공. 작동은 모두 정상작동. 이제 끝나면 나올 메세지, 시작하기 전에 모드 선택만 만들면 됨. 자바 fx 코드로 background
                             설정 배워야함. 코드 총합 342줄.
                                                   */
public class MineGame extends Application {
	// XY²이 게임의 크기이다. 1x1부터 15x15 까지 설정가능.
public static int XY = 10;
public static int Mine = 20;
public static int[][] peripheryMineQuantity = null;
public static Button[][] bt = new Button[XY][XY];
private Map<Button, String> map = new Hashtable<Button, String>();
private Set<Button> set = new HashSet<Button>();
private boolean winOrgo = true;
private BiConsumer<Integer, Integer> setTextFor = (xCoop, yCoop) -> {
	if (peripheryMineQuantity[xCoop][yCoop] == 0) {
		map.put(bt[xCoop][yCoop], "0");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 1) {
		map.put(bt[xCoop][yCoop], "1");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 2) {
		map.put(bt[xCoop][yCoop], "2");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 3) {
		map.put(bt[xCoop][yCoop], "3");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 4) {
		map.put(bt[xCoop][yCoop], "4");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 5) {
		map.put(bt[xCoop][yCoop], "5");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 6) {
		map.put(bt[xCoop][yCoop], "6");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 7) {
		map.put(bt[xCoop][yCoop], "7");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 8) {
		map.put(bt[xCoop][yCoop], "8");
	} else if (peripheryMineQuantity[xCoop][yCoop] == 9) {
		map.put(bt[xCoop][yCoop], "9");
	}
	set.add(bt[xCoop][yCoop]);
};

public void toCount(int x, int y) {
	BiConsumer<Integer, Integer> consume = (xCoop, yCoop) -> {
		if (set.size() != (XY * XY) - Mine) {
			if (peripheryMineQuantity[xCoop][yCoop] == 0) {
				if (!map.containsKey(bt[xCoop][yCoop])) {
					setTextFor.accept(xCoop, yCoop);
					toCount(xCoop, yCoop);
				}
			} else if (peripheryMineQuantity[xCoop][yCoop] > 0) {
				if (!map.containsKey(bt[xCoop][yCoop])) {
					setTextFor.accept(xCoop, yCoop);
				}
			}else if(peripheryMineQuantity[xCoop][yCoop]==-1 ) {
				
			}

	} else {
		winOrgo=false;
		}
	};
	if (x != 0) {
		consume.accept(x - 1, y);
		if (y != MineGame.XY - 1) {
			consume.accept(x - 1, y + 1);
		}
		if (y != 0) {
			consume.accept(x - 1, y - 1);
		}
	}
	if (y != MineGame.XY - 1) {
		consume.accept(x, y + 1);
	}
	if (y != 0) {
		consume.accept(x, y - 1);
	}
	if (x != MineGame.XY - 1) {
		consume.accept(x + 1, y);
		if (y != MineGame.XY - 1) {
			consume.accept(x + 1, y + 1);
		}
		if (y != 0) {
			consume.accept(x + 1, y - 1);
		}

	}
}

@Override
public void start(Stage primaryStage) throws Exception {

	VBox root = new VBox();
	root.setAlignment(Pos.CENTER);
	root.setSpacing(0);
	
	/*VBox beforeRoot = new VBox();
	beforeRoot.setAlignment(Pos.CENTER);
	beforeRoot.setSpacing(0);*/
	
	HBox[] roots = new HBox[XY];
/*	TilePane tiles = new TilePane();
	Button[] selectMode = new Button[4];
    selectMode[0].setText("10x10");
    selectMode[1].setText("25x25");
    selectMode[2].setText("50x50");
    selectMode[3].setText("Free Set");
    
    selectMode[0].setMinSize(200, 200);
    selectMode[1].setMinSize(200, 200);
    selectMode[2].setMinSize(200, 200);
    selectMode[3].setMinSize(200, 200);
    
    selectMode[0].setOnAction(event->{
    	MineGame.XY=10;
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("지뢰찾기");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    });

    selectMode[1].setOnAction(event->{
    	MineGame.XY=25;
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("지뢰찾기");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    });
    selectMode[2].setOnAction(event->{
    	MineGame.XY=50;
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("지뢰찾기");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    });
    selectMode[3].setOnAction(event->{
    	VBox box = new VBox();
    	Label boxL = new Label();
    	Label mineL = new Label();
    	TextArea boxT = new TextArea();
    	TextArea mineT = new TextArea();
    	box.getChildren().add(boxL);
    	box.getChildren().add(boxL);
    });*/
	for (int x = 0; x < XY; x++) {
		roots[x] = new HBox();
		for (int y = 0; y < XY; y++) {
			bt[x][y] = new Button();
			bt[x][y].setPrefSize(50, 50);
			bt[x][y].setMinSize(50, 50);
			bt[x][y].setText(" ");
			bt[x][y].setStyle("fx:padding=0;");
			bt[x][y].setOnAction(event -> {
				int xCoop = 0, yCoop = 0;
				Button button = (Button) event.getSource();
				for (int i = 0; i < XY; i++) {
					for (int j = 0; j < XY; j++) {
						if (button.equals(MineGame.bt[i][j])) {
							xCoop = i;
							yCoop = j;
						}
					}
				}
				if(set.size()==(XY*XY)-Mine-1){
					root.getChildren().clear();
					Label label = new Label();
					label.setText("게임 승리");
					label.setFont(new Font(50));
					root.getChildren().add(label);
				}else if (peripheryMineQuantity[xCoop][yCoop] == -1) {
					root.getChildren().clear();
					Label label = new Label();
					label.setText("게임 패배");
					label.setFont(new Font(50));
					root.getChildren().add(label);
					
				} else if (peripheryMineQuantity[xCoop][yCoop] == 0) {
					
					setTextFor.accept(xCoop, yCoop);
					toCount(xCoop, yCoop);
					Set<Map.Entry<Button, String>> entrySet = map.entrySet();
					Iterator<Map.Entry<Button, String>> entryIterator = entrySet.iterator();
					while (entryIterator.hasNext()) {
						Map.Entry<Button, String> entry = entryIterator.next();
						entry.getKey().setText(entry.getValue());
					}
					
				} else if (peripheryMineQuantity[xCoop][yCoop] > 0) {

					if (peripheryMineQuantity[xCoop][yCoop] == 0) {
						bt[xCoop][yCoop].setText("0");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 1) {
						bt[xCoop][yCoop].setText("1");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 2) {
						bt[xCoop][yCoop].setText("2");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 3) {
						bt[xCoop][yCoop].setText("3");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 4) {
						bt[xCoop][yCoop].setText("4");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 5) {
						bt[xCoop][yCoop].setText("5");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 6) {
						bt[xCoop][yCoop].setText("6");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 7) {
						bt[xCoop][yCoop].setText("7");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 8) {
						bt[xCoop][yCoop].setText("8");
					} else if (peripheryMineQuantity[xCoop][yCoop] == 9) {
						bt[xCoop][yCoop].setText("9");
					}
					
				}
			
			}
			
			);

			roots[x].getChildren().add(bt[x][y]);
			roots[x].setAlignment(Pos.CENTER);
			roots[x].setSpacing(0);
		}
		root.getChildren().add(roots[x]);
	}

	Scene scene = new Scene(root);
	primaryStage.setTitle("지뢰찾기");
	primaryStage.setScene(scene);
	primaryStage.show();

}

public static void main(String[] args) {
	// FieldBuilder에서 지뢰 위치를 get
	FieldBuilder builder = new FieldBuilder();
	peripheryMineQuantity = builder.getperipheryMineQuantity();

	// UI on
		launch(args);

	}
}