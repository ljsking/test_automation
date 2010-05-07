window.initializeCookie = function(){
	var cookie = $Cookie();
	cookie.set('area_name','');
}

window.overrideGoCR = function(){
	if(window.old_goCR == null)
		window.old_goCR = window.goCR;
	window.goCR = goCRWithCookie();
}

window.overrideTCR = function(){
	if(window.old_tCR == null)
		window.old_tCR = window.tCR;
	window.tCR = tCRWithCookie();
}

window.goCRWithCookie = function(){
	var f = function (a, b, c, d){
		var cookie = $Cookie();
		var area_name = cookie.get('area_name');
		var result = c.match(/a=([^&]+)&/);
		if(result!=null){
			result = result[1];
			if(area_name != ""){
				area_name = area_name + ">" + result;
			} else {
				area_name = result;
			}
			cookie.set('area_name',area_name);
		}
		return window.old_goCR(a, b, c, d);
	};
	return f;
}

window.tCRWithCookie = function(){
	var f = function (a, b, c, d, e){
		var cookie = $Cookie();
		var area_name = cookie.get('area_name');
		var result = a.match(/a=([^&]+)&/);
		if(result!=null){
			result = result[1];
			if(area_name != ""){
				area_name = area_name + ">" + result;
			} else {
				area_name = result;
			}
			cookie.set('area_name',area_name);
		}
		return window.old_tCR(a, b, c, d, e);
	};
	return f;
}