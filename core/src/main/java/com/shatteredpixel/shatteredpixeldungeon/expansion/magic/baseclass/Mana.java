package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.watabou.noosa.Game;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

import java.util.Locale;

public class Mana extends Buff {

    {
        announced = false;
        type = buffType.NEUTRAL;
        revivePersists = true;
    }

    public float maxMana = 20;
    public float curMana;

    public float spellPower;
    public float spellDamage;

    public float manaRegen;
    public float costReduction;

    private ManaIndicator manaText;

    @Override
    public boolean act() {

        spend(TICK);
        regenMana();
        //costMana(-0.5f);
        return true;
    }

    public void regenMana(){
        curMana = Math.min(curMana + manaRegen, maxMana);
        curMana = Math.max(curMana + manaRegen, 0);
        updateManaDisplay();
    }

    @Override
    public int icon() {
        return BuffIndicator.COMBO;
    }

    public int color(){
        float percent = manaPercent();
        int r=0;
        int g=0;
        int b=0;
        //like rainbow
        if(percent < 0f){
            r = 255;
            g = 0;
            b = 0;
        }else if(percent < 1/5f){
            r = 255;
            g = Math.round(152 * percent * 5);
            b = 0;
        }else if(percent < 2/5f){
            r = 255;
            g = Math.round(152 + 103 * (percent - 1/5f) *5f);
            b = 0;
        }else if(percent < 3/5f){
            r = Math.round(255 * (3/5f - percent) * 5);
            g = 255;
            b = 0;
        }else if(percent < 4/5f){
            r = 0;
            g = 255;
            b = Math.round(255 * (percent - 3/5f)*5);
            //skip 0x0000FF color because it's too harsh for eyes
        }else if(percent < 1f){
            r = Math.round(150 * (percent - 4/5f) * 5);
            g = Math.round(255 * (1f - percent) * 5);
            b = 255;
        }else {
            r = 150 + Math.round(105 * (percent - 1f) / (percent - 0.86f));
            g = 0;
            b = 255;
        }
        return (r<<16)+(g<<8)+b;
    }

    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(color());
    }

    public float manaPercent(){
        return curMana / maxMana;
    }

    public void costMana(float cost){
        curMana -= cost;
        updateManaDisplay();
    }

    public void updateManaDisplay(){
        if(manaText != null){
            manaText.killAndErase();
        }
        manaText = (ManaIndicator) GameScene.scene.recycle(ManaIndicator.class);
        manaText.camera = GameScene.uiCamera;
        manaText.refresh(target);
    }

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put("max_mana", maxMana);
        bundle.put("cur_mana", curMana);
        bundle.put("mana_regen", manaRegen);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        maxMana = bundle.getFloat("max_mana");
        curMana = bundle.getFloat("cur_mana");
        manaRegen = bundle.getFloat("mana_regen");
    }

    public static class ManaIndicator extends Group {

        private RenderedTextBlock rtb;

        public ManaIndicator() {
            super();
        }

        @Override
        public synchronized void update() {
            super.update();
        }

        public void refresh(Char ch){
            Mana mana = ch.buff(Mana.class);
            if(mana != null){
                Game.runOnRenderThread(new Callback() {
                    @Override
                    public void call() {
                        if(rtb == null) {
                            rtb = PixelScene.renderTextBlock(8);
                            add(rtb);
                        }
                        rtb.text(String.format(Locale.ENGLISH, "%.1f/%.1f", mana.curMana, mana.maxMana));
                        rtb.hardlight(mana.color());
                        rtb.setPos(0, 48);
                    }
                });
            }
        }
    }
}
