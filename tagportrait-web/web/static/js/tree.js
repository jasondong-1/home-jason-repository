$(function () {
    var zTree;
    var setting = {
        data: {
            simpleData: {
                /**
                 * 确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据
                 * 是否采用简单数据模式 (Array)
                 */
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        async: {
            /**
             * 设置 zTree 是否开启异步加载模式
             * 默认值：false
             * true 表示 开启 异步加载模式
             * false 表示 关闭 异步加载模式
             */
            enable: true,
            /**
             * Ajax 获取数据的 URL 地址。[setting.async.enable = true 时生效]
             */
            url: "tag_tree.do",
            /**
             * 默认post
             */
            type: "get",
            /**
             * 异步加载时需要自动提交父节点属性的参数。
             */
            autoParam: ["id"]
        },
        callback: {
            onClick: onClick
        }
    };
    //当你点击父节点时,会异步访问Controller,把id传过去
    var treeNodes = [];
    zTree = $.fn.zTree.init($("#tagTree"), setting, treeNodes);
});
function onClick(event, treeId, treeNode) {

}
