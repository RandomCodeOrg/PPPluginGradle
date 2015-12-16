package com.github.randomcodeorg.ppplugin.data.gradle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.gradle.api.logging.Logger;

import com.github.randomcodeorg.ppplugin.data.BuildLog;

public class GradleBuildLog implements BuildLog {

	private final Logger logger;

	public GradleBuildLog(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	@Override
	public void debug(Throwable th) {
		logger.debug(toMessage(th));
	}

	@Override
	public void warn(String message) {
		logger.debug(message);
	}

	@Override
	public void warn(Throwable th) {
		logger.warn(toMessage(th));
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void error(Throwable th) {
		logger.error(toMessage(th));
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void info(Throwable th) {
		logger.info(toMessage(th));
	}

	private String toMessage(Throwable th) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = null;
		try {
			ps = new PrintStream(baos, true, "utf-8");
		} catch (UnsupportedEncodingException e) {
			ps = new PrintStream(baos, true);
		}
		th.printStackTrace(ps);
		ps.flush();
		String result = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		return result;
	}

}
