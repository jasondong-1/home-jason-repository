(function ($) {
    $.alerts = {
        verticalOffset: -75,
        horizontalOffset: 0,
        repositionOnResize: true,
        overlayOpacity: .3,
        overlayColor: 'black',
        draggable: true,
        okButton: '&nbsp;确定&nbsp;',
        cancelButton: '&nbsp;取消&nbsp;',
        dialogClass: null,
        alert: function (message, title, callback) {
            if (title == null)title = 'Alert';
            $.alerts._show(title, message, null, 'alert', function (result) {
                if (callback)callback(result);
            });
        },
        confirm: function (message, title, callback) {
            if (title == null)title = 'Confirm';
            $.alerts._show(title, message, null, 'confirm', function (result) {
                if (callback)callback(result);
            });
        },
        prompt: function (message, value, title, callback) {
            if (title == null)title = 'Prompt';
            $.alerts._show(title, message, value, 'prompt', function (result) {
                if (callback)callback(result);
            });
        },
        _show: function (title, msg, value, type, callback) {
            $.alerts._hide();
            $.alerts._overlay('show');
            $("BODY").append('<div id="popup_container">' + '<div id="popup_title"></div>' + '<div id="popup_content">' + '<div id="popup_message"></div>' + '</div>' + '</div>');
            if ($.alerts.dialogClass)$("#popup_container").addClass($.alerts.dialogClass);
            var pos = 'fixed';
            $("#popup_container").css({position: pos, zIndex: 99999, padding: 0, margin: 0});
            $("#popup_title").text(title);
            $("#popup_content").addClass(type);
            $("#popup_message").text(msg);
            $("#popup_message").html($("#popup_message").text().replace(/\n/g, '<br />'));
            $("#popup_container").css({
                minWidth: $("#popup_container").outerWidth(),
                maxWidth: $("#popup_container").outerWidth()
            });
            $.alerts._reposition();
            $.alerts._maintainPosition(true);
            switch (type) {
                case'alert':
                    $("#popup_message").after('<div id="popup_panel"><button type="button" class="btn btn-white btn-success btn-sm btn-round" id="popup_ok"><i class="ace-icon fa fa-check"></i>' + $.alerts.okButton + '</button> </div>');
                    $("#popup_ok").click(function () {
                        $.alerts._hide();
                        callback(true);
                    });
                    $("#popup_ok").focus().keypress(function (e) {
                        if (e.keyCode == 13 || e.keyCode == 27)$("#popup_ok").trigger('click');
                    });
                    break;
                case'confirm':
                    $("#popup_message").after('<div id="popup_panel"><button type="button" class="btn btn-white btn-success btn-sm btn-round" id="popup_ok"><i class="ace-icon fa fa-check"></i>' + $.alerts.okButton + '</button> <button type="button" class="btn btn-white btn-gary btn-sm btn-round" id="popup_cancel"><i class="ace-icon fa fa-close"></i>' + $.alerts.cancelButton + '</button></div>');
                    $("#popup_ok").click(function () {
                        $.alerts._hide();
                        if (callback)callback(true);
                    });
                    $("#popup_cancel").click(function () {
                        $.alerts._hide();
                        if (callback)callback(false);
                    });
                    $("#popup_ok").focus();
                    $("#popup_ok, #popup_cancel").keypress(function (e) {
                        if (e.keyCode == 13)$("#popup_ok").trigger('click');
                        if (e.keyCode == 27)$("#popup_cancel").trigger('click');
                    });
                    break;
                case'prompt':
                    $("#popup_message").append('<input type="text" id="popup_prompt" />').after('<div id="popup_panel"><button type="button" class="btn btn-white btn-success btn-sm btn-round" id="popup_ok"><i class="ace-icon fa fa-check"></i>' + $.alerts.okButton + '</button> <button type="button" class="btn btn-white btn-gary btn-sm btn-round" id="popup_cancel"><i class="ace-icon fa fa-close"></i>' + $.alerts.cancelButton + '</button></div>');
                    //$("#popup_prompt").width($("#popup_message").width());
                    $("#popup_ok").click(function () {
                        var val = $("#popup_prompt").val();
                        $.alerts._hide();
                        if (callback)callback(val);
                    });
                    $("#popup_cancel").click(function () {
                        $.alerts._hide();
                        if (callback)callback(null);
                    });
                    $("#popup_prompt, #popup_ok, #popup_cancel").keypress(function (e) {
                        if (e.keyCode == 13)$("#popup_ok").trigger('click');
                        if (e.keyCode == 27)$("#popup_cancel").trigger('click');
                    });
                    if (value)$("#popup_prompt").val(value);
                    $("#popup_prompt").focus().select();
                    break;
            }
            if ($.alerts.draggable) {
                try {
                    $("#popup_container").draggable({handle: $("#popup_title")});
                    $("#popup_title").css({cursor: 'move'});
                } catch (e) {
                }
            }
        },
        _hide: function () {
            $("#popup_container").remove();
            $.alerts._overlay('hide');
            $.alerts._maintainPosition(false);
        },
        _overlay: function (status) {
            switch (status) {
                case'show':
                    $.alerts._overlay('hide');
                    $("BODY").append('<div id="popup_overlay"></div>');
                    $("#popup_overlay").css({
                        position: 'absolute',
                        zIndex: 99998,
                        top: '0px',
                        left: '0px',
                        width: '100%',
                        height: $(document).height(),
                        background: $.alerts.overlayColor,
                        opacity: $.alerts.overlayOpacity
                    });
                    break;
                case'hide':
                    $("#popup_overlay").remove();
                    break;
            }
        },
        _reposition: function () {
            var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
            var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
            if (top < 0)top = 0;
            if (left < 0)left = 0;
            // if ($.browser.msie && parseInt($.browser.version) <= 6)top = top + $(window).scrollTop();
            $("#popup_container").css({top: top + 'px', left: left + 'px'});
            $("#popup_overlay").height($(document).height());
        },
        _maintainPosition: function (status) {
            if ($.alerts.repositionOnResize) {
                switch (status) {
                    case true:
                        $(window).bind('resize', $.alerts._reposition);
                        break;
                    case false:
                        $(window).unbind('resize', $.alerts._reposition);
                        break;
                }
            }
        }
    };

    jQueryAlert = function (message, callback) {
        $.alerts.alert(message, "提示", callback);
    };
    jQueryConfirm = function (message, callback) {
        $.alerts.confirm(message, "确认", callback);
    };
    jQueryPrompt = function (message, value, callback) {
        $.alerts.prompt(message, value, "输入", callback);
    };


})(jQuery);

