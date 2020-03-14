package ru.arsysop.liho.bulk;

import ru.arsysop.liho.content.analysis.ContentAnalysis;
import ru.arsysop.liho.file.File;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class ContentAnalysisFactory implements Function<File, List<ContentAnalysis>> {
	private final List<Function<File, ContentAnalysis>> analyzers;

	ContentAnalysisFactory(List<Function<File, ContentAnalysis>> analyzers) {
		this.analyzers = analyzers;
	}

	@Override
	public List<ContentAnalysis> apply(File file) {
		return analyzers.stream().map(supplier -> supplier.apply(file)).collect(Collectors.toList());
	}
}
