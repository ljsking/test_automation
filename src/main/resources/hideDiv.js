function hideSectionExclude (className){
	$Element('header').hide();
	$Element('footer').hide();
	$Element('aside').hide();
	$Element('add_function').hide();
	$Element('container').css('backgroundImage', 'none');
	$Element('content_wrap').css('borderTopStyle', 'none');
	$A($$('div#content>div')).map(function(v){
		e = $Element(v);
		if(!e.hasClass(className))
		e.hide();
	});
};
