package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import java.util.ArrayList;
import java.util.List;

public class TowerRunner {
    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult run(List<Hero> party) {
        int cleared = 0;
        for (TowerFloor floor : floors) {
            if (party.stream().noneMatch(Hero::isAlive)) break;
            
            for (Hero h : party) h.performTurnStart();
            FloorResult res = floor.explore(party);
            for (Hero h : party) h.performTurnEnd();

            if (res.isCleared()) cleared++;
            else break;
        }
        
        long surviving = party.stream().filter(Hero::isAlive).count();
        return new TowerRunResult(cleared, (int) surviving, cleared == floors.size());
    }
}