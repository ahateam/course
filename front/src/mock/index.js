
var Mock = require("mockjs");




const lecturerInfromation =function(){
    let Infromation= [
        {
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:36,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },
        {
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },{
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },{
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },{
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },{
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },{
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        },{
            curriculumName: '王小虎',    //课程名称
            curriculumNum: '1523059',   //课程编号
            class:'网工171',           //班级
            curriculumNature:'独立',  //课程性质
            executiveHours:16,       //执行学时
            nowHours:12,            //已上学时
            repetitions:2,         //重复次数
        }];
    return {
        data:Infromation
    }
}
Mock.mock("/lecturerInfromation", /post|get/i, lecturerInfromation);