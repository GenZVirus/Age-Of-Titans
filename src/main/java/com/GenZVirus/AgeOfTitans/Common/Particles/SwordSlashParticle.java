package com.GenZVirus.AgeOfTitans.Common.Particles;

import java.util.Locale;

import com.GenZVirus.AgeOfTitans.Common.Init.ParticleInit;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SwordSlashParticle extends SpriteTexturedParticle{

	private double posX, posY, posZ;

	public SwordSlashParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
			double xSpeedIn, double ySpeedIn, double zSpeedIn, SwordSlashParticleData data, IAnimatedSprite sprite) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = xSpeedIn;
		this.motionY = ySpeedIn;
		this.motionZ = zSpeedIn;
		this.posX = xCoordIn;
		this.posY = yCoordIn;
		this.posZ = zCoordIn;
		this.particleScale = 0.1F;
		this.particleRed = data.getRed();
		this.particleGreen = data.getGreen();
		this.particleBlue = data.getBlue();
		this.maxAge = 0;
		this.age = 0;
	}

	@Override
	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.age++ >= this.maxAge){
			this.setExpired();
		}
	}
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<SwordSlashParticleData>{
		private final IAnimatedSprite spriteSet;
		
		public Factory(IAnimatedSprite spriteIn) {
			this.spriteSet = spriteIn;
		}

		@Override
		public Particle makeParticle(SwordSlashParticleData typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			SwordSlashParticle particle = new SwordSlashParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn, spriteSet);
			particle.selectSpriteRandomly(spriteSet);
			return particle;
		}
	}
	
	public static class SwordSlashParticleData implements IParticleData{
		public static final IParticleData.IDeserializer<SwordSlashParticleData> DESERIALIZER = new IParticleData.IDeserializer<SwordSlashParticleData>() {
			public SwordSlashParticleData deserialize(ParticleType<SwordSlashParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException{
				reader.expect(' ');
				float red = (float) reader.readDouble();
				reader.expect(' ');
				float green = (float) reader.readDouble();
				reader.expect(' ');
				float blue = (float) reader.readDouble();
				reader.expect(' ');
				float alpha = (float) reader.readDouble();
				return new SwordSlashParticleData(red, green, blue, alpha);
			}
			
			public SwordSlashParticleData read(ParticleType<SwordSlashParticleData> particleTypeIn, PacketBuffer buffer) {
				return new SwordSlashParticleData(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			};
			
		};
		
		private final float red;
		private final float green;
		private final float blue;
		private final float alpha;
		
		public SwordSlashParticleData(float redIn, float greenIn, float blueIn, float alphatIn) {
			this.red = redIn;
			this.green = greenIn;
			this.blue = blueIn;
			this.alpha = MathHelper.clamp(alphatIn, 0.01F, 4.0F);
		}

		@Override
		public ParticleType<SwordSlashParticleData> getType() {
			return ParticleInit.SWORD_SLASH.get();
		}

		@Override
		public void write(PacketBuffer buffer) {
			buffer.writeFloat(this.red);
			buffer.writeFloat(this.green);
			buffer.writeFloat(this.blue);
			buffer.writeFloat(this.alpha);
			
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public String getParameters() {
			return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(this.getType()), this.red, this.green, this.blue, this.alpha);
		}
		
		@OnlyIn(Dist.CLIENT)
		public float getRed() {
			return this.red;
		}
		
		@OnlyIn(Dist.CLIENT)
		public float getGreen() {
			return this.green;
		}
		
		@OnlyIn(Dist.CLIENT)
		public float getBlue() {
			return this.blue;
		}
		
		@OnlyIn(Dist.CLIENT)
		public float getAlpha() {
			return this.alpha;
		}
	}
	

}
