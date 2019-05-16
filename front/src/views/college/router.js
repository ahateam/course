const  collegeHome = ()=>import('./home/collegeHome')
const  collegeCourse = ()=>import('./course/collegeCourse')
const  collegeWork = ()=>import('./work/collegeWork')
const  collegePlan = ()=>import('./plan/collegePlan')
const  collegeNotice = ()=>import('./notice/collegeNotice')
const  collegeExamine = ()=>import('./examine/collegeExamine')
const  collegeClassroomExamine = ()=>import('./examine/classroomExamine')
const  collegeRoom = ()=>import('./room/collegeRoom')

let routes=[
    {
        path: '/collegeHome',
        name: 'collegeHome',
        component: collegeHome
    },
    {
        path:'/collegeCourse',
        name:'collegeCourse',
        component:collegeCourse
    },
    {
        path:'/collegeExamine',
        name:'collegeExamine',
        component:collegeExamine
    },
    {
        path:'/collegeClassroomExamine',
        name:'collegeClassroomExamine',
        component:collegeClassroomExamine
    },
    {
        path:'/collegeWork',
        name:'collegeWork',
        component:collegeWork
    },
    {
        path:'/collegePlan',
        name:'collegePlan',
        component:collegePlan
    },
    {
        path:'/collegeNotice',
        name:'collegeNotice',
        component:collegeNotice
    },
    {
        path:'/collegeRoom',
        name:'collegeRoom',
        component:collegeRoom
    },
]

export default routes