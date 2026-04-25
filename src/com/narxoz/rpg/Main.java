package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.state.PoisonedState;
import com.narxoz.rpg.state.StunnedState;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hero h1 = new Hero("Arthur", 100, 20, 5);
        Hero h2 = new Hero("Merlin", 80, 25, 2);
        
        h1.setState(new PoisonedState());
        h2.setState(new StunnedState());

        List<TowerFloor> floors = new ArrayList<>();
        floors.add(new CombatFloor());
        floors.add(new RestFloor());
        floors.add(new CombatFloor());
        floors.add(new CombatFloor());

        TowerRunner runner = new TowerRunner(floors);
        TowerRunResult result = runner.run(List.of(h1, h2));

        System.out.println("\n--- Tower Run Summary ---");
        System.out.println("Floors Cleared: " + result.getFloorsCleared());
        System.out.println("Heroes Surviving: " + result.getHeroesSurviving());
        System.out.println("Reached Top: " + result.isReachedTop());
    }
}