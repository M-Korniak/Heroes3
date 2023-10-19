package main.heroes.action;

import lombok.Setter;
import main.heroes.unit.Unit;

import java.util.List;

public class Shoot implements Action {


    private Unit unit;
    @Setter
    private Unit unitToShoot;
    public Shoot(Unit unit) {
        this.unit = unit;
    }
    @Override
    public void execute(List<Unit> listOfWaitingUnits) {
        unit.setWait(true);
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        int distance = distanceCalculator.calculateDistance(unit, unitToShoot);
        DamageCalculator damageCalculator = new DamageCalculator();
        int damage = damageCalculator.calculateDamage(unit, unitToShoot);
        if (distance == 1 || distance > 10) {
            unitToShoot.absorbDamage(damage / 2);
        }
        else {
            unitToShoot.absorbDamage(damage);
        }
    }
}
