const  collegeHome = ()=>import('./home/collegeHome')
const  collegeCourse = ()=>import('./course/collegeCourse')
const  newCurriculum = ()=>import('./course/newCurriculum')

const  collegeWork = ()=>import('./work/collegeWork')
//const  collegePlan = ()=>import('./plan/collegePlan')
const  collegeNotice = ()=>import('./notice/collegeNotice')
//const  collegeExamine = ()=>import('./examine/collegeExamine')
const  collegeClassroomExamine = ()=>import('./examine/classroomExamine')
//const  collegeRoom = ()=>import('./room/collegeRoom')
const collegeFree = ()=>import('./free/collegeFree')

const collegeSemester = ()=>import('./semester/collegeSemester')



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
        path:'/newCurriculum',
        name:'newCurriculum',
        component:newCurriculum
    },
    // {
    //     path:'/collegeExamine',
    //     name:'collegeExamine',
    //     component:collegeExamine
    // },
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
    // {
    //     path:'/collegePlan',
    //     name:'collegePlan',
    //     component:collegePlan
    // },
    {
        path:'/collegeNotice',
        name:'通知管理',
        component:collegeNotice
     },
    // {
    //     path:'/collegeRoom',
    //     name:'collegeRoom',
    //     component:collegeRoom
    // },
    {
        path:'/collegeFree',
        name:'档期管理',
        component:collegeFree
    },
    {
        path:'/collegeSemester',
        name:'学期管理',
        component:collegeSemester
    }
]

export default routes
