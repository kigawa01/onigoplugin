package net.kigawa.util.plugin.game.onigo.runnable;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.stage.StageData;
import net.kigawa.util.plugin.game.stage.runnable.Limiter;

public class GameLimiter extends Limiter {
    Game game;


    public GameLimiter(KigawaPlugin kigawaPlugin, StageData stageData, Game game) {
        super(kigawaPlugin, game.getJoinPlayer(), stageData);
        this.game = game;
    }

    @Override
    public void onRun() {
        game.alwaysEffect();
    }
}
