package environment;

import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int horloge = 0;

	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = leftToRight;
		this.density = density;
	}

	public void update() {
		for (int i=0; i < cars.size(); i++) {
			cars.get(i).update(horloge == speed);
			if (cars.get(i).shouldBeDeleted()){
				cars.remove(i);
			}
		}

		if (horloge == speed) {
			horloge = 0;
			mayAddCar();
		}
		else horloge++;

		// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
		// d'horloge" égal é leur vitesse
		// Notez que cette méthode est appelée é chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut étre ajoutée

	}



	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale é la
	 * densité, si la premiére case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	public boolean isSafe(Case firstCase) {
		for (Car car :
				cars) {
			if (!car.isSafe(firstCase))
				return false;
		}
		return true;
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
