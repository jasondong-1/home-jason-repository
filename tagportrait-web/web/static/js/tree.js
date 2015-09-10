function treeInit(obj, data, treeItemClick) {
    var innerHtml = "";
    $(data).each(function (index, treeNode) {
        innerHtml = innerHtml + treeConstruct(treeNode);
    });
    var tree = '<ul id="tree2" class="tree tree-unselectable" role="tree">' + innerHtml + '</ul>';
    $(obj).html(tree);

    //绑定叶子节点点击事件
    $(".tree-item").click(function () {
        treeItemClick(this);
        //阻止事件冒泡
        event.stopPropagation();
    });

    //绑定主节点点击事件，实际就是去显示主节点下面的叶子节点
    $(".tree-branch").click(function () {
        if ($(this).hasClass("tree-open")) {//处于打开状态
            $(this).removeClass("tree-open").attr("aria-expanded", "true");
            $(this).find(".tree-branch-children").addClass("hide");
        } else { //处于关闭状态
            var url = $(this).attr("url");
            $(this).addClass("tree-open").attr("aria-expanded", "true");
            $(this).children(".tree-branch-children").removeClass("hide");
        }
        //阻止事件冒泡
        event.stopPropagation();
    });

    //绑定叶子节点按钮点击事件
    $(".tree-item a").click(function () {
        alert("叶子节点按钮被点击了");
        //阻止事件冒泡
        event.stopPropagation();
    });

    $(".tree-item").mouseenter(function () {
        $(this).find("a").show();
    });
    $(".tree-item").mouseleave(function () {
        $(this).find("a").hide();
    });
};

//将json对象转化成树HTML
function treeConstruct(data) {
    //必须传入json对象
    var id = data['id'];
    var name = data['name'];
    var health = data['health'];
    var url = data['url'];
    var children = data['children'];
    var color = "";
    if (health == 'Healthy') {
        color = "green";
    } else if (health == 'Unhealthy') {
        color = "red";
    } else {
        color = "black";
    }
    if (children) {
        //还有字节点
        var treeBranch =
            '<li class="tree-branch" role="treeitem" aria-expanded="true">' +
            '<div class="tree-branch-header">' +
            '<span class="tree-branch-name">' +
            '<i class="icon-folder ace-icon fa fa-cogs ' + color + ' bigger-130"></i>' +
            '<span class="tree-label">' + name + '</span>' +
            '</span>' +
            '</div>' +
            '<ul class="tree-branch-children hide" role="group">';
        for (var i = 0; i < children.length; i++) {
            var node = children[i];
            var treeHtml = treeConstruct(node);
            treeBranch = treeBranch + treeHtml;
        }
        treeBranch = treeBranch + '</ul>' +
        '<div class="tree-loader hide" role="alert">' +
        '<div class="tree-loading">' +
        '<i class="ace-icon fa fa-refresh fa-spin blue"></i>' +
        '</div>' +
        '</div>' +
        '</li>';
        return treeBranch;
    } else {
        //字节点
        var treeItem =
            '<li class="tree-item" role="treeitem" url="' + url + '" data="' + id + '">' +
            '<span class="tree-item-name">' +
            '<span class="tree-label action-buttons">' +
            '<i class="ace-icon fa fa-cog ' + color + ' bigger-130"></i>' + name +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a title="卸载服务" style="display: none;"><i class="ace-icon glyphicon glyphicon-remove"></i></a>' +
            '<a title="启动服务" style="display: none;"><i class="ace-icon glyphicon glyphicon-play"></i></a>' +
            '<a title="停止服务" style="display: none;"><i class="ace-icon glyphicon glyphicon-pause"></i></a>' +
            '</span>' +
            '</span>' +
            '</li>'
        return treeItem;
    }
}
