package org.fanniemae.vo;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class POMDepenedncy {
	private String groupId;
	private String artifactId;
	private String currentVersion;
	private String fixVersion;
	
	private Set<POMDepenedncy> exclusions;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getFixVersion() {
		return fixVersion;
	}

	public void setFixVersion(String fixVersion) {
		this.fixVersion = fixVersion;
	}

	public Set<POMDepenedncy> getExclusions() {
		return exclusions;
	}

	public void setExclusions(Set<POMDepenedncy> exclusions) {
		this.exclusions = exclusions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artifactId, currentVersion, fixVersion, groupId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POMDepenedncy other = (POMDepenedncy) obj;
		return Objects.equals(artifactId, other.artifactId) && Objects.equals(currentVersion, other.currentVersion)
				&& Objects.equals(fixVersion, other.fixVersion) && Objects.equals(groupId, other.groupId);
	}
}
