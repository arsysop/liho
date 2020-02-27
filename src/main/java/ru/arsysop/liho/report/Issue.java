package ru.arsysop.liho.report;

import java.util.Objects;

public final class Issue {

	private final IssueType type;
	private final IssueLocation location;

	public Issue(IssueType type, IssueLocation location) {
		this.type = type;
		this.location = location;
	}

	public IssueType type() {
		return type;
	}

	public IssueLocation location() {
		return location;
	}

	@Override
	public boolean equals(Object obj){
		if(!getClass().isInstance(obj)){
			return  false;
		}
		Issue issue = (Issue) obj;
		return  type.equals(issue.type) && location.equals(issue.location);
	}

	@Override
	public int hashCode(){
		return Objects.hash(type, location);
	}

	@Override
	public String toString(){
		return type + "\r\nat " + location;
	}

}
