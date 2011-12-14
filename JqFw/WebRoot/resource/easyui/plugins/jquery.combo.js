/**
 * jQuery EasyUI 1.2.5
 * 
 * Licensed under the GPL terms To use it on other terms please contact us
 * 
 * Copyright(c) 2009-2011 stworthy [ stworthy@gmail.com ]
 * 
 */
(function($) {
	function _1(_2, _3) {
		var _4 = $.data(_2, "combo").options;
		var _5 = $.data(_2, "combo").combo;
		var _6 = $.data(_2, "combo").panel;
		if (_3) {
			_4.width = _3;
		}
		_5.appendTo("body");
		if (isNaN(_4.width)) {
			_4.width = _5.find("input.combo-text").outerWidth();
		}
		var _7 = 0;
		if (_4.hasDownArrow) {
			_7 = _5.find(".combo-arrow").outerWidth();
		}
		var _3 = _4.width - _7;
		if ($.boxModel == true) {
			_3 -= _5.outerWidth() - _5.width();
		}
		_5.find("input.combo-text").width(_3);
		_6.panel("resize", {
					width : (_4.panelWidth ? _4.panelWidth : _5.outerWidth()),
					height : _4.panelHeight
				});
		_5.insertAfter(_2);
	};
	function _8(_9) {
		var _a = $.data(_9, "combo").options;
		var _b = $.data(_9, "combo").combo;
		if (_a.hasDownArrow) {
			_b.find(".combo-arrow").show();
		} else {
			_b.find(".combo-arrow").hide();
		}
	};
	function _c(_d) {
		$(_d).addClass("combo-f").hide();
		var _e = $("<span class=\"combo\"></span>").insertAfter(_d);
		var _f = $("<input type=\"text\" class=\"combo-text\">").appendTo(_e);
		$("<span><span class=\"combo-arrow\"></span></span>").appendTo(_e);
		$("<input type=\"hidden\" class=\"combo-value\">").appendTo(_e);
		var _10 = $("<div class=\"combo-panel\"></div>").appendTo("body");
		_10.panel({
					doSize : false,
					closed : true,
					style : {
						position : "absolute",
						zIndex : 10
					},
					onOpen : function() {
						$(this).panel("resize");
					}
				});
		var _11 = $(_d).attr("name");
		if (_11) {
			_e.find("input.combo-value").attr("name", _11);
			$(_d).removeAttr("name").attr("comboName", _11);
		}
		_f.attr("autocomplete", "off");
		return {
			combo : _e,
			panel : _10
		};
	};
	function _12(_13) {
		var _14 = $.data(_13, "combo").combo.find("input.combo-text");
		_14.validatebox("destroy");
		$.data(_13, "combo").panel.panel("destroy");
		$.data(_13, "combo").combo.remove();
		$(_13).remove();
	};
	function _15(_16) {
		var _17 = $.data(_16, "combo");
		var _18 = _17.options;
		var _19 = $.data(_16, "combo").combo;
		var _1a = $.data(_16, "combo").panel;
		var _1b = _19.find(".combo-text");
		var _1c = _19.find(".combo-arrow");
		$(document).unbind(".combo").bind("mousedown.combo", function(e) {
					$("div.combo-panel").panel("close");
				});
		_19.unbind(".combo");
		_1a.unbind(".combo");
		_1b.unbind(".combo");
		_1c.unbind(".combo");
		if (!_18.disabled) {
			_1a.bind("mousedown.combo", function(e) {
						return false;
					});
			_1b.bind("mousedown.combo", function(e) {
						e.stopPropagation();
					}).bind("keydown.combo", function(e) {
				switch (e.keyCode) {
					case 38 :
						_18.keyHandler.up.call(_16);
						break;
					case 40 :
						_18.keyHandler.down.call(_16);
						break;
					case 13 :
						e.preventDefault();
						_18.keyHandler.enter.call(_16);
						return false;
					case 9 :
					case 27 :
						_25(_16);
						break;
					default :
						if (_18.editable) {
							if (_17.timer) {
								clearTimeout(_17.timer);
							}
							_17.timer = setTimeout(function() {
										var q = _1b.val();
										if (_17.previousValue != q) {
											_17.previousValue = q;
											_1d(_16);
											_18.keyHandler.query.call(_16, _1b
															.val());
											_29(_16, true);
										}
									}, _18.delay);
						}
				}
			});
			_1c.bind("click.combo", function() {
						if (_1a.is(":visible")) {
							_25(_16);
						} else {
							$("div.combo-panel").panel("close");
							_1d(_16);
						}
						_1b.focus();
					}).bind("mouseenter.combo", function() {
						$(this).addClass("combo-arrow-hover");
					}).bind("mouseleave.combo", function() {
						$(this).removeClass("combo-arrow-hover");
					}).bind("mousedown.combo", function() {
						return false;
					});
		}
	};
	function _1d(_1e) {
		var _1f = $.data(_1e, "combo").options;
		var _20 = $.data(_1e, "combo").combo;
		var _21 = $.data(_1e, "combo").panel;
		if ($.fn.window) {
			_21.panel("panel").css("z-index", $.fn.window.defaults.zIndex++);
		}
		_21.panel("move", {
					left : _20.offset().left,
					top : _22()
				});
		_21.panel("open");
		_1f.onShowPanel.call(_1e);
		(function() {
			if (_21.is(":visible")) {
				_21.panel("move", {
							left : _23(),
							top : _22()
						});
				setTimeout(arguments.callee, 200);
			}
		})();
		function _23() {
			var _24 = _20.offset().left;
			if (_24 + _21.outerWidth() > $(window).width()
					+ $(document).scrollLeft()) {
				_24 = $(window).width() + $(document).scrollLeft()
						- _21.outerWidth();
			}
			if (_24 < 0) {
				_24 = 0;
			}
			return _24;
		};
		function _22() {
			var top = _20.offset().top + _20.outerHeight();
			if (top + _21.outerHeight() > $(window).height()
					+ $(document).scrollTop()) {
				top = _20.offset().top - _21.outerHeight();
			}
			if (top < $(document).scrollTop()) {
				top = _20.offset().top + _20.outerHeight();
			}
			return top;
		};
	};
	function _25(_26) {
		var _27 = $.data(_26, "combo").options;
		var _28 = $.data(_26, "combo").panel;
		_28.panel("close");
		_27.onHidePanel.call(_26);
	};
	function _29(_2a, _2b) {
		var _2c = $.data(_2a, "combo").options;
		var _2d = $.data(_2a, "combo").combo.find("input.combo-text");
		_2d.validatebox(_2c);
		if (_2b) {
			_2d.validatebox("validate");
			_2d.trigger("mouseleave");
		}
	};
	function _2e(_2f, _30) {
		var _31 = $.data(_2f, "combo").options;
		var _32 = $.data(_2f, "combo").combo;
		if (_30) {
			_31.disabled = true;
			$(_2f).attr("disabled", true);
			_32.find(".combo-value").attr("disabled", true);
			_32.find(".combo-text").attr("disabled", true);
		} else {
			_31.disabled = false;
			$(_2f).removeAttr("disabled");
			_32.find(".combo-value").removeAttr("disabled");
			_32.find(".combo-text").removeAttr("disabled");
		}
	};
	function _33(_34) {
		var _35 = $.data(_34, "combo").options;
		var _36 = $.data(_34, "combo").combo;
		if (_35.multiple) {
			_36.find("input.combo-value").remove();
		} else {
			_36.find("input.combo-value").val("");
		}
		_36.find("input.combo-text").val("");
	};
	function _37(_38) {
		var _39 = $.data(_38, "combo").combo;
		return _39.find("input.combo-text").val();
	};
	function _3a(_3b, _3c) {
		var _3d = $.data(_3b, "combo").combo;
		_3d.find("input.combo-text").val(_3c);
		_29(_3b, true);
		$.data(_3b, "combo").previousValue = _3c;
	};
//	/**
//	 * 获取多个值
//	 */
//	function getValues(_3f, isBeanValue) {
//		var opts = $.data(_3f, 'combo').options;
//		var data = $.data(_3f, 'combo').data;
//		
//		var _40 = [];
//		var _41 = $.data(_3f, "combo").combo;
//		_41.find("input.combo-value").each(function() {
//					_40.push($(this).val());
//				});
//				
//		return _40;
//				
//		
////		// 是否是对象只
////		if(isBeanValue !== true){
////			return _40;
////		}
//		
////		var beanValues = [];
////		
//////		for (var valIndex = 0; valIndex < _40.length; valIndex++) {
//////			var val = _40[valIndex];
//////			
//////			for (var index = 0; index < data.length; index++) {
//////				var bean = data[index];
//////				if(bean[opts['idField']] == val){
//////					beanValues.push(bean);
//////					break;
//////				}
//////			}
//////		}
////		
////		return beanValues;
//	};
	
//	/**
//	 * 获取缓存数据对象
//	 */
//	function getDataObject(target, key){
//		var val = null;
//		if((val=$.data(target, 'combotree')) && $.data(target, 'combotree')[key]){
//			return val[key];
//		}else if((val=$.data(target, 'combogrid')) && $.data(target, 'combogrid')[key]){
//			return val[key];
//		}else if((val=$.data(target, 'combobox')) && $.data(target, 'combobox')[key]){
//			return val[key];
//		}else if((val=$.data(target, 'combo')) && $.data(target, 'combo')[key]){
//			return val[key];
//		}
//		
//		return val ? val[key];
//	};


	
	/**
	 * 获取值数组
	 */
	function getValues(target, isBeanValue){
		var opts = $.data(target, 'combo').options;
		
		var data = $.data(target, 'combo').data;
		
		switch (opts['xtype']){
			case 'combobox': {
				data = $.data(target, 'combobox').data;
				break;
			}
			
			case 'combogrid': {
				var grid = $.data(target, 'combogrid').grid;
				if(grid){
					data = grid.datagrid('getRows');
				}
				break;
			}
			
			case 'combotree': { // 如果是下拉树
				var tree = $.data(target, "combotree").tree;
				
				if(tree){
					if (opts.multiple) {
						data = tree.tree("getChecked");
					} else {
						data = [tree.tree("getSelected")];
					}
				}
				
				break;
			}
		}
		
		
		var valueField = opts['valueField'] || opts['idField'];
		
		var valArr = [];
		var combo = $.data(target, "combo").combo;
		combo.find("input.combo-value").each(function() {
					valArr.push($(this).val());
				});
		
		if(!isBeanValue || $.isEmpty(valueField, false)){
			return valArr;
		}
		
		if(opts['xtype'] == 'combotree'){
			return data;
		}
		
		var beanArr = [];
		if(valArr.length>0 && $.isArray(valArr) && data.length>0){
			for (var valArrIndex = 0; valArrIndex < valArr.length; valArrIndex++) {
				var key = valArr[valArrIndex];
				for (var dataIndex = 0; dataIndex < data.length; dataIndex++) {
					var bean = data[dataIndex];
					if(bean[valueField] == key){
						beanArr.push(bean);
					}
				}
			}
		}				
				
		return beanArr;
	}
	
	/**
	 * 获取单个值
	 */
	function getValue(target, isBeanValue){
		var valArr = getValues(target, isBeanValue);
		return valArr ? valArr[0] : null;
	}
	
	function _42(_43, _44) {
		var _45 = $.data(_43, "combo").options;
		var _46 = getValues(_43);
		var _47 = $.data(_43, "combo").combo;
		_47.find("input.combo-value").remove();
		var _48 = $(_43).attr("comboName");
		for (var i = 0; i < _44.length; i++) {
			var _49 = $("<input type=\"hidden\" class=\"combo-value\">")
					.appendTo(_47);
			if (_48) {
				_49.attr("name", _48);
			}
			_49.val(_44[i]);
		}
		var tmp = [];
		for (var i = 0; i < _46.length; i++) {
			tmp[i] = _46[i];
		}
		var aa = [];
		for (var i = 0; i < _44.length; i++) {
			for (var j = 0; j < tmp.length; j++) {
				if (_44[i] == tmp[j]) {
					aa.push(_44[i]);
					tmp.splice(j, 1);
					break;
				}
			}
		}
		if (aa.length != _44.length || _44.length != _46.length) {
			if (_45.multiple) {
				_45.onChange.call(_43, _44, _46);
			} else {
				_45.onChange.call(_43, _44[0], _46[0]);
			}
		}
	};
	
//	/**
//	 * 获取值
//	 */
//	function _4a(_4b) {
//		var _4c = getValues(_4b);
//		return _4c[0];
//	};
	
	function _4d(_4e, _4f) {
		_42(_4e, [_4f]);
	};
	function _50(_51) {
		var _52 = $.data(_51, "combo").options;
		var fn = _52.onChange;
		_52.onChange = function() {
		};
		if (_52.multiple) {
			if (_52.value) {
				if (typeof _52.value == "object") {
					_42(_51, _52.value);
				} else {
					_4d(_51, _52.value);
				}
			} else {
				_42(_51, []);
			}
		} else {
			_4d(_51, _52.value);
		}
		_52.onChange = fn;
	};
	$.fn.combo = function(_53, _54) {
		if (typeof _53 == "string") {
			return $.fn.combo.methods[_53](this, _54);
		}
		_53 = _53 || {};
		return this.each(function() {
					var _55 = $.data(this, "combo");
					if (_55) {
						$.extend(_55.options, _53);
					} else {
						var r = _c(this);
						_55 = $.data(this, "combo", {
									options : $.extend({}, $.fn.combo.defaults,
											$.fn.combo.parseOptions(this), _53),
									combo : r.combo,
									panel : r.panel,
									previousValue : null
								});
						$(this).removeAttr("disabled");
					}
					$("input.combo-text", _55.combo).attr("readonly",
							!_55.options.editable);
					_8(this);
					_2e(this, _55.options.disabled);
					_1(this);
					_15(this);
					_29(this);
					_50(this);
				});
	};
	$.fn.combo.methods = {
		options : function(jq) {
			return $.data(jq[0], "combo").options;
		},
		panel : function(jq) {
			return $.data(jq[0], "combo").panel;
		},
		textbox : function(jq) {
			return $.data(jq[0], "combo").combo.find("input.combo-text");
		},
		destroy : function(jq) {
			return jq.each(function() {
						_12(this);
					});
		},
		resize : function(jq, _56) {
			return jq.each(function() {
						_1(this, _56);
					});
		},
		showPanel : function(jq) {
			return jq.each(function() {
						_1d(this);
					});
		},
		hidePanel : function(jq) {
			return jq.each(function() {
						_25(this);
					});
		},
		disable : function(jq) {
			return jq.each(function() {
						_2e(this, true);
						_15(this);
					});
		},
		enable : function(jq) {
			return jq.each(function() {
						_2e(this, false);
						_15(this);
					});
		},
		validate : function(jq) {
			return jq.each(function() {
						_29(this, true);
					});
		},
		isValid : function(jq) {
			var _57 = $.data(jq[0], "combo").combo.find("input.combo-text");
			return _57.validatebox("isValid");
		},
		clear : function(jq) {
			return jq.each(function() {
						_33(this);
					});
		},
		getText : function(jq) {
			return _37(jq[0]);
		},
		setText : function(jq, _58) {
			return jq.each(function() {
						_3a(this, _58);
					});
		},
//		getValues : function(jq) {
//			return getValues(jq[0]);
//		},
		/**
		 * 获取多个值
		 */
		setValues : function(jq, _59) {
			return jq.each(function() {
						_42(this, _59);
					});
		},
//		/**
//		 * 获取值
//		 */
//		getValue : function(jq) {
//			return _4a(jq[0]);
//		},
		
		/**
		 * 获取值数组
		 */
		getValues: function(jq, isBeanValue){
			var val = null;
			jq.each(function() {
						val = getValues(this, isBeanValue);
					});
					
			return val;
		},
		/**
		 * 获取单个值
		 */
		getValue: function(jq, isBeanValue){
			var val = null;
			jq.each(function() {
						val = getValue(this, isBeanValue);
					});
			return val;
		},
		
		setValue : function(jq, _5a) {
			return jq.each(function() {
						_4d(this, _5a);
					});
		}
	};
	$.fn.combo.parseOptions = function(_5b) {
		var t = $(_5b);
		return $.extend({}, $.fn.validatebox.parseOptions(_5b), {
					width : (parseInt(_5b.style.width) || undefined),
					panelWidth : (parseInt(t.attr("panelWidth")) || undefined),
					panelHeight : (t.attr("panelHeight") == "auto"
							? "auto"
							: parseInt(t.attr("panelHeight")) || undefined),
					separator : (t.attr("separator") || undefined),
					multiple : (t.attr("multiple")
							? (t.attr("multiple") == "true" || t
									.attr("multiple") == true)
							: undefined),
					editable : (t.attr("editable")
							? t.attr("editable") == "true"
							: undefined),
					disabled : (t.attr("disabled") ? true : undefined),
					hasDownArrow : (t.attr("hasDownArrow") ? t
							.attr("hasDownArrow") == "true" : undefined),
					value : (t.val() || undefined),
					delay : (t.attr("delay")
							? parseInt(t.attr("delay"))
							: undefined)
				});
	};
	$.fn.combo.defaults = $.extend({}, $.fn.validatebox.defaults, {
				width : "auto",
				panelWidth : null,
				panelHeight : 200,
				multiple : false,
				separator : ",",
				editable : false,
				disabled : false,
				hasDownArrow : true,
				value : "",
				delay : 200,
				keyHandler : {
					up : function() {
					},
					down : function() {
					},
					enter : function() {
					},
					query : function(q) {
					}
				},
				onShowPanel : function() {
				},
				onHidePanel : function() {
				},
				onChange : function(_5c, _5d) {
				}
			});
})(jQuery);
