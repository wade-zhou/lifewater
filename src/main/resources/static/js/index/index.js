
$(function () {
    //调用函数，初始化表格
    var oTable = new TableInit();
    oTable.Init();
});

//无极菜单START
var isFirstMenu;
var menulist;
window.onload = function(){
   $.ajax({
       url: '/department/getAllMenus',
       type: 'post',
       dataType: 'json',
       async:false,
       success:function(data){
           menulist =  data;
           },
   });
   var showlist = $("<ul class=\"sidebar-menu\"></ul>");
    $("<li class=\"header\">动态菜单栏</li>").appendTo(showlist);
    isFirstMenu=menulist.length;
    showall(menulist, showlist);
    $("#div_menu").append(showlist);
}

function showall(menu_list, parent) {
    for (var menu in menu_list) {
        if (menu_list[menu].children.length > 0) {
            var li = $("<li></li>");
            if(isFirstMenu==0){
                li = $("<li></li>");
            }else{
                li = $("<li class=\"treeview\"></li>");
                isFirstMenu=isFirstMenu-1;
            }
            $(li).append("<a onclick=\'selectMenus("+menu_list[menu].id+")\' href=\"javascript:void(0)\"><i class=\"fa fa-share\"></i> <span>"+menu_list[menu].text+"</span><i class=\"fa fa-angle-right pull-right\"></i></a>");
            var nextParent=$("<ul class=\"treeview-menu\"></ul>");
            $(nextParent).appendTo(li);
            $(li).appendTo(parent);
            showall(menu_list[menu].children, nextParent);
        }
        else {
            $("<li><a onclick=\'selectMenus("+menu_list[menu].id+")\' href=\"javascript:void(0)\"><i class=\"fa fa-circle-o\"></i>"+menu_list[menu].text +"</a></li>").appendTo(parent);
        }
    }
}
//无极菜单END



//点击左侧菜单时查询对应的子菜单
function selectMenus(id){
    $("#table").bootstrapTable('refresh', {query: {id:id}});
}


//Table
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#table').bootstrapTable({
            url: '/department/selectByPartentId', //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            dataType:'json',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "desc",                   //排序方式
            sortName: 'edit_time',                //排序字段
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 2,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            strictSearch: true,
            clickToSelect: true,                //是否启用点击选中行
            contentType: 'application/x-www-form-urlencoded',
            height: 460,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox : true
            }, {
                field: 'name',
                title: '菜单名称'
            }, {
                field: 'code',
                title: '菜单编号'
            }, {
                field: 'editTime',
                title: '修改时间'
            }, {
                field: 'parentId',
                title: '上级菜单id'
            },  {
                title: '操作',
                align: 'center',
                formatter:function(value,row,index){
                    var e = '<button type="button" class="btn btn-primary" onclick="updateFun(\''+ row.id +'\')">修改</button>';
                    var d = '<button type="button" class="btn btn-danger" onclick="deleteFun(\''+ row.id +'\')">删除</button>';
                    return e+d;
                } }
            ]
        })
    }

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            sortOrder: params.order,     //排序方式
            sortName: params.sort,       //排序字段
            id: $("#id").val(),
        };
        return temp;
    };
    return oTableInit;
}

