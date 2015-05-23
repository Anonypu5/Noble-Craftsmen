package no.grabit.NobleCraftsmen.util;

/**
 * Created by Ole on 24/05/2015.
 */
public class Time {

	public static float deltaTime;

	public static void setDeltaTime(long lastTimeNano, long nowNano) {
		deltaTime = (float) (nowNano - lastTimeNano) / 1_000_000_000.0f;
	}

	public static float getDeltaTime() {
		return deltaTime;
	}

}

