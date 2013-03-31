function JSConfig() {
	
}
JSConfig.prototype = new JSConfig();

JSConfig.instance = null;

JSConfig.getInstance = function() {
	if(JSConfig.instance == null) JSConfig.instance = new JSConfig();
	return JSConfig.instance;
};

JSConfig.prototype.setRESTUrl = function(url) {
	this.url = url;
};

JSConfig.prototype.getRESTUrl = function() {
	return this.url;
};