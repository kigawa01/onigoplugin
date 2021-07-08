package net.kigawa.util.plugin.game.onigo.runnable;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.stage.runnable.Limiter;
import net.kigawa.util.plugin.game.stage.StageData;
import net.kigawa.util.plugin.all.timer.Counter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class GameLimiter extends Limiter {
    Counter counter;
    KigawaPlugin plugin;
    public GameLimiter(KigawaPlugin kigawaPlugin, StageData stageData, Game game) {
        super(kigawaPlugin,game.getJoinPlayer(),stageData);
        counter=new Counter("鬼ごっこ","onigo",kigawaPlugin);
        plugin=kigawaPlugin;
        oniPlayers=game.getOniPlayer();
        joinPlayers=game.getJoinPlayer();
    }
    List<Player> oniPlayers;
    List<Player> joinPlayers;

    @Override
    public void onRun() {
        for (Player player:oniPlayers){
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,4,1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,4,1));
        }
        for (Player player:joinPlayers){
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4,5));
        }
    }
}
