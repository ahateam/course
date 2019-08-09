

const  collegeHome = ()=>import('./home/collegeHome')
const  collegeCourse = ()=>import('./course/collegeCourse')
const  newCurriculum = ()=>import('./course/newCurriculum')

const  collegeWork = ()=>import('./work/collegeWork')
//const  collegePlan = ()=>import('./plan/collegePlan')
const  collegeNotice = ()=>import('./notice/collegeNotice')
//const  collegeExamine = ()=>import('./examine/collegeExamine')
const  collegeClassroomExamine = ()=>import('./examine/classroomExamine')
const  collegeRoom = ()=>import('./room/collegeRoom')
const collegeFree = ()=>import('./free/collegeFree')

const collegeClass = ()=>import('./class/collegeClass')

const collegeOpen = ()=>import('./open/collegeOpen')
const collegeTeacher = ()=>import('./teacher/collegeTeacher')



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
                path:'/collegeTeacher',
                name:'collegeTeacher',
                component:collegeTeacher
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
            {
                path:'/collegeRoom',
                name:'实验室管理',
                component:collegeRoom
            },
            {
                path:'/collegeNotice',
                name:'通知管理',
                component:collegeNotice
            },
            {
                path:'/collegeOpen',
                name:'开设课程',
                component:collegeOpen
            },
            {
                path:'/collegeFree',
                name:'档期管理',
                component:collegeFree
            },
            {
                path:'/collegeClass',
                name:'学期管理',
                component:collegeClass
            }
        ]
let ROUTES=routes
for(let i=0;i<routes.length;i++){
    ROUTES[i].meta={}
    ROUTES[i].meta.require_college=true

}
// ROUTES.forEach((val,index,arr)=>{
//     console.log(arr[index])
//     arr[index].meta={
//        require_college:true
//     }
// })

export default ROUTES
