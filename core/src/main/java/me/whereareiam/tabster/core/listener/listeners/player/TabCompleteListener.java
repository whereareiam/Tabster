package me.whereareiam.tabster.core.listener.listeners.player;

import me.whereareiam.tabster.core.logic.dummyplayer.DummyPlayer;

import java.util.Set;

public interface TabCompleteListener {
	Set<String> onTabComplete(DummyPlayer dummyPlayer, Set<String> completions);
}