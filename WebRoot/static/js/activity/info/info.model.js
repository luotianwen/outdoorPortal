// tabs置顶高度
var _event_tabs_height = $("div.event-tabs").height(),
// 评价信息是否加载
is_auto_load_evaluate = true,
// 提问信息是否加载
is_auto_load_consultation = true;
// 加载报名列表
is_auto_load_buyer = true,
// 最新评价当前页
nCurrentPage = 0,
// 最热评价当前页
hCurrentPage = 0,
// 最新评价总页数
nTotalPage = 1,
// 最热评价总页数
hTotalPage = 1,
// 加载次数
loadNum=0,
// 最新评价加载条数
nEShowCount=4,
// 最热评价加载条数
hEShowCount=2,

// 提问当前页（最有帮助）
cpCurrentPage=0,
// 提问总页数
cpTotalPage=1,
// 提问每页条数
cpShowCount=4,

//提问当前页（最新）
cnCurrentPage=0,
//提问总页数
cnTotalPage=1,
//提问每页条数
cnShowCount=4,

// 最新提问标识
_CONST_NEW="NEW",
// 最有帮助提问标识
_CONST_PRAISES="PRAISES",

// 默认显示最有帮助的评论信息
consultation_default_show=_CONST_PRAISES,

// 点赞标识（防止重复提交）
isPostPraises=false;