package jp.kobe_u.cs27.memory.coordinator.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import jp.kobe_u.cs27.memory.coordinator.dao.ActionDAO;
import jp.kobe_u.cs27.memory.coordinator.model.Action;

public class FiredAction {
	private ActionDAO actionDAO = null;
	public FiredAction() {
		actionDAO = new ActionDAO();
	}

	public boolean getAndInvokeActionFromId(long actionId) {
		Action action = actionDAO.findAction(actionId);
		if(null == action){
			return false;
		}
		try {
			invokeUrl(action.getUrl());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * URLの実行
	 *
	 * @param urltext
	 */
	public void invokeUrl(String urlText) throws IOException {
		String text = urlText;
		String urltext = "";
		/*
		 * URLに日本語が含まれる場合の処理 "?"以降(=引数)を切り取って、UTF-8エンコード
		 * その後、"?"以前の文字列の後ろに結合させ、叩く
		 */
		if (text.indexOf("?") != -1) {
			// System.out.println("text:" + text);
			int index = text.indexOf("?") + 1;
			String endtext = URLEncoder.encode(text.substring(index), "UTF-8");
			// System.out.println("endtext:" + endtext);
			// エンコードの必要のない=と&を元に戻す
			endtext = endtext.replaceAll("%3D", "=");
			endtext = endtext.replaceAll("%26", "&");
			urltext = (text.substring(0, (index)) + endtext);
			// System.out.println("urltext:" + urltext);
		} else {
			urltext = text;
		}

		URL url = new URL(urltext);
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		@SuppressWarnings("unused")
		InputStream inStream = connection.getInputStream();
	}
}
