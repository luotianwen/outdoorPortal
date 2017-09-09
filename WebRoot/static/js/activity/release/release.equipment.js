$(function() {
	$.fn.equipment = function(op) {
		var $div = $(op.target);
		this.op = {
			$div : $div,
			$add : $div.find(op.add),
			del : op.del,
			index : $div.find(op.index).val()*1,
			$save : $div.find(op.saveActiveInfo)
		};
		this._init();
	}
	$.fn.equipment.prototype = {
		_init : function() {
			this._add();
			this._del();
			this._mouseover();
			this._mouseout();
		},
		_add : function() {
			var _this = this;
			_this.op.$add.on("click", function() {
				var str='<tr>'
					+'<td>'
					+'<input name="aes['+_this.op.index+'].ae_name" maxlength="50" type="text" placeholder="装备名称"/>'
					+'</td>'
					+'<td>'
					+'<input name="aes['+_this.op.index+'].ae_description" maxlength="100" type="text" class="wid03" placeholder="装备说明"/>'
					+'</td>'
					+'<td>'
					+'<i class="btn-del"></i>'
					+'</td>'
					+'</tr>';
				_this.op.index++;
				
				$(this).closest("tr").before(str);
			})
		},
		_del : function() {
			var _this = this;
			_this.op.$div.on("click", _this.op.del, function() {
				var $del = $(this);
				layer.confirm("是否确认删除？",{icon:3},function(l_index){
					var tbody = $del.closest("tbody");
					if(tbody.children("tr").length>2){
						$del.closest("tr").remove();
					}else{
						tbody.children("tr:eq(0)").find("input").val("");
					}
					
					if(activityId != ""){
						_this.op.$save.trigger("click",{
							handle:"d"
						});
					}
					layer.close(l_index);
				})
			})
		},
		_mouseover:function(){
			var _this = this;
			_this.op.$div.on("mouseover", _this.op.del, function() {
				$(this).addClass("selected")
			})
			
		},
		_mouseout:function(){
			var _this = this;
			_this.op.$div.on("mouseout", _this.op.del, function() {
				$(this).removeClass("selected");
			})
		}
	}
	new $.fn.equipment({
		target : "div#equipment_div",
		add : "a#add_equipment",
		del : "i.btn-del",
		index : "input#index_num:hidden",
		saveActiveInfo:"a#saveActiveInfo"
	})
})
