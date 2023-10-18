package com.shatteredpixel.shatteredpixeldungeon.minigames.mvh.monsters;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.RainbowParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.minigames.mvh.MvHMobSprite;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;

public abstract class MvHElementalSprite extends MvHMobSprite {
    protected int boltType;

    protected abstract int texOffset();

    private Emitter particles;
    protected abstract Emitter createEmitter();

    public MvHElementalSprite() {
        super();

        int c = texOffset();

        texture( Assets.Sprites.ELEMENTAL );

        TextureFilm frames = new TextureFilm( texture, 12, 14 );

        idle = new Animation( 10, true );
        idle.frames( frames, c+0, c+1, c+2 );

        run = new Animation( 12, true );
        run.frames( frames, c+0, c+1, c+3 );

        attack = new Animation( 15, false );
        attack.frames( frames, c+4, c+5, c+6 );

        zap = attack.clone();

        die = new Animation( 15, false );
        die.frames( frames, c+7, c+8, c+9, c+10, c+11, c+12, c+13, c+12 );

        play( idle );
    }

    @Override
    public void link( Char ch ) {
        super.link( ch );

        if (particles == null) {
            particles = createEmitter();
        }
    }

    @Override
    public void update() {
        super.update();

        if (particles != null){
            particles.visible = visible;
        }
    }

    @Override
    public void die() {
        super.die();
        if (particles != null){
            particles.on = false;
        }
    }

    @Override
    public void kill() {
        super.kill();
        if (particles != null){
            particles.killAndErase();
        }
    }

    public void zap( int cell ) {

        turnTo( ch.pos , cell );
        play( zap );

        MagicMissile.boltFromChar( parent,
                boltType,
                this,
                cell,
                new Callback() {
                    @Override
                    public void call() {
                        ((Elemental)ch).onZapComplete();
                    }
                } );
        Sample.INSTANCE.play( Assets.Sounds.ZAP );
    }

    @Override
    public void onComplete( Animation anim ) {
        if (anim == zap) {
            idle();
        }
        super.onComplete( anim );
    }

    public static class Fire extends MvHElementalSprite {

        {
            boltType = MagicMissile.FIRE;
        }

        @Override
        protected int texOffset() {
            return 0;
        }

        @Override
        protected Emitter createEmitter() {
            Emitter emitter = emitter();
            emitter.pour( FlameParticle.FACTORY, 0.06f );
            return emitter;
        }

        @Override
        public int blood() {
            return 0xFFFFBB33;
        }
    }

    public static class NewbornFire extends MvHElementalSprite {

        {
            boltType = MagicMissile.FIRE;
        }

        @Override
        protected int texOffset() {
            return 14;
        }

        @Override
        protected Emitter createEmitter() {
            Emitter emitter = emitter();
            emitter.pour( ElmoParticle.FACTORY, 0.06f );
            return emitter;
        }

        @Override
        public int blood() {
            return 0xFF85FFC8;
        }
    }

    public static class Frost extends MvHElementalSprite {

        {
            boltType = MagicMissile.FROST;
        }

        @Override
        protected int texOffset() {
            return 28;
        }

        @Override
        protected Emitter createEmitter() {
            Emitter emitter = emitter();
            emitter.pour( MagicMissile.MagicParticle.FACTORY, 0.06f );
            return emitter;
        }

        @Override
        public int blood() {
            return 0xFF8EE3FF;
        }
    }

    public static class Shock extends MvHElementalSprite {

        //different bolt, so overrides zap
        @Override
        public void zap( int cell ) {
            turnTo( ch.pos , cell );
            play( zap );

            ((Elemental)ch).onZapComplete();
            parent.add( new Beam.LightRay(center(), DungeonTilemap.raisedTileCenterToWorld(cell)));
        }

        @Override
        protected int texOffset() {
            return 42;
        }

        @Override
        protected Emitter createEmitter() {
            Emitter emitter = emitter();
            emitter.pour( SparkParticle.STATIC, 0.06f );
            return emitter;
        }

        @Override
        public int blood() {
            return 0xFFFFFF85;
        }
    }

    public static class Chaos extends MvHElementalSprite {

        {
            boltType = MagicMissile.RAINBOW;
        }

        @Override
        protected int texOffset() {
            return 56;
        }

        @Override
        protected Emitter createEmitter() {
            Emitter emitter = emitter();
            emitter.pour( RainbowParticle.BURST, 0.025f );
            return emitter;
        }

        @Override
        public int blood() {
            return 0xFFE3E3E3;
        }
    }
}

