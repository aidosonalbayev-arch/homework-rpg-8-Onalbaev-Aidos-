package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class RestFloor extends TowerFloor {
    @Override
    protected String getFloorName() {
        return "Safe Haven";
    }

    @Override
    protected void setup(List<Hero> party) {}

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero h : party) {
            h.heal(20);
        }
        return new FloorResult(true, 0, "The party rested by the campfire.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {}
}