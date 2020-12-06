package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car(Game game, Case beforeFirstCase, boolean leftToRight){
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = game.randomGen.nextInt(3);
		if (leftToRight){
			leftPosition = beforeFirstCase;
		} else {
			leftPosition = new Case(beforeFirstCase.absc + length, beforeFirstCase.ord);
		}
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

	public void update(boolean move) {
		if (move){
			if (leftToRight)
				leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord);
			else
				leftPosition = new Case(leftPosition.absc - 1, leftPosition.ord);
		}
		addToGraphics();
	}

	public boolean isSafe(Case firstCase) {
		if (firstCase.ord == leftPosition.ord)
			return firstCase.absc < leftPosition.absc || firstCase.absc >= leftPosition.absc + length;
		return true;
	}

	public boolean shouldBeDeleted() {
		return leftPosition.absc + length < 0 || leftPosition.ord > game.width;
	}
}
