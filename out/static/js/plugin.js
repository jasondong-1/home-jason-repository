String.prototype.format = function () {
    var s = this,
        i = arguments.length;

    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};

/**********************************************************************************************************************
 *
 *                                            o(╯□╰)o华丽的分割线o(╯□╰)o X插件
 *
 *********************************************************************************************************************/
/**
 * 定时器
 * */
jQuery.fn.extend({
    everyTime: function (interval, label, fn, times) {
        return this.each(function () {
            jQuery.timer.add(this, interval, label, fn, times);
        });
    },
    oneTime: function (interval, label, fn) {
        return this.each(function () {
            jQuery.timer.add(this, interval, label, fn, 1);
        });
    },
    stopTime: function (label, fn) {
        return this.each(function () {
            jQuery.timer.remove(this, label, fn);
        });
    }
});

jQuery.extend({
    timer: {
        global: [],
        guid: 1,
        dataKey: "jQuery.timer",
        regex: /^([0-9]+(?:\.[0-9]*)?)\s*(.*s)?$/,
        powers: {
            // Yeah this is major overkill...
            'ms': 1,
            'cs': 10,
            'ds': 100,
            's': 1000,
            'das': 10000,
            'hs': 100000,
            'ks': 1000000
        },
        timeParse: function (value) {
            if (value == undefined || value == null)
                return null;
            var result = this.regex.exec(jQuery.trim(value.toString()));
            if (result[2]) {
                var num = parseFloat(result[1]);
                var mult = this.powers[result[2]] || 1;
                return num * mult;
            } else {
                return value;
            }
        },
        add: function (element, interval, label, fn, times) {
            var counter = 0;

            if (jQuery.isFunction(label)) {
                if (!times)
                    times = fn;
                fn = label;
                label = interval;
            }

            interval = jQuery.timer.timeParse(interval);

            if (typeof interval != 'number' || isNaN(interval) || interval < 0)
                return;

            if (typeof times != 'number' || isNaN(times) || times < 0)
                times = 0;

            times = times || 0;

            var timers = jQuery.data(element, this.dataKey) || jQuery.data(element, this.dataKey, {});

            if (!timers[label])
                timers[label] = {};

            fn.timerID = fn.timerID || this.guid++;

            var handler = function () {
                if ((++counter > times && times !== 0) || fn.call(element, counter) === false)
                    jQuery.timer.remove(element, label, fn);
            };

            handler.timerID = fn.timerID;

            if (!timers[label][fn.timerID])
                timers[label][fn.timerID] = window.setInterval(handler, interval);

            this.global.push(element);

        },
        remove: function (element, label, fn) {
            var timers = jQuery.data(element, this.dataKey), ret;

            if (timers) {

                if (!label) {
                    for (label in timers)
                        this.remove(element, label, fn);
                } else if (timers[label]) {
                    if (fn) {
                        if (fn.timerID) {
                            window.clearInterval(timers[label][fn.timerID]);
                            delete timers[label][fn.timerID];
                        }
                    } else {
                        for (var fn in timers[label]) {
                            window.clearInterval(timers[label][fn]);
                            delete timers[label][fn];
                        }
                    }

                    for (ret in timers[label]) break;
                    if (!ret) {
                        ret = null;
                        delete timers[label];
                    }
                }

                for (ret in timers) break;
                if (!ret)
                    jQuery.removeData(element, this.dataKey);
            }
        }
    }
});

jQuery(window).bind("unload", function () {
    jQuery.each(jQuery.timer.global, function (index, item) {
        jQuery.timer.remove(item);
    });
});

var X = {};
X.G = {};
X.hook = function () {
    var pre_init_str = 'x_init_hook_';
    for (var h in window) {
        if (0 != h.indexOf(pre_init_str))
            continue;
        var func = window[h];
        if (typeof func == 'function') {
            try {
                func();
            } catch (e) {
            }
        }
    }
};

X.ajaxForm = function (ajaxFrm) {
    var param = ajaxFrm.serialize();
    jQuery.ajax({
        url: ajaxFrm.attr("action"),
        cache: false,
        data: param,
        type: "POST",
        dataType: "json",
        success: X.json
    });
    return false;
};

X.get = function (u, data, callback) {
    return X.ajax(u, 'GET', data, callback);
};
X.post = function (u, data, callback) {
    return X.ajax(u, 'POST', data, callback);
};
X.ajax = function (u, method, data, callback) {
    jQuery.ajax({
        url: u,
        cache: false,
        type: method,
        dataType: "json",
        data: data,
        success: callback || X.json
    });
    return false;
};

