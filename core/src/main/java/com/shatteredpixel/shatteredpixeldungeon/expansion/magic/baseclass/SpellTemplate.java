package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;

import java.util.ArrayList;

public abstract class SpellTemplate {
    public float manaCost = 0f;
    public Category spellCate = Category.NONE;
    public int targetProperty;

    public void castSpell(Char caster){

    }

    public void visualEffect(Char caster, int targetCell){

    }

    public void onHit(Char caster, int targetCell, Char victim){

    }

    public void dealDamage(Char caster, Char victim){

    }

    public void onDeath(Char caster, Char victim){

    }

    //Consider: Can one spell have more than one element?
    public enum Category{
        FIRE,
        ICE,
        LIGHTNING,
        SHADOW,
        NATURE,
        PHYSICS,
        BASIC,
        NONE
    }

    public static final int T_NO = 0x01;
    public static final int T_NO_AUTO_AIM = 0x02;
    public static final int T_ANY = 0x04;
    public static final int T_ONLY_FRIENDLY = 0x08;
    public static final int T_NO_SELF = 0x10;
    public static final int T_NO_FRIENDLY = 0x20;
    public static final int T_NO_BOSS = 0x40;
}
