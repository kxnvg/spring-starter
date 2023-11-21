package org.kxnvg;

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.kxnvg.listener.DiscordEventListener;

import javax.security.auth.login.LoginException;

public class FunBot {

    protected static FunBot funBot;
    private ShardManager shardManager = null;

    public FunBot(String token) {
        try {
            shardManager = buildShardManager(token);
        } catch (LoginException e) {
            System.out.println("Failed to start bot! Please check the console for any errors.");
            System.exit(0);
        }
    }

    private ShardManager buildShardManager(String token) throws LoginException {
        return DefaultShardManagerBuilder.createDefault(token)
                .addEventListeners(new DiscordEventListener(this)).build();
    }

    public ShardManager getJDA() {
        return shardManager;
    }
}
