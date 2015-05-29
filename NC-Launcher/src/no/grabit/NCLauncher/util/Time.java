package no.grabit.NCLauncher.util;

/**
 * Created by Ole on 29/05/2015.
 */
public final class Time {

	private static float delta;

	private Time() {}

	public static void setDeltaTime(long now, long lastTime) {
		Time.delta = (now - lastTime) / 1_000_000_000.0f;
	}

	public static float getDeltaTime() {
		return delta;
	}

}
