

// 分页查询
function page(n,s){
	$("#pageNum").val(n);
	$("#pageSize").val(s);
	$("#f_search").submit();
}

$(document).ready(function(){
	// 批量删除
	$('#delete-button').click(function(e){
		var models = $("input[name='modelId']:checkbox:checked");
		if(!models.length){
			alert("删除至少选择一项");
			return false;
		}
		e.preventDefault();
		var form = $("#delete_form");
		var ids = "";
		// 拿出所有要删除的id
		$("input[name='modelId']:checkbox").each(function() { 
			//alert($(this).prop('checked'));
			if ($(this).prop('checked')){
				ids += ",";
				ids += $(this).val();
			}
		});
		// 去掉第一个逗号
		if (ids.length > 0) {
			ids = ids.substring(1, ids.length);
		}
		// 放到隐藏域里
		$('#delete_model_ids').val(ids);
		// 提交
		var r=confirm("您确定删除吗");
		if (r==true){
			//alert(ids);
			form.submit();
		} else{
			return;
		}
	});
})

// 图片裁剪 浏览
function previewImage(file){
  var MAXWIDTH  = 200;
  var MAXHEIGHT = 180;
  var div = document.getElementById('preview');
  if (file.files && file.files[0]){
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.onload = function(){
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
      img.width = rect.width;
      img.height = rect.height;
      img.style.marginLeft = rect.left+'px';
      img.style.marginTop = rect.top+'px';
    };
    var reader = new FileReader();
    reader.onload = function(evt){img.src = evt.target.result;};
    reader.readAsDataURL(file.files[0]);
  }else{
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight )
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if( rateWidth > rateHeight )
        {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}
