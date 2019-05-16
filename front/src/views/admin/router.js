const adminHome  = ()=>import('./home/adminHome')
const adminPlan  = ()=>import('./plan/adminPlan')
const adminCollege  = ()=>import('./college/adminCollege')
const adminTeacher  = ()=>import('./teacher/adminTeacher')
const adminRoom  = ()=>import('./room/adminRoom')
const adminExamine  = ()=>import('./examine/adminExamine')
const adminNotice  = ()=>import('./notice/adminNotice')
const adminWork  = ()=>import('./work/adminWork')
const  adminaddCurriculumExamine = ()=>import('./examine/addCurriculumExamine')

let routes=[
    {
        path: '/adminHome',
        name: 'adminHome',
        component: adminHome
    },
    {
        path: '/adminPlan',
        name: 'adminPlan',
        component: adminPlan
    },
    {
        path: '/adminCollege',
        name: 'adminCollege',
        component: adminCollege
    },
    {
        path: '/adminTeacher',
        name: 'adminTeacher',
        component: adminTeacher
    },
    {
        path: '/adminRoom',
        name: 'adminRoom',
        component: adminRoom
    },
    {
        path: '/adminExamine',
        name: 'adminExamine',
        component: adminExamine
    },
    {
        path: '/adminNotice',
        name: 'adminNotice',
        component: adminNotice
    },
    {
        path: '/adminWork',
        name: 'adminWork',
        component: adminWork
    },
    {
        path:'/addCurriculumExamine',
        name:'addCurriculumExamine',
        component:adminaddCurriculumExamine
    },

]

export default routes