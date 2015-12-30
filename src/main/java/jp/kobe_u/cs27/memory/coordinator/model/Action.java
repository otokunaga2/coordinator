package jp.kobe_u.cs27.memory.coordinator.model;

public class Action {
	private long actionid;
	private String url;
	private String description;


	public Action(long actionid, String url, String description) {
		this.actionid = actionid;
		this.url = url;
		this.description = description;
		update();
	}

	public Action() {
	}

	/**
	 * DBの更新
	 *
	 * @return
	 */
	public boolean update() {
		return true;
	}

	// getter 及び setter
	public long getActionid() {
		return actionid;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public void setActionid(long l) {
		this.actionid = l;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
