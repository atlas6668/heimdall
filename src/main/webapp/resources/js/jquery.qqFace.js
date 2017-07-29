// QQ表情插件
(function($){
	$.fn.qqFace = function(options){
		var defaults = {
			id : 'facebox',
			path : 'face/',
			assign : 'content',
			tip : 'em_'
		};
		var option = $.extend(defaults, options);
		var assign = $('#'+option.assign);
		var id = option.id;
		var path = option.path;
		var tip = option.tip;
		
		if(assign.length<=0){
			alert('缺少表情赋值对象。');
			return false;
		}
		$(this).click(function(e){
			var strFace, labFace;
			if($('#'+id).length<=0){
				strFace = '<div id="'+id+'" style="position:absolute;display:none;z-index:1000;" class="qqFace">' +
							  '<table border="0" cellspacing="0" cellpadding="0"><tr>';
				for(var i=0; i<=88; i++){
					labFace = i;
					strFace += '<td><img src="'+path+i+'.gif" onclick="$(\'#'+option.assign+'\').setCaret();$(\'#'+option.assign+'\').insertAtCaret(\'' + labFace + '\');" /></td>';
					if( i % 15 == 14 ) strFace += '</tr><tr>';
				}
				strFace += '</tr></table></div>';
			}
			$(this).parent().append(strFace);
			var offset = $(this).position();
			var top = offset.top + $(this).outerHeight();
			$('#'+id).css('top',top);
			$('#'+id).css('left',offset.left);
			$('#'+id).show();
			e.stopPropagation();
		});

		$(document).click(function(){
			$('#'+id).hide();
			$('#'+id).remove();
		});
	};

})(jQuery);
jQuery.extend({
	unselectContents: function(){ 
		if(window.getSelection){
			window.getSelection().removeAllRanges(); 
		} else if(document.selection){ 
			document.selection.empty(); 
		} 
	}
}); 
jQuery.fn.extend({ 
	selectContents: function(){ 
		$(this).each(function(i){ 
			var node = this; 
			var selection, range, doc, win; 
			if ((doc = node.ownerDocument) && (win = doc.defaultView) && typeof win.getSelection != 'undefined' && typeof doc.createRange != 'undefined' && (selection = window.getSelection()) && typeof selection.removeAllRanges != 'undefined'){ 
				range = doc.createRange(); 
				range.selectNode(node); 
				if(i == 0){ 
					selection.removeAllRanges(); 
				} 
				selection.addRange(range); 
			} else if (document.body && typeof document.body.createTextRange != 'undefined' && (range = document.body.createTextRange())){ 
				range.moveToElementText(node); 
				range.select(); 
			} 
		}); 
	}, 
	setCaret: function(){ 
		if(!$.browser.msie) return; 
		var initSetCaret = function(){
			var textObj = $(this).get(0); 
			textObj.caretPos = document.selection.createRange().duplicate(); 
		}; 
		$(this).click(initSetCaret).select(initSetCaret).keyup(initSetCaret); 
	}, 
	insertAtCaret: function(textFeildValue){ 
		var textObj = $(this).get(0);   
		textObj.innerHTML=textObj.innerHTML+'<img src="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/'+textFeildValue+'.gif" alt="'+textFeildValue+'"/>'; 
		this.focus(); 
	} 
});