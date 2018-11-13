$(function () {
    initTable();
    //实现预览功能
    $("#photo").change(function preview() {
        //获取文件框的第一个文件,因为文件有可能上传多个文件,这里是一个文件
        var file = document.getElementById("photo").files[0];
        //可以进行一下文件类型的判断
        var fileType = file.type.split("/")[0];
        if (fileType !== "image") {
            alert("请上传图片");
            return;
        }
        //图片大小的限制
        var fileSize = Math.round(file.size / 1024 / 1024);
        if (fileSize >= 3) {
            alert("请上传小于少于3M的图片");
            return;
        }
        //获取img对象
        var img = document.getElementById("image");
        //建一条文件流来读取图片
        var reader = new FileReader();
        //根据url将文件添加的流中
        reader.readAsDataURL(file);
        //实现onload接口
        reader.onload = function (e) {
            //获取文件在流中url
            url = reader.result;
            //将url赋值给img的src属性
            console.log(url);
            img.src = url;
        };
    });
});

function doQuery() {
    $('#table').bootstrapTable('refresh');    //刷新表格
}

//初始化Table
function initTable() {
    $('#table').bootstrapTable({
        url: '/user/getUserListBySearchDTO',                  //请求后台的URL（*）
        method: 'post',                     //请求方式（*）
        // toolbar: '#toolbar',             //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortName: "id",                     //排序列
        sortOrder: "asc",                   //排序方式
        contentType: 'application/x-www-form-urlencoded',
        queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求
        queryParams: function (params) {
            return {
                searchContext: $("#search_context").val(),
                offset: params.offset,  //页码
                limit: params.limit,   //页面大小
                order: params.order, //排序
                sort: params.sort //排序
            };
        },           //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        // showColumns: true,                  //是否显示所有的列
        // showRefresh: true,                  //是否显示刷新按钮
        // minimumCountColumns: 2,             //最少允许的列数
        // clickToSelect: true,                //是否启用点击选中行
        height: $(window).height() - 40,     //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        responseHandler: responseHandler,
        columns: [{
            checkbox: false
        }, {
            field: 'id',
            title: '序号',
            align: 'center'
        }, {
            field: 'name',
            title: '姓名',
            align: 'center'
        }, {
            field: 'email',
            title: '邮箱',
            align: 'center'
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function (value, row, index) {
                if (row['status'] === 1) {
                    return '正常';
                }
                if (row['status'] === 0) {
                    return '禁用';
                }
                return value;
            }
        }, {
            field: 'creator',
            title: '创建人',
            align: 'center'
        }, {
            field: 'created_time',
            title: '创建时间',
            align: 'center',
            formatter: function (value, row, index) {
                return changeDateFormat(value);
            }
        }, {
            field: 'modifier',
            title: '修改人',
            align: 'center'
        }, {
            field: 'modified_time',
            title: '修改时间',
            align: 'center',
            formatter: function (value, row, index) {
                return changeDateFormat(value);
            }
        }, {
            field: 'operation',
            title: '操作',
            align: 'center',
            events: operateEvents,//给按钮注册事件
            formatter: operateFormatter//表格中增加按钮
        }]
    });
};

//刷新页面后返回数据
function responseHandler(res) {
    if (res) {
        return {
            "rows": res["result"],
            "total": res["totalCount"]
        };
    } else {
        return {
            "rows": [],
            "total": 0
        };
    }
}

//转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval) {
    var dateVal = cellval + "";
    if (cellval !== null) {
        var date = new Date(parseInt(dateVal, 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }
}

// 修改按钮、删除按钮
function operateFormatter(value, row, index) {
    var id = row.id;
    return [
        '<button id="btn_edit" type="button" class="btn btn-info" data-toggle="modal" data-target="#editUser" onclick="editUser(' + id + ')">修改</button>',
        '<button id="btn_delete" type="button" class="btn btn-warning" style="margin-left: 10px;" onclick="deleteUser(' + id + ')">删除</button>'
    ].join('');
}

window.operateEvents = {
    // 点击修改按钮执行的方法
    // 'click #btn_edit': function (e, value, row, index) {
    //     $.ajax({
    //         type: "GET",
    //         url: "/user/" + row['id'] + "/detail",
    //         data: {},
    //         dataType: 'JSON',
    //         success: function (data) {
    //             if (data.status !== 0) {
    //                 console.info("info", data.statusInfo);
    //                 return;
    //             }
    //             console.success("success", '获取用户信息成功！' + data);
    //             var userInfo = data.data;
    //             document.getElementById("editModalLabel").innerText = userInfo.name;
    //             document.getElementById("image").src = userInfo.photo;
    //             document.getElementById("edit_email").value = userInfo.email;
    //             $("input[name=status][value=" + userInfo.status + "]").attr("checked", true);
    //         }
    //     });
    // },
    // 点击删除按钮执行的方法
    'click #btn_delete': function (e, value, row, index) {
        $.ajax({
            type: "GET",
            url: "/user/" + row['id'] + "/delete",
            data: {},
            dataType: 'JSON',
            success: function (data) {
                if (data.result !== 0) {
                    console.info("info", data.message);
                    return;
                }
                console.success("success", '删除成功');
                $("#table").bootstrapTable('refresh');
            }
        });
        return false;
    }
};

$(window).resize(function () {
    $("#table").bootstrapTable('resetView', {
        height: $(window).height() - 100
    });
});

function submitCreateForm() {
    var name = $("#create_name").val();
    var email = $("#create_email").val();
    if (name === "" && email === "") {
        alert("请填写用户信息!")
        return false;
    }

    //提交表单
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/user/save",
        data: {
            name: name,
            email: email
        },
        success: function (result) {
            if (result.status === 0) {
                $("#createUser").modal('hide');
                $("#table").bootstrapTable('refresh');
            } else {
                console.log("保存失败，服务器内部异常！", "error");
            }
        },
        error: function () {
            console.log("操作失败，请检查网络！", "error");
        }
    });
}

function submitEditForm() {
    $.ajax({
        type: "POST",
        url: "/wadmin/ad/addAd",
        dataType: 'JSON',
        success: function (data) {
            if (data.result !== 0) {
                console.info("info", data.message);
                return;
            }
            console.success("success", '标签');
            $("#table").bootstrapTable('insertRow', {index: 0, row: data.data});
        }
    });
}

function editUser(id) {
    $.ajax({
        type: "GET",
        url: "/user/" + id + "/detail",
        data: {},
        dataType: 'JSON',
        success: function (data) {
            if (data.status !== 0) {
                console.info("info", data.statusInfo);
                return;
            }
            console.success("success", '获取用户信息成功！' + data);
            // var userInfo = data.data;
            // document.getElementById("editModalLabel").innerText = userInfo.name;
            // document.getElementById("image").src = userInfo.photo;
            // document.getElementById("edit_email").value = userInfo.email;
            // $("input[name=status][value=" + userInfo.status + "]").attr("checked", true);
        }
    });
}

// $("#table").bootstrapTable('remove', {field: 'id',values: [row['id']]});
// 表格中插入数据
// $("#table").bootstrapTable('insertRow', {index: i, row: result.data[i]});
// 更新某一行数据
// $("#table").bootstrapTable('updateRow', {index: indexT, row: rowT});