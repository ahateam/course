

const  laborHome = ()=>import('./home/laborHome')
const  laborWork = ()=>import('./work/laborWork')
const  laborPlan = ()=>import('./plan/collegePlan')
const  laborExamine = ()=>import('./examine/laborExamine')
const  laborRoom = ()=>import('./room/laborRoom')
const  laborClassroomRecord = ()=>import('./examine/classroom/laborClassroomRecord')
const  laborClassroomApply = ()=>import('./examine/classroom/laborClassroomApply')
const  laborChangeClassRecord = ()=>import('./examine/changeClass/laborChangeClassRecord')
const  laborChangeClassApply = ()=>import('./examine/changeClass/laborChangeClassApply')
let routes=[

            {
                path: '/laborHome',
                name: 'laborHome',
                component: laborHome
            },
            {
                path:'/laborExamine',
                name:'laborExamine',
                component:laborExamine
            },
            {
                path:'/laborWork',
                name:'laborWork',
                component:laborWork
            },
            {
                path:'/laborPlan',
                name:'排课',
                component:laborPlan
            },
            {
                path:'/laborRoom',
                name:'laborRoom',
                component:laborRoom
            },
            {
                path:'/laborClassroomApply',
                name:'教室使用申请',
                component:laborClassroomApply
            },
            {
                path:'/laborClassroomRecord',
                name:'教室申请记录',
                component:laborClassroomRecord
            },
            {
                path:'/laborChangeClassApply',
                name:'教师变课申请',
                component:laborChangeClassApply
            },
            {
                path:'/laborChangeClassRecord',
                name:'变课申请记录',
                component:laborChangeClassRecord
            },
        ]
let ROUTES=routes
for(let i=0;i<routes.length;i++){
    ROUTES[i].meta={}
    ROUTES[i].meta.require_labor=true

}
// ROUTES.forEach((val,index,arr)=>{
//     console.log(arr[index])
//     arr[index].meta={
//        require_labor:true
//     }
// })

export default ROUTES
