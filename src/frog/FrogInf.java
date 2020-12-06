package frog;

import util.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;

public class FrogInf implements IFrog {
	private Case aCase;
	private Game game;
	private int ord;
	private int score = 0;

	public FrogInf(Game game){
		this.game = game;
		aCase = new Case(13, 1);
		ord = 1;
	}

	@Override
	public int getOrd() {
		return ord;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public Case getPosition() {
		return aCase;
	}

	@Override
	public Direction getDirection() {
		return null;
	}

	@Override
	public void move(Direction key) {
		switch (key){
			case up:
				ord++;
				if (ord - 1 > score) score = ord-1;
				break;
			case down:
				if(ord > 1) ord--;
				break;
			case right:
				if(aCase.absc < game.width) aCase = new Case(aCase.absc + 1, aCase.ord);
				break;
			case left:
				if(aCase.absc > 0) aCase = new Case(aCase.absc -1, aCase.ord);
				break;
		}
	}
}
