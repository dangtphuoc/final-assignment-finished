function ModalDialog() {
	this.$tag = $('<div>').addClass('modal hide fade').attr({"tabindex":"-1", "role":"dialog", "aria-labelledby":"myModalLabel", "aria-hidden":"true"});
	this.$header = $('<div>').addClass('modal-header');
	this.$body = $('<div>').addClass('modal-body');
	this.$footer = $('<div>').addClass('modal-footer');
	this.$tag.append(this.$header).append(this.$body).append(this.$footer);
	
	var $headerCloseButton = $('<button>').addClass('close').attr({type: 'button', "data-dismiss" : 'modal', "aria-hidden" : 'true'}).text('x');
	this.$header.append($headerCloseButton);
	this.$header.append('<h3>Modal header</h3>');
	this.$body.append('<p>One fine body…</p>');
	
	var $footerCloseButton = $('<button>').addClass('btn').attr({"data-dismiss" : 'modal', "aria-hidden" : 'true'}).text('Close');
	var $saveButton = $('<button>').addClass('btn btn-primary').text('Save Changes');
	var that = this;
	$saveButton.click(function(){
		that.handleSaveChanges();
	});
	this.$footer.append($saveButton);
	this.$footer.append($footerCloseButton);
}

ModalDialog.prototype = new BaseView();

ModalDialog.prototype.showDialog = function() {
	this.$tag.css({'width': '800px'});
	this.$tag.modal('show');
};

ModalDialog.prototype.repaint = function() {
	this.$body.empty();
	this.$body.append(this.model);
};

ModalDialog.prototype.setCallbackFunction = function(context, cb, data) {
	this.cbFunction = cb;
	this.cbContext = context;
	this.data = data;
};

ModalDialog.prototype.handleSaveChanges = function() {
	if(this.cbFunction != undefined) {
		if(this.data != undefined) {
			this.cbFunction.call(this.cbContext, this.data);
		} else {
			this.cbFunction.call(this.cbContext);
		}
	}
	this.$tag.modal('hide');
};