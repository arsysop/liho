package ru.arsysop.liho.check;

import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.Report;

public interface FileCheck {

	/**
	 * @param file description of a file to check. Cannot be {@code null}
	 * @return a sign if the {@linkplain FileCheck} can be applied to the file described or not
	 */
	boolean applicable(File file);

	void analyze(File file, Report report);
}
