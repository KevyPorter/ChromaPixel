package net.kevyporter.chromapixel.api.interaction.representations;

import net.kevyporter.chromapixel.util.UuidHelper;
import net.kevyporter.net.hypixel.api.util.GameType;

public class Booster {
	public static final int TIPPING_COOLDOWN = 1800000;

	private int amount;
	private long dateActivated;
	private int gameType;
	private long length;
	private long originalLength;
	private String purchaserUuid;

	private long tippingTime;
	private String owner;

	public Booster(){}

	public int getCoinAmount() {
		return amount;
	}

	public long getActivationDateAndTime() {
		return dateActivated;
	}

	public GameType getGame() {
		return GameType.fromId(gameType);
	}

	public long getRemainingTime() {
		return length;
	}

	public long getTotalLength() {
		return originalLength;
	}

	public String getOwner() {
		if(this.owner == null || this.owner.equals("!ERROR!")) {
			this.owner = UuidHelper.getUsernameFormUUID(purchaserUuid);
		}
		if(this.owner == null) {
			this.owner = "!ERROR!";
		}
		return owner;
	}

	public void tip() {
		this.tippingTime = System.currentTimeMillis();
	}

	public boolean canTip() {
		return (this.getRemainingTime() < this.getTotalLength() && (this.tippingTime == 0l || System.currentTimeMillis() > this.tippingTime + TIPPING_COOLDOWN ));
	}

	public void setTippingTime(long time) {
		this.tippingTime = time;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Booster) {
			Booster b = (Booster)obj;
			return this.dateActivated == b.dateActivated && this.owner.equals(b.owner) && this.gameType == b.gameType;
		}
		return super.equals(obj);
	}
}
