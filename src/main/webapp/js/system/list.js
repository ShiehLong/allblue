var zTreeObject;

$(document).ready(function () {
    $("#tree-container").css("height", $(document).height()-250 + "px");
    createTree();
});

function createTree() {
    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: true,
            idKey:"code",
            pIdkey:"parent_code"
        }
    };

    var zNodes = [
        {id: 1, pId: 0, name: "父节点1"},
        {id: 11, pId: 1, name: "父节点11"},
        {id: 111, pId: 11, name: "叶子节点111"},
        {id: 112, pId: 11, name: "叶子节点112"},
        {id: 113, pId: 11, name: "叶子节点113"},
        {id: 114, pId: 11, name: "叶子节点114"},
        {id: 12, pId: 1, name: "父节点12"},
        {id: 121, pId: 12, name: "叶子节点121"},
        {id: 122, pId: 12, name: "叶子节点122"},
        {id: 123, pId: 12, name: "叶子节点123"},
        {id: 124, pId: 12, name: "叶子节点124"},
        {id: 13, pId: 1, name: "父节点13", isParent: true},
        {id: 2, pId: 0, name: "父节点2"},
        {id: 21, pId: 2, name: "父节点21"},
        {id: 211, pId: 21, name: "叶子节点211"},
        {id: 212, pId: 21, name: "叶子节点212"},
        {id: 213, pId: 21, name: "叶子节点213"},
        {id: 214, pId: 21, name: "叶子节点214"},
        {id: 22, pId: 2, name: "父节点22"},
        {id: 221, pId: 22, name: "叶子节点221"},
        {id: 222, pId: 22, name: "叶子节点222"},
        {id: 223, pId: 22, name: "叶子节点223"},
        {id: 224, pId: 22, name: "叶子节点224"},
        {id: 23, pId: 2, name: "父节点23"},
        {id: 231, pId: 23, name: "叶子节点231"},
        {id: 232, pId: 23, name: "叶子节点232"},
        {id: 233, pId: 23, name: "叶子节点233"},
        {id: 234, pId: 23, name: "叶子节点234"},
        {id: 3, pId: 0, name: "父节点3", isParent: true}
    ];
    // 获取zTree的节点数据
    $.ajax({
        type: "get",
        dataType: "json",
        url: 'authority/getZtreeNodes',
        success: function (result) {
            if (result.status === 0) {
                var zNodes = result.data;
                zTreeObject = $.fn.zTree.init($("#regionZTree"), setting, zNodes);
            } else {
                console.log("权限数据加载失败，服务器内部异常！", 2);
            }
        },
        error: function () {
            console.log("操作失败，请检查网络！", 2);
        }
    });
}

var newCount = 1;

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function () {
        var zTree = $.fn.zTree.getZTreeObj("regionZTree");
        zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: "new node" + (newCount++)});
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};

/**
 * 添加节点
 * @param obj
 */
function addZTreeNode(obj) {
    var treeObj = $.fn.zTree.getZTreeObj("regionZTree");
    var parentZNode = treeObj.getSelectedNodes(); //获取父节点
    var newNode = obj;
    newNode.nodeFlg = 1;  // 可以自定义节点标识
    newNode = treeObj.addNodes(parentZNode[0], newNode, true);
}

/**
 * 修改子节点
 * @param obj
 */
function editZTreeNode(obj) {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getSelectedNodes();
    for (var i = 0; i < nodes.length; i++) {
        nodes[i].name = obj;
        zTree.updateNode(nodes[i]);
    }
}

/**
 *  删除子节点 --选中节点
 * @param obj
 */
function removeZTreeNodeBySelect() {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getSelectedNodes(); //获取选中节点
    for (var i = 0; i < nodes.length; i++) {
        zTree.removeNode(nodes[i]);
    }
}

/**
 *  删除子节点 --勾选节点
 * @param obj
 */
function removeZTreeNodeByChecked() {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getCheckedNodes(true); //获取勾选节点
    for (var i = 0; i < nodes.length; i++) {
        zTree.removeNode(nodes[i]);
    }
}

/**
 *  根据节点id 批量删除子节点
 * @param obj
 */
function removeZTreeNodebPi(obj) {
    var idnodes = obj.split(",");
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var nodes = zTree.getSelectedNodes();
    for (var i = 0; i < nodes.length; i++) {
        var nodes = zTree.getNodeByParam("id", nodes[i]);
        zTree.removeNode(nodes);
    }
}

/**
 * 选择节点
 * @param obj
 */
function selectzTreeNode(obj) {
    var zTree = $.fn.zTree.getZTreeObj("regionZTree");
    var node = zTree.getNodeByParam("id", obj);
    if (node != null) {
        zTree.selectNode(node, true);//指定选中ID的节点
    }
}