const teacherHome  = ()=>import('./home/teacherHome')
const teacherPlan  = ()=>import('./plan/teacherPlan')
const teacherWork  = ()=>import('./work/teacherWork')
const teacherNotice =()=>import('./notice/teacherNotice')
const teacherChangeWork =()=>import('./changeWork/teacherChangeWork')
const teacherClassroom =()=>import('./classroom/teacherClassroom')
const teacherApplication =()=>import('./classroom/teacherApplication')

let routes=[
    {
        path: '/teacherHome',
        name: 'teacherHome',
        component: teacherHome
    },
    {
        path: '/teacherClassroom',
        name: 'teacherClassroom',
        component: teacherClassroom
    },
    {
        path: '/teacherPlan',
        name: 'teacherPlan',
        component: teacherPlan
    },
    {
        path:'/teacherWork',
        name:'teacherWork',
        component:teacherWork
    },
    {
        path:'/teacherNotice',
        name:'teacherNotice',
        component:teacherNotice
    },
    {
        path:'/teacherChangeWork',
        name:'teacherChangeWork',
        component:teacherChangeWork
    },
    {
        path:'/teacherApplication',
        name:'teacherApplication',
        component:teacherApplication
    },

]

export default routes