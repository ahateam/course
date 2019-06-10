const laboratoryHome  = ()=>import ("./home/laboratoryHome") //首页
const laboratoryPlan  = ()=>import ("./plan/laboratoryPlan") //排课
const laboratoryRoom  = ()=>import ("./room/laboratoryRoom") //教室管理
const laboratoryExamine  = ()=>import ("./examine/laboratoryExamine") //审核管理



let routes=[
    {
        path:"/laboratoryHome",
        name:"laboratoryHome",
        components:laboratoryHome
    },
    {
        path:"/laboratoryPlan",
        name:"排课",
        components:laboratoryPlan
    },
    {
        path:"/laboratoryRoom",
        name:"教室管理",
        components:laboratoryRoom
    },
    {
        path:"/laboratoryExamine",
        name:"审核管理",
        components:laboratoryExamine
    },
]

export default routes
