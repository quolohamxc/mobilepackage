package ws.item;

import ws.model.Page;

public abstract class Item {
	public String html = null;
	public Item() {
		
	}
	public Item(Page page) {
		if(this.isItem(page.url)) {
			this.buildItem(page.html);
		}
	}
	public boolean isItem() {
		return true;
	}
	public boolean isItem(String url) {
			return true;
	}
	public void buildItem(String html) {
		this.html = html;
	}
	public void buildItem(Page page) {
		
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
