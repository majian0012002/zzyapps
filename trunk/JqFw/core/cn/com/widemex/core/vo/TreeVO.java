package cn.com.widemex.core.vo;


/**
 * 树VO
 * easyui树展现的相关属性，主要用来被其他VO或者POJO继承用
 *
 * @author 张中原
 *
 */
public class TreeVO {
	
	/**节点样式*/
	private String iconCls = "icon-tree-folder";
	
	/**父节点key*/
	private String parentId;
	
	/**节点状态，closed为关闭，open为打开*/
	private String state = "closed";

	/**其他信息*/
	private Object otherInfo;
	
	/**是否选中当前行*/
	private boolean checked=false;
	
	/**是否是叶子节点*/
	private boolean leaf = false;
	
	/**是否已经加载*/
	private boolean loaded = false;
	
	
	
	
	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		// 如果是叶子节点，且节点样式为文件夹，则转换成文件样式形式
		if(leaf && "icon-tree-folder".equals(iconCls)){
			this.iconCls = "icon-tree-action";
		}
		this.leaf = leaf;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Object getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(Object otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
//
//	public String get_parentId() {
//		return _parentId;
//	}
//
//	public void set_parentId(String _parentId) {
//		this._parentId = _parentId;
//	} 
}
