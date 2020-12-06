package environment;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

public class Environment implements IEnvironment {
    private ArrayList<Lane> lanes = new ArrayList<>();
    private Game game;

    public Environment(Game game){
        this.game = game;
        lanes.add(new Lane(game, 0, -1, game.randomGen.nextBoolean(), 0.0d));
        for (int i = 1; i < game.height - 1; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1, game.randomGen.nextBoolean(), game.defaultDensity));
        }
        lanes.add(new Lane(game, game.height - 1, -1, game.randomGen.nextBoolean(), 0.0d));
    }

    @Override
    public boolean isSafe(Case c) {
        return lanes.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return (c.ord == game.height - 1);
    }

    @Override
    public void update() {
        for (Lane lane : lanes) {
            lane.update();
        }
    }
}
