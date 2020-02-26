package ru.arsysop.liho.check;

import java.util.Objects;

public final class CommentLine {
	private final String content;
	private final int position;

	public CommentLine(String content, int position) {
		this.content = content;
		this.position = position;
	}

	public String content() {
		return content;
	}

	public int position() {
		return position;
	}

	public boolean notEmpty() {
		return content.length() > 0;
	}

	@Override
	public boolean equals(Object another){
		if(!getClass().isInstance(another)){
			return  false;
		}
		CommentLine line = (CommentLine) another;
		return content.equals(line.content) && (position == line.position);
	}

	@Override
	public int hashCode(){
		return Objects.hash(content, position);
	}

}
