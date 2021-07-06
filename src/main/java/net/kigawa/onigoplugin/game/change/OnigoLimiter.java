package net.kigawa.onigoplugin.game.change;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.stage.Limiter;
import net.kigawa.util.plugin.stage.StageData;
import net.kigawa.util.plugin.timer.Counter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class OnigoLimiter extends Limiter {
    Counter counter;
    KigawaPlugin plugin;
    public OnigoLimiter(KigawaPlugin kigawaPlugin, StageData stageData, OnigoGame onigo) {
        super(kigawaPlugin,onigo.getJoinPlayer(),stageData);
        counter=new Counter("鬼ごっこ","onigo",kigawaPlugin);
        plugin=kigawaPlugin;
        oniPlayers=onigo.getOniPlayer();
        joinPlayers=onigo.getJoinPlayer();
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
