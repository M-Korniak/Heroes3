package main.heroes.action;

import lombok.Setter;
import main.heroes.unit.Unit;

import java.util.List;

public class Attack implements Action {
    private Unit unit;
    @Setter
    private Unit unitToAttack;
    public Attack(Unit unit) {
        this.unit = unit;
    }
    @Override
    public void execute(List<Unit> listOfWaitingUnits) {
        unit.setWait(true);
        DamageCalculator damageCalculator = new DamageCalculator();
        unitToAttack.absorbDamage(damageCalculator.calculateDamage(unit, unitToAttack));

        if (unitToAttack.canCounterAttack()) {
            unit.absorbDamage(damageCalculator.calculateDamage(unitToAttack, unit));
            unitToAttack.setCanCounterAttack(false);
        }
    }
}
