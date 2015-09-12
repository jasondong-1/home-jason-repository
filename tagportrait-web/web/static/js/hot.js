/**
 * Created by yaotianli on 2015/9/11.
 */

//select

    jQuery(function ($) {
        $(".chosen-select").chosen();
//        $('#chosen-multiple-style').on('click', function (e) {
//            var target = $(e.target).find('input[type=radio]');
//            var which = parseInt(target.val());
//            if (which == 2) $('#form-field-select-4').addClass('tag-input-style');
//            else $('#form-field-select-4').removeClass('tag-input-style');
//        });
    });
function onFirstChange() {
    var url='index_second.do'
    var data={id: $("#form-field-select-1").val()};
    X.post(url, data, callback_first)
}

function callback_first(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if(success){
        //alert(JSON.stringify(r));
        var  first= $("#form-field-select-1").val();
        if("无"==first){
            $("#form-field-select-2").empty();
            $("#form-field-select-2").append("<option value=\"无\" selected=\"selected\">无内容</option>");
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"无\" selected=\"selected\">无内容</option>");
        }else{
            $("#form-field-select-2").empty();
            $("#form-field-select-2").append("<option value=\"无\" selected=\"selected\">请选择</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-2").append(option);
            }
        }
        $("#form-field-select-2").trigger("chosen:updated");
        $("#form-field-select-3").trigger("chosen:updated");
    }
}

function onSecondChange() {//alert($("#form-field-select-1").val())
    var url='index_third.do'
    var data={id: $("#form-field-select-2").val()};
    X.post(url, data, callback_second)
}

function callback_second(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if(success){
        var  second=$("#form-field-select-2").val();
        alert(second);
        if("无"==second){
            //alert(dsds+second);
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"无\" selected=\"selected\">无内容</option>");
        }else{
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"无\" selected=\"selected\">请选择</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-3").append(option);
            }
        }
        $("#form-field-select-3").trigger("chosen:updated");
    }
}


//table
jQuery(function ($) {
    var oTable1 = $('#sample-table-2').dataTable({
        "aoColumns": [
            {"bSortable": false},
            null, null, null, null, null,
            {"bSortable": false}
        ]
    });


    $('table th input:checkbox').on('click', function () {
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });

    });


    $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
    function tooltip_placement(context, source) {
        var $source = $(source);
        var $parent = $source.closest('table')
        var off1 = $parent.offset();
        var w1 = $parent.width();

        var off2 = $source.offset();
        var w2 = $source.width();

        if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
        return 'left';
    }
})