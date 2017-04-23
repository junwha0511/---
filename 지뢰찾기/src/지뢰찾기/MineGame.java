package ����ã��;

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

/*20161009 �ʵ���� ����
  20161010 periphery ����, ���� ���� ���� �߻�. ���� ������ �Ϻ� ���� ���� ���ڰ� �������� �ʴ°�. 
           -1���� 1�� �������� ������ ������ 10���� ��ȯ. ������ ������ ��ӵ�. �ٽ� ��ȯ.
                             ���ٽ� predicate �Լ��� �������̽��� �ذ�
  20161011 �ڹ�fX�� ���� �õ�, ���� �� ���� ����
  20161014 �ڹ�fX ���� ����, ��ü �迭 �н� �Ϸ�. �н��� �ϴ� ��. ���� ���� �ִ� List�� Set�� ������ ��� ���� ����.
                             �̺�Ʈ �ڵ鷯�� ���� ������ ������ ������ �ϴ� �̺�Ʈ ó���⿡ ���ڰ� ����� ���۷���Ʈ�� List�� �����ϴ� �� ������. 
                             ������ �ؾ� �� ���� �� List���� x��� y���� ��� �����ؼ� �˻�������. ���� �迭�� ���ٸ� List�� �����ϰ� �迭�� 
                             �ٲٴ°� �������� ��.   
  20161015 ���ٽ��� �̿��� �ڵ带 ���� ����. �ݺ��� �۾��� �Լ����̴� �Լ��� ���α׷����� ���ٽ��� ����ϴ� ���� ���ٴ� ���� �н�.
  20161016 List���� List�� ��ġ�� �׷����� �õ�. ������ �� x,y��ǥ�� ������ 0�� ���� ��, ��� �� 0�� ���������� �ٲ�����
                             ���. for ���� �ʹ� ���� ��ø�Ǳ� ������ ���ٽ��� �̿��ϴ� �͵� ������ �غ��� ���� ������ ������
  20161017 ������ ���� ����� �ƴ�, Ŭ���ÿ� �ΰ��� �޼ҵ�� �з��Ǵ� ����� ���, ����� �ڹ� fx ���� ���ڸ� ������� ��ġ������ ����.
  20161018 �������� ���� ��ġ�� �ϴ� ���� �ٷ� 2�������� �۾������ϵ��� ����. �ڵ� 100���̻� ����. ȣȯ�� up. ���ϴ� ũ��� 
                             ����ã�⸦ �� �� �ְ� �� ������ ����. ���� �ʿ��� ���� �̺�Ʈ�� ȣ���ϴ� �޼ҵ�, StackPane�� ��Ȯ�� ����
                             �׼��� �ϳ��ϳ� ó���ؾ��ϴ� ���Ⱑ �߻�. �̺�Ʈ �������̽��� x��ǥ�� y��ǥ�� �� �� ���� ���� 
  20161025 handle�޼ҵ��� event �ް������� ȣ���� �� �ִ� getSource()�޼ҵ�� for������ ��ǥ ����� ����.
                              �ϳ� ���� ����. �������. toVisible�޼ҵ� ���������� ���������� ������ ����ؼ� �߻�. �ֺ��� �巯���� �ϴ� ����� �����ϰ�� �����۵�.
  20161026 Map,Set �÷����� �̿��� ���� ����. �۵��� ��� �����۵�. ���� ������ ���� �޼���, �����ϱ� ���� ��� ���ø� ����� ��. �ڹ� fx �ڵ�� background
                             ���� �������. �ڵ� ���� 342��.
                                                   */
public class MineGame extends Application {
	// XY���� ������ ũ���̴�. 1x1���� 15x15 ���� ��������.
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
    	primaryStage.setTitle("����ã��");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    });

    selectMode[1].setOnAction(event->{
    	MineGame.XY=25;
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("����ã��");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    });
    selectMode[2].setOnAction(event->{
    	MineGame.XY=50;
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("����ã��");
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
					label.setText("���� �¸�");
					label.setFont(new Font(50));
					root.getChildren().add(label);
				}else if (peripheryMineQuantity[xCoop][yCoop] == -1) {
					root.getChildren().clear();
					Label label = new Label();
					label.setText("���� �й�");
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
	primaryStage.setTitle("����ã��");
	primaryStage.setScene(scene);
	primaryStage.show();

}

public static void main(String[] args) {
	// FieldBuilder���� ���� ��ġ�� get
	FieldBuilder builder = new FieldBuilder();
	peripheryMineQuantity = builder.getperipheryMineQuantity();

	// UI on
		launch(args);

	}
}