with(selenium.browserbot.getCurrentWindow()){
	if (!Snapsie) // create the Snapsie singleton?
		var Snapsie = new function() {
		    // private fields
		    var nativeObj;
		    
		    // private methods
		    
		    function init() {
		        try {
		            nativeObj = new ActiveXObject('Snapsie.CoSnapsie');
		        }
		        catch (e) {
		            showException(e);
		        }
		    }
		    
		    function showException(e) {
		    	//throw e;
		        alert(e + ', ' + (e.message ? e.message : ""));
		    }
		    
		    function isQuirksMode(inDocument) {
		        return (inDocument.compatMode == 'BackCompat');
		    }
		    
		    function getDrawableElement(inDocument) {
		        if (isQuirksMode(inDocument)) {
		            return inDocument.getElementsByTagName('body')[0];
		        }
		        else {
		            // standards mode
		            return inDocument.documentElement;
		        }
		    }
		    
		    /**
		     * Returns the canonical Windows path for a given path. This means
		     * basically replacing any forwards slashes with backslashes.
		     *
		     * @param path  the path whose canonical form to return
		     */
		    function getCanonicalPath(path) {
		        path = path.replace(/\//g, '\\');
		        path = path.replace(/\\\\/g, '\\');
		        return path;
		    }

		    // public methods
		    
		    /**
		     * Saves a screenshot of the current document to a file. If frameId is
		     * specified, a screenshot of just the frame is captured instead.
		     *
		     * @param outputFile  the file to which to save the screenshot
		     * @param frameId     the frame to capture; omit to capture entire document
		     */
		    this.saveSnapshot = function(outputFile, frameId) {
		        if (!nativeObj) {
		            var e = new Exception('Snapsie was not properly initialized');
		            showException(e);
		            return;
		        }
		        
		        var drawableElement = getDrawableElement(document);
		        var drawableInfo = {
		              overflow  : drawableElement.style.overflow
		            , scrollLeft: drawableElement.scrollLeft
		            , scrollTop : drawableElement.scrollTop
		        };
		        drawableElement.style.overflow = 'hidden';
		        
		        var capturableDocument;
		        var frameBCR = { left: 0, top: 0 };
		        if (arguments.length == 1) {
		            capturableDocument = document;
		        }
		        else {
		            var frame = document.getElementById(frameId);
		            capturableDocument = frame.document;
		            
		            // scroll as much of the frame into view as possible
		            frameBCR = frame.getBoundingClientRect();
		            window.scroll(frameBCR.left, frameBCR.top);
		            frameBCR = frame.getBoundingClientRect();
		        }
		        
		        try {
		            nativeObj.saveSnapshot(
		                getCanonicalPath(outputFile),
		                frameId,
		                drawableElement.scrollWidth,
		                drawableElement.scrollHeight,
		                drawableElement.clientWidth,
		                drawableElement.clientHeight,
		                drawableElement.clientLeft,
		                drawableElement.clientTop,
		                frameBCR.left,
		                frameBCR.top
		            );
		        }
		        catch (e) {
		            showException(e);
		        }
		        
		        // revert
		        
		        drawableElement.style.overflow = drawableInfo.overflow;
		        drawableElement.scrollLeft = drawableInfo.scrollLeft;
		        drawableElement.scrollTop = drawableInfo.scrollTop;
		    }
		    
		    // initializers
		    
		    init();
		};
}

selenium.browserbot.getCurrentWindow().hideSectionExclude = function (className){
	var currentWindow = selenium.browserbot.getCurrentWindow();
	currentWindow.$Element('header').hide();
	currentWindow.$Element('footer').hide();
	currentWindow.$Element('aside').hide();
	currentWindow.$Element('add_function').hide();
	currentWindow.$Element('container').css('backgroundImage', 'none');
	currentWindow.$Element('content_wrap').css('borderTopStyle', 'none');
	var divs = currentWindow.$$('div#content>div');
	for(var i = 0; i<divs.length; ++i){
		if(divs[i].style != null && divs[i].className != className )
			divs[i].style.display = 'none';
	}
	var outputFile = "C:\\snapsie_test.png";
};

