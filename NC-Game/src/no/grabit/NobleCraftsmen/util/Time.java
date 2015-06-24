package no.grabit.NobleCraftsmen.util;

/**
 * Created by Ole on 24/05/2015.
 */
public final class Time {

	public static float deltaTime;

	private Time() {}

	public static void setDeltaTime(long lastTimeNano, long nowNano) {
		deltaTime = (nowNano - lastTimeNano) / 1_000_000_000.0f;
	}

	public static float getDeltaTime() {
		return deltaTime;
	}

}

