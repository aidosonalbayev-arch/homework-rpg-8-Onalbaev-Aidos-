package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import java.util.List;

public class CombatFloor extends TowerFloor {
    private Monster monster;

    @Override
    protected String getFloorName() {
        return "Dark Dungeon";
    }

    @Override
    protected void setup(List<Hero> party) {
        monster = new Monster("Skeleton Warrior", 50, 15);
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamage = 0;
        while (monster.isAlive() && party.stream().anyMatch(Hero::isAlive)) {
            for (Hero h : party) {
                if (h.isAlive() && h.canAct()) {
                    monster.takeDamage(h.getModifiedAttack());
                }
            }
            if (monster.isAlive()) {
                for (Hero h : party) {
                    if (h.isAlive()) {
                        monster.attack(h);
                        totalDamage += 10;
                    }
                }
            }
        }
        return new FloorResult(monster.getHp() <= 0, totalDamage, "Defeated " + monster.getName());
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Loot found: Rusty Key.");
    }
}