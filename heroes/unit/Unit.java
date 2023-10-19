package main.heroes.unit;

import main.heroes.fraction.Fraction;
import main.heroes.player.Player;
import main.heroes.board.Field;

public interface Unit {

    int getSpeed();
    boolean isFlying();
    boolean isShooting();
    boolean isLargeUnit();
    boolean isDead();
    boolean canWait();
    boolean canCounterAttack();
    void setWait(boolean wait);
    Player getPlayer();

    void setCanCounterAttack(boolean canCounterAttack);

    void absorbDamage(int damage);
    void setField(Field field);
    Field getField();
    int getMaximalDamage();
    int getMinimalDamage();
    int getNumberOfUnits();
    void doMove(Field destination);

    Fraction getCity();

    void defend();
    void restoreDefence();
    int getRealDefence();
    int getAttack();

    String getSymbol();
}
