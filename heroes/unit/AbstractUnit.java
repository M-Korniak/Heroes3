package main.heroes.unit;

import lombok.Getter;
import lombok.Setter;
import main.heroes.board.Field;
import main.heroes.fraction.Fraction;
import main.heroes.player.Player;

public abstract class AbstractUnit implements Unit {
    @Override
    public int getAttack() {
        return attack;
    }

    private int attack;

    private int defence;
    @Getter
    private Fraction city;

    @Override
    public int getRealDefence() {
        return realDefence;
    }

    private int realDefence;
    private int numberOfArrows;
    private int healthPoints;
    private int totalHealth;
    @Getter
    private boolean isLargeUnit;
    @Getter
    private boolean isDead = false;
    @Getter
    private int minimalDamage;
    @Getter
    private int maximalDamage;
    @Getter
    private int numberOfUnits;
    @Getter
    private int speed;
    @Getter
    private boolean isShooting;
    @Getter
    private boolean isFlying;
    @Getter
    private Player player;
    private String symbol;

    public AbstractUnit(int attack, int defence,
                        int numberOfArrows, int healthPoints,
                         int minimalDamage,
                        int maximalDamage, int numberOfUnits,
                        int speed, boolean isFlying, boolean isShooting,
                        Player player, String symbol, Fraction city) {
        this.attack = attack;
        this.defence = defence;
        this.numberOfArrows = numberOfArrows;
        this.healthPoints = healthPoints;
        this.minimalDamage = minimalDamage;
        this.maximalDamage = maximalDamage;
        this.numberOfUnits = numberOfUnits;
        this.speed = speed;
        this.isFlying = isFlying;
        this.isShooting = isShooting;
        this.player = player;
        this.symbol = symbol;
        this.city = city;
        totalHealth = healthPoints * numberOfUnits;
        realDefence = defence;
    }

    @Getter
    @Setter
    private Field field;
    @Setter
    private boolean wait = true;
    private boolean canCounterAttack = true;

    @Override
    public boolean canWait() {
        return wait;
    }

    @Override
    public String getSymbol() {
        return " " + symbol + this.getPlayer().getSymbol() + " ";
    }
    public boolean canCounterAttack() {
        return canCounterAttack;
    }
    @Override
    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

    @Override
    public void absorbDamage(int damage) {
        totalHealth = totalHealth - damage;
        if (totalHealth <= 0) {
            die();
            return;
        }
        numberOfUnits = totalHealth / healthPoints;
        if (totalHealth % healthPoints != 0) {
            numberOfUnits++;
        }
    }
    private void die() {
        getField().setUnit(null);
        getPlayer().deleteUnit(this);
        isDead = true;
    }
    @Override
    public void doMove(Field destination) {
        if (field != null)
            field.setUnit(null);
        field = destination;
        destination.setUnit(this);
    }

    @Override
    public void defend() {
        realDefence = (int) (defence * 1.3);
    }

    @Override
    public void restoreDefence() {
        realDefence = defence;
    }
}
