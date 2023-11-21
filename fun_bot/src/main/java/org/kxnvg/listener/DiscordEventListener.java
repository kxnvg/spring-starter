package org.kxnvg.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import org.kxnvg.FunBot;

public class DiscordEventListener extends ListenerAdapter {

    public FunBot bot;

    public DiscordEventListener(FunBot bot) {
        this.bot = bot;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        registerCommands(bot.getJDA());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("hello")) {
            event.reply("Hello " + event.getUser().getAsMention() + "!")
                    .setEphemeral(true)
                    .queue();
        } else if (event.getName().equals("ping")) {
            event.reply("pong")
                    .setEphemeral(true)
                    .queue();
        }
    }

    private void registerCommands(ShardManager jda) {
        Guild guild = jda.getGuildById("1117110631204663397");
        if (guild != null) {
            CommandListUpdateAction commands = guild.updateCommands();
            commands.addCommands(Commands.slash("ping", "Have the bot say pong to you")).queue();
            commands.addCommands(Commands.slash("hello", "Have the bot say hello to you in an ephemeral message!")).queue();
        }
    }
}
