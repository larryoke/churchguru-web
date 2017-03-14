

self.onmessage = function(e) {
	var file = e.data[0];
	var xhr = new XMLHttpRequest();
	 xhr.open('POST', '/estore/submit/message', false);
	 xhr.onload = function(e) {
			self.postMessage( file.name + " Uploaded Succesfully");	
	 };
	 xhr.send(file);
}
