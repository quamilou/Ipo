package environment;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

public class EnvInf implements IEnvironment {
    private ArrayList<Lane> lanes = new ArrayList<>();
    private ArrayList<Lane> lanesToDisplay = new ArrayList<>();
    private Game game;

    public EnvInf(Game game){
        this.game = game;
        lanes.add(new Lane(game, 0, -1, game.randomGen.nextBoolean(), 0.0d));
        lanes.add(new Lane(game, 1, -1, game.randomGen.nextBoolean(), 0.0d));
        for (int i = 2; i < game.height; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1, game.randomGen.nextBoolean(), game.defaultDensity));
        }
        lanesToDisplay = lanes;
    }

    @Override
    public boolean isSafe(Case c) {
        if (c.ord == 0 && c.ord == game.height)
            return true;
        return lanesToDisplay.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return (c.ord == game.height - 1);
    }

    @Override
    public void update() {
        int ordMin = game.getFrogOrd() - 1;
        if (ordMin + game.height > lanes.size()) lanes
                .add(new Lane(game, ordMin + game.height, game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1, game.randomGen.nextBoolean(), game.defaultDensity));

        lanesToDisplay = new ArrayList<>();
        for (int i = 0; i < game.height; i++) {
            lanes.get(i + ordMin).setOrd(i);
            lanesToDisplay.add(lanes.get(i + ordMin));
        }
        for (int i = 0; i < lanesToDisplay.size(); i++) {
            lanesToDisplay.get(i).update();
        }
    }
}

