

const adminHome  = ()=>import('./home/adminHome')
const adminPlan  = ()=>import('./plan/adminPlan')
const adminCollege  = ()=>import('./college/adminCollege')
const adminMajor  = ()=>import('./major/adminMajor')

const adminTeacher  = ()=>import('./teacher/adminTeacher')
const adminRoom  = ()=>import('./room/adminRoom')
const adminExamine  = ()=>import('./examine/adminExamine')
const adminNotice  = ()=>import('./notice/adminNotice')
const adminWork  = ()=>import('./work/adminWork')
const  adminaddCurriculumExamine = ()=>import('./examine/addCurriculumExamine')
const adminTerm =()=>import('./term/term')

let routes=[
            {
                path: '/adminHome',
                name: 'adminHome',
                component: adminHome,
                meta:{
                    require_admin:true
                },
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
                path: '/adminMajor',
                name: 'adminMajor',
                component: adminMajor
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
            {
                path:'/adminTerm',
                name:'adminTerm',
                component:adminTerm
            }
        ]

        let ROUTES=routes
        for(let i=0;i<routes.length;i++){
            ROUTES[i].meta={}
            ROUTES[i].meta.require_admin=true

        }


export default ROUTES