X.json = function (r, func) {
    var type = r['type'];
    var data = r['data'];
    if (X.G.task_dialog && type != "task_dialog") {
        window.clearInterval(X.G.task_dialog);
        X.G.task_dialog = undefined;
    }
    if (type == "alert") {
        jQueryAlert(data.message);
        //X.showDialog(data.title, data.message, data.type);
    } else if (type == 'refresh') {
        if (data) {
            jQueryAlert(data, function () {
                window.location.reload();
            });
        } else {
            window.location.reload();
        }

    } else if (type == 'updater') {
        var id = data['id'];
        var inner = data['html'];
        jQuery('#' + id).html(inner);
    } else if (type == 'dialog') {
        var width = data['width'] || 0;
        var height = data['height'] || 0;
        var html = data['html'] || "";
        X.boxShow(html, height, width);
    } else if (type == 'redirect') {
        window.location.href = data;
    } else if (type == 'data') {
        if (func) {
            func(data);
        }
    } else if (!X.G.task_dialog && type == "task_dialog") {
        X.showDialog("正在操作", "正在进行操作请稍后...", 'wait');
        var task_id = data["id"];
        var times = data["times"] || 2000;
        X.G.task_dialog = window.setInterval(function () {
            X.post(CTX_ROOT + "/admin/task/status.do", {"id": task_id});
        }, times);
    }
};

X.boxShow = function (innerHTML, height, width) {
    //移除上个对话框的遮罩层 bootstrap bug
    $(".modal-backdrop").remove();


    var dialog = jQuery('#append_parent');
    dialog.html(innerHTML);
    //弹出框 margin-top 不用很大
   // $(".modal .modal-dialog").css('margin-top', '100px');
    if (width) {
        $(".modal").css("width", width);
        $(".modal").css("margin-left", function () {
            return -($(this).width() / 2);
        });
    }
    if (height) {
        $(".modal-body").css("max-height", height);

    }
    $(".modal").modal({show: true});
    X.hook();
    return true;
};

X.showDialog = function (title, content, type) {
    var dialog_header = '<button type="button" class="close" data-dismiss="modal">&times;</button>';
    var dialog_html = '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">{0}<h4 class="modal-title blue bigger">{1}</h4></div>' +
        '<div class="modal-body" style="font-size:14px;">{2}</div>{3}</div></div></div>';
    if (type.trim() == "wait") {
        dialog_html = dialog_html.format("", title,
            '<i class="icon-spinner icon-spin green bigger-125"></i>' + content, "");
    } else if (type.trim() == "error") {
        dialog_html = dialog_html.format(dialog_header, title,
            '<i class="icon-bolt red bigger-125"></i>&nbsp;&nbsp;' + content, "");
    } else {
        var footer = '<div class="modal-footer">' +
            '<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button></div>';
        dialog_html = dialog_html.format(dialog_header, title, '<i class="icon-ok green bigger-125"></i>' + content, footer);
    }
    X.boxShow(dialog_html);
};

window.x_init_hook_click = function () {
    jQuery('a.delete').unbind('click').click(function () {
        var u = jQuery(this).attr('href');
        if (confirm('该操作无法恢复，您确定要这么做吗？')) {
            X.get(u);
            return false;
        } else {
            return false;
        }
    });

    jQuery('.ajax').unbind('click').click(function () {

        if (jQuery(this).attr('no') == 'yes')
            return false;
        var ask = jQuery(this).attr('ask');
        if (ask && !confirm(ask)) {
            return false;
        }
        X.get(jQuery(this).attr('href'));
        return false;
    });

    $('table th input:checkbox').on('click', function () {
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });
    });

    $('table tr > td:first-child input:checkbox').on('click', function () {
        var that = this;
        var parentCheckbox = $(this).closest('table').find('th:first-child input:checkbox');
        parentCheckbox.prop('checked', that.checked);
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                if (this.checked != that.checked) {
                    parentCheckbox.prop('checked', false);
                    return false;
                }
            });
    });
};

window.x_init_hook_util = function () {
    //jquery validation
    $(".validateForm").validate();
};

window.x_init_hook_button = function () {
    jQuery('a.submitbtn').unbind('click').click(function () {
        var form = jQuery(this).parents('form:first');
        if (form.hasClass("ajaxForm")) {
            return X.ajaxForm(form);
        }
        var submit = jQuery("input[type=submit]", form);
        //找到提交按钮则触发提交按钮的click否则则直接form.submit
        if (submit.length != 0) {
            submit.trigger("click");
        } else {
            form.submit();
        }
        return false;
    });

    jQuery(".history").unbind("click").click(function () {
        history.back();
    });

    /*
     * 提交按钮在form外部使用
     * */
    jQuery('a.submitForm').unbind('click').click(function () {
        var ask = jQuery(this).attr('ask');
        if (ask && !confirm(ask)) {
            return false;
        }
        var form = jQuery(".ajaxForm");
        return X.ajaxForm(form);
    });
};

window.x_init_hook_refresh = function () {
    jQuery(".refresh_time").each(function (index, ele) {
        var url = jQuery(ele).attr("url");
        if (url) {
            var time = jQuery(ele).attr("time") || "1s";//一秒
            jQuery(ele).everyTime(time, function () {
                var element = $(this);
                $.ajax({
                    url: url,
                    cache: false,
                    type: "GET",
                    success: function (data, textStatus) {
                        if (textStatus == "success") {
                            if (data["html"]) {
                                element.html(data["html"]);
                            } else {
                                element.html(data);
                            }
                        } else {
                            console.warn(url + "does not exist.");
                        }
                    }
                });
            });
        }
    });
};

jQuery(document).ready(X.hook);

