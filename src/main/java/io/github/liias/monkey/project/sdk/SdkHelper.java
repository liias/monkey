package io.github.liias.monkey.project.sdk;

import com.google.common.collect.ImmutableMap;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.util.SystemInfoRt;
import org.jetbrains.annotations.NotNull;

public class SdkHelper {
	public static final Integer MONKEYDO_CMD = 0;
	public static final Integer MONKEYDO_TEST_PARAM =1;
	public static final Integer SHELL_CMD = 2;
	public static final Integer SIMULATOR_CMD = 3;

	private static final ImmutableMap<Integer, String> linux = ImmutableMap.<Integer, String>builder()
		.put(MONKEYDO_CMD, "monkeydo")
		.put(MONKEYDO_TEST_PARAM, "-t")
		.put(SHELL_CMD, "shell")
		.put(SIMULATOR_CMD, "simulator")
		.build();

	private static final ImmutableMap<Integer, String> mac = ImmutableMap.<Integer, String>builder()
		.put(MONKEYDO_CMD, "monkeydo")
		.put(MONKEYDO_TEST_PARAM, "-t")
		.put(SHELL_CMD, "shell")
		.put(SIMULATOR_CMD, "ConnectIQ.app")
		.build();

	private static final ImmutableMap<Integer, String> win = ImmutableMap.<Integer, String>builder()
		.put(MONKEYDO_CMD, "monkeydo.bat")
		.put(MONKEYDO_TEST_PARAM, "/t")
		.put(SHELL_CMD, "shell.exe")
		.put(SIMULATOR_CMD, "simulator.exe")
		.build();

	@NotNull
	public static String get(Integer cmd) throws ExecutionException {
		if (SystemInfoRt.isWindows) {
			if (win.containsKey(cmd))
			return win.get(cmd);
		} else if (SystemInfoRt.isLinux) {
			if (linux.containsKey(cmd))
				return linux.get(cmd);
		} else if (SystemInfoRt.isMac) {
			if (mac.containsKey(cmd))
				return mac.get(cmd);
		} else {
			throw new ExecutionException("Unsupported OS " + SystemInfoRt.OS_NAME);
		}
		throw new ExecutionException("Unsupported CMD " + cmd);
	}

}
