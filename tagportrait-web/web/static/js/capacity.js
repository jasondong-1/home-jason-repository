/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/10/28 17:09
 */
var treeClick=0;
function onClick(event, treeId, treeNode) {
        var name = treeNode.name;
		 var id=treeNode.id;
    name=name.substr(0,name.lastIndexOf("("));
        if(!treeNode.isParent){
            if(treeClick==0) {
                $("#txt").append(name);
                $("#txtId").append(id);
                if (!$("#txt").length > 0) {
                    $("#main").append(" <div class='alert alert-warning alert-dismissible' role='alert'>" +
                        " <button  type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                        " <span aria-hidden='true' id='close'>&times;</span>" +
                        " </button> <strong id='txt'></strong> <strong id='txtId' style='display: none'> </strong></div>");
                    $("#txt").append(name);
                    $("#txtId").append(id);
                    $("#main").on('click' ,'button',function () {
                        treeClick=0;
                    });
                }
                treeClick=1;
            }else{ alert("请先选择查询关系"); }
        }else{
            return true;
        }
}
function onChange() {
    if(treeClick==1){
        var val=$("#form-field-select-3").val();
        $("#txt").append(val);
		 $("#txtId").append(val);
        treeClick=0;
    }else{
        alert("请先选择标签！");
    }
}
function searchCallback(r) {
    $("#result").empty();
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    for (var i=0;i<data.pageData.length;i++) {
        $("#result").append("<p style='margin: 5px'>" + data.pageData[i] + "</p>");
    }
    $("#result").append("<p style='margin: 5px'>总共" + data.count + "条</p>");
}
