package no.grabit.NCLauncher.util;

/**
 * Created by Ole on 29/05/2015.
 */
public final class Time {

	private static float delta;
	private static long time;

	private Time() {}

	public static void addTime(float delta) {
		Time.time += delta * 1_000_000_000L;
	}

	public static void setTime(long time) {
		Time.time = time;
	}

	public static void setDeltaTime(long now, long lastTime) {
		Time.delta = (now - lastTime) / 1_000_000_000.0f;
	}

	public static float deltaTime() {
		return delta;
	}

	public static long getTime() {
		return time;
	}

	public static long getSecond() {
		return 1_000_000_000L;
	}

}
