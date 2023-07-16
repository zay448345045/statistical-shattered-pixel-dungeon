package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.custom.messages.M;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public abstract class SpellBase implements Bundlable{
    public float baseManaCost = 0f;
    public Category spellCate = Category.NONE;
    public int targetProperty;
<<<<<<< HEAD
    public int level = 1;
    public int maxLevel = 1;

    //cache data before mana is cost.
    public float maxMana;
    public float curMana;
    public float manaRegen;
    public float spellPower;
    public float spellDamage;
    public float costReduction;

    public void tryToCastSpell(Char caster){
        Mana mana = caster.buff(Mana.class);
        if(mana != null){
            buildData(mana);
            //yes, you can have negative mana value, but the penalty is high.
            mana.costMana(manaCost());
            castSpell(caster);
            ((Hero)caster).spendAndNext(castTime());
        }
    }

    protected void buildData(Mana mana){
        maxMana = mana.RMaxMana();
        curMana = mana.RCurMana();
        manaRegen = mana.RManaRegen();
        spellPower = mana.RSpellPower(spellCate);
        spellDamage = mana.RSpellDamage(spellCate);
        costReduction = mana.RCostReduce(spellCate);
    }




=======
    public int level;
    public int maxLevel;
>>>>>>> parent of fc08e2cff (last commit)

    public abstract void castSpell(Char caster);

    public abstract void visualEffect(Char caster, int targetCell);

    public abstract void onHit(Char caster, int targetCell, Char victim);

    public abstract void dealDamage(Char caster, Char victim);

    public abstract void onDeath(Char caster, Char victim);

    public String name(){
        return M.L(this.getClass(), "name");
    }

    public String desc(){
        return M.L(this.getClass(), descKey());
    }

    public String descKey(){
        return "desc_" + level;
    }

    public float manaCost(){
        return baseManaCost;
    }

    public void upgrade(int lvl){
        level = Math.min(level + lvl, maxLevel);
    }

    public void degrade(int lvl){
        level -= lvl;
    }

    //Consider: Can one spell have more than one element?
    public enum Category{
        FIRE,
        ICE,
        LIGHTNING,
        SHADOW,
        NONE,
        PASSIVE
    }

    public static final int T_NO = 0x01;
    public static final int T_NO_AUTO_AIM = 0x02;
    public static final int T_ANY = 0x04;
    public static final int T_ONLY_FRIENDLY = 0x08;
    public static final int T_NO_SELF = 0x10;
    public static final int T_NO_FRIENDLY = 0x20;
    public static final int T_NO_BOSS = 0x40;

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put("spell_level", level);
    }
}
