jQuery.fn.setText = function(textObject) {

	if(textObject == null) textObject = "";
	
	if(textObject.isHtmlWrapper == true) {
		this.html(textObject.text);
	}
	else {
		this.text(textObject);
	}
	
	return this;
};