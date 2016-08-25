package joshie.harvest.calendar.command;

import joshie.harvest.api.HFCommand;
import joshie.harvest.api.HFQuest;
import joshie.harvest.api.calendar.Season;
import joshie.harvest.calendar.CalendarServer;
import joshie.harvest.core.handlers.HFTrackers;
import joshie.harvest.core.helpers.CalendarHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import static joshie.harvest.calendar.HFCalendar.DAYS_PER_SEASON;

@HFQuest
public class HFCommandDay extends HFCommand {
    @Override
    public String getCommandName() {
        return "day";
    }

    @Override
    public String getUsage() {
        return "<day>";
    }

    @Override
    public boolean execute(MinecraftServer server, ICommandSender sender, String[] parameters) {
        if (parameters != null && parameters.length == 1) {
            try {
                CalendarServer calendar = HFTrackers.getCalendar(sender.getEntityWorld());
                int day = Math.min(DAYS_PER_SEASON, Math.max(1, parseInt(parameters[0]))) - 1;
                Season season = calendar.getDate().getSeason();
                int year = Math.max(1, calendar.getDate().getYear());
                long time = CalendarHelper.getTime(day, season, year);
                sender.getEntityWorld().setWorldTime(time);
                calendar.recalculateAndUpdate();
                return true;
            } catch (NumberFormatException ignored) {}
        }
        return false;
    }
}