/*左侧导航栏菜单生成的内容*/


//学校管理员权限菜单
const  adminMenu =[
    {
        path:'/adminHome',
        title:'学校首页',
        icon:'course_shouye',
        child:[]
    },
    {
        path:'/adminTerm',
        title:'学期管理',
        icon:'course_shouye',
        child:[]
    },
    {
        path:'/adminPlan',
        title:'排课管理',
        icon:'course_908caidan_anpai',
        child:[]
    },
    {
        path:'/adminCollege',
        title:'学院管理',
        icon:'course_guanli',
        child:[]
    },
    {
        path:'/adminMajor',
        title:'专业管理',
        icon:'course_guanli',
        child:[]
    },
    {
        path:'/adminTeacher',
        title:'教师管理',
        icon:'course_xueyuanpeizhi',
        child:[]
    },
    {
        path:'/adminRoom',
        title:'教室管理',
        icon:'course_guanli1',
        child:[]
    },
    {
        path:'/adminOccupy',
        title:'档期管理',
        icon:'course_guanli1',
        child:[
            {
                path:'/adminOccupyTeacher',
                title:'教师档期',
                icon:'course_guanli1',
                child:[]
            },
            {
                path:'/adminOccupyClass',
                title:'班级档期',
                icon:'course_guanli1',
                child:[]
            },
            {
                path:'/adminOccupyRoom',
                title:'教室档期',
                icon:'course_guanli1',
                child:[]
            },
        ]
    },
    {
        path:'/adminExamine',
        title:'审核管理',
        icon:'course_xiugai1',
        child:[]
    },
    {
        path:'/adminNotice',
        title:'通知管理',
        icon:'course_tongzhi',
        child:[]
    },
    {
        path:'/adminWork',
        title:'工作总量',
        icon:'course_tongji',
        child:[]
    },

]
//学院权限菜单
const collegeMenu = [
    {
        path:'/collegeHome',
        title:'学院首页',
        icon:'course_shouye',
        child:[]
    },
    {
        path:'/collegeOpen',
        title:'开设课程',
        icon:'course__zhuanzhengongzuoliang',
        child:[]
    },
    {
        path:'/collegeCourse',
        title:'课程大纲',
        icon:'course__zhuanzhengongzuoliang',
        child:[]
    },
    {
        path:'/collegeFree',
        title:'档期管理',
        icon:'course__zhuanzhengongzuoliang',
        child:[]
    },
    {
        path:'/collegeWork',
        title:'工作量管理',
        icon:'course_guanli1',
        child:[]
    },
    // {
    //     path:'/collegePlan',
    //     title:'排课管理',
    //     icon:'course_908caidan_anpai',
    //     child:[]
    // },
    // {
    //     path:'/collegeExamine',
    //     title:'审核管理',
    //     icon:'course_xiugai1',
    //     child:[]
    // },
     {
        path:'/collegeRoom',
        title:'教室管理',
        icon:'course_xiugai1',
        child:[]
    },
    {
        path:'/collegeTeacher',
        title:'教师管理',
        icon:'course_xiugai1',
        child:[]
    },
    {
        path:'/collegeClass',
        title:'班级管理',
        icon:'course_tongzhi',
        child:[]
    },

    {
        path:'/collegeNotice',
        title:'通知管理',
        icon:'course_tongzhi',
        child:[]
    },

]

//教师权限菜单
const  teacherMenu=[
    {
        path:'/teacherHome',
        title:'教师首页',
        icon:'course_shouye',
        child:[]
    },
    {
        path:'/teacherPlan',
        title:'我的排课',
        icon:'course_908caidan_anpai',
        child:[]
    },
    {
        path:'/teacherWork',
        title:'工作总量',
        icon:'course__zhuanzhengongzuoliang',
        child:[]
    },
    {
        path:'/teacherNotice',
        title:'我的通知',
        icon:'course_tongzhi',
        child:[]
    },
    {
        path:'/teacherChangeWork',
        title:'变课申请',
        icon:'course_tongzhi',
        child:[]
    },
    {
        path:'/teacherClassroom',
        title:'实验室申请',
        icon:'course_tongzhi',
        child:[]
    },

]


//实验室
const laborMenu=[
    {
        path:'/laborHome',
        title:'实验室首页',
        icon:'course_shouye',
        child:[]
    },
    {
        path:'/laborRoom',
        title:'教室管理',
        icon:'course_xiugai1',
        child:[]
    },

    {
        path:'/laborPlan',
        title:'排课管理',
        icon:'course_908caidan_anpai',
        child:[]
    },

    {
        path:'/laborExamine',
        title:'审核管理',
        icon:'course_xiugai1',
        child:[
            {
                path:'/laborClassroomApply',
                title:'教室使用申请',
            },
            // {
            //     path:'/laborClassroomRecord',
            //     title:'教室使用记录',
            // },
            {
                path:'/laborChangeClassApply',
                title:'教师变课申请',
            },
            // {
            //     path:'/laborChangeClassRecord',
            //     title:'变课申请记录',
            // },
        ]
    },


]



export default {
    adminMenu,
    laborMenu,
    collegeMenu,
    teacherMenu
}
