package jp.kobe_u.cs27.memory.coordinator.model;

public class Action {
	private String actionid;
	private String url;
	private String description;


	public Action(String actionid, String url, String description) {
		this.actionid = actionid;
		this.url = url;
		this.description = description;
		update();
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
	public String getActionid() {
		return actionid;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public void setActionid(String actionid) {
		this.actionid = actionid;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
